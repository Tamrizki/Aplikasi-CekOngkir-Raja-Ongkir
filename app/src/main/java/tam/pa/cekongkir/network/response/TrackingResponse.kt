package tam.pa.cekongkir.network.response

data class TrackingResponse(
        val rajaongkir: dataRajaOngkir
) {
    data class dataRajaOngkir(
            val query: dataquery,
            val status: datastatus,
            val result: dataresult
    ){
        data class dataquery(
              val waybill: String,
              val courier: String
        ){}

        data class datastatus(
            val code: Int,
            val description: String
        ){}

        data class dataresult(
            val delivered: Boolean,
            val summary: datasummary,
            val details: datadetails,
            val delivery_status: datadeliverystatus,
            val manifest: List<datamanifest>
        ){
            data class datasummary(
                val courier_code: String,
                val courier_name: String,
                val waybill_number: String,
                val service_code: String,
                val waybill_date: String,
                val shipper_name: String,
                val receiver_name: String,
                val origin: String,
                val destination: String,
                val status: String
            )

            data class datadetails(
                val waybill_number: String,
                val waybill_date: String,
                val waybill_time: String,
                val weight: String,
                val origin: String,
                val destination: String,
                val shippper_name: String,
                val shipper_address1: String,
                val shipper_address2: String,
                val shipper_address3: String,
                val shipper_city: String,
                val receiver_name: String,
                val receiver_address1: String,
                val receiver_address2: String,
                val receiver_address3: String,
                val receiver_city: String
            )

            data class datadeliverystatus(
                val status: String,
                val pod_receiver: String,
                val pod_date: String,
                val pod_time: String
            )

            data class datamanifest(
                val manifest_code: String,
                val manifest_description: String,
                val manifest_date: String,
                val manifest_time: String,
                val city_name: String
            )
        }
    }
}