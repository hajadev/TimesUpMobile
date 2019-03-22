package com.hrand.android.timesupmobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.daos.ThemeDao
import com.hrand.android.timesupmobile.daos.WordDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    init {
        ThemeDao.init(this)
        WordDao.init(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBtnListener()
    }

    private fun initBtnListener(){
        btn_new_game.setOnClickListener {
            val intent = NewGameActivity.newIntent(this)
            startActivity(intent)
        }

        btn_exit.setOnClickListener { v -> this.finish() }

        btn_option.setOnClickListener {v ->
            val intent = OptionActivity.newIntent(this)
            startActivity(intent)
        }

        btn_words_list.setOnClickListener { v ->
            val intent = WordsListActivity.newIntent(this)
            startActivity(intent) }

        btn_rules.setOnClickListener {
            val intent = RulesActivity.newIntent(this)
            startActivity(intent)
        }

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
