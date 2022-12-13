package com.example.examtp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ShowFavorite : AppCompatActivity() {
    lateinit var nom : TextView
    lateinit var genre : TextView
    lateinit var date : TextView
    lateinit var sharedPref:SharedPref
    lateinit var back : Button
    lateinit var reset : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_favorite)
        nom = findViewById(R.id.nom)
        genre = findViewById(R.id.genre)
        date = findViewById(R.id.date)
        sharedPref = SharedPref(this)
        nom.text = sharedPref.sharedPref.getString("nomfilm","")
        genre.text = sharedPref.sharedPref.getString("genre","")
        date.text = sharedPref.sharedPref.getString("date","")

        back = findViewById(R.id.back)
        reset = findViewById(R.id.reset)

        back.setOnClickListener {
            finish()
        }

        reset.setOnClickListener {
            sharedPref.clearSharedPreference()
            nom.text = ""
            genre.text = ""
            date.text = ""
            finish()
        }

    }
}