package tam.pa.cekongkir.ui.cekresi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tam.pa.cekongkir.databinding.CustomListCekResiBinding
import tam.pa.cekongkir.localDB.roomDb.CekResiEntity

class CekResiAdapter( val listData: ArrayList<CekResiEntity>,
                      val listener: OnAdapterListener
                      ): RecyclerView.Adapter<CekResiAdapter.vHolder>() {
    class vHolder( val binding: CustomListCekResiBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = vHolder(
        CustomListCekResiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: vHolder, position: Int) {
        val dataResi = listData[position]
        holder.binding.textCourier.setText( dataResi.courier )
        holder.binding.textStatus.setText( dataResi.status )
        holder.binding.textWaybill.setText( dataResi.resi )
        holder.binding.container.setOnClickListener {
            listener.OnClick( dataResi )
        }
        holder.binding.container.setOnLongClickListener {
            listener.OnDelete( dataResi )
            true
        }
    }

    override fun getItemCount() = listData.size

    interface OnAdapterListener{
        fun OnClick(result : CekResiEntity)
        fun OnDelete(result : CekResiEntity)
    }

    fun setData(data: List<CekResiEntity>){
        listData.clear()
        listData.addAll( data )
        notifyDataSetChanged()
    }
}