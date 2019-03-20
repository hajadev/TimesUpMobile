package com.hrand.android.timesupmobile.daos

import android.content.Context
import android.util.Log
import com.hrand.android.timesupmobile.models.Word
import com.hrand.android.timesupmobile.models.Word_
import com.hrand.android.timesupmobile.models.Word_.value
import com.hrand.android.timesupmobile.utils.ObjectBox
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query

object WordDao {

    private lateinit var wordsBox:Box<Word>

    fun init(context: Context){
        //ObjectBox.build(context)
        wordsBox = ObjectBox.boxStore.boxFor()
        Log.i("haja", "WorkDao initialized")
    }

    fun getAll(): List<Word>{
        return wordsBox.query{order(Word_.id)}.find()
    }

    fun get(wordValue: String): Word? {
        return wordsBox.query{equal(value, wordValue)}.findFirst()
    }

    fun addWords(wordsList: List<Word>){
        wordsBox.put(wordsList)
    }

    fun addWord(word: Word){
        wordsBox.put(word)
    }

    fun deleteWord(word: Word?){
        if(word != null)
            wordsBox.remove(word)
    }

}