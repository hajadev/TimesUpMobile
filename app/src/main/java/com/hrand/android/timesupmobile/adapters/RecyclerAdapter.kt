package com.hrand.android.timesupmobile.adapters

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.models.Word
import com.hrand.android.timesupmobile.utils.inflate
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*


class RecyclerAdapter(private val words: ArrayList<Word>)  : RecyclerView.Adapter<RecyclerAdapter.WordHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.WordHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return WordHolder(inflatedView)

    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.WordHolder, position: Int) {
        holder.bindWord(words[position])
    }

    class WordHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        //2
        private var view: View = v
        private var word: Word? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        fun bindWord(wordToBind : Word){
            word = wordToBind
            val word_id = wordToBind.id
            val word_difficulty = wordToBind.difficulty
            val word_value = wordToBind.value

            view.tv_id.text = word_id.toString()
            view.tv_difficulty.text = word_difficulty.toString()
            view.tv_value.text = word_value
        }

        //4
        override fun onClick(v: View) {
            Log.d("haja", "CLICK!")
            Toast.makeText(v.context, "Value = ${word?.value}", Toast.LENGTH_SHORT).show()
        }

        companion object {
            //5
            private val WORD_KEY = "WORD"
        }

    }

}
