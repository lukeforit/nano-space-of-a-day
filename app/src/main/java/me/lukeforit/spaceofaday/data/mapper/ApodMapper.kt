package me.lukeforit.spaceofaday.data.mapper

import me.lukeforit.spaceofaday.common.Utils
import me.lukeforit.spaceofaday.data.model.Apod
import me.lukeforit.spaceofaday.data.source.cache.ApodEntity
import javax.inject.Inject

class ApodMapper @Inject constructor() {

    fun map(entity: ApodEntity): Apod =
            Apod(
                    entity.copyright,
                    entity.date,
                    entity.explanation,
                    entity.hdurl,
                    entity.mediaType,
                    entity.serviceVersion,
                    entity.title,
                    entity.url.orEmpty()
            )

    fun map(apod: Apod): ApodEntity =
            ApodEntity(
                    Utils.getDateAsInt(apod.date!!),
                    apod.copyright,
                    apod.date,
                    apod.explanation,
                    apod.hdurl,
                    apod.mediaType,
                    apod.serviceVersion,
                    apod.title,
                    apod.url
            )
}
