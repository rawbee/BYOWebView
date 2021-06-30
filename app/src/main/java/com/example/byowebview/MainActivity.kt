package com.example.byowebview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.underdog_tech.pinwheel_android.Pinwheel
import com.underdog_tech.pinwheel_android.PinwheelEventListener
import com.underdog_tech.pinwheel_android.model.*
import java.io.Serializable

const val EVENTS_MESSAGE = "com.example.byowebview.EVENTS"

data class CapturedEvent(val eventName: String, var payload: String?) : Serializable

class MainActivity : AppCompatActivity(), PinwheelEventListener {
    val capturedEvents = mutableListOf<CapturedEvent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pinwheel_fragment)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val webView = findViewById<WebView>(R.id.webView)
        val token = "your token from https://developer.getpinwheel.com/test-console"
        Pinwheel.init(webView, token, this)
    }

    fun showEvents() {
        runOnUiThread {
            val intent = Intent(this@MainActivity, EventsView::class.java).apply {
                putExtra(EVENTS_MESSAGE, capturedEvents as Serializable)
            }
            startActivity(intent)
        }
    }

    override fun onEvent(eventName: PinwheelEventType, payload: PinwheelEventPayload?) {
        print("onEvent")
        print(eventName.toString())
        capturedEvents.add(CapturedEvent(eventName.toString(), payload.toString()))
    }

    override fun onError(error: PinwheelError) {
        print("onError")
        print(error.toString())
    }

    override fun onExit(error: PinwheelError?) {
        print("onExit")
        print(error.toString())
        showEvents()
    }

    override fun onLogin(result: PinwheelLoginPayload) {
        print("onLogin")
        print(result.toString())
    }

    override fun onSuccess(result: PinwheelResult) {
        print("onSuccess")
        print(result.toString())
    }
}