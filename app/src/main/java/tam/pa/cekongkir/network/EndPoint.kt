package tam.pa.cekongkir.network

import retrofit2.Response
import retrofit2.http.*
import tam.pa.cekongkir.network.response.BiayaResponse
import tam.pa.cekongkir.network.response.DistrikResponse
import tam.pa.cekongkir.network.response.KotaResponse
import tam.pa.cekongkir.network.response.TrackingResponse

interface EndPoint {
    @GET("city")
    suspend fun getCity(): Response<KotaResponse>

    @GET("subdistrict")
    suspend fun getSubdistrict(
            @Query( "city") city: String
    ): Response<DistrikResponse>

    @FormUrlEncoded
    @POST("cost")
    suspend fun getCost(
            @Field("origin" ) origin: String,
            @Field("originType" ) originType: String,
            @Field("destination" ) destination: String,
            @Field("destinationType" ) destinationType: String,
            @Field("weight" ) weight: String,
            @Field("courier" ) courier: String
    ): Response<BiayaResponse>

    @FormUrlEncoded
    @POST("waybill" )
    suspend fun getWayBill(
            @Field("waybill") waybill: String,
            @Field("courier") courier: String
    ): Response<TrackingResponse>
}