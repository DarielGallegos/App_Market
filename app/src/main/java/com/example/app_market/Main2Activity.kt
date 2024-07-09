package com.example.app_market

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Main2Activity : AppCompatActivity() {

    private lateinit var personLinearLayout: LinearLayout
    private lateinit var favorite: FloatingActionButton
    private lateinit var cactusCardView: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        personLinearLayout = findViewById(R.id.person_linear_layout)
        favorite = findViewById(R.id.favorite)
        cactusCardView = findViewById(R.id.cactus_card_view)

        personLinearLayout.setOnClickListener {
            val intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }

        favorite.setOnClickListener {
            val intent = Intent(this, Main4Activity::class.java)
            startActivity(intent)
        }

        cactusCardView.setOnClickListener {
            val intent = Intent(this, Main5Activity::class.java)
            startActivity(intent)
        }
    }
}
