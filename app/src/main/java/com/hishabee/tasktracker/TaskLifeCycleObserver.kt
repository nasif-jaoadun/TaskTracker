package com.hishabee.tasktracker

import android.content.res.Resources
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.hishabee.tasktracker.util.LOG_TAG
import com.hishabee.tasktracker.util.ON_CREATE
import com.hishabee.tasktracker.util.ON_DESTROY
import com.hishabee.tasktracker.util.ON_PAUSE
import com.hishabee.tasktracker.util.ON_RESUME
import com.hishabee.tasktracker.util.ON_START
import com.hishabee.tasktracker.util.ON_STOP
import com.hishabee.tasktracker.utils.Utility

class TaskLifeCycleObserver: LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateEvent(){
        Utility.showLog(Utility.Companion.TypeOfFlag.errorLog, LOG_TAG, ON_CREATE)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartEvent(){
        Utility.showLog(Utility.Companion.TypeOfFlag.infoLog, LOG_TAG, ON_START)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeEvent(){
        Utility.showLog(Utility.Companion.TypeOfFlag.wtfLog, LOG_TAG, ON_RESUME)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPauseEvent(){
        Utility.showLog(Utility.Companion.TypeOfFlag.debugLog, LOG_TAG, ON_PAUSE)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopEvent(){
        Utility.showLog(Utility.Companion.TypeOfFlag.verboseLog, LOG_TAG, ON_STOP)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyEvent(){
        Utility.showLog(Utility.Companion.TypeOfFlag.warnLog, LOG_TAG, ON_DESTROY)
    }

}