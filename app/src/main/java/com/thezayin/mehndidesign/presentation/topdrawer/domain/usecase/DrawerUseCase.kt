package com.thezayin.mehndidesign.presentation.topdrawer.domain.usecase

import com.thezayin.mehndidesign.presentation.topdrawer.domain.model.DrawerItem
import com.thezayin.mehndidesign.presentation.topdrawer.domain.repository.DrawerRepository
import com.thezayin.mehndidesign.presentation.utils.Response
import kotlinx.coroutines.flow.Flow

interface DrawerUseCase : () -> Flow<Response<List<DrawerItem>>>
class DrawerUseCaseImpl(
    private val repository: DrawerRepository
) : DrawerUseCase {
    override fun invoke() = repository.getDrawerItems()
}