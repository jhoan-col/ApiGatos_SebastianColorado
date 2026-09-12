package com.example.devices

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id") val id: String,
    @SerializedName("url") val image: String?,
    @SerializedName("breeds") val breeds: List<Breed>?
) {
    // Si la API devuelve información de raza la usamos, de lo contrario mostramos un fallback
    val name: String get() = breeds?.firstOrNull()?.name ?: "Gato Desconocido"
    val origin: String? get() = breeds?.firstOrNull()?.origin
    val temperament: String? get() = breeds?.firstOrNull()?.temperament
    val description: String? get() = breeds?.firstOrNull()?.description
}

data class Breed(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin") val origin: String?,
    @SerializedName("temperament") val temperament: String?,
    @SerializedName("description") val description: String?
)