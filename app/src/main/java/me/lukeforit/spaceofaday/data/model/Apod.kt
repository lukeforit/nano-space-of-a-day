package me.lukeforit.spaceofaday.data.model

import com.squareup.moshi.Json

class Apod(
        val copyright: String? = null,
        val date: String? = null,
        val explanation: String? = null,
        val hdurl: String? = null,
        @Json(name = "media_type")
        val mediaType: String? = null,
        @Json(name = "service_version")
        val serviceVersion: String? = null,
        val title: String? = null,
        val url: String? = null
)
