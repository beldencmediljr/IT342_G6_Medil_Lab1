package com.example.userauthapp.model

data class User(
    val username: String,
    val email: String? = null,
    val password: String
)