package me.lukeforit.spaceofaday.data.source.cache

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = TABLE_NAME)
class ApodEntity(
        @PrimaryKey
        val id: Int = 0,
        val copyright: String? = null,
        val date: String? = null,
        val explanation: String? = null,
        val hdurl: String? = null,
        @ColumnInfo(name = "media_type")
        val mediaType: String? = null,
        @ColumnInfo(name = "service_version")
        val serviceVersion: String? = null,
        val title: String? = null,
        val url: String? = null
)