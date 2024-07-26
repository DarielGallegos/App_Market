package view

import model.dto.REQUEST.ProductosDetalle

interface ListaProductosView {
    fun initRecyclerView(list : List<ProductosDetalle>)
}