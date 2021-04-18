package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView

class ListActivity : AppCompatActivity() {

//    private lateinit var listView : ListView
    private lateinit var listContainer : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listContainer = findViewById(R.id.list_container)

        for (state in statesList){

            val textView : TextView = TextView(this)
            textView.text = state

            listContainer.addView(textView)
        }

//        listView = findViewById(R.id.list_view)
//        val listViewAdapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, statesList)
//        listView.adapter = listViewAdapter


    }
}