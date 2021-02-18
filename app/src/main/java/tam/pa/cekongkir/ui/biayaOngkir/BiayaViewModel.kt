package tam.pa.cekongkir.ui.biayaOngkir

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tam.pa.cekongkir.localDB.sharedPref.PrefModel
import tam.pa.cekongkir.network.RajaOngkirRepo
import tam.pa.cekongkir.network.Resource
import tam.pa.cekongkir.network.response.BiayaResponse

class BiayaViewModel(
        private val repository: RajaOngkirRepo
): ViewModel() {

    val preferences: MutableLiveData<List<PrefModel>> = MutableLiveData()
    val biayaResponse: MutableLiveData<Resource<BiayaResponse>> = MutableLiveData()

    fun getPreferences(){
        preferences.value = repository.getDataPref()
        Log.d("_cekPref", "data = "+repository.getDataPref().toString())
    }

    fun fetchCost(origin: String,
                  originType: String,
                  destination: String,
                  destinationType: String,
                  weight: String,
                  courier: String
    ) = viewModelScope.launch {
        biayaResponse.value = Resource.Loading()
        try {
            val response = repository.fetchCost(origin, originType, destination, destinationType, weight, courier)
            biayaResponse.value = Resource.Success( response.body()!! )
        }catch (e: Exception){
            biayaResponse.value = Resource.Error( e.message.toString() )
        }
    }

}
