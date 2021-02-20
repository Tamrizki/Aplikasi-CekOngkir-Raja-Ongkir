package tam.pa.cekongkir.localDB.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
        entities = [CekResiEntity::class],
        exportSchema = false,
        version = 1
)

abstract class CekOngkirDb: RoomDatabase() {

    abstract fun cekResiDao(): CekResiDao

    companion object{
        @Volatile private var instance: CekOngkirDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                CekOngkirDb::class.java, "cekOngkir.db")
                    .allowMainThreadQueries()
                    .build()
    }
}