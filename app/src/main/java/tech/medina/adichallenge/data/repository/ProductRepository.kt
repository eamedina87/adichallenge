package tech.medina.adichallenge.data.repository

import tech.medina.adichallenge.data.api.ProductApi
import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.db.AdiChallengeDatabase
import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.data.mapper.IMapper
import javax.inject.Inject

interface IProductRepository {
    suspend fun getAllProducts(): List<ProductEntity>
    suspend fun getProductById(id: String): ProductDto
    suspend fun searchProducts(query: String): List<ProductEntity>
}

class ProductRepository @Inject constructor(
        private val api: ProductApi,
        private val database: AdiChallengeDatabase,
        private val mapper: IMapper<ProductDto, ProductEntity>
): IProductRepository {

    private val dtos = listOf(
            ProductDto("$", "Shoes with different colors", "ABCD", "https://assets.adidas.com/images/w_320,h_320,f_auto,q_auto:sensitive,fl_lossy/6634cf36274b4ea5ac46ac4e00b2021e_9366/Superstar_Shoes_Black_FY0071_01_standard.jpg", "prostar shoes", 15.0),
            ProductDto("$", "Shoes for soccer", "ABCE", "https://assets.adidas.com/images/w_320,h_320,f_auto,q_auto:sensitive,fl_lossy/c7099422ccc14e44b406abec00ba6c96_9366/NMD_R1_V2_Shoes_Black_FY6862_01_standard.jpg", "adifunk shoes", 60.0),
            ProductDto("$", "basketball jersey", "ABFG", "https://assets.adidas.com/images/w_320,h_320,f_auto,q_auto:sensitive,fl_lossy/c93fa315d2f64775ac1fab96016f09d1_9366/Dame_6_Shoes_Black_FV8624_01_standard.jpg", "nba swingman", 80.0),
            ProductDto("$", "leather official basketball", "ABlk", "https://assets.adidas.com/images/w_320,h_320,f_auto,q_auto:sensitive,fl_lossy/3eebc0498b1347e397f8ab94016140ba_9366/FS1496_00_plp_standard.jpg", "basketball", 40.0),

    )

    override suspend fun getAllProducts(): List<ProductEntity> {
        database.productDao().deleteAll()
        val apiProducts = dtos//api.getAllProducts()
        database.productDao().insert(* apiProducts.map { mapper.map(it)}.toTypedArray())
        return database.productDao().getAll()
    }

    override suspend fun getProductById(id: String): ProductDto =
        api.getProductById(id)

    override suspend fun searchProducts(query: String): List<ProductEntity> =
        database.productDao().getAll().filter { it.name.contains(query) || it.description.contains(query) }
        //database.productDao().search(query)

}