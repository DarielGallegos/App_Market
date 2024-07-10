package model.dto.POST

data class ClientPOST(var nombres: String, var apellidos: String
            , var fecha_nac: String, var genero: String, var correo: String
            , var telefono: String, var foto: String, var usuario: String,
            var passwd: String, var id_rol: Int, var creado_por: String, var estado: Int)
