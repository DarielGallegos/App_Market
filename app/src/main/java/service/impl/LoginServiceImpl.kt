    package service.impl

    import android.content.Context
    import android.util.Log
    import client.Client
    import client.services.LoginServiceMethods
    import com.google.gson.Gson
    import controller.impl.LoginControllerImpl
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.GlobalScope
    import kotlinx.coroutines.launch
    import model.common.ApiResponseBody
    import model.dto.POST.CredentialsPOST
    import model.dto.REQUEST.Credentials
    import retrofit2.Call
    import retrofit2.Response
    import service.LoginService

    class LoginServiceImpl(context: Context) : LoginService{

        private val controller = LoginControllerImpl(context)
        private val service = Client.ClientRetrofit.getService(LoginServiceMethods::class.java) as LoginServiceMethods
        override fun getLogin(e:CredentialsPOST) {
            service.login(e).enqueue(object : retrofit2.Callback<ApiResponseBody> {
                override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<ApiResponseBody>,
                    response: Response<ApiResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody?.data?.content != null) {
                            val gson = Gson()
                            val e = gson.fromJson(gson.toJson(responseBody.data.content), Array<Credentials>::class.java).toList()
                            controller.persistLogin(responseBody.status == "OK", e[0])
                        }else{
                            controller.persistLogin(false, null)
                        }
                        }
                    }
            })
        }
    }