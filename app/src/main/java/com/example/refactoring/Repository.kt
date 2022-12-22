package com.example.refactoring

interface Repository {

    fun isEnabled(): Boolean

    fun changeEnabled(enabled: Boolean)

    class Base(private var enabled: Boolean = false) : Repository {

        override fun isEnabled(): Boolean = enabled

        override fun changeEnabled(enabled: Boolean) {
            this.enabled = enabled
        }
    }
}