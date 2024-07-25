package com.example.app_market

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import client.Client
import client.services.ProductService
import com.example.app_market.adapters.CatProductosAdapter
import com.example.app_market.databinding.ActivityCatalogoProductosBinding
import com.google.gson.Gson
import model.common.ApiResponseBody
import model.common.Producto
import okhttp3.internal.ignoreIoExceptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CatalogoProductosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogoProductosBinding
    var productos: List<Producto>? = null
    var productosAux: List<Producto>? = null
    var adapter:CatProductosAdapter? = null
    private var lastQuery: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogoProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.Inicio.setOnClickListener{

            val intent = Intent(this, DashboardClient::class.java)
            startActivity(intent)
        }

        val rec_view = binding.recViewCatProductos
        rec_view.layoutManager = GridLayoutManager(this, 2)

        binding.carrito.setOnClickListener{

        }

        binding.lyPasarelaCategorias.refresco.setOnClickListener{
            filterCategoria(query = 1 )
            return@setOnClickListener
        }
        binding.lyPasarelaCategorias.lacteos.setOnClickListener{
            filterCategoria(query = 2 )
            return@setOnClickListener
        }
        binding.lyPasarelaCategorias.botanas.setOnClickListener{
            filterCategoria(query = 3 )
            return@setOnClickListener
        }
        binding.lyPasarelaCategorias.enlatados.setOnClickListener{
            filterCategoria(query = 4 )
            return@setOnClickListener
        }

        binding.buscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("buscar", s.toString())
                filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        val apiService = Client.ClientRetrofit.getService(ProductService::class.java) as ProductService
        val call = apiService.listarProductos()

        call.enqueue(object : Callback<ApiResponseBody>{

            override
            fun onResponse(call: Call <ApiResponseBody>, response: Response<ApiResponseBody>) {
                if (response.isSuccessful()) {

                    productos = Gson().fromJson(Gson().toJson(response.body()?.data?.content),Array<Producto>::class.java).toList()
                    productosAux = productos
                    adapter = CatProductosAdapter(
                        this@CatalogoProductosActivity,
                        productos
                    )

                    rec_view.adapter = adapter

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

    @SuppressLint("NotifyDataSetChanged")
    fun filter(query: String): Boolean {

        if(query.isEmpty()) {
            productos = productosAux
            adapter!!.setData(productos!!)
            adapter!!.notifyDataSetChanged()
            return true
        }

        productos = productos!!.filter { producto ->
            producto.producto.contains(query, ignoreCase = true)
        }

        Log.d("productos", productos!!.size.toString())
        adapter!!.setData(productos!!)
        adapter!!.notifyDataSetChanged()
        return true;
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterCategoria(query: Int): Boolean {

        if (lastQuery == query) {
            productos = productosAux?.toList()
            adapter!!.setData(productos!!)
            adapter!!.notifyDataSetChanged()

            lastQuery = null
            return true
        }

        lastQuery = query
        productos = productosAux

        if(query == 0) {

            adapter!!.setData(productos!!)
            adapter!!.notifyDataSetChanged()
            return true
        }

        Log.d("FilterCategoria", "Query: $query")

        productos = productos!!.filter { producto ->
            producto.id_categoria == query
        }

        adapter!!.setData(productos!!)
        adapter!!.notifyDataSetChanged()
        return true;
    }
}
