package com.hrand.android.timesupmobile.adapters

import android.view.View
import android.view.ViewGroup
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.models.Word
import com.hrand.android.timesupmobile.utils.inflate
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*


class RecyclerAdapter(private val words: List<Word>)  : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerAdapter.WordHolder>()  {

    internal lateinit var itemLongClickListener: ItemLongClickListener
    private var wordList = words

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.WordHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return WordHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.WordHolder, position: Int) {
        holder.bindWord(wordList[position])
    }

    fun getElt(pos: Int): Word {
        return wordList.get(pos)
    }

    fun setWordsList(wl: List<Word>){
        wordList = wl
    }

    fun setOnLongClickListener(longClickListener: ItemLongClickListener){
        itemLongClickListener = longClickListener
    }

    interface ItemLongClickListener{
        fun onItemLongClick(view: View, position: Int)
    }

    inner class WordHolder(v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v),/* View.OnClickListener,*/ View.OnLongClickListener {

        private var view: View = v
        private var word: Word? = null

        init {
            v.setOnLongClickListener(this)
        }

        fun bindWord(wordToBind : Word){
            word = wordToBind
            val word_id = wordToBind.id
            val word_difficulty = wordToBind.difficulty
            val word_value = wordToBind.value

 //           view.tv_id.text = word_id.toString()
            view.tv_difficulty.text = word_difficulty.toString()
            view.tv_value.text = word_value
            if(!wordToBind.themes.isEmpty()){
                view.tv_theme.text = wordToBind.themes.get(0).value
            }
            else{
                view.tv_theme.text = "No Theme"
            }
        }

        override fun onLongClick(v: View): Boolean {
            itemLongClickListener.onItemLongClick(v, adapterPosition)
            return true
        }

    }

}
