package fastcampus.part5.domain.repository

import fastcampus.part5.domain.model.Product

interface MainRepository {
    fun getProductList() : List<Product>
}