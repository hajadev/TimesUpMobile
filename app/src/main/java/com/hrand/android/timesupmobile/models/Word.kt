package com.hrand.android.timesupmobile.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
class Word(id: Long, value: String, difficulty: Long) {
    @Id
    var id: Long = 0
    var value: String? = null
    var difficulty: Long = 0

    lateinit var themes: ToMany<Theme>
}