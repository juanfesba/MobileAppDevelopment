package com.example.datastorage.Servicios

import android.content.Context
import android.content.SharedPreferences
import com.example.datastorage.Modelos.User
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson


class UserReservedServices(context: Context) : AppCompatActivity(), IUserServices
{
    override fun consultUsers(): List<User>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var preferencias = context.getSharedPreferences("usuarios", 0)

    override fun verifyUser(user: User): Boolean
    {
        var returnValue : Boolean = false
        var result = preferencias.getString(user.email, null)

        if(result != null)
        {
            var localUser = Gson().fromJson(result, User::class.javaObjectType)
            if (user.email.equals(localUser.email) && user.password.equals(localUser.password))
            {
                returnValue = true
            }
        }

        return returnValue
    }

    override fun saveUser(user: User)
    {
        val editor = preferencias.edit()
        var jsonUser = Gson().toJson(user)
        editor.putString(user.email, jsonUser)
        editor.commit()
        finish()
    }

    override fun existUser(user: User): Boolean
    {
        var returnValue : Boolean = false
        var result = preferencias.getString(user.email, null)

        if(result != null)
        {
            var localUser = Gson().fromJson(result, User::class.javaObjectType)
            if (user.email.equals(localUser.email))
            {
                returnValue = true
            }
        }

        return returnValue
    }

    override fun getUser(mail: String): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}