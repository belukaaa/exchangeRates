package com.leavingston.exchangerates.ViewModel

import androidx.lifecycle.ViewModel
import com.leavingston.exchangerates.repository.ExchangeRatesRepo


class GelRatesViewModule(private var repo : ExchangeRatesRepo) : ViewModel() {
}