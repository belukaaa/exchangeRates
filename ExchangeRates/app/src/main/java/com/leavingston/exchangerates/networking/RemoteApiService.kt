package com.leavingston.exchangerates.networking

import com.leavingston.exchangerates.models.Example
import retrofit2.Call
import retrofit2.http.GET

// ინტერფეისი დამაკავშირებელი სერვერის და ჩვენი აპლიკაციის , აქედან ხდება სერვერზე ქოლები
interface RemoteApiService {
    //GET ანოტაცია ნიშნავს რომ სერვერიდან ითხოვს მონაცემებს
    //POST ანოტაცია ნიშნავს სერვერზე მონაცემების გაგზავნას
    //DELETE ანოტაცია ნიშნავს სერვერიდან მონაცემების წაშლას
    @GET("/v6/e43ec3672cb735733fab7a71/latest/USD")
    fun getExchangeRates() : Call<Example>
    @GET("/v6/e43ec3672cb735733fab7a71/latest/GEL")
    fun getGelRates() : Call<Example>
    @GET("/v6/e43ec3672cb735733fab7a71/latest/EUR")
    fun getEurRates() : Call<Example>



}