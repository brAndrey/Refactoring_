package com.example.refactoring

import android.app.Application

class App: Application() {

    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = MainViewModel(Communication.Base(),Repository.Base(),Map.ChoouseState())
    }
}