package fastcampus.part5.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import fastcampus.part5.domain.model.BasketProduct
import fastcampus.part5.domain.model.PurchaseHistory
import java.time.ZonedDateTime

@Entity(tableName = "history")
data class PurchaseHistoryEntity(
    val basketList: List<BasketProduct>,
    val purchaseAt: ZonedDateTime
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int =0
}

fun PurchaseHistoryEntity.toDomainModel() : PurchaseHistory {
    return PurchaseHistory(
        basketList = basketList,
        purchaseAt = purchaseAt
    )
}

fun PurchaseHistory.toEntity() : PurchaseHistoryEntity {
    return PurchaseHistoryEntity(
        basketList = basketList,
        purchaseAt = purchaseAt
    )
}