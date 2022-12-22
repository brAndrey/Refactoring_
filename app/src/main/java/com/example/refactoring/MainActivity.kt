package com.example.refactoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as App).viewModel

        val switchCompat = findViewById<SwitchCompat>(R.id.switchCompat)

        val view = findViewById<View>(R.id.view)

        viewModel.obsorve(this){
            it.apply(view,switchCompat)
        }

        switchCompat.setOnCheckedChangeListener { _, isChacked ->
            viewModel.changeEnabled(isChacked)
        }

    }
}

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

    class On : Abstract(View.VISIBLE)

    class Off : Abstract(View.GONE)
}