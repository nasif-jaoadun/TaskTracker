package com.hishabee.tasktracker.utils

import android.util.Log

class Utility {


    companion object{
        enum class TypeOfFlag{
            errorLog,infoLog,verboseLog,warnLog,wtfLog,debugLog
        }
        val isShowLog = true;

        fun showLog(typeOFLog : TypeOfFlag, tag: String , message: String){
            if(isShowLog){
                when (typeOFLog) {
                    TypeOfFlag.errorLog -> Log.e(tag,message)
                    TypeOfFlag.infoLog -> Log.i(tag,message)
                    TypeOfFlag.verboseLog -> Log.v(tag,message)
                    TypeOfFlag.warnLog -> Log.w(tag,message)
                    TypeOfFlag.wtfLog -> Log.wtf(tag,message)
                    TypeOfFlag.debugLog -> Log.d(tag,message)
                    else -> Log.e(tag,message)
                }
            }
        }


    }

}