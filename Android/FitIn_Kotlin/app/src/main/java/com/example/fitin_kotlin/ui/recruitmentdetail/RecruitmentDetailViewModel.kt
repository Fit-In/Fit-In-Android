package com.example.fitin_kotlin.ui.recruitmentdetail

import androidx.lifecycle.*
import com.example.fitin_kotlin.data.model.network.response.ResponseRecruitment
import com.example.fitin_kotlin.data.model.network.response.ResponseRecruitmentList
import com.example.fitin_kotlin.data.repository.RecruitmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecruitmentDetailViewModel @Inject constructor(
    private val recruitmentRepository: RecruitmentRepository,
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