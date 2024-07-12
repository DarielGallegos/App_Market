package com.example.app_market

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.app_market.databinding.ActivityMainCerrarBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import storage.StoragePreferences

class MainCerrarActivity : AppCompatActivity() {


    private lateinit var btnLogout: LinearLayout
    private val preference = StoragePreferences.getInstance(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_client)

        btnLogout = findViewById(R.id.btcerrar)

        }


            }



