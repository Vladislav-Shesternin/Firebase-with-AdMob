package com.example.firebaseadmob

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaseadmob.databinding.ActivityMainBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

const val TAG = "VLAD"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adRequest: AdRequest
    private var interstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this)
        adRequest = AdRequest.Builder().build()

        val initInterstitialAd: (InterstitialAd?) -> Unit = { interstitialAd = it }
        loadInterstitialAd(this, adRequest, initInterstitialAd)

        binding.btnShowBanner.setOnClickListener {
            binding.adBannerBottom.apply {
                loadAd(adRequest)
                adListener = Ddd()
            }
        }

        binding.btnShowInterstitial.setOnClickListener {
            loadInterstitialAd(this, adRequest, initInterstitialAd)
            interstitialAd?.apply {
                show(this@MainActivity)
                fullScreenContentCallback = object : FullScreenContentCallback(){

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        super.onAdFailedToShowFullScreenContent(p0)
                        Log.i(TAG, "onAdFailedToShowFullScreenContent: ")
                    }

                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()
                        Log.i(TAG, "onAdShowedFullScreenContent: ")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        Log.i(TAG, "onAdDismissedFullScreenContent: ")
                    }

                    override fun onAdImpression() {
                        super.onAdImpression()
                        Log.i(TAG, "onAdImpression: ")
                    }
                }
            }
        }
    }

    private fun loadInterstitialAd(
        context: Context,
        adRequest: AdRequest,
        block: (InterstitialAd?) -> Unit
    ) {

        InterstitialAd.load(
            context,
            context.getString(R.string.interstitial_ad_unit_id),
            adRequest,
            Mmm(block)
        )
    }
}

class Mmm(private val block: (InterstitialAd?) -> Unit) : InterstitialAdLoadCallback() {

    override fun onAdLoaded(interstitialAd: InterstitialAd) {
        super.onAdLoaded(interstitialAd)
        Log.i(TAG, "onAdLoaded: ")
        block(interstitialAd)
    }

    override fun onAdFailedToLoad(adError: LoadAdError) {
        super.onAdFailedToLoad(adError)
        Log.i(TAG, "onAdFailedToLoad: ")
        block(null)
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