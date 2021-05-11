package tech.medina.adichallenge.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_query")
data class SearchQueryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "query") val query: String
)