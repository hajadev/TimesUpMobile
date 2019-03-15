package com.hrand.android.timesupmobile.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hrand.android.timesupmobile.R
import kotlinx.android.synthetic.main.activity_new_game.*

class NewGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        btn_back.setOnClickListener {
            this.finish()
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, NewGameActivity::class.java)
            return intent
        }
    }

    fun difficulty_initialization(){

    }

}