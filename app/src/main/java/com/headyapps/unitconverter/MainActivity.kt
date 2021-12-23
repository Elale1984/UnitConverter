package com.headyapps.unitconverter

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.headyapps.unitconverter.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {


    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    private val viewModel: MainViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                binding.convertFromUnits.selectedItem.toString(),
                binding.convertToUnits.selectedItem.toString()

            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.conversionUIState.collect {
                when(it) {
                    is MainViewModel.ConversionUIState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.txtConversionResult.isVisible = true
                        binding.txtConversionResultLabel.isVisible = true


                        when (binding.convertFromUnits.selectedItem.toString()) {
                            "Cups", "Ounces", "Gallons", "Pints", "Liters" ->

                                when (binding.convertToUnits.selectedItem.toString()) {
                                    "Cups" -> Toast.makeText(
                                        this@MainActivity,
                                        "Convert to ${binding.convertFromUnits.selectedItem} to ${binding.convertToUnits.selectedItem}", Toast.LENGTH_SHORT
                                    ).show()
                                    "Ounces" -> Toast.makeText(
                                        this@MainActivity,
                                        "Convert to ${binding.convertFromUnits.selectedItem} to ${binding.convertToUnits.selectedItem}", Toast.LENGTH_SHORT
                                    ).show()
                                    "Gallons" -> Toast.makeText(
                                        this@MainActivity,
                                        "Convert to ${binding.convertFromUnits.selectedItem} to ${binding.convertToUnits.selectedItem}", Toast.LENGTH_SHORT
                                    ).show()
                                    "Pints" -> Toast.makeText(
                                        this@MainActivity,
                                        "Convert to ${binding.convertFromUnits.selectedItem} to ${binding.convertToUnits.selectedItem}", Toast.LENGTH_SHORT
                                    ).show()
                                    "Liters" -> Toast.makeText(
                                        this@MainActivity,
                                        "Convert to ${binding.convertFromUnits.selectedItem}to ${binding.convertToUnits.selectedItem}", Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }


                    }
                    is MainViewModel.ConversionUIState.Error -> {
                        binding.progressBar.isVisible = false
                    }
                    is MainViewModel.ConversionUIState.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.txtConversionResult.isVisible = false
                        binding.txtConversionResultLabel.isVisible = false
                    }
                    else -> Unit
                }
            }
        }
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

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}