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

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        //setting convert to spinner
        convert_to_units.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        btn_convert.setOnClickListener { convertUnits(convert_from_units.selectedItem, convert_to_units.selectedItem) }

    }

    // This method handles the conversion based on the selected items from the convert_from_units and the convert_to_units
    private fun convertUnits(unitsFrom: Any?, unitsTo: Any?) {

        when (unitsFrom) {
            "Cups", "Ounces", "Gallons", "Pints", "Liters" ->

                when (unitsTo) {
                    "Cups" -> Toast.makeText(
                        this@MainActivity,
                        "Convert to $unitsFrom to $unitsTo", Toast.LENGTH_SHORT
                    ).show()
                    "Ounces" -> Toast.makeText(
                        this@MainActivity,
                        "Convert to $unitsFrom to $unitsTo", Toast.LENGTH_SHORT
                    ).show()
                    "Gallons" -> Toast.makeText(
                        this@MainActivity,
                        "Convert to $unitsFrom to $unitsTo", Toast.LENGTH_SHORT
                    ).show()
                    "Pints" -> Toast.makeText(
                        this@MainActivity,
                        "Convert to $unitsFrom to $unitsTo", Toast.LENGTH_SHORT
                    ).show()
                    "Liters" -> Toast.makeText(
                        this@MainActivity,
                        "Convert to $unitsFrom to $unitsTo", Toast.LENGTH_SHORT
                    ).show()
                }
        }

    }


}