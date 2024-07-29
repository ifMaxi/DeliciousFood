package com.maxidev.deliciousfood.data.remote

import com.maxidev.deliciousfood.data.remote.dto.MealDTO
import com.maxidev.deliciousfood.utils.Constants.BY_ID
import com.maxidev.deliciousfood.utils.Constants.RANDOM_SINGLE
import com.maxidev.deliciousfood.utils.Constants.SEARCH
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(SEARCH)
    suspend fun getSearchMeal(
        @Query("s") s: String,
        @Query("page") page: Int?
    ): MealDTO

    @GET(BY_ID)
    suspend fun getMealById(
        @Query("i") i: String
    ): MealDTO

    @GET(RANDOM_SINGLE)
    suspend fun getRandomMeal(): MealDTO
}