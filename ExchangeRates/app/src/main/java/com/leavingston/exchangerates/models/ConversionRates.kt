package com.leavingston.exchangerates.models

import com.google.gson.annotations.SerializedName


data class ConversionRates (

    @SerializedName("USD") var USD : Double,
    @SerializedName("AED") var AED : Double,
    @SerializedName("AFN") var AFN : Double,
    @SerializedName("ALL") var ALL : Double,
    @SerializedName("AMD") var AMD : Double,
    @SerializedName("ANG") var ANG : Double,
    @SerializedName("AOA") var AOA : Double,
    @SerializedName("ARS") var ARS : Double,
    @SerializedName("AUD") var AUD : Double,
    @SerializedName("AWG") var AWG : Double,
    @SerializedName("AZN") var AZN : Double,
    @SerializedName("BAM") var BAM : Double,
    @SerializedName("BBD") var BBD : Double,
    @SerializedName("BDT") var BDT : Double,
    @SerializedName("BGN") var BGN : Double,
    @SerializedName("BHD") var BHD : Double,
    @SerializedName("BIF") var BIF : Double,
    @SerializedName("BMD") var BMD : Double,
    @SerializedName("BND") var BND : Double,
    @SerializedName("BOB") var BOB : Double,
    @SerializedName("BRL") var BRL : Double,
    @SerializedName("BSD") var BSD : Double,
    @SerializedName("BTN") var BTN : Double,
    @SerializedName("BWP") var BWP : Double,
    @SerializedName("BYN") var BYN : Double,
    @SerializedName("BZD") var BZD : Double,
    @SerializedName("CAD") var CAD : Double,
    @SerializedName("CDF") var CDF : Double,
    @SerializedName("CHF") var CHF : Double,
    @SerializedName("CLP") var CLP : Double,
    @SerializedName("CNY") var CNY : Double,
    @SerializedName("COP") var COP : Double,
    @SerializedName("CRC") var CRC : Double,
    @SerializedName("CUC") var CUC : Double,
    @SerializedName("CUP") var CUP : Double,
    @SerializedName("CVE") var CVE : Double,
    @SerializedName("CZK") var CZK : Double,
    @SerializedName("DJF") var DJF : Double,
    @SerializedName("DKK") var DKK : Double,
    @SerializedName("DOP") var DOP : Double,
    @SerializedName("DZD") var DZD : Double,
    @SerializedName("EGP") var EGP : Double,
    @SerializedName("ERN") var ERN : Double,
    @SerializedName("ETB") var ETB : Double,
    @SerializedName("EUR") var EUR : Double,
    @SerializedName("FJD") var FJD : Double,
    @SerializedName("FKP") var FKP : Double,
    @SerializedName("FOK") var FOK : Double,
    @SerializedName("GBP") var GBP : Double,
    @SerializedName("GEL") var GEL : Double,
    @SerializedName("GGP") var GGP : Double,
    @SerializedName("GHS") var GHS : Double,
    @SerializedName("GIP") var GIP : Double,
    @SerializedName("GMD") var GMD : Double,
    @SerializedName("GNF") var GNF : Double,
    @SerializedName("GTQ") var GTQ : Double,
    @SerializedName("GYD") var GYD : Double,
    @SerializedName("HKD") var HKD : Double,
    @SerializedName("HNL") var HNL : Double,
    @SerializedName("HRK") var HRK : Double,
    @SerializedName("HTG") var HTG : Double,
    @SerializedName("HUF") var HUF : Double,
    @SerializedName("IDR") var IDR : Double,
    @SerializedName("ILS") var ILS : Double,
    @SerializedName("IMP") var IMP : Double,
    @SerializedName("INR") var INR : Double,
    @SerializedName("IQD") var IQD : Double,
    @SerializedName("IRR") var IRR : Double,
    @SerializedName("ISK") var ISK : Double,
    @SerializedName("JMD") var JMD : Double,
    @SerializedName("JOD") var JOD : Double,
    @SerializedName("JPY") var JPY : Double,
    @SerializedName("KES") var KES : Double,
    @SerializedName("KGS") var KGS : Double,
    @SerializedName("KHR") var KHR : Double,
    @SerializedName("KID") var KID : Double,
    @SerializedName("KMF") var KMF : Double,
    @SerializedName("KRW") var KRW : Double,
    @SerializedName("KWD") var KWD : Double,
    @SerializedName("KYD") var KYD : Double,
    @SerializedName("KZT") var KZT : Double,
    @SerializedName("LAK") var LAK : Double,
    @SerializedName("LBP") var LBP : Double,
    @SerializedName("LKR") var LKR : Double,
    @SerializedName("LRD") var LRD : Double,
    @SerializedName("LSL") var LSL : Double,
    @SerializedName("LYD") var LYD : Double,
    @SerializedName("MAD") var MAD : Double,
    @SerializedName("MDL") var MDL : Double,
    @SerializedName("MGA") var MGA : Double,
    @SerializedName("MKD") var MKD : Double,
    @SerializedName("MMK") var MMK : Double,
    @SerializedName("MNT") var MNT : Double,
    @SerializedName("MOP") var MOP : Double,
    @SerializedName("MRU") var MRU : Double,
    @SerializedName("MUR") var MUR : Double,
    @SerializedName("MVR") var MVR : Double,
    @SerializedName("MWK") var MWK : Double,
    @SerializedName("MXN") var MXN : Double,
    @SerializedName("MYR") var MYR : Double,
    @SerializedName("MZN") var MZN : Double,
    @SerializedName("NAD") var NAD : Double,
    @SerializedName("NGN") var NGN : Double,
    @SerializedName("NIO") var NIO : Double,
    @SerializedName("NOK") var NOK : Double,
    @SerializedName("NPR") var NPR : Double,
    @SerializedName("NZD") var NZD : Double,
    @SerializedName("OMR") var OMR : Double,
    @SerializedName("PAB") var PAB : Double,
    @SerializedName("PEN") var PEN : Double,
    @SerializedName("PGK") var PGK : Double,
    @SerializedName("PHP") var PHP : Double,
    @SerializedName("PKR") var PKR : Double,
    @SerializedName("PLN") var PLN : Double,
    @SerializedName("PYG") var PYG : Double,
    @SerializedName("QAR") var QAR : Double,
    @SerializedName("RON") var RON : Double,
    @SerializedName("RSD") var RSD : Double,
    @SerializedName("RUB") var RUB : Double,
    @SerializedName("RWF") var RWF : Double,
    @SerializedName("SAR") var SAR : Double,
    @SerializedName("SBD") var SBD : Double,
    @SerializedName("SCR") var SCR : Double,
    @SerializedName("SDG") var SDG : Double,
    @SerializedName("SEK") var SEK : Double,
    @SerializedName("SGD") var SGD : Double,
    @SerializedName("SHP") var SHP : Double,
    @SerializedName("SLL") var SLL : Double,
    @SerializedName("SOS") var SOS : Double,
    @SerializedName("SRD") var SRD : Double,
    @SerializedName("SSP") var SSP : Double,
    @SerializedName("STN") var STN : Double,
    @SerializedName("SYP") var SYP : Double,
    @SerializedName("SZL") var SZL : Double,
    @SerializedName("THB") var THB : Double,
    @SerializedName("TJS") var TJS : Double,
    @SerializedName("TMT") var TMT : Double,
    @SerializedName("TND") var TND : Double,
    @SerializedName("TOP") var TOP : Double,
    @SerializedName("TRY") var TRY : Double,
    @SerializedName("TTD") var TTD : Double,
    @SerializedName("TVD") var TVD : Double,
    @SerializedName("TWD") var TWD : Double,
    @SerializedName("TZS") var TZS : Double,
    @SerializedName("UAH") var UAH : Double,
    @SerializedName("UGX") var UGX : Double,
    @SerializedName("UYU") var UYU : Double,
    @SerializedName("UZS") var UZS : Double,
    @SerializedName("VES") var VES : Double,
    @SerializedName("VND") var VND : Double,
    @SerializedName("VUV") var VUV : Double,
    @SerializedName("WST") var WST : Double,
    @SerializedName("XAF") var XAF : Double,
    @SerializedName("XCD") var XCD : Double,
    @SerializedName("XDR") var XDR : Double,
    @SerializedName("XOF") var XOF : Double,
    @SerializedName("XPF") var XPF : Double,
    @SerializedName("YER") var YER : Double,
    @SerializedName("ZAR") var ZAR : Double,
    @SerializedName("ZMW") var ZMW : Double

)