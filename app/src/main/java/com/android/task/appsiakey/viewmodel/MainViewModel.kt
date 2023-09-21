package com.android.task.appsiakey.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.task.appsiakey.model.AppIsKey
import com.android.task.appsiakey.repository.Repository
import com.android.task.appsiakey.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    private val _bookApiData = MutableLiveData<NetworkResult<AppIsKey>>(NetworkResult.Loading())

    fun getPixBay(): LiveData<NetworkResult<AppIsKey>> {
        viewModelScope.launch {
            _bookApiData.postValue(repository.getPixBay())
        }
        return _bookApiData
    }
}
