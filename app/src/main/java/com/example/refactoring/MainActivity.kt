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


        val enaibld= viewModel.isEnabled
        switchCompat.isChecked=enaibld
        appyView(view,enaibld)

        switchCompat.setOnCheckedChangeListener { _, isChacked ->
            viewModel.changeEnabled(isChacked)
            appyView(view,isChacked)
        }

    }
    private fun appyView(view: View, enaivld:Boolean){
        view.visibility=if (enaivld) View.VISIBLE else View.GONE
    }

}