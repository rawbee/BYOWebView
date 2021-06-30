package com.example.byowebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.underdog_tech.pinwheel_android.Pinwheel
import com.underdog_tech.pinwheel_android.PinwheelEventListener
import com.underdog_tech.pinwheel_android.model.*

class Listener : PinwheelEventListener {
    override fun onEvent(eventName: PinwheelEventType, payload: PinwheelEventPayload?) {
        print("onEvent")
        print(eventName.toString())
    }

    override fun onError(error: PinwheelError) {
        print("onError")
        print(error.toString())
    }

    override fun onExit(error: PinwheelError?) {
        print("onExit")
        print(error.toString())
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

class MainActivity : AppCompatActivity() {
    val listener = Listener()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pinwheel_fragment)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val webView = findViewById<WebView>(R.id.webView)
        val token = "your token from https://developer.getpinwheel.com/test-console"
        Pinwheel.init(webView, token, null)
    }
}