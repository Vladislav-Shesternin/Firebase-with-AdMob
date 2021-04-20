package com.example.firebaseadmob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaseadmob.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()

        binding.btnShowBanner.setOnClickListener {
            binding.adBannerBottom.apply {
                loadAd(adRequest)
                adListener = Ddd()
            }
        }

        binding.btnShowInterstitial.setOnClickListener {

        }
    }
}

class Ddd : AdListener() {
    private val TAG = this::class.simpleName

    override fun onAdClosed() {
        super.onAdClosed()
        Log.i(TAG, "onAdClosed: ")
    }

    override fun onAdFailedToLoad(p0: LoadAdError) {
        super.onAdFailedToLoad(p0)
        Log.i(TAG, "onAdFailedToLoad: ")
    }

    override fun onAdOpened() {
        super.onAdOpened()
        Log.i(TAG, "onAdOpened: ")
    }

    override fun onAdLoaded() {
        super.onAdLoaded()
        Log.i(TAG, "onAdLoaded: ")
    }

    override fun onAdClicked() {
        super.onAdClicked()
        Log.i(TAG, "onAdClicked: ")
    }

    override fun onAdImpression() {
        super.onAdImpression()
        Log.i(TAG, "onAdImpression: ")
    }
}