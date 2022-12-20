package com.example.refactoring

class MainViewModel(
    private val repository: Repository
) {

    val isEnabled: Boolean
        get() = repository.isEnabled()

    fun changeEnabled(enabled: Boolean) {
        repository.changeEnabled(enabled)
    }
}