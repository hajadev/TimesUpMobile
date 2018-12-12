package com.hrand.android.timesupmobile.utils

import android.util.Log
import com.hrand.android.timesupmobile.models.Theme
import com.hrand.android.timesupmobile.models.Word

fun initThemesData(): MutableList<Theme> {
    val themes = mutableListOf<Theme>()
    val t1 = Theme(value = "Animaux")
    val t2 = Theme(value = "Ville")
    themes.add(t1)
    Log.i("haja", "t1 = ${t1.value}")
    themes.add(t2)
    Log.i("haja", "t2 = ${t2.value}")
    return themes
}

fun initWordsData(): MutableList<Word> {
    val words = mutableListOf<Word>()
    words.addAll(listOf(Word(/*id = 0, */value = "Cheval", difficulty = 1), Word(/*id = 1, */value = "Hypocampe", difficulty = 2), Word(/*id = 2, */value = "Panthere", difficulty = 1), Word(/*id = 3, */value = "Paris", difficulty = 1)))
    return words
}