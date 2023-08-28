package com.github.mikanichinose.tododecompose.feature.detail.presentation

import com.github.mikanichinose.tododecompose.domain.model.TodoDomainModel

sealed interface TodoUiModel {
    data class Success(
        val id: Int,
        val title: String,
        val detail: String,
    ) : TodoUiModel

    object Loading : TodoUiModel
    object Error : TodoUiModel
}

fun TodoDomainModel.toUiModel(): TodoUiModel =
    TodoUiModel.Success(
        id = id.value,
        title = title,
        detail = detail,
    )
