package com.iamgjm.fibonacidroid.request.domain

import androidx.core.text.isDigitsOnly

class NumberValidator {

    operator fun invoke(input: String) : Boolean {
        if (input.isEmpty()) return false

        if (!input.isDigitsOnly()) return false

        return input.toInt() > 0
    }
}