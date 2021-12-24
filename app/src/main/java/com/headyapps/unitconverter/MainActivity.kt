package com.headyapps.unitconverter

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.headyapps.unitconverter.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

//    creating the viewBinding
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

//   Setting the viewModel
    private val viewModel: MainViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//      inflating the binding and setting contentView to the binding
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      onClick for the btnConvert that sends the data to the stateflow
        binding.btnConvert.setOnClickListener {
            var convertFrom = binding.convertFromUnits.selectedItem.toString()
            var convertTo =  binding.convertToUnits.selectedItem.toString()
            var unitAmount = binding.etInitialInput.text.toString()
            var convertedResult = binding.txtConversionResult.text.toString()

//          Sending to method convert and sending data to stateflow
            viewModel.convert(
                convertFrom,
                convertTo,
                unitAmount,
                convertedResult

            )
        }

//      coroutines setup to observe the flows
        lifecycleScope.launchWhenStarted {
//          state flow collection
            viewModel.conversionUIState.collect {

//              Updating the UI from the sealed class in MainViewModel
                when(it) {
                    is MainViewModel.ConversionUIState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.txtConversionResult.isVisible = true
                        binding.txtConversionResultLabel.isVisible = true
                        binding.txtConversionResult.text = it.result.toString()

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
                        Snackbar.make(
                            binding.root,
                            it.message,
                            Snackbar.LENGTH_SHORT
                        ).show()
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
        //setting onItemSelectedListeners for convertFrom
        convert_from_units.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        //setting onItemSelectedListeners for convertTo
        convert_to_units.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

//    close the binding
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}