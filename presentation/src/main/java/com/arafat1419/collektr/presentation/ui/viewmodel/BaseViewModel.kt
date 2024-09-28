package com.arafat1419.collektr.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : IViewState, Event : IViewEvent> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }

    val currentState: State get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState

    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow()
    val uiEvent = _uiEvent.asSharedFlow()

    abstract fun createInitialState(): State
    abstract fun onTriggerEvent(event: Event)

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    protected fun setEvent(event: Event) {
        viewModelScope.launch { _uiEvent.emit(event) }
    }

    protected fun <T> handleResource(
        resource: Resource<T>,
        setLoading: (Boolean) -> Unit,
        setError: (String) -> Unit,
        onSuccess: (data: T) -> Unit
    ) {
        when (resource) {
            is Resource.Error -> {
                setLoading(false)
                setError(resource.message.toString())
            }

            is Resource.Loading -> setLoading(true)
            is Resource.Success -> {
                setLoading(false)
                setError("")
                onSuccess(resource.data!!)
            }
        }
    }

}

interface IViewState
interface IViewEvent