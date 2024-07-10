package model.common

import java.util.Date

data class ApiResponseBody(var status:String, var statusCode: Int, var data: Data, var timeStamp: Date)
