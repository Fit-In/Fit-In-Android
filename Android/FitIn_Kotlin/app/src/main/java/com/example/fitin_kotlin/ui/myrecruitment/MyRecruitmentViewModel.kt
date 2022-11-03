package com.example.fitin_kotlin.ui.myrecruitment

import androidx.lifecycle.*
import com.example.fitin_kotlin.data.model.network.response.ResponseRecruitment
import com.example.fitin_kotlin.data.repository.SaveBookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRecruitmentViewModel @Inject constructor(
    private val saveBookmarkRepository: SaveBookmarkRepository,
    state: SavedStateHandle
) : ViewModel(){
    // TODO BookmarkMyNews에서 state로 saveId 받아서 호출함
    private val savedRecruit = MutableLiveData<Long>()

    // TODO LiveData saveDB 조회한 recruitment 값을 가져와 DataBinding으로 연결하게 제공
    private val _saveRecruit = MutableLiveData<ResponseRecruitment>()
    val saveRecruit: LiveData<ResponseRecruitment>
        get() = _saveRecruit

    init {
        savedRecruit.value = state.getLiveData<Long>("savedRecruitId").value
        getSavedRecruit()
    }

    private fun getSavedRecruit() {
        val savedRecruitId: Long = savedRecruit.value!!
        viewModelScope.launch {
            _saveRecruit.value = saveBookmarkRepository.getSaveRecruitment(savedRecruitId)
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