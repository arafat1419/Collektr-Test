package com.arafat1419.collektr.presentation.ui.features.home

import androidx.lifecycle.viewModelScope
import com.arafat1419.collektr.domain.usecase.auction.categories.GetAuctionCategoriesUseCase
import com.arafat1419.collektr.domain.usecase.auction.list.GetAuctionsUseCase
import com.arafat1419.collektr.presentation.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAuctionsUseCase: GetAuctionsUseCase,
    private val getAuctionCategoriesUseCase: GetAuctionCategoriesUseCase
) : BaseViewModel<HomeViewState, HomeViewEvent>() {
    override fun createInitialState(): HomeViewState = HomeViewState()

    override fun onTriggerEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.NavigateToDetails -> setEvent(HomeViewEvent.NavigateToDetails(event.auction))
            is HomeViewEvent.GetAuctions -> {
                setState { copy(selectedCategory = auctionCategories.first { it.id == event.categoryId }) }
                getAuctions(event.categoryId)
            }

            HomeViewEvent.GetAuctionsCategories -> getAuctionsCategories()
        }
    }

    private fun getAuctionsCategories() {
        viewModelScope.launch {
            getAuctionCategoriesUseCase.invoke().collect { resource ->
                handleResource(
                    resource = resource,
                    setLoading = { setState { copy(isLoading = it) } },
                    setError = { setState { copy(error = it) } },
                ) { data ->
                    setState { copy(auctionCategories = data) }
                    if (data.isNotEmpty()) {
                        onTriggerEvent(HomeViewEvent.GetAuctions(currentState.auctionCategories[0].id))
                    }
                }
            }
        }
    }

    private fun getAuctions(categoryId: Int) {
        viewModelScope.launch {
            getAuctionsUseCase.invoke(categoryId)
                .collect { resource ->
                    handleResource(
                        resource = resource,
                        setLoading = { setState { copy(isLoading = it) } },
                        setError = { setState { copy(error = it) } },
                    ) {
                        setState { copy(auctions = it) }
                    }
                }
        }
    }
}

