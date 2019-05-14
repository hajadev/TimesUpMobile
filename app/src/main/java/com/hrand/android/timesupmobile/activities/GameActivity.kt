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
    var currentIndexWord = 0
    lateinit var gameTeamActionFragment: GameTeamActionFragment
    lateinit var sessionFragment: SessionFragment

    var t1Points = 0
    var t2Points = 0
    var t3Points = 0
    var t4Points = 0

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
        if(!gameTeamActionFragmentIsVisible) {
            supportFragmentManager.beginTransaction().add(R.id.rl_fragment, gameTeamActionFragment, "gameTeamActionFragment").commit()
            gameTeamActionFragmentIsVisible = true
        }
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
            val wordsShuffled = allWords.shuffled()
            Log.d("haja", "Mélange des mots")
            for (i in 0..nbWord) {
                listToReturn.add(wordsShuffled[i])
            }
        }

        return listToReturn
    }

    fun nextTeam(){
        if(currentTeam==nbTeam){ // we are the last team
            Log.d("haja", "Reinitialisation team à 1")
            currentTeam = 1
        }
        else{
            Log.d("haja", "Incrémentation team")
            currentTeam++
        }
    }

    fun nextSession(): Boolean{
        if(currentSession<3){
            currentSession++
            currentIndexWord = 0
            displayGameTeamActionFragment()
            wordsList = wordsList.shuffled()
            return true
        }
        else{ // end of the game
            return false
        }
    }

    /**
     * Increment the point of the team who found the word
     */
    fun wordFound(){
        when(currentTeam){
            1 -> t1Points++
            2 -> t2Points++
            3 -> t3Points++
            4 -> t4Points++
            else -> {
                Log.e("haja", "Problème de comptage de points...")
            }
        }
    }

    /**
     * Display the winner
     * ToDo: Equality case
     */
    fun displayWinner(){
        var winner = 1
        if(t1Points<t2Points)
            winner = 2
        if(t2Points<t3Points)
            winner = 3
        if(t3Points<t4Points)
            winner = 4
        Toast.makeText(this, "La team $winner a gagné la partie !!! Bravo !", Toast.LENGTH_SHORT).show()
    }

    fun endGame(){
        displayWinner()
        this.finish()
    }

}