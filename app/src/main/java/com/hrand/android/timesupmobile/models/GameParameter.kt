package com.hrand.android.timesupmobile.models

class GameParameter{

    companion object{
        var difficulty = 2
        var duration = 5
        var nbTeam = 2
        var currentSession = 1
        var currentTeam = 1
        var gameTeamActionFragmentIsVisible = false
        var sessionFragmentIsVisible = false
        var statFragmentIsVisible = false
        var gameStarted = false

        fun initParameters(){
            difficulty = 2
            duration = 5
            nbTeam = 2
            currentSession = 1
            currentTeam = 1
            gameTeamActionFragmentIsVisible = false
            sessionFragmentIsVisible = false
            statFragmentIsVisible = false
            gameStarted = false
        }
    }

}