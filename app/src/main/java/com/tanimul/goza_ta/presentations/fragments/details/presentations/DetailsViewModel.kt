package com.tanimul.goza_ta.presentations.fragments.details.presentations

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
class DetailsViewModel : ViewModel() {

    private val _uiAction = Channel<DetailsUiActions>(Channel.CONFLATED)
    val uiAction = _uiAction.receiveAsFlow()

    fun uiActions(actions: DetailsUiActions) {
        _uiAction.trySend(actions)
    }


}

sealed interface DetailsUiActions {
    data object OnNavigateBack : DetailsUiActions
    data object OnBookNow : DetailsUiActions
}