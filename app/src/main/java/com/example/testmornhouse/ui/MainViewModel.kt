package com.example.testmornhouse.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmornhouse.model.NumberFact
import com.example.testmornhouse.model.NumbersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val numbersRepository: NumbersRepository
    ) : ViewModel() {

    private var factAboutNumberHistoryListMutable = MutableLiveData<List<NumberFact>>()

    private var numberFactMutable = MutableLiveData<NumberFact>()

    var numberFact: LiveData<NumberFact> = numberFactMutable

    var factAboutNumberHistoryList: LiveData<List<NumberFact>> = factAboutNumberHistoryListMutable

    fun obtainFactAboutNumber(givenNumber: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val obtainedFact = async {
                    try {
                        numbersRepository.getRemoteNumberFact(givenNumber)
                    }
                    catch (e: IOException){
                        e.printStackTrace()
                        cancel()
                    }
                }

                val result = obtainedFact.await()

                if(result !is String)return@withContext

                val newNumberFact = NumberFact(givenNumber, result)

                withContext(Dispatchers.Main) {
                    numberFactMutable.value = newNumberFact
                }
            }
        }
    }

    fun updateFactAboutNumberHistoryList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val obtainedHistory = async {
                    numbersRepository.getNumberFactHistoryList()
                }

                withContext(Dispatchers.Main) {
                    factAboutNumberHistoryListMutable.value = obtainedHistory.await()
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}