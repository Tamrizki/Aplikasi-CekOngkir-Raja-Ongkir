package tam.pa.cekongkir

import android.app.Application
import android.util.Log
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import tam.pa.cekongkir.localDB.roomDb.CekOngkirDb
import tam.pa.cekongkir.localDB.sharedPref.CekOngkirSharedPref
import tam.pa.cekongkir.network.ApiService
import tam.pa.cekongkir.network.EndPoint
import tam.pa.cekongkir.network.RajaOngkirRepo
import tam.pa.cekongkir.ui.biayaOngkir.BiayaViewModelFactory
import tam.pa.cekongkir.ui.kota.KotaViewModelFactory
import tam.pa.cekongkir.ui.tracking.TrackingViewModelFactory
import timber.log.Timber

class CekOngkirApplication: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@CekOngkirApplication))
        bind() from provider { CekOngkirSharedPref( instance() ) }
        bind() from provider { CekOngkirDb( instance() ) }
        bind<EndPoint>() with singleton { ApiService.getClient() }
        bind() from provider { RajaOngkirRepo( instance(), instance() , instance()) }

        bind() from provider { KotaViewModelFactory( instance() ) }
        bind() from provider { BiayaViewModelFactory( instance() ) }
        bind() from provider { TrackingViewModelFactory( instance() ) }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant( Timber.DebugTree() )
    }

}