@file:Suppress("PrivatePropertyName", "DEPRECATION")

package com.example.helloworld

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.bumptech.glide.Glide
import java.util.regex.Matcher
import java.util.regex.Pattern


class FirstActivity : AppCompatActivity() {



    private lateinit var emailBox : EditText
    private lateinit var passwordBox : EditText
    private lateinit var signInButton : Button
    private lateinit var createAccountButton: Button
    private lateinit var forgotPassword : TextView
    private lateinit var signInSwitch : SwitchCompat
    private lateinit var thumbnail : ImageView
//    private lateinit var ageSpinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        Log.v("Unique name", "onCreate method k andr hai abhi")

        val emailAndPasswordList = getEmailAndPassword()
        if (!emailAndPasswordList.isNullOrEmpty()){

            val openHomeActivityIntent : Intent = Intent(this, HomeActivity::class.java)

            val bundle : Bundle = Bundle()
            bundle.putString("email_text", emailAndPasswordList[0])
            bundle.putString("password_text", emailAndPasswordList[1])

            openHomeActivityIntent.putExtras(bundle)

            startActivity(openHomeActivityIntent)
            finish()

            Toast.makeText(this, "Welcome, Welcome! Ankit.", Toast.LENGTH_SHORT).show()

        }

        // Initialise views
        emailBox = findViewById(R.id.email)
        passwordBox = findViewById(R.id.password)
        signInButton = findViewById(R.id.sign_in_button)
        createAccountButton = findViewById(R.id.create_account_button)
        forgotPassword = findViewById(R.id.forgot_password)
        signInSwitch = findViewById(R.id.sign_in_switch)
        thumbnail = findViewById(R.id.thumbnail)
//        ageSpinner = findViewById(R.id.spinner)

//        initialiseSpinner(
//            context = this,
//            spinner = ageSpinner,
//            values = ArrayList<Int>().apply { add(1); add(2); add(3); add(4) },
//            functionOnItemSelected = { position ->
//                Toast.makeText(this, "Position : $position", Toast.LENGTH_SHORT).show()
//            }
//        )

        Glide.with(this).load(thumbnailUrl).into(thumbnail)

        signInButton.setOnClickListener {
            val emailString : String = emailBox.text.toString()
            val passwordString : String = passwordBox.text.toString()
            val areYouHuman : Boolean = signInSwitch.isChecked


            if (areYouHuman){
                if (checkEmailValidUsingRegex(emailString)){

                    if (checkPasswordValidUsingRegex(passwordString)){

                        val signInSuccessful : Boolean = true
                        if (signInSuccessful){
                            storeEmailAndPassword(emailString, passwordString)

                            val openHomeActivityIntent : Intent = Intent(this, HomeActivity::class.java)

                            val bundle : Bundle = Bundle()
                            bundle.putString("email_text", emailString)
                            bundle.putString("password_text", passwordString)

                            openHomeActivityIntent.putExtras(bundle)

                            startActivity(openHomeActivityIntent)
                            finish()

                            Toast.makeText(this, "Welcome, Welcome! Ankit.", Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(this, "Wrong credentials!!!!", Toast.LENGTH_SHORT).show()

                        }

                    }else{
                        Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Please join being human!", Toast.LENGTH_SHORT).show()
            }
        }

        createAccountButton.setOnClickListener {
            val secondActivityIntent = Intent(this, SecondActivity::class.java)
            startActivity(secondActivityIntent)
            finish()
        }

        forgotPassword.setOnClickListener {
            Toast.makeText(this, "We can't help you! HAHA", Toast.LENGTH_SHORT).show()
        }

    }

    fun getEmailAndPassword(): List<String>? {
        val preferences : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val emailString : String = preferences.getString("EMAIL_STRING", null) ?: return null
        val passwordString : String = preferences.getString("PASSWORD_STRING", null) ?: return null

        return listOf(emailString, passwordString)

    }

    private fun storeEmailAndPassword(email : String, password : String) {
        val preferences : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val edit = preferences.edit()

        edit.putString("EMAIL_STRING", email)
        edit.putString("PASSWORD_STRING", password)

        edit.apply()
    }
}