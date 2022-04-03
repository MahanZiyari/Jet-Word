package com.mahan.compose.jetword.Repository

import com.mahan.compose.jetword.network.WordApi
import javax.inject.Inject

class WordRepository @Inject constructor(private val api: WordApi) {

    suspend fun getAllWords() = api.getAllWords()

    suspend fun getWords(numberOfWords: Int) = api.getWords(numberOfWords)
}