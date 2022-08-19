package com.example.fitin_kotlin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitin_kotlin.data.model.network.response.ResponseNewsList
import com.example.fitin_kotlin.data.model.network.response.ResponseRecruitmentList
import com.example.fitin_kotlin.data.repository.NewsRepository
import com.example.fitin_kotlin.data.repository.RecruitmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val recruitmentRepository: RecruitmentRepository
) : ViewModel(){
    // 뉴스 & 채용정보 API 호출해서 상위 3개 아이템 띄우게끔 진행 필요
    // RecyclerView는 아이템 3개만, 중복되는 UI/UX 로직은 그대로 활용하기, NewsAdapter 동일하게 적용

    private val _newsList = MutableLiveData<List<ResponseNewsList>>()
    val newsList: LiveData<List<ResponseNewsList>>
        get() = _newsList

    val requestNews: MutableLiveData<ResponseNewsList?> = MutableLiveData<ResponseNewsList?>()

    private val _recruitmentList = MutableLiveData<List<ResponseRecruitmentList>>()
    val recruitmentList: LiveData<List<ResponseRecruitmentList>>
        get() = _recruitmentList

    val requestRecruitment: MutableLiveData<ResponseRecruitmentList?> = MutableLiveData<ResponseRecruitmentList?>()

    init {
        getNewsList()
        getRecruitmentList()
    }

    private fun getNewsList() {
        viewModelScope.launch {
            _newsList.value = newsRepository.getNewsList()
        }
    }

    private fun getRecruitmentList() {
        viewModelScope.launch {
            _recruitmentList.value = recruitmentRepository.getRecruitmentList()
        }
    }

    fun onEventNewsDetail(responseNewsList: ResponseNewsList) {
        requestNews.value = responseNewsList
    }

    fun onEventNewsDetailFinish() {
        requestNews.value = null
    }

    fun onEventRecruitmentDetail(responseRecruitmentList: ResponseRecruitmentList) {
        requestRecruitment.value = responseRecruitmentList
    }

    fun onEventRecruitmentDetailFinish() {
        requestRecruitment.value = null
    }

    private val _eventNewsList = MutableLiveData<Boolean>()
    val eventNewsList: LiveData<Boolean>
        get() = _eventNewsList

    fun onNewsList() {
        _eventNewsList.value = true
    }

    fun onNewsListFinish() {
        _eventNewsList.value = false
    }

    private val _eventRecruitmentList = MutableLiveData<Boolean>()
    val eventRecruitmentList: LiveData<Boolean>
        get() = _eventRecruitmentList

    fun onRecruitmentList() {
        _eventRecruitmentList.value = true
    }

    fun onRecruitmentListFinish() {
        _eventRecruitmentList.value = false
    }

}