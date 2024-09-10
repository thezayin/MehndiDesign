package com.thezayin.databases.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thezayin.databases.dao.ArabicDao
import com.thezayin.databases.dao.BridalDao
import com.thezayin.databases.dao.ClassicDao
import com.thezayin.databases.dao.FavouriteDao
import com.thezayin.databases.dao.FingerDao
import com.thezayin.databases.dao.FootDao
import com.thezayin.databases.dao.HomeImageDao
import com.thezayin.databases.dao.IndianDao
import com.thezayin.databases.dao.IndoDao
import com.thezayin.databases.dao.MoroccanDao
import com.thezayin.databases.dao.PakistaniDao
import com.thezayin.databases.dao.TattooDao
import com.thezayin.databases.dao.TikkiDao
import com.thezayin.databases.models.ArabicModel
import com.thezayin.databases.models.BridalModel
import com.thezayin.databases.models.ClassicModel
import com.thezayin.databases.models.FingerModel
import com.thezayin.databases.models.FootModel
import com.thezayin.databases.models.ImageModel
import com.thezayin.databases.models.IndianModel
import com.thezayin.databases.models.IndoModel
import com.thezayin.databases.models.LikeImageModel
import com.thezayin.databases.models.MoroccanModel
import com.thezayin.databases.models.PakistaniModel
import com.thezayin.databases.models.TattooModel
import com.thezayin.databases.models.TikkiModel


@Database(
    entities = [
        IndoModel::class,
        FootModel::class,
        TikkiModel::class,
        ImageModel::class,
        TattooModel::class,
        IndianModel::class,
        BridalModel::class,
        FingerModel::class,
        ArabicModel::class,
        ClassicModel::class,
        MoroccanModel::class,
        PakistaniModel::class,
        LikeImageModel::class
    ],
    version = 9,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun homeImageDao(): HomeImageDao
    abstract fun pakistaniDao(): PakistaniDao
    abstract fun moroccanDao(): MoroccanDao
    abstract fun classicDao(): ClassicDao
    abstract fun tattooDao(): TattooDao
    abstract fun indianDao(): IndianDao
    abstract fun arabicDao(): ArabicDao
    abstract fun fingerDao(): FingerDao
    abstract fun bridalDao(): BridalDao
    abstract fun tikkiDao(): TikkiDao
    abstract fun indoDao(): IndoDao
    abstract fun footDao(): FootDao
    abstract fun favouriteDao(): FavouriteDao
}