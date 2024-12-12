package capstone.obye2.network

import capstone.obye2.model.RequestData
import capstone.obye2.model.ResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("predict") // Ganti dengan endpoint API yang sesuai
    fun submitData(@Body requestData: RequestData): Call<ResponseData>
}
