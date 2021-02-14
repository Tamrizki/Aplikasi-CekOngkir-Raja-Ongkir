package tam.pa.cekongkir.ui.kota

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tam.pa.cekongkir.network.EndPoint
import tam.pa.cekongkir.network.Resource
import tam.pa.cekongkir.network.response.KotaResponse

class KotaViewModel(
        val api: EndPoint
): ViewModel() {

    val titleBar: MutableLiveData<String> = MutableLiveData("")
    val kotaResponse: MutableLiveData<Resource<KotaResponse>> = MutableLiveData()

    init {
        fetchKota()
    }

    fun fetchKota() = viewModelScope.launch{
        kotaResponse.value = Resource.Loading()
        try {
            kotaResponse.value = Resource.Success(api.getCity().body()!!)
        }catch (e: Exception){
            kotaResponse.value = Resource.Error(e.message.toString())
        }
    }
}