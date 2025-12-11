package com.practise.newsapp.common.utils

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    return Regex(emailRegex).matches(email)
}

// Strong password validation using regex
fun isStrongPassword(password: String): Boolean {
    // Min 8 chars, 1 uppercase, 1 lowercase, 1 digit, 1 special char
    val passwordRegex =
        "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-={}:;\"'<>,.?/]).{8,}$"
    return Regex(passwordRegex).matches(password)
}

fun isValidUserName(username: String): Boolean {
    val usernameRegex = "^[A-Za-z0-9]{4,20}$"
    return Regex(usernameRegex).matches(username)
}