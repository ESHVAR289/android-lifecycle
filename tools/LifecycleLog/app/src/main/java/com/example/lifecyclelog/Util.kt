package com.example.lifecyclelog

import android.text.TextUtils
import android.util.Log

/**
 * Lifecycle monitoring utility
 */
object Util {
    @JvmStatic
    fun recLifeCycle(callingClass: Class<*>, state: LifecycleState?) {
        val note: String?
        note = when (state) {
            LifecycleState.CALL_TO_SUPER -> "→☐"
            LifecycleState.RETURN_FROM_SUPER -> "☐→"
            else -> null
        }
        recLifeCycle(callingClass, note)
    }

    private fun recLifeCycle(callingClass: Class<*>, note: String?) {
        val className = callingClass.simpleName
        val s = Thread.currentThread().stackTrace
        val methodName = s[4].methodName
        Log.i("LifecycleLog",
                className + "." + methodName + if (TextUtils.isEmpty(note)) "" else " / $note")
    }

    enum class LifecycleState {
        CALL_TO_SUPER, RETURN_FROM_SUPER
    }
}