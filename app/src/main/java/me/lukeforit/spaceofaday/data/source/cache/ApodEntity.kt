package me.lukeforit.spaceofaday.data.source.cache

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = TABLE_NAME)
class ApodEntity {
    @PrimaryKey
    var id: Int = 0
    var copyright: String? = null
    var date: String? = null
    var explanation: String? = null
    var hdurl: String? = null
    @ColumnInfo(name = "media_type")
    var mediaType: String? = null
    @ColumnInfo(name = "service_version")
    var serviceVersion: String? = null
    var title: String? = null
    var url: String? = null

    constructor() {}

    @Ignore
    constructor(id: Int, copyright: String, date: String, explanation: String, hdurl: String, mediaType: String, serviceVersion: String, title: String, url: String) {
        this.id = id
        this.copyright = copyright
        this.date = date
        this.explanation = explanation
        this.hdurl = hdurl
        this.mediaType = mediaType
        this.serviceVersion = serviceVersion
        this.title = title
        this.url = url
    }
}
