package kr.co.jh.framework.entity._config

import com.p6spy.engine.logging.Category
import com.p6spy.engine.spy.appender.MessageFormattingStrategy
import org.hibernate.engine.jdbc.internal.FormatStyle
import java.util.*


//TODO: 멀티모듈시 이동 필요 (spy.properties 챙겨 옮겨야함)
class P6SpyLogger : MessageFormattingStrategy {
    override fun formatMessage(
        connectionId: Int,
        now: String,
        elapsed: Long,
        category: String,
        prepared: String,
        sql: String,
        url: String
    ): String {
        val callStack = Stack<String>()
        val stackTrace = Throwable().stackTrace
        for (stackTraceElement in stackTrace) {
            val trace = stackTraceElement.toString()
            if (trace.startsWith("kr.co.jh.framework") && !trace.contains("P6SpyLogger") && !trace.contains(
                    "$$"
                )
            ) {
                callStack.push(trace)
            }
        }
        val callStackBuilder = StringBuilder()
        var order = 1
        while (callStack.size != 0) {
            callStackBuilder.append(
                """
		${order++}. ${callStack.pop()}"""
            )
        }
        val message = StringBuilder()
            .append("\n\tExecution Time: ").append(elapsed).append(" ms\n")
            .append("\n\tCall Stack (number 1 is entry point): ").append(callStackBuilder)
            .append("\n")
            .append("\n----------------------------------------------------------------------------------------------------")
            .toString()
        return sqlFormat(sql, category, message)
    }

    private fun sqlFormat(sql: String, category: String, message: String): String {
        var sqlVar = sql
        if (sqlVar.trim { it <= ' ' }.isEmpty()) {
            return ""
        }
        if (Category.STATEMENT.name == category) {
            val s = sql.trim { it <= ' ' }.lowercase(Locale.ROOT)
            sqlVar = if (s.startsWith("create") || s.startsWith("alter") || s.startsWith("comment")) {
                FormatStyle.DDL
                    .formatter
                    .format(sql)
            } else {
                FormatStyle.BASIC
                    .formatter
                    .format(sql)
            }
        }
        return StringBuilder().append("\n")
            .append(sqlVar)
            .append(message)
            .toString()
    }
}
