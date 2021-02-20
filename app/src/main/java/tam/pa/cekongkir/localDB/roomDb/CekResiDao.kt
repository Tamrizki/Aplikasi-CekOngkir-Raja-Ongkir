package tam.pa.cekongkir.localDB.roomDb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CekResiDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(cekResiEntity: CekResiEntity)

    @Update
    suspend fun update(cekResiEntity: CekResiEntity)

    @Delete
    suspend fun delete(cekResiEntity: CekResiEntity)

    @Query ("SELECT * FROM tableCekOgkir")
    fun select(): LiveData<List<CekResiEntity>>

}
