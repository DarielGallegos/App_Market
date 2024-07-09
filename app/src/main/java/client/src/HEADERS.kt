package client.src

enum class HEADERS {
    ;
    companion object {
        //KEY HEADERS
        const val HEADER_CONTENT_TYPE = "Content-Type"
        const val HEADER_ACCEPT_CHARSET = "Accept-Charset"
        const val HEADER_USER_AGENT = "User-Agent"
        //VALUE HEADERS
        const val HEADER_CONTENT_TYPE_VALUE = "application/json"
        const val HEADER_ACCEPT_CHARSET_VALUE = "UTF-8"
        const val HEADER_USER_AGENT_VALUE = "Retrofit-Sample-App"
    }
}