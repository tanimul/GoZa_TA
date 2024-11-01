package com.tanimul.goza_ta.network.api

import com.tanimul.goza_ta.common.constants.ApiConstants
import com.tanimul.goza_ta.common.domain.model.Recommended
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET(ApiConstants.API_RECOMMENDED)
    suspend fun recommended(): Response<List<Recommended>>
}