package com.trustwallet.walletconnect.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.trustwallet.walletconnect.WCInteractor
import com.trustwallet.walletconnect.models.WCPeerMeta

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val wcSession = CreateSession.initWCSession()
    }
}
