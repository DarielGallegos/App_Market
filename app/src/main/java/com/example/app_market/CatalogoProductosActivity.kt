package com.example.app_market

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import client.Client
import com.example.app_market.adapters.CatProductosAdapter
import com.example.app_market.databinding.ActivityCatalogoProductosBinding
import model.common.Producto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import client.services.ProductService
import com.example.app_market.databinding.PasarelaCategoriasBinding
import com.google.gson.Gson
import model.common.ApiResponseBody


class CatalogoProductosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogoProductosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogoProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rec_view = binding.recViewCatProductos
        rec_view.layoutManager = GridLayoutManager(this, 2)

        binding.lyPasarelaCategorias.refresco.setOnClickListener{

            return@setOnClickListener
        }


        val apiService = Client.ClientRetrofit.getService(ProductService::class.java) as ProductService
        val call = apiService.listarProductos()

        call.enqueue(object : Callback<ApiResponseBody>{

            override
            fun onResponse(call: Call <ApiResponseBody>, response: Response<ApiResponseBody>) {
                if (response.isSuccessful()) {

                    val productos: List<Producto>? = Gson().fromJson(Gson().toJson(response.body()?.data?.content),Array<Producto>::class.java).toList()
                    rec_view.adapter = CatProductosAdapter(
                        this@CatalogoProductosActivity,
                        productos
                    )

                } else {
                    Toast.makeText(
                        this@CatalogoProductosActivity,
                        "Error: " + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override
            fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                Toast.makeText(this@CatalogoProductosActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })



    }
}
