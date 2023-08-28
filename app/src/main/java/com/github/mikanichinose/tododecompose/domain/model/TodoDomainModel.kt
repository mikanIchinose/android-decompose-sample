package com.github.mikanichinose.tododecompose.domain.model

data class TodoDomainModel(
    val id: TodoId,
    val title: String,
    val detail: String,
)

@JvmInline
value class TodoId(val value: Int)