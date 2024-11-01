package com.tanimul.goza_ta.presentations.fragments.home.presentations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanimul.goza_ta.R
import com.tanimul.goza_ta.common.domain.model.Recommended
import com.tanimul.goza_ta.presentations.fragments.home.domain.model.Category
import com.tanimul.goza_ta.presentations.fragments.home.domain.usecase.RecommendedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recommendedUseCase: RecommendedUseCase
) : ViewModel() {

    private val _uiAction = Channel<HomeUiActions>(Channel.CONFLATED)
    val
            uiAction = _uiAction.receiveAsFlow()

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _recommended = Channel<Unit>(Channel.CONFLATED)
    val recommended = _recommended.receiveAsFlow().flatMapLatest {
        recommendedUseCase(Unit)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)


    init {
        loadCategories()

        viewModelScope.launch {
            recommended()
        }
    }

    private fun loadCategories() {
        _categories.value = listOf(
            Category(
                id = 1,
                title = "Flights",
                image = R.drawable.ic_category_flights
            ),
            Category(
                id = 2,
                title = "Hotels",
                image = R.drawable.ic_category_hotel
            ),
            Category(
                id = 3,
                title = "Visa",
                image = R.drawable.ic_category_visa
            ),
            Category(
                id = 4,
                title = "Buses",
                image = R.drawable.ic_category_buses
            ), Category(
                id = 5,
                title = "Flights",
                image = R.drawable.ic_category_flights
            ),
            Category(
                id = 6,
                title = "Hotels",
                image = R.drawable.ic_category_hotel
            ),
            Category(
                id = 7,
                title = "Visa",
                image = R.drawable.ic_category_visa
            ),
            Category(
                id = 8,
                title = "Buses",
                image = R.drawable.ic_category_buses
            )
        )
    }

    private fun recommended() {
        _recommended.trySend(Unit)
    }

    fun uiActions(actions: HomeUiActions) {
        _uiAction.trySend(actions)
    }

    fun selectedPlace(recommended: Recommended) {
        Timber.d("selectedPlace $recommended")
        uiActions(
            HomeUiActions.OnSelectedPlace(
                recommended
            )
        )
    }

    fun seeAll() {
        Timber.d("seeAll")
        viewModelScope.launch {
            recommended.collect {
                it?.data?.let { data ->
                    Timber.d("recommended: $data")
                    uiActions(
                        HomeUiActions.OnNavigateSeeAll(
                            data
                        )
                    )
                }
            }
        }

    }

    fun searchPlaces(key: String) {
        Timber.d("search place: $key")
    }

}

sealed interface HomeUiActions {
    data class OnSelectedPlace(val recommended: Recommended) : HomeUiActions
    data class OnNavigateSeeAll(val recommendedList: List<Recommended>) : HomeUiActions
}