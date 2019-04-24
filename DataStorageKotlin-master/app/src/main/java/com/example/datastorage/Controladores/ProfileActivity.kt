package com.example.datastorage.Controladores

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.datastorage.R
import com.example.datastorage.Servicios.UserDBServices
import kotlinx.android.synthetic.main.activity_register.*
import java.io.ByteArrayInputStream

class ProfileActivity : AppCompatActivity() {

    companion object {
        const val MAIL = "mail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        showProfile()
    }

    fun showProfile()
    {
        val idMail = intent.getStringExtra(MAIL)



        val user = UserDBServices(this).getUser(idMail)
        /*val tamañosafe:ByteArray?=user.image
        val tamaño = if (tamañosafe != null) tamañosafe.size else -1
        val image= BitmapFactory.decodeByteArray(user.image,0,tamaño)*/

        val stream=ByteArrayInputStream(user.image)
        val image=BitmapFactory.decodeStream(stream)
        val drawable=RoundedBitmapDrawableFactory.create(resources,image)


        val imageUpload=findViewById<ImageView>(R.id.imageProfile)
        //imageUpload.setImageBitmap(image)
        imageUpload.setImageDrawable(drawable)


        findViewById<TextView>(R.id.nameProfile).text = "Nombre: " + user.name
        findViewById<TextView>(R.id.ageProfile).text = "Edad: " + (user.age).toString()
        findViewById<TextView>(R.id.emailProfile).text = "Correo: " + user.email

    }

    fun volver(view: View)
    {
        val intent = Intent(this, UsersListActivity::class.java)
        startActivity(intent)
    }
}
