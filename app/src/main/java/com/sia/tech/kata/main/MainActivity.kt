package com.sia.tech.kata.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sia.tech.kata.R
import com.sia.tech.kata.TechKataApplication
import com.sia.tech.kata.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as TechKataApplication).appComponent.inject(this)

        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}