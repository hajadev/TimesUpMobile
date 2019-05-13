package com.hrand.android.timesupmobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.activities.GameActivity
import kotlinx.android.synthetic.main.fragment_game_team_action.*

class GameTeamActionFragment : Fragment() {

    lateinit var currentActivity: GameActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_game_team_action, container, false)
        return view
    }

    private fun afterViewsCreated(){
        currentActivity = this.activity as GameActivity

        val sessionNumber = getString(R.string.session) + " " + currentActivity.currentSession

        currentActivity.findViewById<TextView>(R.id.tv_session_number).text = sessionNumber

        // Description of the session
        when(currentActivity.currentSession){
            1 -> currentActivity.findViewById<TextView>(R.id.tv_session_desc).text = getString(R.string.describe_the_word)
            2 -> currentActivity.findViewById<TextView>(R.id.tv_session_desc).text = getString(R.string.word_to_word)
            3 -> currentActivity.findViewById<TextView>(R.id.tv_session_desc).text = getString(R.string.mimic_the_word)
            else -> currentActivity.findViewById<TextView>(R.id.tv_session_desc).text = getString(R.string.error)
        }

        tv_team.text = "Team ${currentActivity.currentTeam}"

        btn_play.setOnClickListener {
            currentActivity.displaySessionFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        afterViewsCreated()
    }

    companion object {

        fun newInstance(): GameTeamActionFragment {
            return GameTeamActionFragment()
        }
    }
}