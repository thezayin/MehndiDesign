package com.thezayin.mehndidesign.presentation.topdrawer.domain.repository

import com.thezayin.mehndidesign.presentation.topdrawer.domain.model.DrawerItem
import com.thezayin.mehndidesign.presentation.utils.Response
import kotlinx.coroutines.flow.Flow

interface DrawerRepository {
    fun getDrawerItems(): Flow<Response<List<DrawerItem>>>
}