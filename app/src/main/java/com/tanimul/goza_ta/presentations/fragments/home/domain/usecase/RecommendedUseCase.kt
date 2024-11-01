package com.tanimul.goza_ta.presentations.fragments.home.domain.usecase

import com.tanimul.goza_ta.common.domain.model.Recommended
import com.tanimul.goza_ta.network.core.Resource
import com.tanimul.goza_ta.presentations.fragments.home.domain.repository.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RecommendedUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(parameters: Unit):  Flow<Resource<List<Recommended>>> {
        Timber.d("recommend")
       return repository.recommended()
    }


}