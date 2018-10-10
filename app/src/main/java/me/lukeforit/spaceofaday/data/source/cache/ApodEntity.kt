package me.lukeforit.spaceofaday.data.source.cache

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = TABLE_NAME)
data class ApodEntity(
        @PrimaryKey
        var id: Int = 0,
        var copyright: String? = null,
        var date: String? = null,
        var explanation: String? = null,
        var hdurl: String? = null,
        @ColumnInfo(name = "media_type")
        var mediaType: String? = null,
        @ColumnInfo(name = "service_version")
        var serviceVersion: String? = null,
        var title: String? = null,
        var url: String? = null
)