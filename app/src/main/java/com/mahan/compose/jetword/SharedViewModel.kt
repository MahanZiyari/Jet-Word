package com.mahan.compose.jetword

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahan.compose.jetword.Repository.WordRepository
import com.mahan.compose.jetword.model.DataOrException
import com.mahan.compose.jetword.model.Result
import com.mahan.compose.jetword.util.GameMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception
import kotlin.random.Random

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: WordRepository) : ViewModel() {

    var activeWords: MutableState<DataOrException<List<String>, Boolean, Exception>> = mutableStateOf(
        DataOrException(null, true, Exception(""))
    )

    var allWords: MutableState<DataOrException<ArrayList<String>, Boolean, Exception>> = mutableStateOf(
        DataOrException(null, true, Exception(""))
    )

    private var _selectedMode: MutableState<GameMode?> = mutableStateOf(null)
    val selectedMode = _selectedMode


    private var _userText = mutableStateOf("")
    val userText = _userText

    private var _result: MutableState<Result?> = mutableStateOf(null)
    val result = _result

    init {
        getAllWords()
        //getCountedWords()
    }

    fun setNewResult(newResult: Result) {
        _result.value = newResult
    }


    fun getAllWords() {
        viewModelScope.launch {
            allWords.value.loading = true
            allWords.value = repository.getAllWords()
            if (!allWords.value.data.isNullOrEmpty()) allWords.value.loading = false
        }
    }

    fun getCountedWords() {
        when (_selectedMode.value) {
            // If User choose easy
            GameMode.Easy -> viewModelScope.launch {
                activeWords.value.loading = true
                //activeWords.value = repository.getWords(5)
                if (!activeWords.value.data.isNullOrEmpty()) activeWords.value.loading = false
            }
            // If User choose medium
            GameMode.Medium -> viewModelScope.launch {
                activeWords.value.loading = true
                //activeWords.value = repository.getWords(10)
                if (!activeWords.value.data.isNullOrEmpty()) activeWords.value.loading = false
            }
            // If User choose hard
            GameMode.Hard -> viewModelScope.launch {
                activeWords.value.loading = true
                //activeWords.value = repository.getWords(15)
                if (!activeWords.value.data.isNullOrEmpty()) activeWords.value.loading = false
            }
            else -> {}
        }
    }


    fun setupGame(gameMode: GameMode) {
        _result.value = null
        _selectedMode.value = gameMode
        //getCountedWords()
        settingUpGame(gameMode)
    }

    private fun settingUpGame(gameMode: GameMode) {
        when(gameMode) {
            GameMode.Easy -> {
                activeWords.value.data = allWords.value.data?.filter { it.length <= 4 }?.shuffled()?.subList(0, 5)
                if (!activeWords.value.data.isNullOrEmpty()) activeWords.value.loading = false
            }
            GameMode.Medium -> {
                activeWords.value.data = allWords.value.data?.filter { it.length in 4..8 }?.shuffled()?.subList(0, 10)
                if (!activeWords.value.data.isNullOrEmpty()) activeWords.value.loading = false
            }
            GameMode.Hard -> {
                activeWords.value.data = allWords.value.data?.filter { it.length in 8..12 }?.shuffled()?.subList(0, 10)
                if (!activeWords.value.data.isNullOrEmpty()) activeWords.value.loading = false
            }
            else -> {}
        }
    }

    fun onUserTextChange(newText: String) {
        _userText.value = newText
    }


}