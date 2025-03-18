package com.veragames.sudokufun.domain.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Chronometer {
    private val isRunning = MutableStateFlow(false)
    private val isStopped = MutableStateFlow(false)
    private val chronometerMillis = MutableStateFlow(0L)

    fun start() {
        resume()
        CoroutineScope(Dispatchers.IO).launch {
            while (isStopped.value.not()) {
                if (isRunning.value) {
                    delay(100)
                    chronometerMillis.update {
                        it.plus(100)
                    }
                }
            }
        }
    }

    fun pause() {
        isRunning.value = false
    }

    fun resume() {
        isRunning.value = true
        isStopped.value = false
    }

    fun stop(): Long {
        val totalTime = chronometerMillis.value
        isRunning.value = false
        isStopped.value = true
        chronometerMillis.update { 0L }
        return totalTime
    }

    fun getChronometer() = chronometerMillis.asStateFlow()

    fun isRunning() = isRunning.asStateFlow()
}
