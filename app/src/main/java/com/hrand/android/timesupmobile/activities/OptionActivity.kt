package com.hrand.android.timesupmobile.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.hrand.android.timesupmobile.R

class OptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)
        initBtnListener()
    }

    private fun initBtnListener(){
        val btnBack = findViewById<Button>(R.id.btn_back)
        btnBack.setOnClickListener { _ -> this.finish() }

        val btnReset = findViewById<Button>(R.id.btn_reset_content)
        btnReset.setOnClickListener { _ ->
            Toast.makeText(this, R.string.content_reset, Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, OptionActivity::class.java)
            return intent
        }
    }

}
