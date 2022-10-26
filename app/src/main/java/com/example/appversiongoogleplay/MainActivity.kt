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




//        val packName = "com.facebook.orca"
        val packName = "com.facebook.orca"

        AppVersionOnGooglePlayUtils(this, packName).getVersionOnGooglePlay {
                Log.d(TAG, "version is $it")
                    //version is 370.0.0.14.108
        }

    }
}