package com.github.mikanichinose.tododecompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.github.mikanichinose.tododecompose.data.repository.TodoRepositoryImpl
import com.github.mikanichinose.tododecompose.domain.repository.TodoRepository
import com.github.mikanichinose.tododecompose.feature.detail.domain.usecase.GetTodoUseCase
import com.github.mikanichinose.tododecompose.feature.detail.presentation.DefaultDetailComponent
import com.github.mikanichinose.tododecompose.feature.list.domain.usecase.GetTodosUseCase
import com.github.mikanichinose.tododecompose.feature.list.presentation.DefaultListComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {
    private val repository: TodoRepository = TodoRepositoryImpl()
    private val navigation = StackNavigation<Config>()

    private val _stack = childStack(
        source = navigation,
        initialConfiguration = Config.List,
        childFactory = ::child
    )

    init {
        backHandler.register(BackCallback { navigation.pop() })
    }

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = _stack

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.List -> RootComponent.Child.ListChild(
                DefaultListComponent(
                    componentContext = componentContext,
                    getTodos = GetTodosUseCase(repository),
                    onClicked = { model ->
                        navigation.push(
                            Config.Detail(model.id.value)
                        )
                    }
                ))

            is Config.Detail -> RootComponent.Child.DetailChild(
                DefaultDetailComponent(
                    componentContext = componentContext,
                    id = config.id,
                    getTodo = GetTodoUseCase(repository)
                )
            )
        }

    sealed interface Config : Parcelable {
        @Parcelize
        object List : Config

        @Parcelize
        data class Detail(
            val id: Int
        ) : Config
    }
}