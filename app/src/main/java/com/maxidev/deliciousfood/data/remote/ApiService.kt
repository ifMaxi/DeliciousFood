package com.maxidev.deliciousfood.data.remote

import com.maxidev.deliciousfood.data.remote.dto.CategoriesDTO
import com.maxidev.deliciousfood.data.remote.dto.FilterByCategoryDTO
import com.maxidev.deliciousfood.data.remote.dto.MealDTO
import com.maxidev.deliciousfood.utils.Constants.BY_ID
import com.maxidev.deliciousfood.utils.Constants.FILTER
import com.maxidev.deliciousfood.utils.Constants.MEAL_CATEGORIES
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

    @GET(MEAL_CATEGORIES)
    suspend fun getCategories(
        @Query("page") page: String?
    ): CategoriesDTO

    @GET(FILTER)
    suspend fun getFilterByCategory(
        @Query("c") c: String
    ): FilterByCategoryDTO
}