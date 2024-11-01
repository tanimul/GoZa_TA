package com.tanimul.goza_ta.presentations.fragments.see_all.presentations

import androidx.lifecycle.ViewModel
import com.tanimul.goza_ta.common.domain.model.Recommended
import com.tanimul.goza_ta.presentations.fragments.home.domain.usecase.RecommendedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SeeAllViewModel : ViewModel() {

    private val _uiAction = Channel<SeeAllUiActions>(Channel.CONFLATED)
    val uiAction = _uiAction.receiveAsFlow()

    private val _recommended = MutableStateFlow<List<Recommended>>(emptyList())
    val recommended: StateFlow<List<Recommended>> = _recommended

    fun uiActions(actions: SeeAllUiActions) {
        _uiAction.trySend(actions)
    }

    fun recommended(recommended: List<Recommended>) {
        _recommended.value = recommended
    }

    fun selectedPlace(recommended: Recommended) {
        uiActions(
            SeeAllUiActions.SelectedPlace(
                recommended
            )
        )
    }


}

sealed interface SeeAllUiActions {
    data object OnNavigateBack : SeeAllUiActions
    data class SelectedPlace(val recommended: Recommended) : SeeAllUiActions
}