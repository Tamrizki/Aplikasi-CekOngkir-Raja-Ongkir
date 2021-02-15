package tam.pa.cekongkir.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tam.pa.cekongkir.network.response.DistrikResponse
import tam.pa.cekongkir.network.response.KotaResponse

interface EndPoint {
    @GET("city")
    suspend fun getCity(): Response<KotaResponse>

    @GET("subdistrict")
    suspend fun getSubdistrict(
            @Query( "city") city: String
    ): Response<DistrikResponse>
}