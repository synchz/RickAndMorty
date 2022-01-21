package com.synchz.rick_morty.remote.model

data class InfoModel(
    var count: Int,
    var pages: Int,
    var next: String,
    var prev: String
)