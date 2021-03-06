package com.hrand.android.timesupmobile.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.utils.gotoActivity
import kotlinx.android.synthetic.main.activity_new_game.*

class NewGameActivity : AppCompatActivity() {

    var difficulty = 2
    var duration = 30
    var nbTeam = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        listenerInitialization()

    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, NewGameActivity::class.java)
            return intent
        }
    }

    private fun listenerInitialization(){

        /* Cancel Button */
        btn_back.setOnClickListener {
            this.finish()
        }

        /* Difficulty Listeners */

        btnEasy.setOnClickListener {
            btnEasy.setImageResource(R.drawable.badge_facile_selected)
            btnMedium.setImageResource(R.drawable.badge_normal)
            btnHard.setImageResource(R.drawable.badge_difficile)
            btnExpert.setImageResource(R.drawable.badge_expert)

            difficulty = 1
        }

        btnMedium.setOnClickListener {
            btnEasy.setImageResource(R.drawable.badge_facile)
            btnMedium.setImageResource(R.drawable.badge_normal_selected)
            btnHard.setImageResource(R.drawable.badge_difficile)
            btnExpert.setImageResource(R.drawable.badge_expert)

            difficulty = 2
        }

        btnHard.setOnClickListener {
            btnEasy.setImageResource(R.drawable.badge_facile)
            btnMedium.setImageResource(R.drawable.badge_normal)
            btnHard.setImageResource(R.drawable.badge_difficile_selected)
            btnExpert.setImageResource(R.drawable.badge_expert)

            difficulty = 3
        }

        btnExpert.setOnClickListener {
            btnEasy.setImageResource(R.drawable.badge_facile)
            btnMedium.setImageResource(R.drawable.badge_normal)
            btnHard.setImageResource(R.drawable.badge_difficile)
            btnExpert.setImageResource(R.drawable.badge_expert_selected)

            difficulty = 4
        }

        /* Duration Listeners */

        tv30.setOnClickListener {
            tv30.setTextColor(ContextCompat.getColor(this, R.color.colorGreen))
            tv40.setTextColor(ContextCompat.getColor(this, R.color.colorPurple))
            tv50.setTextColor(ContextCompat.getColor(this, R.color.colorPurple))

            duration = 30
        }
        tv40.setOnClickListener {
            tv30.setTextColor(ContextCompat.getColor(this, R.color.colorPurple))
            tv40.setTextColor(ContextCompat.getColor(this, R.color.colorGreen))
            tv50.setTextColor(ContextCompat.getColor(this, R.color.colorPurple))

            duration = 40
        }
        tv50.setOnClickListener {
            tv30.setTextColor(ContextCompat.getColor(this, R.color.colorPurple))
            tv40.setTextColor(ContextCompat.getColor(this, R.color.colorPurple))
            tv50.setTextColor(ContextCompat.getColor(this, R.color.colorGreen))

            duration = 50
        }

        btn2player.setOnClickListener{
            nbTeam = 2
            btn2player.setBackgroundResource(R.drawable.selected_nb_player)
            btn3player.setBackgroundResource(R.drawable.select_nb_player)
            btn4player.setBackgroundResource(R.drawable.select_nb_player)
        }

        btn3player.setOnClickListener{
            nbTeam = 3
            btn2player.setBackgroundResource(R.drawable.select_nb_player)
            btn3player.setBackgroundResource(R.drawable.selected_nb_player)
            btn4player.setBackgroundResource(R.drawable.select_nb_player)
        }

        btn4player.setOnClickListener{
            nbTeam = 4
            btn2player.setBackgroundResource(R.drawable.select_nb_player)
            btn3player.setBackgroundResource(R.drawable.select_nb_player)
            btn4player.setBackgroundResource(R.drawable.selected_nb_player)
        }

        btn_validate.setOnClickListener {
            /*val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("difficulty", difficulty)
            intent.putExtra("duration", duration)
            intent.putExtra("nbTeam", nbTeam)
            startActivity(intent)*/
            gotoActivity(GameActivity::class, extras = mapOf("difficulty" to difficulty, "duration" to duration, "nbTeam" to nbTeam))
        }

    }

}