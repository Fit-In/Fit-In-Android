package com.example.fitin_kotlin.ui.createbookmark

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.model.network.request.RequestCreateBookmark
import com.example.fitin_kotlin.data.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateBookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    private val prefs: EncryptedSharedPreferenceController,
    state: SavedStateHandle
) : ViewModel() {

    private val bookmarkDirection = MutableLiveData<String>()

    val bookmarkName: MutableLiveData<String> = MutableLiveData<String>()

    private val _eventCompleteCreateFromBookmark = MutableLiveData<Boolean>()
    val eventCompleteCreateFromBookmark: LiveData<Boolean>
        get() = _eventCompleteCreateFromBookmark

    private val _eventCompleteCreateFromAddBookmark = MutableLiveData<Boolean>()
    val eventCompleteCreateFromAddBookmark: LiveData<Boolean>
        get() = _eventCompleteCreateFromAddBookmark
    val savedId = MutableLiveData<Long?>()

    init {
        bookmarkDirection.value = state.getLiveData<String>("separator").value


    }

    fun onCreateBookmark(view: View) {
        val email = prefs.getUserEmail()
        val requestCreateBookmark = RequestCreateBookmark(email, bookmarkName.value, 0, 0)

        viewModelScope.launch {
            val createBookmark = bookmarkRepository.createBookmark(requestCreateBookmark)
            when (createBookmark.isSuccessful) {
                true -> {
                    if (bookmarkDirection.value == "Bookmark") {
                        _eventCompleteCreateFromBookmark.value = true
                        Log.e("북마크에서 옴","북마크")
                    } else if (bookmarkDirection.value!!.contains("addBookmark")) {
                        Log.e("북마크 추가에서 옴", "북마크 추가")
                        savedId.value = bookmarkDirection.value.toString().substring(12).toLong()
                        Log.e("아이디", savedId.value.toString())
                        _eventCompleteCreateFromAddBookmark.value = true
                    }

                }
                else -> {
                    if (bookmarkDirection.value == "Bookmark") {
                        _eventCompleteCreateFromBookmark.value = false
                    } else if (bookmarkDirection.value == "addBookmark") {
                        _eventCompleteCreateFromAddBookmark.value = false
                    }
                }
            }
        }
    }

    fun onEventCompleteFromBookmark() {
        _eventCompleteCreateFromBookmark.value = false
    }

    fun onEventCompleteFromAddBookmark() {
        _eventCompleteCreateFromAddBookmark.value = false
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