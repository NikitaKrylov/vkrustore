package com.example.vkrustore.feature.common

import com.example.vkrustore.feature.common.state.BaseAction
import com.example.vkrustore.feature.common.state.BaseSideEffect
import com.example.vkrustore.feature.common.state.BaseState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State: BaseState, in Action: BaseAction?, SideEffect: BaseSideEffect?> {

    protected val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()

    abstract val state: StateFlow<State>

    abstract fun processAction(action: Action)
}