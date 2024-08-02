package com.thezayin.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thezayin.categories.data.local.dao.ArabicDao
import com.thezayin.categories.data.local.dao.BridalDao
import com.thezayin.categories.data.local.dao.ClassicDao
import com.thezayin.categories.data.local.dao.FingerDao
import com.thezayin.categories.data.local.dao.FootDao
import com.thezayin.categories.data.local.dao.IndianDao
import com.thezayin.categories.data.local.dao.IndoDao
import com.thezayin.categories.data.local.dao.MoroccanDao
import com.thezayin.categories.data.local.dao.PakistaniDao
import com.thezayin.categories.data.local.dao.TattooDao
import com.thezayin.categories.data.local.dao.TikkiDao
import com.thezayin.entities.ArabicModel
import com.thezayin.entities.BridalModel
import com.thezayin.entities.ClassicModel
import com.thezayin.entities.FingerModel
import com.thezayin.entities.FootModel
import com.thezayin.entities.ImageModel
import com.thezayin.entities.IndianModel
import com.thezayin.entities.IndoModel
import com.thezayin.entities.LikeImageModel
import com.thezayin.entities.MoroccanModel
import com.thezayin.entities.PakistaniModel
import com.thezayin.entities.TattooModel
import com.thezayin.entities.TikkiModel
import com.thezayin.home.data.local.dao.HomeImageDao
import com.thezayin.like.data.dao.LikeDao

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
    version = 8,
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
    abstract fun likeDao(): LikeDao
}