package tech.medina.adichallenge.utils

import com.google.gson.Gson
import tech.medina.adichallenge.data.api.dto.ProductDto
import java.io.File

object FakeApi {

    val productList: List<ProductDto> = getEntityListFromJson("products.json")
    val product: ProductDto = getEntityFromJson("product_by_id.json")

    private inline fun <reified T> getEntityFromJson(json: String): T =
        Gson().fromJson(getResource(json).readText(), T::class.java)

    private inline fun <reified T> getEntityListFromJson(json: String): List<T> =
        Gson().fromJson(getResource(json).readText(), Array<T>::class.java).asList()

    private fun getResource(fileName: String): File {
        val loader = ClassLoader.getSystemClassLoader()
        val resource = loader.getResource(fileName)
        return File(resource.path)
    }

}