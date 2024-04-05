package com.thezayin.mehndidesign.presentation.topdrawer.domain.model

import java.util.UUID

data class DrawerItem(
    val id: UUID = UUID.randomUUID(),
    val icon: Int? = null,
    val title: String? = null,
    val isSelected: Boolean? = null,
)
