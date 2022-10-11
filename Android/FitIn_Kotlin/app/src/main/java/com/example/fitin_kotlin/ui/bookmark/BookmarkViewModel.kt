package com.example.fitin_kotlin.ui.bookmark

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.model.network.request.RequestCreateBookmark
import com.example.fitin_kotlin.data.model.network.response.ResponseBookmark
import com.example.fitin_kotlin.data.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    private val prefs: EncryptedSharedPreferenceController
) : ViewModel() {

    private val _bookmarkList = MutableLiveData<List<ResponseBookmark>>()
    val bookmarkList: LiveData<List<ResponseBookmark>>
        get() = _bookmarkList

    init {
        getBookmarkList()
    }

    private fun getBookmarkList() {
        val email = prefs.getUserEmail()
        viewModelScope.launch {
            Log.e("이메일", email!!)
            _bookmarkList.value = bookmarkRepository.getBookmarkList(email)
            Log.e("북마크", bookmarkList.value.toString())
        }
    }

    private val _eventCreateBookmark = MutableLiveData<String?>()
    val eventCreateBookmark: LiveData<String?>
        get() = _eventCreateBookmark

    fun onCreateBookmark() {
        _eventCreateBookmark.value = "Bookmark"
    }

    fun onCreateBookmarkComplete() {
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