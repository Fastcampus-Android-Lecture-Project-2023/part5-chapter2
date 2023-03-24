package fastcampus.part5.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import fastcampus.part5.data.db.converter.BasketConverter
import fastcampus.part5.data.db.converter.LikeConverter
import fastcampus.part5.data.db.converter.PurchaseConverter
import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.model.Price
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.Shop

@Entity(tableName = "purchase")
@TypeConverters(PurchaseConverter::class)
data class PurchaseProductEntity(
    @PrimaryKey
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: Price,
    val category: Category,
    val shop: Shop,
    val isNew: Boolean,
    val isFreeShipping: Boolean,
    val isLike: Boolean
)
fun PurchaseProductEntity.toDomainModel() : Product {
    return Product(
        productId = productId,
        productName = productName,
        imageUrl = imageUrl,
        price = price,
        category = category,
        shop = shop,
        isNew = isNew,
        isFreeShipping = isFreeShipping,
        isLike = isLike
    )
}