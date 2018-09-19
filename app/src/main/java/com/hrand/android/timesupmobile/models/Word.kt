package com.hrand.android.timesupmobile.models

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.generator.model.ToMany

@Entity
class Word {
    @Id
    var id: Long = 0
    var value: String? = null
    var difficulty: Long = 0

    @Backlink(to = "words")
    lateinit var themes: List<Theme>
}