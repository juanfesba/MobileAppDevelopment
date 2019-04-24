package com.example.datastorage.Controladores

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.service.autofill.Validators.not
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.datastorage.Modelos.User
import com.example.datastorage.R
import com.example.datastorage.Servicios.RegisterServices
import java.io.ByteArrayOutputStream

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerServices : RegisterServices
    private lateinit var imageUploaded : ImageView
    private val RESULTLOADIMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerServices = RegisterServices(this)
        imageUploaded = findViewById(R.id.imageUpload)
    }

    fun profile(view: View)
    {
        val nombre = findViewById<TextView>(R.id.nombre);
        val email = findViewById<TextView>(R.id.correo);
        val password = findViewById<TextView>(R.id.contraseña);
        val edad = findViewById<TextView>(R.id.edad);
        val image = findViewById<ImageView>(R.id.imageUpload);

        val bitmap = (image.getDrawable() as BitmapDrawable).getBitmap()
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        val imagebyte = stream.toByteArray()

        val user = User(null, nombre.text.toString(), email.text.toString(), edad.text.toString().toInt(), password.text.toString(),imagebyte)

        if(this.registerServices.existUser(user))
        {
            Toast.makeText(this, "Este usuario ya esta registrado",  Toast.LENGTH_SHORT).show()
        }
        else
        {
            this.registerServices.saveUser(user)
            val intent = Intent(this, UsersListActivity::class.java)
            startActivity(intent)
        }

        /*val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)*/
    }

    fun volver(view: View)
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun uploadImage(view: View)
    {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, RESULTLOADIMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULTLOADIMAGE && resultCode == RESULT_OK && data != null)
        {
            val selectedImage = data.getData()
            imageUploaded.setImageURI(selectedImage)
            //imageUploaded.
        }
    }

}
