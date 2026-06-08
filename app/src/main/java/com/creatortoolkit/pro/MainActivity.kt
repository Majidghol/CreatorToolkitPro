package com.creatortoolkit.pro

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private var interstitialAd: InterstitialAd? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this)

        loadAd()

        webView = WebView(this)
        setContentView(webView)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        webView.loadUrl("file:///android_asset/index.html")
    }

    private fun loadAd() {
        val request = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            "ca-app-pub-XXXXXXXXXXXXXXXX/XXXXXXXXXX",
            request,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                }
            }
        )
    }

    override fun onBackPressed() {
        if (interstitialAd != null) {
            interstitialAd?.show(this)
        }
        super.onBackPressed()
    }
}
