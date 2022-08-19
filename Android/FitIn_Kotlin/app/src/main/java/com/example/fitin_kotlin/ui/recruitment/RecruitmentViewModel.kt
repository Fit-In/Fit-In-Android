package com.example.fitin_kotlin.ui.recruitment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitin_kotlin.data.model.network.response.ResponseRecruitmentList
import com.example.fitin_kotlin.data.repository.RecruitmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecruitmentViewModel @Inject constructor(
    private val recruitmentRepository: RecruitmentRepository
) : ViewModel(){

    private val _recruitmentList = MutableLiveData<List<ResponseRecruitmentList>>()
    val recruitmentList: LiveData<List<ResponseRecruitmentList>>
        get() = _recruitmentList

    val requestRecruitment: MutableLiveData<ResponseRecruitmentList?> = MutableLiveData<ResponseRecruitmentList?>()

    init {
        getRecruitmentList()
    }

    private fun getRecruitmentList() {
        viewModelScope.launch {
            _recruitmentList.value = recruitmentRepository.getRecruitmentList()
        }
    }

    fun displayRecruitment(responseRecruitmentList: ResponseRecruitmentList) {
        requestRecruitment.value = responseRecruitmentList
    }

    fun displayRecruitmentFinish() {
        requestRecruitment.value = null
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