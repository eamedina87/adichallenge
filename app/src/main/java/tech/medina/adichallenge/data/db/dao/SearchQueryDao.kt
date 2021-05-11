package tech.medina.adichallenge.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.DELETE
import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.data.db.entity.ProductFTSEntity
import tech.medina.adichallenge.data.db.entity.SearchQueryEntity

@Dao
interface SearchQueryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inser(vararg: SearchQueryEntity)

    @Query("SELECT `query` FROM search_query")
    fun getAll(): List<String>

    @Query("DELETE FROM search_query")
    fun deleteAll()

}