package com.example.week7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class WebviewActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        
        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()

        // Cho phép JavaScript trong WebView
        webView.settings.javaScriptEnabled = true

        // Tải trang web vào WebView
        webView.loadUrl("https://www.google.com")
    }

    override fun onBackPressed() {
        // Kiểm tra xem WebView có lịch sử trang web trước đó không
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}