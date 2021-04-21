package com.example.helloworld

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide

class ListActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        listView = findViewById(R.id.listView)

        val customAdapter : CustomArrayAdapter = CustomArrayAdapter(this, usersList)
        listView.adapter = customAdapter

    }
}

data class User(
    val name : String,
    val userName : String,
    val profilePic : String
)

class CustomArrayAdapter(context: Context, doubleValueList : ArrayList<User>)
    : ArrayAdapter<User>(context, 0, doubleValueList){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var rootView : View? = convertView
        if (convertView == null){
            rootView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        }

        val name : TextView = rootView!!.findViewById(R.id.name)
        val password : TextView = rootView.findViewById(R.id.password)
        val profilePic : ImageView = rootView.findViewById(R.id.profile_pic)

        val user : User = getItem(position)!!

        name.text = user.name
        password.text = user.userName

        Glide.with(rootView).load(user.profilePic).into(profilePic)

        return rootView
    }
}