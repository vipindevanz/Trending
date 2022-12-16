package com.pns.trending

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.pns.trending.data.entities.BuiltBy

class Converters {

    @TypeConverter
    fun listToJson(value: List<BuiltBy>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<BuiltBy>::class.java).toList()
}