package tam.pa.cekongkir.network

import retrofit2.Response
import retrofit2.http.GET
import tam.pa.cekongkir.network.response.KotaResponse

interface EndPoint {
    @GET("city")
    suspend fun getCity(): Response<KotaResponse>
}