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

        viewModel.obsorve(this){
            it.apply(view,switchCompat)
        }


        switchCompat.setOnCheckedChangeListener { _, isChacked ->
            viewModel.changeEnabled(isChacked)
        }

    }
}

