package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class ListActivity : AppCompatActivity() {

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        listView = findViewById(R.id.list_view)
        val listViewAdapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, statesList)
        listView.adapter = listViewAdapter
    }
}