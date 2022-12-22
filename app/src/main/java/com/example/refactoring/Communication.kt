package com.example.refactoring

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication : Observe {

    fun map(value: State)

    class Base(
        private val liveData: MutableLiveData<State> = MutableLiveData()
    ) : Communication {

        // TODO class Observer< > выяснить что за класс
        // генератор состояния
        override fun obsorve(owner: LifecycleOwner, observer: Observer<State>) {
            liveData.observe(owner, observer)
        }

        // изменение состояния liveData
        override fun map(value: State) {
            liveData.value = value
        }
    }
}

interface Observe {
    fun obsorve(owner: LifecycleOwner, observer: Observer<State>)
}