package com.leavingston.exchangerates.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
class ratesModel (
    @PrimaryKey
    val id : Int,
    val GEL : Double ,
    val EUR : Double,
    val UPDATED_TIME : String
        )