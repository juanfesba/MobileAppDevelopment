package com.example.datastorage.Servicios

import com.example.datastorage.Modelos.User

interface IUserServices
{
    fun verifyUser(user: User) : Boolean
    fun saveUser(user: User)
    fun consultUsers() : List<User>?
    fun existUser(user: User) : Boolean
    fun getUser(mail:String) : User
}