package com.hrand.android.timesupmobile.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.hrand.android.timesupmobile.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBtnListener()
    }

    private fun initBtnListener(){
        val btnExit = findViewById<Button>(R.id.btn_exit)
        btnExit.setOnClickListener { v -> this.finish() }

        val btnOption = findViewById<Button>(R.id.btn_option)
        btnOption.setOnClickListener {v ->
            val intent = OptionActivity.newIntent(this)
            startActivity(intent)
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
