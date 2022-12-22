package com.example.refactoring

import android.icu.number.NumberFormatter.GroupingStrategy
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainViewModel(
    private val communication: Communication,
    private val repository: Repository,
    private val choouseStateStrategy: Map.ChoouseState

) : Observe {

    init {
        repository.isEnabled().let { enaibld ->
            communication.map(State.Initial(enaibld, choouseStateStrategy.map(enaibld)))
        }
    }

    override fun obsorve(owner: LifecycleOwner, observer: Observer<State>) {
        communication.obsorve(owner, observer)
    }

    fun changeEnabled(enabled: Boolean) {
        repository.changeEnabled(enabled)
        communication.map(choouseStateStrategy.map(enabled))
    }
}

interface Map<S,R>{
    fun map(sourse:S):R
    class ChoouseState():Map<Boolean,State> {
        override fun map(sourse: Boolean): State {
            return if (sourse) State.On()else State.Off()
        }
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