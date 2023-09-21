package com.android.task.appsiakey.repository

import com.android.task.appsiakey.api.AppsKeyApiInterface
import com.android.task.appsiakey.model.AppIsKey
import com.android.task.appsiakey.utils.NetworkResult
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val apiInterface: AppsKeyApiInterface) {

    suspend fun getPixBay(): NetworkResult<AppIsKey> {
        val response = apiInterface.getPixBay()
        return try {
            if (response?.isSuccessful == true
            ) {
                val responseBody = response.body()
                if (responseBody != null) {
                    NetworkResult.Success(responseBody)
                } else NetworkResult.Error("Something went wrong...!")
            } else {
                val message = response?.errorBody().toString()
                NetworkResult.Error(message)
            }
        } catch (e: HttpException) {
            NetworkResult.Error(e.message ?: "Something went wrong")
        } catch (e: IOException) {
            NetworkResult.Error("Please check your network connection")
        } catch (e: Exception) {
            NetworkResult.Error("Something went wrong")
        }

    }
}