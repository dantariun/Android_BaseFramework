package com.pepperkim.baseframework

import androidx.lifecycle.*
import com.pepperkim.baseframework.api.MainRepository
import com.pepperkim.baseframework.api.model.kakao.MovieContents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel(), DefaultLifecycleObserver {

    val TAG = "MainViewModel"
    var livedata = MutableLiveData<String>("no message")

    private val _movieContents by lazy { MutableLiveData<MovieContents>() }
    val movieContents:LiveData<MovieContents> by this::_movieContents

    fun getData() = livedata

    fun loadData(){
        viewModelScope.launch {
            val data = repository.searchMovieContent("이효리")
            when (data.isSuccessful){
                true -> _movieContents.postValue(data.body())
                else -> Timber.d(TAG, "TEST -> ${data.errorBody()}")
            }
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

    }

    fun getUserId(){

        viewModelScope.launch {

            /*App.getInstance().getDataStore().getUserID.collect{
                // do it Something
            }*/

            livedata.postValue(App.getInstance().getDataStore().getUserID().first())
        }
    }

    fun setUserId(id:String){
        viewModelScope.launch {
            App.getInstance().getDataStore().setUserId(id)
        }
    }
}