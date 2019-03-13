package com.hrand.android.timesupmobile.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.adapters.RecyclerAdapter
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.models.Word
import kotlinx.android.synthetic.main.activity_words_list.*



class WordsListActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: androidx.recyclerview.widget.LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words_list)

        linearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        // separation between items
        recyclerView.addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(this, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL))

        btn_back.setOnClickListener { _ -> this.finish() }

        // retrieve the list word to the adapter
        WordDao.init(this)

        fab.setOnClickListener { view ->
            val intent = AddWordActivity.newIntent(this)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()

        adapter = RecyclerAdapter(WordDao.getAll() as ArrayList<Word>)
        recyclerView.adapter = adapter

    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, WordsListActivity::class.java)
            return intent
        }
    }

}
