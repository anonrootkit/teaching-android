@file:Suppress("PrivatePropertyName")

package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import java.util.regex.Matcher
import java.util.regex.Pattern


class FirstActivity : AppCompatActivity() {

    private var PASSWORD_MIN_LENGTH : Int = 8
    private var EMAIL_MIN_SIZE : Int = 8
    private var CORRECT_EMAIL : String = "ankit@ankit.com"
    private var CORRECT_PASSWORD : String = "Ankit@ankit123"
    private val passwordRegex : String = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=()])(?=\\S+$).{8,20}$"
    private val emailRegex : String = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"


    private lateinit var emailBox : EditText
    private lateinit var passwordBox : EditText
    private lateinit var signInButton : Button
    private lateinit var forgotPassword : TextView
    private lateinit var signInSwitch : SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        // Initialise views
        emailBox = findViewById(R.id.email)
        passwordBox = findViewById(R.id.password)
        signInButton = findViewById(R.id.sign_in_button)
        forgotPassword = findViewById(R.id.forgot_password)
        signInSwitch = findViewById(R.id.sign_in_switch)


        signInButton.setOnClickListener {
            val emailString : String = emailBox.text.toString()
            val passwordString : String = passwordBox.text.toString()
            val areYouHuman : Boolean = signInSwitch.isChecked


            if (areYouHuman){
                if (checkEmailValidUsingRegex(emailString)){

                    if (checkPasswordValidUsingRegex(passwordString)){

                        val signInSuccessful : Boolean = signInUser(emailString, passwordString)
                        if (signInSuccessful){

                            val openHomeActivityIntent : Intent = Intent(this, HomeActivity::class.java)
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

        forgotPassword.setOnClickListener {
            Toast.makeText(this, "We can't help you! HAHA", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkPasswordValidUsingRegex(password: String) : Boolean {

        val pattern: Pattern = Pattern.compile(passwordRegex)

        if (password.length < PASSWORD_MIN_LENGTH) {
            return false
        }

        val matcher: Matcher = pattern.matcher(password)

        return matcher.matches()
    }

    private fun checkPasswordValid(password: String): Boolean {

        if (password.length < PASSWORD_MIN_LENGTH) return false

        var doesCapitalLetterExist : Boolean = false

        for (character in password){
            if (character.toInt() in 65..90){
                doesCapitalLetterExist = true
                break
            }
        }

        if (doesCapitalLetterExist){

            var doesNumberExist : Boolean = false

            for (character in password){
                if (character.toInt() in 48..57){
                    doesNumberExist = true
                    break
                }
            }

            if (doesNumberExist){

                var doesSpecialCharacterExist : Boolean = false

                for (character in password){
                    if (character.toInt() == 35 || character.toInt() == 64){
                        doesSpecialCharacterExist = true
                        break
                    }
                }

                return doesSpecialCharacterExist

            }else{
                return false
            }

        }else{
            return false
        }

    }


    fun checkEmailValidUsingRegex(email: String) : Boolean {
        val pattern: Pattern = Pattern.compile(emailRegex)

        if (email.length < EMAIL_MIN_SIZE) {
            return false
        }

        val matcher: Matcher = pattern.matcher(email)

        return matcher.matches()
    }


    fun checkEmailValid(email: String) : Boolean{
        return email.length >= EMAIL_MIN_SIZE
    }

    fun signInUser(emailString: String, passwordString: String) : Boolean{
        return emailString == CORRECT_EMAIL && passwordString == CORRECT_PASSWORD
    }

}