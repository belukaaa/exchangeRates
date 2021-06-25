package com.leavingston.exchangerates.repository

import androidx.lifecycle.LiveData
import com.leavingston.exchangerates.models.ratesModel
import com.leavingston.exchangerates.room.DAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class roomRepository(private val dao : DAO) {

    fun getData()  : LiveData<ratesModel> {
        return dao.getRates()
    }
    fun updateData(ratesModel: ratesModel) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.updateRates(ratesModel)
        }
    }
    fun deleteRates(){
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteRates()
        }
    }

    fun addRates(ratesModel: ratesModel){
       CoroutineScope(Dispatchers.IO).launch {
           dao.addRates(ratesModel)
        }

    }


}