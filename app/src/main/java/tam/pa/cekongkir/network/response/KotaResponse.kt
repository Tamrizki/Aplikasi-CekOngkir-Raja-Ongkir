package tam.pa.cekongkir.network.response

data class KotaResponse(
        val rajaongkir: KotaRajaOngkir
) {
    data class KotaRajaOngkir(
        val query: List<Any>,
        val status: DataStatus,
        val results: List<DataResult>
    ){
        data class DataStatus(
                val code: Int,
                val description: String
        )
        data class DataResult(
                val city_id: String,
                val province_id: String,
                val province: String,
                val type: String,
                val city_name: String,
                val postal_code: String
        )
    }
}