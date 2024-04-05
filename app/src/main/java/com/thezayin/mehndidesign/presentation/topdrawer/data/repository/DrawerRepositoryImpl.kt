package com.thezayin.mehndidesign.presentation.topdrawer.data.repository

import com.thezayin.mehndidesign.R
import com.thezayin.mehndidesign.presentation.topdrawer.domain.model.DrawerItem
import com.thezayin.mehndidesign.presentation.topdrawer.domain.repository.DrawerRepository
import com.thezayin.mehndidesign.presentation.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DrawerRepositoryImpl() : DrawerRepository {
    override fun getDrawerItems(): Flow<Response<List<DrawerItem>>> = flow {
        try {
            emit(Response.Loading)
            val list = listOf(
                DrawerItem(
                    icon = R.drawable.ic_bridal_mehndi,
                    title = "Bridal Mehndi",
                    isSelected = false
                ),
                DrawerItem(
                    icon = R.drawable.ic_eid_mehndi,
                    title = "Eid Henna",
                    isSelected = false
                ),
                DrawerItem(
                    icon = R.drawable.ic_punjabi_mehndi,
                    title = "Punjabi Mehndi",
                    isSelected = false
                ),
                DrawerItem(
                    icon = R.drawable.ic_pakistani_mehndi,
                    title = "Pakistani Mehndi",
                    isSelected = false
                ),
                DrawerItem(
                    icon = R.drawable.ic_indian_mehndi,
                    title = "Indian Henna",
                    isSelected = false
                ),
                DrawerItem(
                    icon = R.drawable.ic_henna,
                    title = "Henna",
                    isSelected = false
                ),
                DrawerItem(
                    icon = R.drawable.ic_jewellery_mehndi,
                    title = "Jewellery Mehndi",
                    isSelected = false
                ),
                DrawerItem(
                    icon = R.drawable.ic_indian_henna,
                    title = "Indian Mehndi",
                    isSelected = false
                ),
                DrawerItem(
                    icon = R.drawable.ic_western_indian,
                    title = "Western Mehndi",
                    isSelected = false
                ),
                DrawerItem(
                    icon = R.drawable.ic_african_mehndi,
                    title = "African Mehndi",
                    isSelected = false
                ),
                DrawerItem(
                    icon = R.drawable.ic_tattoo_mehndi,
                    title = "Tattoo Mehndi",
                    isSelected = false
                ),
            )
            emit(Response.Success(list))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }
}