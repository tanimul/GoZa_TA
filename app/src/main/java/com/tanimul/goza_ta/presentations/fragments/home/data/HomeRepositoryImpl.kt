package com.tanimul.goza_ta.presentations.fragments.home.data

import android.net.ConnectivityManager
import com.tanimul.goza_ta.common.domain.model.Recommended
import com.tanimul.goza_ta.network.api.ApiInterface
import com.tanimul.goza_ta.network.core.Resource
import com.tanimul.goza_ta.network.core.networkCall
import com.tanimul.goza_ta.presentations.fragments.home.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HomeRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val connectivityManager: ConnectivityManager
): HomeRepository{

    override suspend fun recommended(): Flow<Resource<List<Recommended>>> {
        return networkCall(connectivityManager) {
            apiInterface.recommended()
        }.flowOn(Dispatchers.IO)
    }

}
