package com.example.fitin_kotlin.ui.addbookmark

import androidx.lifecycle.*
import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.model.network.request.RequestCreateBookmark
import com.example.fitin_kotlin.data.model.network.response.ResponseBookmark
import com.example.fitin_kotlin.data.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    private val prefs: EncryptedSharedPreferenceController,
    state: SavedStateHandle
) : ViewModel() {

    private val savedId = MutableLiveData<Long?>()

    private val _bookmarkList = MutableLiveData<List<ResponseBookmark>>()
    val bookmarkList: LiveData<List<ResponseBookmark>>
        get() = _bookmarkList

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    private val _eventAddComplete = MutableLiveData<Boolean>()
    val eventAddComplete: LiveData<Boolean>
        get() = _eventAddComplete

    init {
        savedId.value = state.getLiveData<Long?>("saveId").value
        getBookmarkList()
    }

    private fun getBookmarkList() {
        val email = prefs.getUserEmail()

        viewModelScope.launch {
            _bookmarkList.value = bookmarkRepository.getBookmarkList(email!!)
        }
    }

    fun addBookmark(responseBookmark: ResponseBookmark) {
        val bookmarkId = responseBookmark.id
        val requestAddBookmark = RequestCreateBookmark("email","name",bookmarkId!!,savedId.value!!)
        viewModelScope.launch {
            val bookmark = bookmarkRepository.addBookmark(requestAddBookmark)
            when (bookmark.isSuccessful) {
                true -> {
                    _toastMessage.value = "북마크에 추가되었습니다."
                    _eventAddComplete.value = true
                }
                else -> {
                    _toastMessage.value = "실패했습니다"
                }
            }
        }
    }

    private val _eventCreateBookmark = MutableLiveData<String?>()
    val eventCreateBookmark: LiveData<String?>
        get() = _eventCreateBookmark

    fun onCreateBookmark() {
        _eventCreateBookmark.value = "addBookmark " + savedId.value.toString()
    }

    fun onCreateBookmarkComplete() {
        getBookmarkList()
        _eventCreateBookmark.value = null
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