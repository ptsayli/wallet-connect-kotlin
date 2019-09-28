package com.trustwallet.walletconnect.sample

import com.google.gson.GsonBuilder
import com.trustwallet.walletconnect.WCInteractor
import com.trustwallet.walletconnect.models.WCPeerMeta
import com.trustwallet.walletconnect.models.ethereum.WCEthereumSignMessage
import com.trustwallet.walletconnect.models.ethereum.WCEthereumTransaction
import com.trustwallet.walletconnect.models.session.WCSession
import okhttp3.OkHttpClient

object CreateSession {
    val uri =
        "wc:0f342943-1432-48e7-b19b-eedaccfffc84@1?bridge=https%3A%2F%2Fbridge.walletconnect.org&key=6b251f40ff1327f41f4eac12ae74df96a48b4c9f3d989e0b16c5e154f758cc94"
    lateinit var wci: WCInteractor
    fun initWCSession() {
        val session = WCSession.from(uri)
        val clientMeta = WCPeerMeta("", "")
        val client = OkHttpClient.Builder().build()
        val builder = GsonBuilder()
        wci = WCInteractor(session, clientMeta, client, builder)
        wci.connect()
        wci.onSessionRequest = ::onSessionRequest
        wci.onEthSign = ::onEthSign
        wci.onEthTransaction = ::onEthSendTransaction
        wci.onCustomRequest = ::onCustomRequest
    }

    fun onSessionRequest(id: Long, peer: WCPeerMeta) {
        val accounts = listOf("0x7eD7f36694153bA6EfF6ca6726b60F6E2Bb17fcf")
        wci.approveSesssion(accounts, 3)
    }

    fun onEthSign(id: Long, message: WCEthereumSignMessage) {
        wci.approveRequest(id, message)
    }

    fun onEthSendTransaction(id: Long, transaction: WCEthereumTransaction) {
        wci.approveRequest(id, transaction)
    }

    fun onCustomRequest(id: Long, payload: String) {
        wci.approveRequest(id, payload)
    }
}