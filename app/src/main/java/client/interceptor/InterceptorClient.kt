package client.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import client.src.HEADERS

class InterceptorClient : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader(HEADERS.HEADER_CONTENT_TYPE, HEADERS.HEADER_CONTENT_TYPE_VALUE)
            .addHeader(HEADERS.HEADER_ACCEPT_CHARSET, HEADERS.HEADER_ACCEPT_CHARSET_VALUE)
            .addHeader(HEADERS.HEADER_USER_AGENT, HEADERS.HEADER_USER_AGENT_VALUE)
            .build()
        return chain.proceed(newRequest)
    }
}