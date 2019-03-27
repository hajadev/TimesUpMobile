package com.hrand.android.timesupmobile.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.activities.GameActivity

class SessionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val currentActivity = this.activity as GameActivity
        //super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_session, container, false)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    companion object {

        fun newInstance(): SessionFragment {
            return SessionFragment()
        }
    }
}