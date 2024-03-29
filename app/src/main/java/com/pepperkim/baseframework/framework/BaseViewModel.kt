package com.pepperkim.baseframework.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
//    protected val compositeDisposable = CompositeDisposable() // use rx.

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _isLottieLoading = MutableLiveData<Boolean>(false)
    val isLottieLoading: LiveData<Boolean> get() = _isLottieLoading

    override fun onCleared() {
//        compositeDisposable.dispose()
        super.onCleared()
    }

    fun showProgress() {
        _isLoading.value = true
    }

    fun hideProgress() {
        _isLoading.value = false
    }

    fun showLottieProgress() {
        _isLottieLoading.value = true
    }

    fun hideLottieProgress() {
        _isLottieLoading.value = false
    }
}