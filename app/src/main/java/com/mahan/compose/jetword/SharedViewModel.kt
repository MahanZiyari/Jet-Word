package com.mahan.compose.jetword

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahan.compose.jetword.Repository.WordRepository
import com.mahan.compose.jetword.model.DataOrException
import com.mahan.compose.jetword.util.GameMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: WordRepository) : ViewModel() {

    var activeWords: MutableState<DataOrException<ArrayList<String>, Boolean, Exception>> = mutableStateOf(
        DataOrException(null, true, Exception(""))
    )

    private var _selectedMode: MutableState<GameMode?> = mutableStateOf(null)
    val selectedMode = _selectedMode

    init {
        //getAllWords()
        getCountedWords()
    }


    fun getAllWords() {
        viewModelScope.launch {
            activeWords.value.loading = true
            activeWords.value = repository.getAllWords()
            if (!activeWords.value.data.isNullOrEmpty()) activeWords.value.loading = false
        }
    }

    fun getCountedWords() {
        when (_selectedMode.value) {
            // If User choose easy
            GameMode.Easy -> viewModelScope.launch {
                activeWords.value.loading = true
                activeWords.value = repository.getWords(5)
                if (!activeWords.value.data.isNullOrEmpty()) activeWords.value.loading = false
            }
            // If User choose medium
            GameMode.Medium -> viewModelScope.launch {
                activeWords.value.loading = true
                activeWords.value = repository.getWords(10)
                if (!activeWords.value.data.isNullOrEmpty()) activeWords.value.loading = false
            }
            // If User choose hard
            GameMode.Hard -> viewModelScope.launch {
                activeWords.value.loading = true
                activeWords.value = repository.getWords(15)
                if (!activeWords.value.data.isNullOrEmpty()) activeWords.value.loading = false
            }
            else -> {}
        }
    }


    fun setupGame(gameMode: GameMode) {
        _selectedMode.value = gameMode
        getCountedWords()
    }
}