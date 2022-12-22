package com.example.refactoring

import android.view.View
import android.widget.CompoundButton

// класс отвечающий за состояние UI активити

sealed class State {
    abstract fun apply(view: View, compoundButton: CompoundButton)

    class Initial(
        private val isEnabled: Boolean,
        private val state: State
    ) : State() {
        override fun apply(view: View, compoundButton: CompoundButton) {
            compoundButton.isChecked = isEnabled
            state.apply(view, compoundButton)
        }
    }

    abstract class Abstract(private val visibility: Int) : State() {
        override fun apply(view: View, compoundButton: CompoundButton) {
            view.visibility = visibility
        }
    }
// on и off - по switchCompat с переключением видимости view
    class On : Abstract(View.VISIBLE)

    class Off : Abstract(View.GONE)
}
