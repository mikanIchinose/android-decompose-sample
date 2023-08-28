package com.github.mikanichinose.tododecompose.data.model

import com.github.mikanichinose.tododecompose.domain.model.TodoDomainModel
import com.github.mikanichinose.tododecompose.domain.model.TodoId

data class TodoModel(
    val id: Int,
    val title: String,
    val detail: String,
)

fun TodoModel.toDomainModel(): TodoDomainModel {
    return TodoDomainModel(
        id = TodoId(id),
        title = title,
        detail = detail,
    )
}

fun TodoDomainModel.toDTO(): TodoModel {
    return TodoModel(
        id = id.value,
        title,
        detail,
    )
}