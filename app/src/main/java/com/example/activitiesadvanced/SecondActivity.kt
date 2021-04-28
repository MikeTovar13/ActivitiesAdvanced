package com.example.activitiesadvanced

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        this.getAttrPreferences()

        ButtonFailedMain.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        ButtonSuccessMain.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

    }

    // Work from Get Preferences (Get attributes)
    private fun getAttrPreferences() {
        val preferences = getSharedPreferences(getString(R.string.app_preferences), MODE_PRIVATE)
        TextViewSecondActivity.text = preferences.getString("userName", "")
    }
}