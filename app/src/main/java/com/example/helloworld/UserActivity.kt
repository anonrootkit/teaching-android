package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class UserActivity : AppCompatActivity() {

    private lateinit var username : TextView
    private lateinit var userPassword : TextView
    private lateinit var userProfilePic : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val informationBundle : Bundle = intent.extras!!
        val usernameString : String = informationBundle.getString("USERNAME")!!
        val userPasswordString : String = informationBundle.getString("PASSWORD")!!
        val userProfilePicString : String = informationBundle.getString("THUMBNAIL")!!

        username = findViewById(R.id.user_name)
        userPassword = findViewById(R.id.user_password)
        userProfilePic = findViewById(R.id.user_image)

        username.text = usernameString
        userPassword.text = userPasswordString

        Glide.with(userProfilePic)
            .load(userProfilePicString)
            .placeholder(R.mipmap.ic_launcher)
            .into(userProfilePic)
    }
}