package com.example.fitin_kotlin.ui.bookmarkmynews

import android.util.Log
import androidx.lifecycle.*
import com.example.fitin_kotlin.data.model.network.response.ResponseSavedBookmark
import com.example.fitin_kotlin.data.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkMyNewsViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    state: SavedStateHandle
) : ViewModel(){
    // TODO state로 넘겨 받은 id를 기준으로 bookmark/{bookmarkId} API 호출함
    private val requestBookmark = MutableLiveData<Long>()
    val bookmarkId = MutableLiveData<Long>()

    val requestSavedNews: MutableLiveData<ResponseSavedBookmark?> = MutableLiveData<ResponseSavedBookmark?>()

    private val _myNews = MutableLiveData<List<ResponseSavedBookmark>>()
    val myNews: LiveData<List<ResponseSavedBookmark>>
        get() = _myNews

    init {
        requestBookmark.value = state.getLiveData<Long>("bookmarkId").value
        Log.e("state 확인", requestBookmark.value.toString())
        getBookmark()
    }

    private fun getBookmark() {
        val bookmarkId: Long = requestBookmark.value!!
        viewModelScope.launch {
            _myNews.value = bookmarkRepository.findBookmark(bookmarkId)
        }
    }

    fun displaySavedNews(responseSavedBookmark: ResponseSavedBookmark) {
        requestSavedNews.value = responseSavedBookmark
    }

    fun displaySavedNewsFinish() {
        requestSavedNews.value = null
    }

    // TODO 추가 상세화면으로 넘어가게 클릭 리스너 이벤트 처리하기 & 백버튼 추가 구현하기
    private val _eventBookmarkMyRecruitment = MutableLiveData<Boolean>()
    val eventBookmarkMyRecruitment: LiveData<Boolean>
        get() = _eventBookmarkMyRecruitment

    fun onEventBookmarkMyRecruit() {
        bookmarkId.value = requestBookmark.value
        _eventBookmarkMyRecruitment.value = true
    }

    fun onEventBookmakMyRecruitComplete() {
        _eventBookmarkMyRecruitment.value = false
    }

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
