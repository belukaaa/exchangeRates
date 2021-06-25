package com.leavingston.exchangerates.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leavingston.exchangerates.models.Example
import com.leavingston.exchangerates.models.ratesModel
import retrofit2.http.DELETE

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRates(rates : ratesModel)

    @Query("SELECT * FROM rates")
    fun getRates() : LiveData<ratesModel>

    @Update
    fun updateRates(updatingRates : ratesModel)

    @Query("DELETE FROM rates")
    fun deleteRates()

}