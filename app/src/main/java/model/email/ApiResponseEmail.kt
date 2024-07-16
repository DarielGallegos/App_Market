package model.email

import java.util.Date

data class ApiResponseEmail(var status:String, var statusCode: Int, var data: Data, var timeStamp: Date)
