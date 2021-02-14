package tam.pa.cekongkir.localDB.roomDb

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class CekOngkirDb: RoomDatabase() {
    companion object{
        @Volatile private var instance: CekOngkirDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
//            instance ?:
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                CekOngkirDb::class.java, "CekOngkir.db")
                    .allowMainThreadQueries()
                    .build()
    }
}