package com.example.refactoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as App).viewModel

        val switchCompat = findViewById<SwitchCompat>(R.id.switchCompat)

        val view = findViewById<View>(R.id.view)

        when {
            viewModel.isEnabled -> {
                switchCompat.isChecked = true
                view.visibility = View.VISIBLE
            }
            else -> {
                switchCompat.isChecked = false
                view.visibility = View.GONE
            }
        }

        switchCompat.setOnCheckedChangeListener { _, b ->
            viewModel.changeEnabled(b)
            when {
                b -> view.visibility = View.VISIBLE
                else -> view.visibility = View.GONE
            }
        }

    }
}