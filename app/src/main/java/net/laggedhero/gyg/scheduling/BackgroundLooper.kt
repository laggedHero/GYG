package net.laggedhero.gyg.scheduling

import android.os.HandlerThread
import android.os.Looper
import android.os.Process

class BackgroundLooper {

    companion object {

        val instance by lazy {
            val handlerThread = HandlerThread(
                    "BackgroundHandlerThread", Process.THREAD_PRIORITY_BACKGROUND)
            handlerThread.start()

            handlerThread.looper
        }
    }
}
