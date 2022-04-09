package com.mahan.compose.jetword.Repository

import com.mahan.compose.jetword.model.DataOrException
import com.mahan.compose.jetword.network.WordApi
import javax.inject.Inject

class WordRepository @Inject constructor(private val api: WordApi) {

    private var data = DataOrException<ArrayList<String>, Boolean, Exception>()

    suspend fun getAllWords(): DataOrException<ArrayList<String>, Boolean, Exception> {
        try {
            data.data = api.getAllWords()
        } catch (e: Exception) {
            data.exception = e
            e.stackTrace
        }

        return data
    }

    suspend fun getWords(numberOfWords: Int): DataOrException<ArrayList<String>, Boolean, Exception> {
        try {
            data.data = api.getWords(numberOfWords)
        } catch (e: Exception) {
            data.exception = e
            e.stackTrace
        }
        return data
    }
}