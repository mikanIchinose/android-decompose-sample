package com.github.mikanichinose.tododecompose.domain.repository

import com.github.mikanichinose.tododecompose.domain.model.TodoDomainModel
import com.github.mikanichinose.tododecompose.domain.model.TodoId
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getAll(): Flow<List<TodoDomainModel>>

    suspend fun getById(id: TodoId): TodoDomainModel?

    suspend fun delete(id: TodoId)

    suspend fun add(todo: TodoDomainModel)
}