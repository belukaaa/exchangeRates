package com.leavingston.exchangerates.models

import com.google.gson.annotations.SerializedName
data class Example (

    @SerializedName("result") var result : String,
    @SerializedName("documentation") var documentation : String,
    @SerializedName("terms_of_use") var termsOfUse : String,
    @SerializedName("time_last_update_unix") var timeLastUpdateUnix : Int,
    @SerializedName("time_last_update_utc") var timeLastUpdateUtc : String,
    @SerializedName("time_next_update_unix") var timeNextUpdateUnix : Int,
    @SerializedName("time_next_update_utc") var timeNextUpdateUtc : String,
    @SerializedName("base_code") var baseCode : String,
    @SerializedName("conversion_rates") var conversionRates : ConversionRates

)