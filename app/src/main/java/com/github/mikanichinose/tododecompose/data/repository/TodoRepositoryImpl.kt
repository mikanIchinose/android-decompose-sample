package com.github.mikanichinose.tododecompose.data.repository

import com.github.mikanichinose.tododecompose.data.model.TodoModel
import com.github.mikanichinose.tododecompose.data.model.toDTO
import com.github.mikanichinose.tododecompose.data.model.toDomainModel
import com.github.mikanichinose.tododecompose.domain.model.TodoDomainModel
import com.github.mikanichinose.tododecompose.domain.model.TodoId
import com.github.mikanichinose.tododecompose.domain.repository.TodoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

class TodoRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TodoRepository {
    override suspend fun getAll(): Flow<List<TodoDomainModel>> {
        return flowOf(fakeTodos.map(TodoModel::toDomainModel)).flowOn(ioDispatcher)
    }

    override suspend fun getById(id: TodoId): TodoDomainModel? {
        return fakeTodos.find { it.id == id.value }?.toDomainModel()
    }

    override suspend fun delete(id: TodoId) {
        fakeTodos.removeIf {
            it.id == id.value
        }
    }

    override suspend fun add(todo: TodoDomainModel) {
        fakeTodos.add(todo.toDTO())
    }
}

private var fakeTodos = mutableListOf(
    TodoModel(id = 1, title = "task1", detail = "detail"),
    TodoModel(id = 2, title = "task2", detail = "detail"),
    TodoModel(id = 3, title = "task3", detail = "detail"),
    TodoModel(id = 4, title = "task4", detail = "detail"),
)