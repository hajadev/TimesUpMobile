package com.hrand.android.timesupmobile.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.models.Word

class GameActivity : AppCompatActivity() {

    var difficulty = 2
    var duration = 30
    var nbTeam = 2
    var currentSession = 1
    var currentTeam = 1

    lateinit var wordsList: List<Word>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        if (savedInstanceState != null) {
            difficulty = savedInstanceState.getInt("difficulty", 2)
            duration = savedInstanceState.getInt("duration", 30)
            nbTeam = savedInstanceState.getInt("nbTeam", 2)
        }

        wordsList = initWords(8)

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


}