package tech.medina.adichallenge.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import tech.medina.adichallenge.data.db.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert
    fun insert(vararg product: ProductEntity)

    @Query("SELECT * FROM product")
    fun getAll(): List<ProductEntity>

    @Query("""
        SELECT * FROM product
        JOIN product_fts ON product_fts.remoteId = product.remoteId
        WHERE product_fts MATCH :query
    """)
    fun search(query: String): List<ProductEntity>

    @Query("DELETE FROM product")
    fun deleteAll()

}