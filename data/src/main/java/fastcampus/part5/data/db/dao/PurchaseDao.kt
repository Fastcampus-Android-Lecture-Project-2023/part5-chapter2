package fastcampus.part5.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fastcampus.part5.data.db.entity.LikeProductEntity
import fastcampus.part5.data.db.entity.PurchaseProductEntity

@Dao
interface PurchaseDao {

    @Query("SELECT * FROM purchase")
    suspend fun getAll() : List<PurchaseProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item : PurchaseProductEntity)

    @Query("DELETE FROM purchase WHERE productId=:id")
    suspend fun delete(id: String)

}