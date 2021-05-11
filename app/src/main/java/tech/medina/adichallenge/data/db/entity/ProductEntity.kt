package tech.medina.adichallenge.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
        @PrimaryKey @ColumnInfo(name = "remoteId") val remoteId: String,
        @ColumnInfo(name = "currency") val currency: String?,
        @ColumnInfo(name = "description") val description: String,
        @ColumnInfo(name = "image") val image: String?,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "price") val price: Double?
)

@Entity(tableName = "product_fts")
@Fts4(contentEntity = ProductEntity::class)
data class ProductFTSEntity(
        @PrimaryKey @ColumnInfo(name = "rowid") val rowId: Int,
        @ColumnInfo(name = "remoteId") val remoteId: String,
        @ColumnInfo(name = "description") val description: String,
        @ColumnInfo(name = "name") val name: String,
)