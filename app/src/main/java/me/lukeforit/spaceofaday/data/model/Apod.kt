package me.lukeforit.spaceofaday.data.model

import com.squareup.moshi.Json

class Apod {
    var copyright: String? = null
    var date: String? = null
    var explanation: String? = null
    var hdurl: String? = null
    @Json(name = "media_type")
    var mediaType: String? = null
    @Json(name = "service_version")
    var serviceVersion: String? = null
    var title: String? = null
    var url: String? = null

    constructor() {}

    constructor(copyright: String, date: String, explanation: String, hdurl: String, mediaType: String, serviceVersion: String, title: String, url: String) {
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
