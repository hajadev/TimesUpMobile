package com.hrand.android.timesupmobile.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.adapters.RecyclerAdapter
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.models.Word
import kotlinx.android.synthetic.main.activity_words_list.*

class WordsListActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: androidx.recyclerview.widget.LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    //private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words_list)

        //recyclerView = findViewById(R.id.rv_words)
        linearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        // separation between items
        recyclerView.addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(this, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL))

        btn_back.setOnClickListener { _ -> this.finish() }

        // retrieve the list word to the adapter
        WordDao.init(this)

        adapter = RecyclerAdapter(WordDao.getAll() as ArrayList<Word>)
        recyclerView.adapter = adapter

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
        }

        Log.i("haja", "On est dans le wordListActivity")

    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, WordsListActivity::class.java)
            return intent
        }
    }

}
