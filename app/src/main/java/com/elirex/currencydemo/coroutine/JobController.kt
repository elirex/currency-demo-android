package com.elirex.currencydemo.coroutine

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicReference

class JobController<T> {

    private val activeJob = AtomicReference<Deferred<T>?>(null)

    suspend fun joinPreviousOrRun(block: suspend() -> T): T {
        activeJob.get()?.let {
            return it.await()
        }
        return coroutineScope {
            val newJob = async(start = CoroutineStart.LAZY) {
                block()
            }

            newJob.invokeOnCompletion {
                activeJob.compareAndSet(newJob, null)
            }

            val result: T
            while (true) {
                if (!activeJob.compareAndSet(null, newJob)) {
                    val currentJob = activeJob.get()
                    if (currentJob == null) {
                        yield()
                    } else {
                        newJob.cancel()
                        result = currentJob.await()
                        break
                    }
                } else {
                    result = newJob.await()
                    break
                }
            }
            result
        }
    }
}