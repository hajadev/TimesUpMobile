package com.hrand.android.timesupmobile.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.adapters.RecyclerAdapter
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.models.Word
import kotlinx.android.synthetic.main.activity_words_list.*



class WordsListActivity : AppCompatActivity(), RecyclerAdapter.ItemLongClickListener {

    private lateinit var linearLayoutManager: androidx.recyclerview.widget.LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter
    private lateinit var wordsList: ArrayList<Word>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words_list)

        linearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        // separation between items
        recyclerView.addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(this, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL))

        btn_back.setOnClickListener { this.finish() }

        fab.setOnClickListener { view ->
            val intent = AddWordActivity.newIntent(this)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        initAdapter()
        adapter.notifyDataSetChanged()
        recyclerView.adapter = adapter
    }

    override fun onItemLongClick(view: View, position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Menu")
        val choices = arrayOf("Modifier", "Supprimer")

        builder.setItems(choices) { _, i ->
            when(i){
                0 -> Toast.makeText(this, "Modification...", Toast.LENGTH_SHORT).show()
                1 -> {
                    // setup the delete confirmation alert builder
                    val suppressionChoiceBuilder = AlertDialog.Builder(this)
                    suppressionChoiceBuilder.setTitle("Confirmation de suppression")
                    suppressionChoiceBuilder.setMessage("Êtes-vous sûr de vouloir supprimer ${adapter.getElt(position).value} ?")

                    // add buttons
                    suppressionChoiceBuilder.setPositiveButton("Oui") { _, _ ->
                        // Suppression du mot en base
                        WordDao.deleteWord(adapter.getElt(position))
                        // suppression dans l'adapter
                        wordsList.remove(adapter.getElt(position))
                        adapter.setWordsList(wordsList)
                        adapter.notifyDataSetChanged()
                        //adapter.refreshData()
                    }
                    suppressionChoiceBuilder.setNegativeButton("Non", null)

                    // create and show dialog
                    val suppressionChoiceDialog = suppressionChoiceBuilder.create()
                    suppressionChoiceDialog.show()
                }
                else -> Toast.makeText(this, "other", Toast.LENGTH_SHORT).show()
            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun initAdapter(){
        // retrieve the list word to the adapter
        WordDao.init(this)
        wordsList = WordDao.getAllFilteredByName() as ArrayList<Word>
        adapter = RecyclerAdapter(wordsList)
        adapter.setOnLongClickListener(this)

    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, WordsListActivity::class.java)
            return intent
        }
    }

}
