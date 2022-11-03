package com.example.fitin_kotlin.ui.bookmarkmyrecruitment

import android.util.Log
import androidx.lifecycle.*
import com.example.fitin_kotlin.data.model.network.response.ResponseSavedBookmark
import com.example.fitin_kotlin.data.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkMyRecruitmentViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    state: SavedStateHandle
) : ViewModel(){
    // TODO state는 News에서 받았는데 argType을 정하지 않고 받을 수 있는지 확인(만약 안된다면 navigation에 argtype 추가해야함 그리고 news에서 넘겨줘야함 action에서)
    private val requestBookmark = MutableLiveData<Long>()
    val bookmarkId = MutableLiveData<Long>()

    val requestSavedRecruit: MutableLiveData<ResponseSavedBookmark?> = MutableLiveData<ResponseSavedBookmark?>()

    private val _myRecruitment = MutableLiveData<List<ResponseSavedBookmark>>()
    val myRecruitment: LiveData<List<ResponseSavedBookmark>>
        get() = _myRecruitment

    init {
        requestBookmark.value = state.getLiveData<Long>("bookmarkId").value
        Log.e("state 확인", requestBookmark.value.toString())
        getBookmark()
    }

    private fun getBookmark() {
        val bookmarkId: Long = requestBookmark.value!!
        viewModelScope.launch {
            _myRecruitment.value = bookmarkRepository.findBookmark(bookmarkId)
        }
    }

    fun displaySavedRecruit(responseSavedBookmark: ResponseSavedBookmark) {
        requestSavedRecruit.value = responseSavedBookmark
    }

    fun displaySavedRecruitFinish() {
        requestSavedRecruit.value = null
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

    // TODO 추가 상세화면으로 넘어가게 클릭 리스너 이벤트 처리하기 & 백버튼 추가 구현하기

    private val _eventBookmarkMyNews = MutableLiveData<Boolean>()
    val eventBookmarkMyNews: LiveData<Boolean>
        get() = _eventBookmarkMyNews

    fun onEventBookmarkMyNews() {
        bookmarkId.value = requestBookmark.value
        _eventBookmarkMyNews.value = true
    }

    fun onEventBookmarkMyNewsComplete() {
        _eventBookmarkMyNews.value = false
    }
}