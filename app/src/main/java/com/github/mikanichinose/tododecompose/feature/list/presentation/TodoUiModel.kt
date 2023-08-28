package com.github.mikanichinose.tododecompose.feature.list.presentation

import com.github.mikanichinose.tododecompose.domain.model.TodoDomainModel
import com.github.mikanichinose.tododecompose.domain.model.TodoId

data class TodoUiModel(
    val id: TodoId,
    val title: String,
)

fun TodoDomainModel.toUiModel(): TodoUiModel =
    TodoUiModel(
        id = id,
        title = title
    )