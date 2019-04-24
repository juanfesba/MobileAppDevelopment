package com.example.datastorage.Modelos

data class User(
    val idUser: Int?,
    var name: String?,
    val email: String?,
    val age: Int?,
    var password: String?,
    var image: ByteArray?
    )