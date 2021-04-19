package com.example.helloworld

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView

class ListActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        listView = findViewById(R.id.listView)

        val doubleValueList : ArrayList<DoubleValues> = ArrayList()

        for (index in 0 until nameList.size){
            val doubleValue : DoubleValues = DoubleValues(
                name = nameList[index],
                password = passwordList[index]
            )
            doubleValueList.add(doubleValue)
        }

        val customAdapter : CustomArrayAdapter = CustomArrayAdapter(this, doubleValueList)
        listView.adapter = customAdapter

    }
}

data class DoubleValues(val name : String, val password : String)

class CustomArrayAdapter(context: Context, doubleValueList : ArrayList<DoubleValues>)
    : ArrayAdapter<DoubleValues>(context, 0, doubleValueList){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var rootView : View? = convertView
        if (convertView == null){
            rootView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        }

        val name : TextView = rootView!!.findViewById(R.id.name)
        val password : TextView = rootView.findViewById(R.id.password)

        val doubleValueItem : DoubleValues = getItem(position)!!

        name.text = doubleValueItem.name
        password.text = doubleValueItem.password

        return rootView
    }
}