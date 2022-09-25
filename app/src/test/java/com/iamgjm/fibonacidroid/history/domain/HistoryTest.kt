package com.iamgjm.fibonacidroid.history.domain

import com.iamgjm.fibonacidroid.history.fibonacciitem.presentation.FibonacciItemData
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class HistoryTest {

    @Test
    fun test_error() {
        //given
        val history = spyk(History(), recordPrivateCalls = true)
        coEvery {
           history["readHistory"]()
        } returns History.Result(error = "Some error Message")

        //when
        val results = runBlocking {
            history().toList()
        }

        //then
        Assert.assertEquals(2, results.size)
        //first emit
        Assert.assertTrue(results[0].loading)
        //second emit
        Assert.assertFalse(results[1].loading)
        Assert.assertNotNull(results[1].error)
    }

    @Test
    fun test_success() {
        //given
        val history = spyk(History(), recordPrivateCalls = true)
        coEvery {
            history["readHistory"]()
        } returns History.Result(data = listOf(FibonacciItemData(listOf(0, 1, 1), "date")))

        //when
        val results = runBlocking {
            history().toList()
        }

        //then
        Assert.assertEquals(2, results.size)
        //first emit
        Assert.assertTrue(results[0].loading)
        //second emit
        Assert.assertFalse(results[1].loading)
        Assert.assertTrue(results[1].data.isNotEmpty())
    }
}