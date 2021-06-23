package com.example.byowebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.underdog_tech.pinwheel_android.Pinwheel

class MainActivity : AppCompatActivity() {
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