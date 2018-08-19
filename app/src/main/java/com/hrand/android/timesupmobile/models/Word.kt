package com.hrand.android.timesupmobile.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Word {
    @Id var id: Long = 0,
    var value: String,
    var difficulty: Long = 0
}