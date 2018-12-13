package com.hrand.android.timesupmobile.daos

import android.content.Context
import android.util.Log
import com.hrand.android.timesupmobile.models.Theme
import com.hrand.android.timesupmobile.models.Theme_
import com.hrand.android.timesupmobile.utils.ObjectBox
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query

object ThemeDao {

    private lateinit var themesBox: Box<Theme>

    fun init(context: Context){
        //ObjectBox.build(context)
        ThemeDao.themesBox = ObjectBox.boxStore.boxFor()
        Log.i("haja", "ThemeDao initialized")
    }

    fun getAll(): List<Theme>{
        return themesBox.query{order(Theme_.id)}.find()
    }

    fun addThemes(themesList: List<Theme>){
        themesBox.put(themesList)
    }

    fun addTheme(theme: Theme){
        themesBox.put(theme)
    }

}