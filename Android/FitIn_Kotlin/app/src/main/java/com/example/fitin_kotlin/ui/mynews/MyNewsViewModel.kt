package com.example.fitin_kotlin.ui.mynews

import androidx.lifecycle.*
import com.example.fitin_kotlin.data.model.network.response.ResponseNews
import com.example.fitin_kotlin.data.repository.SaveBookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyNewsViewModel @Inject constructor(
    private val saveBookmarkRepository: SaveBookmarkRepository,
    state: SavedStateHandle
) : ViewModel() {
    // TODO BookmarkMyNews에서 state로 saveId 받아서 호출함
    private val savedNews = MutableLiveData<Long>()

    // TODO LiveData saveDB 조회한 news 값을 가져와 DataBinding으로 연결하게 제공
    private val _saveNews = MutableLiveData<ResponseNews>()
    val saveNews: LiveData<ResponseNews>
        get() = _saveNews

    init {
        savedNews.value = state.getLiveData<Long>("savedNewsId").value
        getSavedNews()
    }

    private fun getSavedNews() {
        val savedNewsId: Long = savedNews.value!!
        viewModelScope.launch {
            _saveNews.value = saveBookmarkRepository.getSaveNews(savedNewsId)
        }
    }

    // TODO 이벤트 처리 & 백버튼 구현

    private val _eventBack = MutableLiveData<Boolean>()
    val eventBack: LiveData<Boolean>
        get() = _eventBack

    fun onBack() {
        _eventBack.value = true
    }

    fun onBackComplete() {
        _eventBack.value = false
    }
}