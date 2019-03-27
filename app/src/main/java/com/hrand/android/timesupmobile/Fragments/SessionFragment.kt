package com.hrand.android.timesupmobile.Fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.activities.GameActivity
import kotlinx.android.synthetic.main.fragment_session.*
import androidx.core.os.HandlerCompat.postDelayed




class SessionFragment : Fragment() {

    var startTime = 0L
    lateinit var customHandler: Handler
    var timeInMilliseconds = 0L
    var timeSwapBuff = 0L
    var updatedTime = 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val currentActivity = this.activity as GameActivity
        //super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_session, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()

        btn_found.setOnClickListener {
            startTimer(30)
        }

        btn_pass.setOnClickListener {
            timeSwapBuff += timeInMilliseconds
            customHandler.removeCallbacks(updateTimerThread)
        }

    }

    private fun startTimer(duration: Int){
        startTime = SystemClock.uptimeMillis()
        customHandler = Handler()
        customHandler.postDelayed(updateTimerThread, 0)
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
            customHandler.postDelayed(this, 0)
        }

    }

    companion object {

        fun newInstance(): SessionFragment {
            return SessionFragment()
        }
    }
}