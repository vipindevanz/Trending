package com.pns.trending.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

class BuiltBy {
    var href: String? = null
    var avatar: String? = null
    var username: String? = null
}

@Entity
data class Repo (
    var author: String,
    var name: String,
    var avatar: String,
    @PrimaryKey
    var url: String = "",
    var description: String,
    var language: String,
    var languageColor: String,
    var stars : Int,
    var forks : Int,
    var currentPeriodStars : Int,
    var builtBy: List<BuiltBy>,
    var expanded: Boolean = false
)