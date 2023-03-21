package fastcampus.part5.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import fastcampus.part5.domain.model.SearchKeyword

@Entity(tableName = "search")
data class SearchKeywordEntity(
    @PrimaryKey
    val keyword: String
)

fun SearchKeywordEntity.toDomain() : SearchKeyword {
    return SearchKeyword(keyword = keyword)
}