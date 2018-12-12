package com.hrand.android.timesupmobile

import android.app.Application
import android.util.Log
import com.hrand.android.timesupmobile.utils.ObjectBox

class App : Application() {

    companion object Constants {
        const val TAG = "ObjectBoxExample"
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("haja", "Winter is comming!!!")
        ObjectBox.build(this)
    }

}