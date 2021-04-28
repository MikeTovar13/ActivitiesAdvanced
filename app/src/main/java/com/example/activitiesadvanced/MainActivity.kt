package com.example.activitiesadvanced

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

const val REQUEST_CODE_ACTIVITY_SECOND = 1
const val REQUEST_CODE_PICK_IMAGE = 2
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButtonSaveMain.setOnClickListener {
            this.saveSharedPreferences()
            this.goToNextActivity()
        }

        ButtonContinueMain.setOnClickListener {
            this.goToNextActivity()
        }

        ButtonPickMain.setOnClickListener {
            this.pickFromGallery()
        }
    }

    private fun goToNextActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_ACTIVITY_SECOND)
        //Example:    startActivity(intent) Activity call default
    }

    // Work with Shared Preferences (Saved info)
    private fun saveSharedPreferences() {
        val preferences = getSharedPreferences(getString(R.string.app_preferences), MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("userName", EditTextMain.text.toString())
        editor.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_ACTIVITY_SECOND) {
            when (resultCode) {
                RESULT_CANCELED -> {
                    Toast.makeText(this, "Fallido", Toast.LENGTH_LONG).show()
                }
                RESULT_OK -> {
                    Toast.makeText(this, "Exitoso", Toast.LENGTH_LONG).show()
                }
                2 -> {
                    Toast.makeText(this, "Exitoso", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(this, "Incierto", Toast.LENGTH_LONG).show()
                }
            }
        } else if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                val uri = data?.data
                ImagePickMain.setImageURI(uri)
                Toast.makeText(this, "Imagen seleccionada", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "No selecciono imagen", Toast.LENGTH_LONG).show()
            }
        }

    }

    // Get file from anything other app
    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK) // Permit get file (ACTION is different for method)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }
}