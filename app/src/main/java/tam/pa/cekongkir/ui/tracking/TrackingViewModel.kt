package tam.pa.cekongkir.ui.tracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tam.pa.cekongkir.localDB.roomDb.CekResiEntity
import tam.pa.cekongkir.network.RajaOngkirRepo
import tam.pa.cekongkir.network.Resource
import tam.pa.cekongkir.network.response.TrackingResponse

class TrackingViewModel(
        private val repository: RajaOngkirRepo
): ViewModel() {

    val trackingResponse: MutableLiveData<Resource<TrackingResponse>> = MutableLiveData()
    val cekResi: LiveData<List<CekResiEntity>> = repository.getTracking()

    fun fetchTracking(
        waybill: String,
        courier: String
    ) = viewModelScope.launch{
        trackingResponse.value = Resource.Loading()
        try {
            val response = repository.fetchTrack( waybill, courier )
            trackingResponse.value = Resource.Success( response.body()!! )
            saveCekResi( response.body()!!.rajaongkir )
        }catch ( e: Exception ){
            trackingResponse.value = Resource.Error( e.message.toString() )
        }
    }

    fun saveCekResi(cekResi: TrackingResponse.dataRajaOngkir) = viewModelScope.launch {
        repository.saveTracking( CekResiEntity(
            resi = cekResi.query.waybill,
            courier = cekResi.query.courier,
            status = cekResi.result.delivery_status.status
        ))
    }

    fun deleteResi( dataResi : CekResiEntity ) = viewModelScope.launch {
        repository.deleteTracking( CekResiEntity(
                resi = dataResi.resi,
                courier = dataResi.courier,
                status = dataResi.status
        ))
    }
}