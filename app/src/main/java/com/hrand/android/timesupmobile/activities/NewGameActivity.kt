package com.hrand.android.timesupmobile.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hrand.android.timesupmobile.R
import kotlinx.android.synthetic.main.activity_new_game.*

class NewGameActivity : AppCompatActivity() {

    var difficulty = 2
    var duration = 30


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        btn_back.setOnClickListener {
            this.finish()
        }

        btnEasy.setOnClickListener {
            btnEasy.setImageResource(R.drawable.badge_facile_selected)
            btnMedium.setImageResource(R.drawable.badge_normal)
            btnHard.setImageResource(R.drawable.badge_difficile)
            btnExpert.setImageResource(R.drawable.badge_expert)
        }

        btnMedium.setOnClickListener {
            btnEasy.setImageResource(R.drawable.badge_facile)
            btnMedium.setImageResource(R.drawable.badge_normal_selected)
            btnHard.setImageResource(R.drawable.badge_difficile)
            btnExpert.setImageResource(R.drawable.badge_expert)
        }

        btnHard.setOnClickListener {
            btnEasy.setImageResource(R.drawable.badge_facile)
            btnMedium.setImageResource(R.drawable.badge_normal)
            btnHard.setImageResource(R.drawable.badge_difficile_selected)
            btnExpert.setImageResource(R.drawable.badge_expert)
        }

        btnExpert.setOnClickListener {
            btnEasy.setImageResource(R.drawable.badge_facile)
            btnMedium.setImageResource(R.drawable.badge_normal)
            btnHard.setImageResource(R.drawable.badge_difficile)
            btnExpert.setImageResource(R.drawable.badge_expert_selected)
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