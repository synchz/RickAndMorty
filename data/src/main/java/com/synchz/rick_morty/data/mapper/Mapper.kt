package com.synchz.rick_morty.data.mapper

interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E

}