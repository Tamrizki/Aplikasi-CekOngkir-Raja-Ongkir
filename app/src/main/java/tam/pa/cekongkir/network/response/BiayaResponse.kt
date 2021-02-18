package tam.pa.cekongkir.network.response

data class BiayaResponse(
        val rajaongkir: BiayaRajaOngkir
) {
    data class BiayaRajaOngkir(
            val query: dataQuery,
            val status: dataStatus,
            val origin_details: dataOriginDetails,
            val destination_details: dataDestinationDetails,
            val results: List<dataResults>
    ){
        data class dataQuery(
                val origin: String,
                val originType: String,
                val destination: String,
                val destinationType: String,
                val weight: Int,
                val courier: String
        )
        data class dataStatus(
                val code: Int,
                val description: String
        )
        data class dataOriginDetails(
                val city_id: String,
                val province_id: String,
                val province: String,
                val type: String,
                val city_name: String,
                val postal_code: String
        )
        data class dataDestinationDetails(
                val subdistrict_id: String,
                val province_id: String,
                val province: String,
                val city_id: String,
                val city: String,
                val type: String,
                val subdistrict_name: String
        )
        data class dataResults(
                val code: String,
                val name: String,
                val costs: List<dataCosts>
        ){
            data class dataCosts(
                    val service: String,
                    val description: String,
                    val cost: List<dataSubCost>
            ){
                data class dataSubCost(
                        val value: Int,
                        val etd: String,
                        val note: String
                )
            }
        }
    }
}