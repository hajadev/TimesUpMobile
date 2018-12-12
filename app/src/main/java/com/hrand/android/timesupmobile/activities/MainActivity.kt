package com.hrand.android.timesupmobile.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.models.Theme
import com.hrand.android.timesupmobile.models.Theme_
import com.hrand.android.timesupmobile.models.Word
import com.hrand.android.timesupmobile.utils.*
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var themesBox: Box<Theme>
    //private lateinit var wordsBox: Box<Word>

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
        //ObjectBox.build(this)
        themesBox = ObjectBox.boxStore.boxFor()
        //wordsBox = ObjectBox.boxStore.boxFor()

        val themes = initThemesData()
        /*Log.i("haja", "listes des themes (${themes.size}) a insérer")
        for (t in themes){
            Log.i("haja", t.value)
        }*/
        themesBox.put(themes)
        //wordsBox.put(initWordsData())
        WordDao.init(this)

        val listToInsert = initWordsData()
        Log.i("haja", "listes des mots (${listToInsert.size}) à insérer")
        for (w in listToInsert ){
            Log.i("haja", "${w.value}")
        }
        WordDao.addWords(listToInsert)
        val listeW: List<Word> = WordDao.getAll()
        Log.i("haja", "listes des mots (${listeW.size}) en base")
        for (w in listeW ){
            Log.i("haja", "${w.id} / ${w.value}")
        }

        val listeTheme = themesBox.query{order(Theme_.id)}.find()
        /*Log.i("haja", "listes des themes (${listeTheme.size}) en base")
        for (t in listeTheme){
            Log.i("haja", t.value)
        }*/

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
