package com.hrand.android.timesupmobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.activities.GameActivity
import kotlinx.android.synthetic.main.fragment_stat.*

class StatFragment : Fragment() {

    lateinit var gameActivity: GameActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        gameActivity = this.activity as GameActivity
        val view = inflater.inflate(R.layout.fragment_stat, container, false)

        return view
    }

    private fun setListener(){
        btn_exit.setOnClickListener {
            gameActivity.finish()
        }
    }

    override fun onStart() {
        super.onStart()
        setListener()
    }

    companion object {

        fun newInstance(): StatFragment {
            return StatFragment()
        }
    }

}