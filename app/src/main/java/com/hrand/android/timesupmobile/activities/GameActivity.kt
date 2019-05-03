package com.hrand.android.timesupmobile.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hrand.android.timesupmobile.Fragments.GameTeamActionFragment
import com.hrand.android.timesupmobile.Fragments.SessionFragment
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.models.Word
import com.hrand.android.timesupmobile.utils.getExtra

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

        // Fragments initialization
        gameTeamActionFragment = GameTeamActionFragment.newInstance()
        sessionFragment = SessionFragment.newInstance()

        displayGameTeamActionFragment()

    }

    fun displayGameTeamActionFragment(){
        supportFragmentManager.beginTransaction().add(R.id.rl_fragment, gameTeamActionFragment, "gameTeamActionFragment").commit()
        sessionFragmentIsVisible = false
        gameTeamActionFragmentIsVisible = true
    }

    fun displaySessionFragment(){
        supportFragmentManager.beginTransaction().remove(gameTeamActionFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.rl_fragment, sessionFragment, "sessionFragment").commit()
        sessionFragmentIsVisible = true
        gameTeamActionFragmentIsVisible = false
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

        allWords.shuffled()
        for(i in 0..nbWord){
            listToReturn.add(allWords[i])
        }

        return listToReturn
    }

    fun nextTeam(){
        if(currentTeam==nbTeam){
            currentTeam = 1
        }
        else{
            currentTeam++
        }
    }

}