package com.android.task.appsiakey.api

import com.android.task.appsiakey.model.AppIsKey
import com.android.task.appsiakey.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppsKeyApiInterface {

    @GET("?key=${Constants.API_KEY}")
    suspend fun getPixBay(
        @Query("q") q: String? = "kitten",
        @Query("image_type") imageType: String? = "photo",
        @Query("pretty") pretty: Boolean? = true
    ): Response<AppIsKey>?
}
