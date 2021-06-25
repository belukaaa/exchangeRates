package com.leavingston.exchangerates.repository

import androidx.lifecycle.LiveData
import com.leavingston.exchangerates.networking.RemoteApiService

//repository არის დამაკავშირებელი ინტერფეისის რომელიც სერვერზე გზავნის ქოლებს , ჩვენს მიერ შექმნილ ვიუმოდელთან
class ExchangeRatesRepo(private val api : RemoteApiService) {


    fun getRates() = api.getExchangeRates()
    fun getGelRates() = api.getGelRates()
    fun getEurRates() = api.getEurRates()
}