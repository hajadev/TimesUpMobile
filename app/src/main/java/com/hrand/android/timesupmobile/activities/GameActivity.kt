package com.hrand.android.timesupmobile.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hrand.android.timesupmobile.fragments.GameTeamActionFragment
import com.hrand.android.timesupmobile.fragments.SessionFragment
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.models.Word

class GameActivity : AppCompatActivity() {

    var difficulty = 2
    var duration = 5
    var nbTeam = 2
    var currentSession = 1
    var currentTeam = 1
    var gameTeamActionFragmentIsVisible = false
    var sessionFragmentIsVisible = false
    lateinit var gameTeamActionFragment: GameTeamActionFragment
    lateinit var sessionFragment: SessionFragment

    lateinit var wordsList: List<Word>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val bundle: Bundle? = intent.extras

        if (bundle != null) {
            difficulty = bundle.getInt("difficulty", 2)
            duration = bundle.getInt("duration", 10)
            Log.d("haja", "duration=$duration")
            nbTeam = bundle.getInt("nbTeam", 2)
        }else{
            Log.d("haja", "savedInstanceState est null!!!")
        }

        wordsList = initWords(8)

        if(!wordsList.isEmpty()) {
            // Fragments initialization
            gameTeamActionFragment = GameTeamActionFragment.newInstance()
            sessionFragment = SessionFragment.newInstance()
            displayGameTeamActionFragment()
        }
        else{
            Toast.makeText(this, "Base de mots non initialisé.", Toast.LENGTH_SHORT).show()
            this.finish()
        }

    }

    fun displayGameTeamActionFragment(){
        if(sessionFragmentIsVisible){
            supportFragmentManager.beginTransaction().remove(sessionFragment).commit()
            sessionFragmentIsVisible = false
        }
        supportFragmentManager.beginTransaction().add(R.id.rl_fragment, gameTeamActionFragment, "gameTeamActionFragment").commit()
        gameTeamActionFragmentIsVisible = true
    }

    fun displaySessionFragment(){
        if(gameTeamActionFragmentIsVisible) {
            supportFragmentManager.beginTransaction().remove(gameTeamActionFragment).commit()
            gameTeamActionFragmentIsVisible = false
        }
        supportFragmentManager.beginTransaction().add(R.id.rl_fragment, sessionFragment, "sessionFragment").commit()
        sessionFragmentIsVisible = true
    }

    fun closeSessionFragment(){
        supportFragmentManager.beginTransaction().remove(sessionFragment).commit()
        sessionFragmentIsVisible = false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(sessionFragmentIsVisible){
            sessionFragment.stopTimer()
            closeSessionFragment()
        }
    }

    private fun initWords(nbWord: Int) : List<Word> {
        val listToReturn = ArrayList<Word>()
        val allWords  = WordDao.getAll()

        if(!allWords.isEmpty()) {
            allWords.shuffled()
            for (i in 0..nbWord) {
                listToReturn.add(allWords[i])
            }
        }

        return listToReturn
    }

    fun nextTeam(){
        if(currentTeam==nbTeam){ // we are the last team
            currentTeam = 1
        }
        else{
            currentTeam++
        }
    }

    fun nextSession(): Boolean{
        if(currentSession<3){
            currentSession++
            displayGameTeamActionFragment()
            return true
        }
        else{ // end of the game
            return false
        }
    }

    fun endGame(){
        this.finish()
    }

}