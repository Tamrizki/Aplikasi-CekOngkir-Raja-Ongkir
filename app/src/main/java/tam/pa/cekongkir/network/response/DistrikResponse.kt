package tam.pa.cekongkir.network.response

class DistrikResponse(
        val distrikRajaOngkir: DistrikRajaOngkir
)  {
    data class DistrikRajaOngkir(
            val query: DataQuery,
            val status: DataStatus,
            val result: List<DataResult>
    ){
        data class DataQuery(
                val key: String
        )
        data class DataStatus(
                val code: Int,
                val description: String
        )
        data class DataResult(
                val subdistrict_id: String,
                val province_id: String,
                val province: String,
                val city_id: String,
                val city: String,
                val type: String,
                val subdistrict_name: String
        )
    }
}