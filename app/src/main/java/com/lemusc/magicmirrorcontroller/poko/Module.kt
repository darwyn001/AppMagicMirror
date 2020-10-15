package com.lemusc.magicmirrorcontroller.poko

import com.google.gson.annotations.SerializedName

data class Module(
    @SerializedName("index") val index: String,
    @SerializedName("identifier") val id: String,
    @SerializedName("name") val nombre: String,
    @SerializedName("path") val ruta: String,
    @SerializedName("file") val archivo: String,
    @SerializedName("config") val configuracion: String,
    @SerializedName("classes") var clase: String,
    @SerializedName("hidden") var mostrar: Boolean
)