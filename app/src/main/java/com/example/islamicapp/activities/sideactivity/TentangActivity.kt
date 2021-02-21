package com.example.islamicapp.activities.sideactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.islamicapp.R
import com.example.islamicapp.adapter.TentangAdapter
import kotlinx.android.synthetic.main.activity_tentang.*

class TentangActivity : AppCompatActivity() {

    val nama = arrayOf<String>("App Name","Build Version","Email","Developer","Copyright")
    val des = arrayOf<String>(
        "Islamic App",
        "Version 1.0",
        "syahrul.am9773@gmail.com",
        "Muhammad Syahrul Anwar",
        "Copyright © 2021 All rights reserved"
    )

    val imageId = arrayOf<Int>(
        R.drawable.ic_home,R.drawable.ic_about,R.drawable.ic_email,
        R.drawable.ic_pro,R.drawable.ic_cr
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tentang)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "About App"

        val myListAdapter = TentangAdapter(this,nama,des,imageId)
        listView.adapter = myListAdapter

        listView.setOnItemClickListener(){ adapterView, _, position, _ ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            Toast.makeText(this, "Click on $itemAtPos", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}