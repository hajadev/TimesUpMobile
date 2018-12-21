package com.hrand.android.timesupmobile.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.adapters.RecyclerAdapter
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.models.Word
import kotlinx.android.synthetic.main.activity_words_list.*
import com.hrand.android.timesupmobile.Fragments.FullScreenDialog



class WordsListActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: androidx.recyclerview.widget.LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction

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
            launchAddDialog()
        }

        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = fragmentManager.findFragmentById(R.id.fsd)
        if (fragment != null) {
            fragmentTransaction.hide(fragment)
            //fragmentTransaction.remove(fragment)
        }
        fragmentTransaction.commit()

        Log.i("haja", "On est dans le wordListActivity")

    }

    fun launchAddDialog(){
        fragmentTransaction = fragmentManager.beginTransaction()
        //val fragment = FullScreenDialog()
        //fragmentTransaction.add(R.id.fsd, fragment)
        fragmentTransaction.show(fsd)
        fragmentTransaction.commit()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, WordsListActivity::class.java)
            return intent
        }
    }

}
