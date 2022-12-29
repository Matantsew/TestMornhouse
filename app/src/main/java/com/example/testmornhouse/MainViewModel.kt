package com.example.testmornhouse

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
class MainViewModel @Inject constructor(private val numbersRepository: NumbersRepository) : ViewModel() {

    private var numberFactsHistoryMutableList = MutableLiveData<NumberFact>()

    var numberFactsHistoryList: LiveData<NumberFact> = numberFactsHistoryMutableList

    fun getFactAboutNumber(givenNumber: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val obtainedFact = async {
                    numbersRepository.getRemoteNumberFact(givenNumber)
                }

                val newNumberFact = NumberFact(givenNumber, obtainedFact.await())

                withContext(Dispatchers.Main){
                    numberFactsHistoryMutableList.value = newNumberFact
                }
            }
        }
    }
}