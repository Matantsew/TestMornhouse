package com.example.testmornhouse.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmornhouse.model.NumberFact
import com.example.testmornhouse.model.NumbersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val numbersRepository: NumbersRepository
    ) : ViewModel() {

    private var obtainedNumberFactMutable = MutableLiveData<NumberFact>()

    var obtainedNumberFact: LiveData<NumberFact> = obtainedNumberFactMutable

    fun obtainFactAboutNumber(givenNumber: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val obtainedFact = async {
                    numbersRepository.getRemoteNumberFact(givenNumber)
                }

                val newNumberFact = NumberFact(givenNumber, obtainedFact.await())

                withContext(Dispatchers.Main){
                    obtainedNumberFactMutable.value = newNumberFact
                }
            }
        }
    }

    fun obtainFactAboutNumberHistoryList(): List<NumberFact> {
        return numbersRepository.getNumberFactHistoryList()
    }
}