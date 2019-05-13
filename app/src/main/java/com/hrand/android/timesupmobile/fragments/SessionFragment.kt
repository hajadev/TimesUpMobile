package com.hrand.android.timesupmobile.fragments

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.activities.GameActivity
import kotlinx.android.synthetic.main.fragment_session.*


class SessionFragment : Fragment() {

    var startTime = 0L
    lateinit var customHandler: Handler
    var timeInMilliseconds = 0L
    var timeSwapBuff = 0L
    var updatedTime = 0L
    var timerCanRun = false
    lateinit var gameActivity: GameActivity
    //var currentIndex = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        gameActivity = this.activity as GameActivity
        //super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_session, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()

        tv_word_to_find.text = gameActivity.wordsList[gameActivity.currentIndexWord].value

        setListener()

        startTimer()

    }

    private fun setListener(){
        btn_found.setOnClickListener {
            gameActivity.wordFound()
            if(hasNextWord()){
                gameActivity.currentIndexWord++
                tv_word_to_find.text = gameActivity.wordsList[gameActivity.currentIndexWord].value
            }
            else{
                timerCanRun = false
                gameActivity.nextTeam()
                if(!gameActivity.nextSession()) // go to next session if we are not on the last session
                    gameActivity.endGame()
            }
        }

        btn_pass.setOnClickListener {
            timeSwapBuff += timeInMilliseconds
            customHandler.removeCallbacks(updateTimerThread)
        }
    }

    private fun startTimer(){

        startTime = 0L
        timeInMilliseconds = 0L
        timeSwapBuff = 0L
        updatedTime = 0L

        timerCanRun = true
        startTime = SystemClock.uptimeMillis()
        customHandler = Handler()
        customHandler.postDelayed(updateTimerThread2, 0)
    }

    private val updateTimerThread = object : Runnable {

        override fun run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime

            updatedTime = timeSwapBuff + timeInMilliseconds

            var secs = (updatedTime / 1000).toInt()
            val mins = secs / 60
            secs %= 60
            val milliseconds = (updatedTime % 1000).toInt()
            tv_timer.text = ("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds))

            if(timerCanRun) {
                customHandler.postDelayed(this, 0)
            }
        }

    }

    private val updateTimerThread2 = object : Runnable {

        override fun run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime

            updatedTime = timeSwapBuff + timeInMilliseconds

            var secs = (updatedTime / 1000).toInt()
            secs %= 60
            val timeToDisplay = gameActivity.duration - secs
            tv_timer.text = String.format("%02d", timeToDisplay)

            // The Fragment is Visible so the timer must go on
            if((timeToDisplay>0)&&(timerCanRun)) {
                customHandler.postDelayed(this, 0)
            }
            else{
                gameActivity.nextTeam()
                gameActivity.displayGameTeamActionFragment()
            }
        }

    }

    fun stopTimer(){
        timerCanRun = false
    }

    /**
     * If there is a word next return true
     * Else return false
     */
    private fun hasNextWord(): Boolean{
        if(gameActivity.currentIndexWord<(gameActivity.wordsList.size-1)){
            return true
        }
        return false
    }

    companion object {

        fun newInstance(): SessionFragment {
            return SessionFragment()
        }
    }
}