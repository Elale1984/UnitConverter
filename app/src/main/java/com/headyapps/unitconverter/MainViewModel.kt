package com.headyapps.unitconverter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _conversionState = MutableStateFlow<ConversionUIState>(ConversionUIState.Empty)
    val conversionUIState: StateFlow<ConversionUIState> = _conversionState

    fun convert(
        convertFrom: String,
        convertTo: String,
        convertInput: String,
    ) = viewModelScope.launch {

        if(convertFrom != "" && convertTo != "" && convertInput != "" && convertFrom != convertTo) {

            _conversionState.value = ConversionUIState.Loading
            delay(500L)


            val conversionInput = convertInput.toDouble()
            var convertedResult = 0.0


            when(convertFrom){
                "Cups" -> {
                    when(convertTo){
                        "Ounces" -> convertedResult = conversionInput * 8.0
                        "Gallon" -> convertedResult = conversionInput / 16.0
                        "Pints"  -> convertedResult = conversionInput / 2.0
                        "Liters" -> convertedResult = conversionInput / 4.227
                    }
                }
                "Ounces" -> {
                    when(convertTo){
                        "Cups" -> convertedResult = conversionInput / 8.0
                        "Gallon" -> convertedResult = conversionInput / 128.0
                        "Pints"  -> convertedResult = conversionInput / 16.0
                        "Liters" -> convertedResult = conversionInput / 33.814
                    }
                }
                "Gallons" -> {
                    when(convertTo){
                        "Cups" -> convertedResult = conversionInput * 16
                        "Ounces" -> convertedResult = conversionInput * 128.0
                        "Pints"  -> convertedResult = conversionInput * 8
                        "Liters" -> convertedResult = conversionInput * 3.785
                    }
                }
                "Pints" -> {
                    when(convertTo){
                        "Cups" -> convertedResult = conversionInput * 2.0
                        "Ounces" -> convertedResult = conversionInput * 16.0
                        "Gallons"  -> convertedResult = conversionInput / 8.0
                        "Liters" -> convertedResult = conversionInput / 2.113
                    }
                }
                "Liters" -> {
                    when(convertTo){
                        "Cups" -> convertedResult = conversionInput * 4.227
                        "Ounces" -> convertedResult = conversionInput * 33.814
                        "Gallons"  -> convertedResult = conversionInput / 3.785
                        "Pints" -> convertedResult = conversionInput * 2.113
                    }
                }
            }

            _conversionState.value = ConversionUIState.Success(convertedResult, convertTo)



        }
        else {
            _conversionState.value = ConversionUIState.Error("One or more of the conversion types are empty")
        }
    }
    sealed class ConversionUIState {
        data class Success(val result: Double, val convertedType: String) : ConversionUIState()
        data class Error(val message: String) : ConversionUIState()
        object Loading : ConversionUIState()
        object Empty : ConversionUIState()
    }


}