package com.telyu.nourimate.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(

    @field:SerializedName("makan siang")
    val makanSiang: List<MakanSiangItem>,

    @field:SerializedName("sarapan")
    val sarapan: List<SarapanItem>,

    @field:SerializedName("makan malam")
    val makanMalam: List<MakanMalamItem>
)

data class MakanMalamItem(

    @field:SerializedName("Recipe ID")
    val recipeID: Int
)

data class MakanSiangItem(

    @field:SerializedName("Recipe ID")
    val recipeID: Int
)

data class SarapanItem(

    @field:SerializedName("Recipe ID")
    val recipeID: Int
)