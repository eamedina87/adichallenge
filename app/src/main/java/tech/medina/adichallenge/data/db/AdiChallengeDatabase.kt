package tech.medina.adichallenge.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.medina.adichallenge.data.db.dao.ProductDao
import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.data.db.entity.ProductFTSEntity

@Database(
        entities = [ProductEntity::class, ProductFTSEntity::class],
        exportSchema = false,
        version = 1
)
abstract class AdiChallengeDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

}