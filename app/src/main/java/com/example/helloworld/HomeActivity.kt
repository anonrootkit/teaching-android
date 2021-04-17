package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    private lateinit var emailText : TextView
    private lateinit var passwordText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle : Bundle = intent.extras!!

        val email : String = bundle.getString("email_text")!!
        val password : String = bundle.getString("password_text")!!

        emailText = findViewById(R.id.email)
        passwordText = findViewById(R.id.password)

        emailText.text = email
        passwordText.text = password

    }
}