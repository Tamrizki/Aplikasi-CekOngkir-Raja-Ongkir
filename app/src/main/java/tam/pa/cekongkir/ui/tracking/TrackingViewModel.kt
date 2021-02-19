package tam.pa.cekongkir.ui.tracking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tam.pa.cekongkir.network.RajaOngkirRepo
import tam.pa.cekongkir.network.Resource
import tam.pa.cekongkir.network.response.TrackingResponse

class TrackingViewModel(
        private val repository: RajaOngkirRepo
): ViewModel() {

    val trackingResponse: MutableLiveData<Resource<TrackingResponse>> = MutableLiveData()

    fun fetchTracking(
        waybill: String,
        courier: String
    ) = viewModelScope.launch{
        trackingResponse.value = Resource.Loading()
        try {
            val response = repository.fetchTrack( waybill, courier )
            trackingResponse.value = Resource.Success( response.body()!! )
        }catch ( e: Exception ){
            trackingResponse.value = Resource.Error( e.message.toString() )
        }
    }

}