package fastcampus.part5.domain.model

import java.time.ZonedDateTime

data class PurchaseHistory(
    val basketList: List<BasketProduct>,
    val purchaseAt: ZonedDateTime
)