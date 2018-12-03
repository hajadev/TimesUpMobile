package com.hrand.android.timesupmobile.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.models.Theme
import com.hrand.android.timesupmobile.models.Word
import com.hrand.android.timesupmobile.utils.*
import io.objectbox.Box
import io.objectbox.kotlin.boxFor

class MainActivity : AppCompatActivity() {

    private lateinit var themesBox: Box<Theme>
    private lateinit var wordsBox: Box<Word>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBtnListener()
    }

    private fun initBtnListener(){
        val btnExit = findViewById<Button>(R.id.btn_exit)
        btnExit.setOnClickListener { v -> this.finish() }

        val btnOption = findViewById<Button>(R.id.btn_option)
        btnOption.setOnClickListener {v ->
            val intent = OptionActivity.newIntent(this)
            startActivity(intent)
        }

        val btnLoadData = findViewById<Button>(R.id.btn_load_data)
        btnLoadData.setOnClickListener { v ->
            initDataBase()
        }
    }

    private fun initDataBase(){
        ObjectBox.build(this)
        themesBox = ObjectBox.boxStore.boxFor()
        wordsBox = ObjectBox.boxStore.boxFor()
        themesBox.put(initThemesData())
        wordsBox.put(initWordsData())
        Toast.makeText(this, "Données chargés", Toast.LENGTH_SHORT).show()
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
