package com.hrand.android.timesupmobile.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.daos.ThemeDao
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.utils.initData

class OptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)
        initBtnListener()

        Log.i("haja", "On est dans le optionActivity")
    }

    private fun initBtnListener(){
        val btnBack = findViewById<Button>(R.id.btn_back)
        btnBack.setOnClickListener { _ -> this.finish() }

        val btnReset = findViewById<Button>(R.id.btn_reset_content)
        btnReset.setOnClickListener { _ ->
            WordDao.clearWords()
            ThemeDao.clearThemes()
            initData()
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
