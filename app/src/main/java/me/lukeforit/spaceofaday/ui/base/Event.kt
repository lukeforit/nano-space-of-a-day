package me.lukeforit.spaceofaday.ui.base

class Event<T>(private val data: T) {
    var isDelivered: Boolean = false
        private set

    init {
        isDelivered = false
    }

    fun deliverData(): T? {
        if (isDelivered) {
            return null
        } else {
            isDelivered = true
            return data
        }
    }

    fun peekData(): T {
        return data
    }
}
