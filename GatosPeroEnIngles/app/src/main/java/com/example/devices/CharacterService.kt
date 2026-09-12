package com.example.devices

import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("images/search")
    suspend fun getCharacters(
        @Query("limit") limit: Int = 20,
        @Query("has_breeds") hasBreeds: Int = 1
    ): List<Character>
}