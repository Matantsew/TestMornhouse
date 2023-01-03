package com.example.testmornhouse.ui

import com.example.testmornhouse.model.NumberFact

interface IMainActivity {
    fun onFactAboutGivenNumberSelected(selectedNumberFact: NumberFact)
}