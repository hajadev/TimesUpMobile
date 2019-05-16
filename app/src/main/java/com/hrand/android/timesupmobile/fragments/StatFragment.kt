package com.hrand.android.timesupmobile.fragments

import android.graphics.Color
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
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size


class StatFragment : Fragment() {

    lateinit var gameActivity: GameActivity
    var widthGlobal = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        gameActivity = this.activity as GameActivity
        val view = inflater.inflate(R.layout.fragment_stat, container, false)
        widthGlobal = view.width

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
        viewKonfetti.build()
                .addColors(Color.YELLOW, Color.LTGRAY/*, Color.GREEN, Color.MAGENTA*/)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(12))
                .setPosition(0f, rl_stat_fragment.width + 50f, -50f, -50f)
                .streamFor(100, 5000L)
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

    companion object {

        fun newInstance(): StatFragment {
            return StatFragment()
        }
    }

}