package com.tanimul.goza_ta.common.extention

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)

fun Any.toJson(): String? {
    return Gson().toJson(this)
}