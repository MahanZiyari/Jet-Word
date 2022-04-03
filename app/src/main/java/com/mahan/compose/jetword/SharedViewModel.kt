package com.mahan.compose.jetword

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahan.compose.jetword.Repository.WordRepository
import com.mahan.compose.jetword.util.GameMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: WordRepository) : ViewModel() {

    var activeWords: MutableState<ArrayList<String>> = mutableStateOf(
        arrayListOf()
    )

    var selectedMode: MutableState<GameMode?> = mutableStateOf(null)

    init {
        //getAllWords()
        getCountedWords()
    }


    fun getAllWords() {
        viewModelScope.launch {
            activeWords.value = repository.getAllWords()
        }
    }

    fun getCountedWords() {
        when (selectedMode.value) {
            // If User choose easy
            GameMode.Easy -> viewModelScope.launch {
                activeWords.value = repository.getWords(5)
            }
            // If User choose medium
            GameMode.Medium -> viewModelScope.launch {
                activeWords.value = repository.getWords(10)
            }
            // If User choose hard
            GameMode.Hard -> viewModelScope.launch {
                activeWords.value = repository.getWords(10)
            }
            else -> {}
        }
    }
}