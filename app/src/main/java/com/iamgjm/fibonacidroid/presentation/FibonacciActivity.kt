package com.iamgjm.fibonacidroid.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iamgjm.fibonacidroid.R
import com.iamgjm.fibonacidroid.history.presentation.HistoryFragment
import com.iamgjm.fibonacidroid.base.noConstructorViewModel
import com.iamgjm.fibonacidroid.base.presentation.BaseFragment
import com.iamgjm.fibonacidroid.base.toFragment
import com.iamgjm.fibonacidroid.domain.Authentication
import com.iamgjm.fibonacidroid.login.presentation.LoginFragment
import com.iamgjm.fibonacidroid.registration.presentation.RegistrationFragment
import com.iamgjm.fibonacidroid.request.presentation.RequestFragment

class FibonacciActivity : AppCompatActivity() {

    private lateinit var viewModel: FibonacciSharedViewModel

    private val authentication = Authentication()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        navigate(authentication.navigate())
    }

    private fun setupViewModel() {
        viewModel = noConstructorViewModel()

        viewModel.navigationEvent.observe(this) {
            navigate(it)
        }
    }

    private fun navigate(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.Login -> {
                toFragment(LoginFragment())
            }
            is NavigationEvent.Register -> {
                toFragment(RegistrationFragment())
            }
            is NavigationEvent.History -> {
                toFragment(HistoryFragment())
            }
            is NavigationEvent.Request -> {
                toFragment(RequestFragment())
            }
            is NavigationEvent.Finish -> {
                finish()
            }
        }
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments

        if (fragments.size < 2) {
            finish()
            return
        }

        (fragments.lastOrNull() as? BaseFragment)?.goBack()
    }
}