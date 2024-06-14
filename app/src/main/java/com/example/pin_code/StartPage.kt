package com.example.pin_code

import android.R.attr.value
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


lateinit var pinEditText: EditText
lateinit var result: TextView
lateinit var pref: SharedPreferences



class StartPage : AppCompatActivity() {
    var pincode = 0
    var pref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start_page)
        pinEditText = findViewById(R.id.pin_code)
        result = findViewById(R.id.result)

        pref = getSharedPreferences("TABLE2", Context.MODE_PRIVATE)
        pincode = pref?.getInt("code", 0)!!
        result.text = pincode.toString()

        if(pincode != 0){
            val myIntent: Intent = Intent(
                this@StartPage,
                HomePage::class.java
            )
            this@StartPage.startActivity(myIntent)
        }
    }

    fun savePin(res: Int){
        val editor = pref?.edit()
        editor?.putInt("code", res)
        editor?.apply()
    }

    fun onClickSave(view: View) {
        pincode = Integer.parseInt(pinEditText.getText().toString())
        savePin(pincode)
        val myIntent: Intent = Intent(
            this@StartPage,
            HomePage::class.java
        )
        this@StartPage.startActivity(myIntent)
    }

}