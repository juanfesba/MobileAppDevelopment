package com.example.datastorage.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.example.datastorage.Adapters.UsersListAdapter
import com.example.datastorage.Modelos.User
import com.example.datastorage.R
import com.example.datastorage.Servicios.UserDBServices


class UsersListActivity : AppCompatActivity()
{
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)

        val listPosts: List<User>? = UserDBServices(this).consultUsers()
        listView = findViewById<ListView>(R.id.listUsers) as ListView
        val adapter = UsersListAdapter(this, listPosts)
        listView.adapter = adapter

        listView.setClickable(true)
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val clickedMail = adapter.getMail(i)
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra(ProfileActivity.MAIL, clickedMail)
            startActivity(intent)
            //Toast.makeText(this, "Item Clicked " + adapter.getName(i),Toast.LENGTH_SHORT).show()
        }
    }
}
