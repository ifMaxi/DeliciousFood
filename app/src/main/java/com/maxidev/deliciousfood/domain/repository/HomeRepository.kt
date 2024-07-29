package com.maxidev.deliciousfood.domain.repository

import com.maxidev.deliciousfood.domain.model.RandomMeal

interface HomeRepository {

    suspend fun fetchRandomMeal(): RandomMeal
}