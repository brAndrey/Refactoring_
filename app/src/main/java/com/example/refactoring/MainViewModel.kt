package com.example.refactoring

import androidx.lifecycle.LifecycleOwner
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
// наблюдатель состояния
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


