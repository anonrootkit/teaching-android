package com.example.helloworld

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

fun initialiseSpinner(
    context: Context,
    spinner: Spinner,
    values: ArrayList<*>,
    functionOnItemSelected: (Int) -> Unit
) {
    val spinnerAdapter : ArrayAdapter<*> = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, values)
    spinner.adapter = spinnerAdapter

    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            functionOnItemSelected(position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }
    }

}