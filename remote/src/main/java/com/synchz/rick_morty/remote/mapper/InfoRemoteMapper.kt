package com.synchz.rick_morty.remote.mapper

import com.synchz.rick_morty.data.model.InfoEntity
import com.synchz.rick_morty.remote.model.InfoModel
import javax.inject.Inject

class InfoRemoteMapper @Inject constructor() : Mapper<InfoModel, InfoEntity> {
    override fun from(info: InfoEntity) = InfoModel(
        count = info.count,
        pages = info.pages,
        next = info.next,
        prev = info.prev
    )

    override fun to(info: InfoModel) = InfoEntity(
        count = info.count,
        pages = info.pages,
        next = info.next,
        prev = info.prev
    )
}