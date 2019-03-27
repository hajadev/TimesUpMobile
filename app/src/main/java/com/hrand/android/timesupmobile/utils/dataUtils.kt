package com.hrand.android.timesupmobile.utils

import android.util.Log
import com.hrand.android.timesupmobile.daos.ThemeDao
import com.hrand.android.timesupmobile.daos.WordDao
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


fun initData(){

    val themes = mutableListOf<Theme>()
    val t1 = Theme(value = "Animaux")
    val t2 = Theme(value = "Sport")
    themes.add(t1)
    themes.add(t2)

    val w1 = Word(value = "Cheval", difficulty = 1)
    w1.themes.add(t1)
    val w2 = Word(value = "Hypocampe", difficulty = 2)
    w2.themes.add(t1)
    val w3 = Word(value = "Panthere", difficulty = 1)
    w3.themes.add(t1)
    val w4 = Word(value = "Tennis", difficulty = 1)
    w4.themes.add(t2)
    val w5 = Word(value = "Lion", difficulty = 1)
    w5.themes.add(t1)
    val w6 = Word(value = "Ours", difficulty = 1)
    w6.themes.add(t1)
    val w7 = Word(value = "Libellule", difficulty = 1)
    w7.themes.add(t1)
    val w8 = Word(value = "Dromadaire", difficulty = 1)
    w8.themes.add(t1)
    val w9 = Word(value = "Léopard", difficulty = 1)
    w9.themes.add(t1)
    val w10 = Word(value = "Tennis", difficulty = 1)
    w10.themes.add(t2)
    val w11 = Word(value = "Yoga", difficulty = 1)
    w11.themes.add(t2)
    val w12 = Word(value = "Karaté", difficulty = 1)
    w12.themes.add(t2)
    val w13 = Word(value = "Judo", difficulty = 1)
    w13.themes.add(t2)
    val w14 = Word(value = "Baseball", difficulty = 1)
    w14.themes.add(t2)
    val w15 = Word(value = "Pêche", difficulty = 1)
    w15.themes.add(t2)

    t1.words.add(w1)
    t1.words.add(w2)
    t1.words.add(w3)
    t1.words.add(w4)
    t1.words.add(w5)
    t1.words.add(w6)
    t1.words.add(w7)
    t1.words.add(w8)
    t1.words.add(w9)

    t2.words.add(w4)
    t2.words.add(w11)
    t2.words.add(w12)
    t2.words.add(w13)
    t2.words.add(w14)
    t2.words.add(w15)

    ThemeDao.addThemes(themes)
    WordDao.addWord(w1)
    WordDao.addWord(w2)
    WordDao.addWord(w3)
    WordDao.addWord(w4)
    WordDao.addWord(w5)
    WordDao.addWord(w6)
    WordDao.addWord(w7)
    WordDao.addWord(w8)
    WordDao.addWord(w9)
    WordDao.addWord(w10)
    WordDao.addWord(w11)
    WordDao.addWord(w12)
    WordDao.addWord(w13)
    WordDao.addWord(w14)
    WordDao.addWord(w15)

}
