package com.leavingston.exchangerates.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leavingston.exchangerates.loadingState.LoadingState
import com.leavingston.exchangerates.models.Example
import com.leavingston.exchangerates.repository.ExchangeRatesRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EurRatesViewModel(private val repo : ExchangeRatesRepo) : ViewModel() , Callback<Example> {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData<Example>()
    val data : LiveData<Example>
        get() = _data

    init {
        fetchData()
    }

    fun fetchData() {
        _loadingState.postValue(LoadingState.LOADED)
        repo.getEurRates().enqueue(this)

    }


    override fun onResponse(call: Call<Example>, response: Response<Example>) {
        if (response.isSuccessful){
            Log.e("wywrywwywy" , "$response")

            _data.postValue(response.body())
            _loadingState.postValue(LoadingState.LOADED)
        }else {
            Log.e("Tag" , "$response")

            _loadingState.postValue(LoadingState.error(response.toString()))
        }
    }

    override fun onFailure(call: Call<Example>, t: Throwable) {

        _loadingState.postValue(LoadingState.error(t.message))
        Log.e("Tag" , "$t")
    }

}