package com.example.datastorage.Controladores

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.datastorage.Modelos.User
import com.example.datastorage.R
import com.example.datastorage.Servicios.LoginServices
import com.example.datastorage.Servicios.UserDBServices //Código Test

class MainActivity : AppCompatActivity()
{
    private lateinit var loginServices : LoginServices

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginServices= LoginServices(this)

        //UserDBServices(this)

        /*val user = User(null, "Leo", "leo@gmail.com", 35, "secret")
        val user2 = User(null, "Jose", "leonardo@hotmail.com", 25, "3")
        val user3 = User(null, "Messi", "messi@gmail.com", 31, "2")

        UserDBServices(this).saveUser(user) //Código Test
        UserDBServices(this).saveUser(user2) //Código Test
        UserDBServices(this).saveUser(user3) //Código Test*/
    }

    fun login(view: View)
    {
        val email = findViewById<TextView>(R.id.email);
        val password = findViewById<TextView>(R.id.password);
        val user = User(null, null, email.text.toString(), null, password.text.toString(),null)

        if(this.loginServices.verifyUser(user))
        {
            val intent = Intent(this, UsersListActivity::class.java)
            startActivity(intent)
        }
        else
        {
            Toast.makeText(this, "Datos incorrectos",  Toast.LENGTH_SHORT).show()
        }
    }

    fun register(view: View)
    {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}

