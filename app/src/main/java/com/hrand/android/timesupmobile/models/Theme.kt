package com.hrand.android.timesupmobile.models

import io.objectbox.annotation.Convert
import io.objectbox.annotation.Id

data class Theme {
    @Id var id: Long = 0,
    var value: String,
    @Convert(converter = Long::class, dbType = String::class)
    val strings: List<String> = listOf()
}