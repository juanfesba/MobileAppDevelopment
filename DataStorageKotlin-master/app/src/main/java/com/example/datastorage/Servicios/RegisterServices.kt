package com.example.datastorage.Servicios


import android.content.Context
import com.example.datastorage.Modelos.User

class RegisterServices(context: Context)
{
    private lateinit var user : User
    private var dbConnection : UserDBServices = UserDBServices(context)
    private var sharedConnection : UserReservedServices = UserReservedServices(context)

    fun existUser(user: User) : Boolean
    {
        this.user = user
        var result : Boolean = false

        if(sharedConnection.existUser(user))
        {
            result =  sharedConnection.existUser(this.user)
        }
        else
            result =  dbConnection.existUser(this.user)

        return result
    }

    fun saveUser(user: User){
        dbConnection.saveUser(user)
    }
}