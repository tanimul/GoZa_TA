package com.tanimul.goza_ta.presentations.fragments.home.domain.repository

import com.tanimul.goza_ta.common.domain.model.Recommended
import com.tanimul.goza_ta.network.core.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun recommended(): Flow<Resource<List<Recommended>>>
}
