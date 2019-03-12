package com.hrand.android.timesupmobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.daos.ThemeDao
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.models.Theme
import com.hrand.android.timesupmobile.utils.*
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //private lateinit var themesBox: Box<Theme>
    //private lateinit var wordsBox: Box<Word>

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
        btn_exit.setOnClickListener { v -> this.finish() }

        btn_option.setOnClickListener {v ->
            val intent = OptionActivity.newIntent(this)
            startActivity(intent)
        }

        btn_load_data.setOnClickListener { v ->
            initDataBase()
        }

        btn_words_list.setOnClickListener { v ->
            val intent1 = WordsListActivity.newIntent(this)
            Log.i("haja", "listing des mots")
            startActivity(intent1) }
    }

    private fun initDataBase(){

        //themesBox = ObjectBox.boxStore.boxFor()

        /*val themes = initThemesData()
        themesBox.put(themes)*/

        ThemeDao.addThemes(initThemesData())
        WordDao.addWords(initWordsData())

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
