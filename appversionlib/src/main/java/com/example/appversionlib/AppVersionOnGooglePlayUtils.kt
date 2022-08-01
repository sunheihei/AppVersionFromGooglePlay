package com.example.appversionlib

import android.content.Context
import android.content.pm.PackageInfo
import android.util.Log
import okhttp3.*

import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

class AppVersionOnGooglePlayUtils(
    private val context: Context,
    private val packName: String,
    private val defaultErrorVersion: String? = "0",

    ) {


    fun getVersionOnGooglePlay(versionCallback: (version: String) -> Unit) {

        val url =
            "https://play.google.com/store/apps/details?id=$packName&hl=en"


        val client = OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build()
        val request =
            Request.Builder().url(url).build()

        val call = client.newCall(request)
        call.enqueue(
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    versionCallback.invoke(defaultErrorVersion!!)
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val str = response.body!!.string()
                        val version = parseVersion(str)
//                        Log.d("MainActivity", "version： $version")
                        if (version == defaultErrorVersion) {
                            versionCallback.invoke(defaultErrorVersion!!)
                        } else {
                            versionCallback.invoke(version!!)
                        }

                    } else {
                        versionCallback.invoke(defaultErrorVersion!!)
                    }
                }
            })
    }

    private fun parseVersion(string: String): String? {

//        val pattern1 = "\\[\\[\\[\\\".*\\\"\\]\\]"
//        val pattern2 = "\\\".*\\\""

//        val pattern1 = "\\[\\[\\[\\\"\\d+\\.\\d+\\.\\d+\\\"\\]\\]"
        val pattern1 = "\\[\\[\\[\\\"[\\d.]*\"\\]\\]"
        val pattern2 = "\\\"[\\d.]*\\\""
        val r1 = Pattern.compile(pattern1)
        val m1 = r1.matcher(string)
        if (m1.find()) {
            val str2 = m1.group(0)
            Log.d("MainActivity", "str")
            val r2 = Pattern.compile(pattern2)
            val m2 = r2.matcher(str2)
            if (m2.find()) {
                return m2.group(0).replace("\"", "")
            }
        }
        return defaultErrorVersion
    }


//    private fun googlePlayVersionCompare() {
//        if (versionOnline == "0")
//            return
//        val newVOnline = versionOnline.replace(".", "").toInt()
//        val newVLocal = getLocalVersionName().replace(".", "").toInt()
////        Log.d("VERSION233", "newVOnline : $newVOnline")
////        Log.d("VERSION233", "newVLocal : ${newVLocal}")
//    }

    fun getLocalVersionName(): String {
        val packageInfo: PackageInfo = context.packageManager
            .getPackageInfo(context.packageName, 0)
        return packageInfo.versionName

//        return BuildConfig.VERSION_NAME
    }

}