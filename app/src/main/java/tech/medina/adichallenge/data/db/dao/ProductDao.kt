package tech.medina.adichallenge.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tech.medina.adichallenge.data.db.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<ProductEntity>)

    @Query("SELECT * FROM product")
    suspend fun getAll(): List<ProductEntity>

    @Query("""
    SELECT product.* FROM product 
    JOIN product_fts ON (product_fts.remoteId = product.remoteId) 
    WHERE product_fts MATCH :query
    """)
    suspend fun search(query: String): List<ProductEntity>

    @Query("DELETE FROM product")
    suspend fun deleteAll()

}