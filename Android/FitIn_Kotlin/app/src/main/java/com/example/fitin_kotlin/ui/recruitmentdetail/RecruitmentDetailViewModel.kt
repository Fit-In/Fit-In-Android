package com.example.fitin_kotlin.ui.recruitmentdetail

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.fitin_kotlin.data.model.network.request.RequestRecruitmentBookmark
import com.example.fitin_kotlin.data.model.network.response.ResponseRecruitment
import com.example.fitin_kotlin.data.model.network.response.ResponseRecruitmentList
import com.example.fitin_kotlin.data.model.network.response.asRequestRecruitmentBookmark
import com.example.fitin_kotlin.data.repository.RecruitmentRepository
import com.example.fitin_kotlin.data.repository.SaveBookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecruitmentDetailViewModel @Inject constructor(
    private val recruitmentRepository: RecruitmentRepository,
    private val saveBookmarkRepository: SaveBookmarkRepository,
    state: SavedStateHandle
) : ViewModel(){

    private val requestRecruitment = MutableLiveData<ResponseRecruitmentList>()
    private val _recruitment = MutableLiveData<ResponseRecruitment>()
    val recruitment: LiveData<ResponseRecruitment>
        get() = _recruitment

    init {
        requestRecruitment.value = state.getLiveData<ResponseRecruitmentList>("selectedRecruitment").value
        getRecruitment()
    }

    private fun getRecruitment() {
        val recruitmentId: Long? = requestRecruitment.value?.id
        viewModelScope.launch {
            _recruitment.value = recruitmentRepository.getRecruitment(recruitmentId!!)
        }
    }

    // 성공적으로 API를 통해서 saveId 받으면 그 값과 함께 북마크 추가화면으로 넘어가기 위한
    // LiveData 변수 Navigation 활용하기 위해서

    val saveId: MutableLiveData<Long?> = MutableLiveData<Long?>()

    fun onAddBookmark(view: View) {
        val requestRecruitmentBookmark = _recruitment.value!!.asRequestRecruitmentBookmark()
        viewModelScope.launch {
            val saveRecruitment = saveBookmarkRepository.saveRecruitmentBookmark(requestRecruitmentBookmark)
            when (saveRecruitment.isSuccessful) {
                true -> {
                    Log.e("성공", saveRecruitment.body()!!.data.toString())
                    if (saveRecruitment.body()!!.state == 200) {
//                        Log.e("성공", saveRecruitment.body()!!.data.toString())
                        val result = saveRecruitment.body()!!.data.toString().toDouble().toLong()
                        saveId.value = result
                        Log.e("결과", saveId.value.toString())
                    }
                }
                else -> {
                    saveId.value = 0
                }
            }
        }
    }

    fun onAddBookmarkComplete() {
        saveId.value = null
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