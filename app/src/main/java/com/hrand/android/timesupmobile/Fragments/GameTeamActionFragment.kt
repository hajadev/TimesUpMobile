package com.hrand.android.timesupmobile.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.activities.GameActivity

class GameTeamActionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val currentActivity = this.activity as GameActivity
        //super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_game_team_action, container, false)
        val sessionNumber = getString(R.string.session) + " " + currentActivity.currentSession

        view .findViewById<TextView>(R.id.tv_session_number).text = sessionNumber

        // Description of the session
        when(currentActivity.currentSession){
            1 -> view.findViewById<TextView>(R.id.tv_session_desc).text = getString(R.string.describe_the_word)
            2 -> view.findViewById<TextView>(R.id.tv_session_desc).text = getString(R.string.word_to_word)
            3 -> view.findViewById<TextView>(R.id.tv_session_desc).text = getString(R.string.mimic_the_word)
            else -> view.findViewById<TextView>(R.id.tv_session_desc).text = getString(R.string.error)
        }

        return view
    }

    companion object {

        fun newInstance(): GameTeamActionFragment {
            return GameTeamActionFragment()
        }
    }
}