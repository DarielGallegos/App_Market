package com.example.app_market.car_market

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.app_market.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.common.Producto
import model.dto.POST.Pedido
import model.dto.POST.ProductoPedido
import service.impl.DetallesProductosFinancierosServiceImpl
import storage.DataStoreCarMarket
import storage.StoragePreferences
import utils.MailSender
import view.DetallesProductosFinancierosView
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetallesProductosFinancieros : AppCompatActivity(), OnMapReadyCallback, DetallesProductosFinancierosView{
    private lateinit var btnBack: ImageView
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var adapter: ProductosAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var Service = DetallesProductosFinancierosServiceImpl(this)
    private lateinit var btnPedido: Button
    private val mail = MailSender()

    private var preferences = StoragePreferences.getInstance(this)
    private var client: Int? = null
    private var email = ""
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_productos_financieros)

        mapView = findViewById(R.id.mapView)
        btnBack = findViewById(R.id.imgBack)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        btnPedido = findViewById(R.id.btnpedido)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val subtotal = intent.getDoubleExtra("SUBTOTAL", 0.0)
        val impuesto = intent.getDoubleExtra("IMPUESTO", 0.0)
        val envio = intent.getDoubleExtra("ENVIO", 0.0)
        val total = intent.getDoubleExtra("TOTAL", 0.0)

        val txtSubtotal = findViewById<EditText>(R.id.txtsubtotal)
        val txtImpuesto = findViewById<EditText>(R.id.txtimpuesto)
        val txtEnvio = findViewById<EditText>(R.id.txtenvio)
        val txtTotal = findViewById<EditText>(R.id.txttotal)
        val txtCliente = findViewById<EditText>(R.id.txtcliente)
        val txtTelefono = findViewById<EditText>(R.id.txttelefono)

        btnBack.setOnClickListener{
            val intent = Intent(this@DetallesProductosFinancieros, ProductosCarMarket::class.java)
            startActivity(intent)
        }


        lifecycleScope.launch(Dispatchers.IO) {
            preferences.getCredentiales().collect {
                withContext(Dispatchers.Main) {
                    if (it.id != null) {
                        txtCliente.setText(it.nombre)
                        txtTelefono.setText(it.telefono)
                        client = it.id
                        email = it.correo.toString()
                    }
                }
            }
        }

        txtSubtotal.setText(subtotal.toString())
        txtImpuesto.setText(impuesto.toString())
        txtEnvio.setText(envio.toString())
        txtTotal.setText(total.toString())

        val productosList = intent.getParcelableArrayListExtra<Producto>("PRODUCTOS_LIST")
        val productosPedido = productosList?.map { producto ->
            ProductoPedido(
                idProducto = producto.id,
                cantidad = producto.cantidad,
                monto = producto.precio.toDouble()
            )
        } ?: emptyList()

        btnPedido.setOnClickListener {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val latLng = LatLng(it.latitude, it.longitude)
                    val destino = "${latLng.latitude},${latLng.longitude}"

                    val pedido = Pedido(
                        idUsuario = 1,
                        idCliente = client,
                        destino = destino,
                        productos = productosPedido,
                        impuesto = 1,
                        subtotal = subtotal,
                        envio = envio,
                        total = total,
                        estadoPedido = "",
                        creadoPor = "Cliente Final",
                        estado = 1
                    )

                    Service.pedidosProductos(pedido)

                    val subject = "Factura - Supermercado El Economico"
                    val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                    val productosList = intent.getParcelableArrayListExtra<Producto>("PRODUCTOS_LIST") ?: emptyList()
                    val body = """
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            color: #000000;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
         .logo {
            width: 75px; 
        }
    </style>
</head>
<body>
    <h2>Factura de Pedido</h2>
    <p>Estimado/a ${txtCliente.text},</p>
    <p>Nos complace informarle que su pedido ha sido procesado.</p>
    <p>Gracias por su compra. A continuación, encontrará los detalles de su pedido:</p>
    <table>
        <tr>
            <th colspan="2">Factura Supermercado El Económico</th>
            <th><center>Hora de Pedido: $currentTime<center/></th>
            <th><center><img src="https://i.imgur.com/xCQHTF3.png" class="logo" alt="Logo del Supermercado"/><center/></th>
        </tr>
        <tr>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Precio Unitario</th>
            <th>Total</th>
        </tr>
        ${productosList.joinToString("") { producto ->
                        """
            <tr>
                <td>${producto.producto}</td>
                <td>${producto.cantidad}</td>
                <td>L. ${producto.precio}</td>
                <td>L. ${producto.cantidad * producto.precio}</td>
            </tr>
            """
                    }}
        <tr>
            <td colspan="3"><strong>Subtotal</strong></td>
            <td><strong>L. $subtotal</strong></td>
        </tr>
        <tr>
            <td colspan="3"><strong>Impuesto</strong></td>
            <td><strong>L. $impuesto</strong></td>
        </tr>
        <tr>
            <td colspan="3"><strong>Envío</strong></td>
            <td><strong>L. $envio</strong></td>
        </tr>
        <tr>
            <td colspan="3"><strong>Total</strong></td>
            <td><strong>L. $total</strong></td>
        </tr>
    </table>
    <p>Atentamente,<br>El equipo de Supermercado El Económico</p>
</body>
</html>
""".trimIndent()

                    mail.sendEmail(email, subject, body)
                    setResult(RESULT_OK)
                    finish()
                    //Log.d("DetallesProductos", "Pedido: $pedido")
                }
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            enableMyLocation()
        }
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.isMyLocationEnabled = true
            getLastLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val latLng = LatLng(it.latitude, it.longitude)
                googleMap.addMarker(MarkerOptions().position(latLng).title("¡ESTAS AQUI!"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation()
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun getStatus(status: Boolean) {
        Toast.makeText(this,
            if(status) "El pedido ha sido procesado"
                    else "No se ha podido procesar el pedido",
            Toast.LENGTH_LONG
        ).show()
        if(status){
            //mail.sendEmail(email, "Supermerrcado El Económico", "Su pedido ha sido procesado")
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}