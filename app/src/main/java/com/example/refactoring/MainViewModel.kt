package com.example.refactoring

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainViewModel(
    private val communication: Communication,
    private val repository: Repository

) : Observe {

    init {
        communication.map(State.Initial(repository.isEnabled()) )
    }

    override fun obsorve(owner: LifecycleOwner, observer: Observer<State>) {
        communication.obsorve(owner, observer)
    }

    fun changeEnabled(enabled: Boolean) {
        repository.changeEnabled(enabled)
        communication.map(if (repository.isEnabled()) State.On() else State.Off())
    }
}

interface Communication : Observe {

    fun map(value: State)

    class Base(
        private val liveData: MutableLiveData<State> = MutableLiveData()
    ) : Communication {

        // TODO class Observer< > выяснить что за класс
        override fun obsorve(owner: LifecycleOwner, observer: Observer<State>) {
            liveData.observe(owner, observer)
        }

        override fun map(value: State) {
            liveData.value = value
        }
    }
}

interface Observe {
    fun obsorve(owner: LifecycleOwner, observer: Observer<State>)
}