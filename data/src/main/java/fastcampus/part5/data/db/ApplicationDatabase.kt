package fastcampus.part5.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import fastcampus.part5.data.db.dao.BasketDao
import fastcampus.part5.data.db.dao.LikeDao
import fastcampus.part5.data.db.dao.PurchaseDao
import fastcampus.part5.data.db.entity.BasketProductEntity
import fastcampus.part5.data.db.entity.LikeProductEntity
import fastcampus.part5.data.db.entity.PurchaseProductEntity

@Database(
    entities = [
        PurchaseProductEntity::class,
        LikeProductEntity::class,
        BasketProductEntity::class,
    ],
    version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "ApplicationDatabase.db"
    }

    abstract fun purchaseDao() : PurchaseDao

    abstract fun likeDao() : LikeDao

    abstract fun basketDao() : BasketDao
}