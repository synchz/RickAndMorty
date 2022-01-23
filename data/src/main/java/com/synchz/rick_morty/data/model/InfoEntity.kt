package com.synchz.rick_morty.data.model

data class InfoEntity(
    var count: Int,
    var pages: Int,
    var next: String?,
    var prev: String?
)