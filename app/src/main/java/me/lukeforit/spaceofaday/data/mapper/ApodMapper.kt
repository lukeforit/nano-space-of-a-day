package me.lukeforit.spaceofaday.data.mapper

import java.util.ArrayList

import javax.inject.Inject

import me.lukeforit.spaceofaday.common.Utils
import me.lukeforit.spaceofaday.data.model.Apod
import me.lukeforit.spaceofaday.data.source.cache.ApodEntity

class ApodMapper @Inject
constructor() {

    fun map(entity: ApodEntity): Apod {
        return Apod(
                entity.copyright,
                entity.date,
                entity.explanation,
                entity.hdurl,
                entity.mediaType,
                entity.serviceVersion,
                entity.title,
                entity.url)
    }

    fun map(apod: Apod): ApodEntity {
        return ApodEntity(
                Utils.getDateAsInt(apod.date!!),
                apod.copyright,
                apod.date,
                apod.explanation,
                apod.hdurl,
                apod.mediaType,
                apod.serviceVersion,
                apod.title,
                apod.url)
    }

    fun map(list: List<ApodEntity>): List<Apod> {
        val result = ArrayList<Apod>()
        for (entity in list) {
            result.add(map(entity))
        }
        return result
    }
}
