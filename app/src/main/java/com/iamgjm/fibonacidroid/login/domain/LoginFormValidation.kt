package com.iamgjm.fibonacidroid.login.domain

import java.util.regex.Pattern

class LoginFormValidation {

    companion object {
        private val EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
    }

    /**
     *
     */
    fun showButton(
        email: String,
        password: String
    ) : Boolean {
        if (email.isEmpty()) return false

        if (!EMAIL_PATTERN.matcher(email).matches()) return false

        return password.isNotEmpty()
    }
}