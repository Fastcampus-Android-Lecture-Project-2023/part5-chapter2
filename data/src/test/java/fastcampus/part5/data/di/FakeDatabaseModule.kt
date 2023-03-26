package fastcampus.part5.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fastcampus.part5.data.db.ApplicationDatabase
import fastcampus.part5.data.db.dao.BasketDao
import fastcampus.part5.data.db.dao.LikeDao
import fastcampus.part5.data.db.dao.PurchaseHistoryDao
import fastcampus.part5.data.db.dao.SearchDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FakeDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ApplicationDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ApplicationDatabase::class.java,
            ApplicationDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchDao(database: ApplicationDatabase): SearchDao {
        return database.searchDao()
    }

    @Provides
    @Singleton
    fun provideLikeDao(database: ApplicationDatabase): LikeDao {
        return database.likeDao()
    }

    @Provides
    @Singleton
    fun provideBasketDao(database: ApplicationDatabase): BasketDao {
        return database.basketDao()
    }

    @Provides
    @Singleton
    fun providePurchaseHistoryDao(database: ApplicationDatabase): PurchaseHistoryDao {
        return database.purchaseHistoryDao()
    }
}