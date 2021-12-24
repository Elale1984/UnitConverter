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

    fun convert(convertFrom: String, convertTo: String, convertInput: String, convertResult: String) = viewModelScope.launch {

        if(convertFrom != "" && convertTo != "" && convertInput != "") {

            _conversionState.value = ConversionUIState.Loading
            delay(1000L)


            val conversionInput = convertInput.toInt()
            val convertedResult = conversionInput * 5

            _conversionState.value = ConversionUIState.Success(convertedResult)



        }
        else {
            _conversionState.value = ConversionUIState.Error("One or more of the conversion types are empty")
        }
    }
    sealed class ConversionUIState {
        data class Success(val result: Int) : ConversionUIState()
        data class Error(val message: String) : ConversionUIState()
        object Loading : ConversionUIState()
        object Empty : ConversionUIState()
    }


}