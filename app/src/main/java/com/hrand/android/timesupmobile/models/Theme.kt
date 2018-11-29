package com.hrand.android.timesupmobile.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
class Theme {
    @Id
    var id: Long = 0
    var value: String = ""

    lateinit var words: ToMany<Word>
}