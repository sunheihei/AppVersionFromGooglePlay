package com.example.appversiongoogleplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.appversionlib.AppVersionOnGooglePlayUtils

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val packName = "com.zhiliaoapp.musically"

        AppVersionOnGooglePlayUtils(this, packName).getVersionOnGooglePlay {
                Log.d(TAG, "version is $it")
        }

    }
}