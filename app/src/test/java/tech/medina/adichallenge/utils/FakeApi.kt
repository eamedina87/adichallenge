package tech.medina.adichallenge.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.api.dto.ReviewDto
import java.io.File
import java.util.*

object FakeApi {

    val product: ProductDto = getEntityFromJson("product_by_id.json")
    val review: ReviewDto = getEntityFromJson("review.json")

    fun reviewList(): List<ReviewDto> {
        val list = arrayListOf<ReviewDto>()
        repeat(3) {
            list.add(getEntityFromJson("review.json"))
        }
        return list
    }

    fun productList(): List<ProductDto> {
        val list = arrayListOf<ProductDto>()
        repeat(3) {
            list.add(getEntityFromJson("product_by_id.json"))
        }
        return list
    }

    private inline fun <reified T> getEntityFromJson(json: String): T =
        Gson().fromJson(getResource(json).readText(), T::class.java)
    
    private fun getResource(fileName: String): File {
        val loader = ClassLoader.getSystemClassLoader()
        val resource = loader.getResource(fileName)
        return File(resource.path)
    }

}