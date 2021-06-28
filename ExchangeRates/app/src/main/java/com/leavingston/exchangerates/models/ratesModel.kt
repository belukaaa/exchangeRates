package com.leavingston.exchangerates.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "rates")
class ratesModel (
    @PrimaryKey
    val id : Int,
    val GEL : Double ,
    val EUR : Double,
    val UPDATED_TIME : String
        ) : Parcelable