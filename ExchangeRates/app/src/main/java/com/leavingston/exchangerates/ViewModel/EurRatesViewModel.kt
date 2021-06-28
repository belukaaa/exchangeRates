package com.leavingston.exchangerates.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leavingston.exchangerates.loadingState.LoadingState
import com.leavingston.exchangerates.models.Example
import com.leavingston.exchangerates.repository.ExchangeRatesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// extend CallBack<Example>
class EurRatesViewModel(private val repo : ExchangeRatesRepo) : ViewModel() {
    val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData<Example>()
    val data : LiveData<Example>
        get() = _data

    fun getGel(): Double? {
        return data.value?.conversionRates?.GEL
    }

    val respone = GlobalScope.launch(Dispatchers.IO) {
        val rates = repo.getEurRates()
        if (rates.isSuccessful){
            Log.i("FDFDFDAfdafa" ,"${rates.body()?.conversionRates?.GEL}")
            _data.postValue(rates.body())
            _loadingState.postValue(LoadingState.LOADED)
        }else{
            _loadingState.postValue(LoadingState.error(rates.errorBody().toString()))
        }
    }

}