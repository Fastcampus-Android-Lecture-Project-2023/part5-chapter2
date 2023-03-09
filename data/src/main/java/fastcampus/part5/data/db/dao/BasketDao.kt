package fastcampus.part5.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fastcampus.part5.data.db.entity.BasketProductEntity

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket")
    suspend fun getAll() : List<BasketProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item : BasketProductEntity)

    @Query("DELETE FROM basket WHERE productId=:id")
    suspend fun delete(id: String)

}