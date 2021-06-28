package com.leavingston.exchangerates.repository

import androidx.lifecycle.LiveData
import com.leavingston.exchangerates.networking.RemoteApiService

//repository არის დამაკავშირებელი ინტერფეისის რომელიც სერვერზე გზავნის ქოლებს , ჩვენს მიერ შექმნილ ვიუმოდელთან
class ExchangeRatesRepo(private val api : RemoteApiService) {


    suspend fun getUsdRates() = api.getExchangeRates()

    suspend fun getEurRates() = api.getRates()

}