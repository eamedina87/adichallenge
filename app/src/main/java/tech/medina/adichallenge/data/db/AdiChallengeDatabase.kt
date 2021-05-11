package tech.medina.adichallenge.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.medina.adichallenge.data.db.dao.ProductDao
import tech.medina.adichallenge.data.db.dao.SearchQueryDao
import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.data.db.entity.ProductFTSEntity
import tech.medina.adichallenge.data.db.entity.SearchQueryEntity

@Database(
        entities = [ProductEntity::class, ProductFTSEntity::class, SearchQueryEntity::class],
        exportSchema = false,
        version = 1
)
abstract class AdiChallengeDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun searchQueryDao(): SearchQueryDao

}