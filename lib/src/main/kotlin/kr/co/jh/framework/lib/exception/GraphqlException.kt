package kr.co.jh.framework.lib.exception

import graphql.ErrorClassification
import graphql.GraphQLError
import graphql.language.SourceLocation

/**
 * Created by park on
 * 참고
 * https://docs.spring.io/spring-graphql/docs/1.0.2-SNAPSHOT/reference/html/#overview
 * 2022/11/13.
 */
abstract class GraphqlException(

    @JvmField
    @Suppress("INAPPLICABLE_JVM_FIELD")
    override val message: String?
): GraphQLError, RuntimeException(message) {

    override fun getMessage(): String? = message

    override fun getLocations(): MutableList<SourceLocation>? = null

    override fun getErrorType(): ErrorClassification? = null

    override fun getExtensions(): MutableMap<String, Any> {
        return mutableMapOf(
            Pair("exception", this.javaClass.simpleName)
        )
    }
}
