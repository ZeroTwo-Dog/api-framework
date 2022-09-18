package kr.co.jh.framework.lib.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LoggerUtils {
    inline fun <reified T> T.logger(): Logger {
        if (T::class.isCompanion) {
            return LoggerFactory.getLogger(T::class.java.enclosingClass)
        }
        return LoggerFactory.getLogger(T::class.java)
    }
}
