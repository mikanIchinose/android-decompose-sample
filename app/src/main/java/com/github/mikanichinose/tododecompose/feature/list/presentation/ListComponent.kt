package com.github.mikanichinose.tododecompose.feature.list.presentation

import com.arkivanov.decompose.value.Value

interface ListComponent {
    val models: Value<List<TodoUiModel>>
    fun onItemClicked(model: TodoUiModel)
}
