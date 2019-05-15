package com.hrand.android.timesupmobile.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.activities.GameActivity
import kotlinx.android.synthetic.main.fragment_stat.*
import android.view.animation.AnimationUtils.loadAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils


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
        tv_winner.text = "L'équipe ${gameActivity.winner} a gagné !!!"
        setListener()
        animate()
        /*
        animate1()
        animate2()
        animate3()
        */
    }

    private fun animate(){
        val animation1 = AnimationUtils.loadAnimation(gameActivity, R.anim.my_animation)
        val animation2 = AnimationUtils.loadAnimation(gameActivity, R.anim.my_animation)
        val animation3 = AnimationUtils.loadAnimation(gameActivity, R.anim.my_animation)
        star_1.startAnimation(animation1)
        animation2.startOffset=500
        star_2.startAnimation(animation2)
        animation3.startOffset=1000
        star_3.startAnimation(animation3)
    }

    private fun animate1(){
        //UI thread
        Thread(Runnable {
            gameActivity.runOnUiThread(java.lang.Runnable {
                val animation1 = AnimationUtils.loadAnimation(gameActivity, R.anim.my_animation)
                star_1.startAnimation(animation1)
                Thread.sleep(1000)
            })
        }).start()
    }
    private fun animate2(){
        //UI thread
        Thread(Runnable {
            gameActivity.runOnUiThread(java.lang.Runnable {
                val animation1 = AnimationUtils.loadAnimation(gameActivity, R.anim.my_animation)
                star_2.startAnimation(animation1)
                Thread.sleep(1000)
            })
        }).start()
    }
    private fun animate3(){
        //UI thread
        Thread(Runnable {
            gameActivity.runOnUiThread(java.lang.Runnable {
                val animation1 = AnimationUtils.loadAnimation(gameActivity, R.anim.my_animation)
                star_3.startAnimation(animation1)
                Thread.sleep(1000)
            })
        }).start()
    }

    companion object {

        fun newInstance(): StatFragment {
            return StatFragment()
        }
    }

}