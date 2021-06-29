package com.leavingston.exchangerates.networking

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.leavingston.exchangerates.networking.NetworkStatusCheck.NetworkUtil
import com.leavingston.exchangerates.networking.NetworkStatusCheck.NetworkUtil.getConnectivityStatusString


class BroadcastReciever(ForceExitPause : (context : Context) -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val status = getConnectivityStatusString(context!!)
        Log.e("Sulod", "$status")
        if ("android.net.conn.CONNECTIVITY_CHANGE" == intent?.action) {
            if (status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
//                ForceExitPause(context).execute()


            } else {
//                ResumeForceExitPause(context).execute()



            }
        }


    }
}