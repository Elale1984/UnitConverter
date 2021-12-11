package com.headyapps.unitconverter

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //setting convert from spinner
        convert_from_units.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@MainActivity, resources.getStringArray(R.array.start_con_array)[position], Toast.LENGTH_SHORT)
                    .show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        //setting convert to spinner
        convert_to_units.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@MainActivity, resources.getStringArray(R.array.start_con_array)[position], Toast.LENGTH_SHORT)
                    .show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}