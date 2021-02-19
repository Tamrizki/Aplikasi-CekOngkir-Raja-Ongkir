package tam.pa.cekongkir.ui.tracking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tam.pa.cekongkir.network.RajaOngkirRepo

class TrackingViewModelFactory(
        private val repo: RajaOngkirRepo
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrackingViewModel(repo) as T
    }
}