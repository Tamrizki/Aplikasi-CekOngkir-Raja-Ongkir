package tam.pa.cekongkir.network

import tam.pa.cekongkir.localDB.sharedPref.*

class RajaOngkirRepo (
        private val api: EndPoint,
        private val pref: CekOngkirSharedPref
        ){
//    suspend fun fetchKota(): Response<KotaResponse>{
//      return api.getCity()
//    }
//    disederhanakan menjadi seperti dibawah
    suspend fun fetchKota() = api.getCity()

    suspend fun fetchDistrik(idKota: String) = api.getSubdistrict(idKota)

    fun savePref(type: String, id: String, name: String){
        when(type){
            origin -> {
                pref.setData(prefOriginId, id)
                pref.setData(prefOriginName, name)
            }
            destination -> {
                pref.setData(prefDestinationId, id)
                pref.setData(prefDestinationName, name)
            }
        }
    }

    fun getDataPref(): List<PrefModel>{
        return listOf(
                PrefModel(origin, pref.getString(prefOriginId), pref.getString(prefOriginName)),
                PrefModel(destination, pref.getString(prefDestinationId), pref.getString(prefDestinationName))
        )
    }
}