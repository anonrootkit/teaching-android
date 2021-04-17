package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat

class SecondActivity : AppCompatActivity() {


    private lateinit var nameBox: EditText
    private lateinit var emailBox: EditText
    private lateinit var passwordBox: EditText
    private lateinit var createAccountButton: Button
    private lateinit var signInButton: Button
    private lateinit var statesSpinner: Spinner
    private val statesList : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Initialise views
        nameBox = findViewById(R.id.name)
        emailBox = findViewById(R.id.email)
        passwordBox = findViewById(R.id.password)
        createAccountButton = findViewById(R.id.create_account_button)
        signInButton = findViewById(R.id.sign_in_button)
        statesSpinner = findViewById(R.id.state_spinner)

        initialiseStatesList()

        val spinnerAdapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, statesList)
        statesSpinner.adapter = spinnerAdapter

        createAccountButton.setOnClickListener {
            val emailString: String = emailBox.text.toString()
            val passwordString: String = passwordBox.text.toString()


            if (checkEmailValidUsingRegex(emailString)) {

                if (checkPasswordValidUsingRegex(passwordString)) {

                    val createAccountSuccessful: Boolean = true
                    if (createAccountSuccessful) {

                        val openHomeActivityIntent: Intent = Intent(this, HomeActivity::class.java)

                        val bundle: Bundle = Bundle()
                        bundle.putString("email_text", emailString)
                        bundle.putString("password_text", passwordString)

                        openHomeActivityIntent.putExtras(bundle)

                        startActivity(openHomeActivityIntent)
                        finish()

                        Toast.makeText(this, "Welcome, Welcome! Ankit.", Toast.LENGTH_SHORT).show()

                    }

                } else {
                    Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
            }
        }

        signInButton.setOnClickListener {
            val firstActivityIntent : Intent = Intent(this, FirstActivity::class.java)
            startActivity(firstActivityIntent)
            finish()
        }

        statesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedState = statesList[position]

                Toast.makeText(this@SecondActivity, selectedState, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun initialiseStatesList() {
        statesList.add("Andhra")
        statesList.add("Arunachal")
        statesList.add("Delhi")
        statesList.add("HP")
        statesList.add("TM")
    }
}