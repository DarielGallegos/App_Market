package model.common

class Producto (
    //var producto: String,
    //var cod_producto: String,
    //var id_categoria: Int,
    //var marca: String,
    /*var foto: Blob,
    var precio: Float,
    var stock_min: Int,
    var stock_max: Int,
    var creado_por: String,
    var modificado_por: String,
    var creado_en: String,
    var modificado_en: String,
    var estado: Int*/

)
 {
     var id:Int = 0
         get(){
         return field
     }
    set(id){
        field= id
    }

     var producto:String =""
         get(){
             return field
         }
         set(pr){
             field= pr
         }

     var foto:String = ""
         get(){
             return field
         }
         set(ft){
             field= ft
         }

     var precio:Float= 0.0f
         get(){
             return field
         }
         set(pc){
             field = pc
         }
     var marca:String = ""
         get(){
             return field
         }
         set(mc){
             field = mc
         }

     var cantidad:Int = 0
         get(){
             return field
         }
         set(cn){
             field = cn
         }

     var subtotal:Double = 0.0
         get(){
             return field
         }
         set(sb){
             field = sb
         }

     var descripcion:String = ""
         get(){
             return field
         }
         set(ds){
             field = ds
         }

     var id_categoria:Int = 0
         get(){
             return field
         }
         set(ct){
             field = ct
         }
 }



