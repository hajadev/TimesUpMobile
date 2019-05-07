package com.hrand.android.timesupmobile.Fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.activities.GameActivity
import kotlinx.android.synthetic.main.fragment_session.*
import androidx.core.os.HandlerCompat.postDelayed
import com.hrand.android.timesupmobile.models.Word


class SessionFragment : Fragment() {

    var startTime = 0L
    lateinit var customHandler: Handler
    var timeInMilliseconds = 0L
    var timeSwapBuff = 0L
    var updatedTime = 0L
    var timerCanRun = false
    lateinit var currentActivity: GameActivity
    var currentIndex = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        currentActivity = this.activity as GameActivity
        //super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_session, container, false)
        Log.d("haja", "SessionFragment.onCreateView()")

        return view
    }

    override fun onStart() {
        super.onStart()

        tv_word_to_find.text = currentActivity.wordsList[currentIndex].value

        btn_found.setOnClickListener {
            if(hasNextWord()){
                currentIndex++
                tv_word_to_find.text = currentActivity.wordsList[currentIndex].value
            }
            else{
                timerCanRun = false
                currentActivity.nextTeam()
                if(!currentActivity.nextSession()) // go to next session if we are not on the last session
                    currentActivity.endGame()
            }
        }

        btn_pass.setOnClickListener {
            timeSwapBuff += timeInMilliseconds
            customHandler.removeCallbacks(updateTimerThread)
        }

        startTimer()

    }

    private fun startTimer(){
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
            val timeToDisplay = currentActivity.duration - secs
            tv_timer.text = String.format("%02d", timeToDisplay)

            // The Fragment is Visible so the timer must go on
            if((timeToDisplay>0)&&(timerCanRun)) {
                customHandler.postDelayed(this, 0)
            }
            else{
                currentActivity.nextTeam()
                currentActivity.displayGameTeamActionFragment()
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
        if(currentIndex<(currentActivity.wordsList.size-1)){
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