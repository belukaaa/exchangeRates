package com.leavingston.exchangerates.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.leavingston.exchangerates.models.ratesModel
import com.leavingston.exchangerates.repository.roomRepository

class DBViewModel(private val repo:roomRepository ) : ViewModel(){

    fun saveRates(ratesModel: ratesModel){
        repo.addRates(ratesModel)
    }
    fun getRates() : LiveData<ratesModel> {
        return repo.getData()
    }
    fun deleteRates(){
        repo.deleteRates()
    }

    fun updateRates(ratesModel: ratesModel) {
        repo.updateData(ratesModel)
    }

}