package com.hrand.android.timesupmobile.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.adapters.RecyclerAdapter
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.models.Word
import kotlinx.android.synthetic.main.activity_words_list.*

class WordsListActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    //private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words_list)

        //recyclerView = findViewById(R.id.rv_words)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        // separation between items
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        btn_back.setOnClickListener { _ -> this.finish() }

        // retrieve the list word to the adapter
        WordDao.init(this)



        adapter = RecyclerAdapter(WordDao.getAll() as ArrayList<Word>)
        recyclerView.adapter = adapter

        Log.i("haja", "On est dans le wordListActivity")

    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, WordsListActivity::class.java)
            return intent
        }
    }

}
