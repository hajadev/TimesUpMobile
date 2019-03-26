package com.hrand.android.timesupmobile.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hrand.android.timesupmobile.R

class GameActivity : AppCompatActivity() {

    var difficulty = 2
    var duration = 30
    var nbTeam = 2
    var currentSession = 1
    var currentTeam = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        if (savedInstanceState != null) {
            difficulty = savedInstanceState.getInt("difficulty", 2)
            duration = savedInstanceState.getInt("duration", 30)
            nbTeam = savedInstanceState.getInt("nbTeam", 2)
        }

    }



}