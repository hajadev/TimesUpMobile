package com.hrand.android.timesupmobile.utils

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.reflect.KClass

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}


/******************** Extension for passing Arguments to Activities *******************************/

fun Activity.gotoActivity(cls: KClass<out Activity>, finish: Boolean = false,
                          extras: Map<String, Any?>? = null) {
    val intent = Intent(this, cls.java)
    extras?.forEach { intent.addExtra(it.key, it.value) }
    startActivity(intent)
    if (finish) finish()
}

fun Intent.addExtra(key: String, value: Any?) {
    when (value) {
        is Long -> putExtra(key, value)
        is String -> putExtra(key, value)
        is Boolean -> putExtra(key, value)
        is Float -> putExtra(key, value)
        is Double -> putExtra(key, value)
        is Int -> putExtra(key, value)
        is Parcelable -> putExtra(key, value)
        //Add other types when needed
    }
}

inline fun <reified T> Activity.getExtra(extra: String): T? {
    return intent.extras?.get(extra) as? T?
}