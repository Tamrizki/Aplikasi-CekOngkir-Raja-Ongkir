package tam.pa.cekongkir.ui.biayaOngkir.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tam.pa.cekongkir.databinding.CustomListServiceBinding
import tam.pa.cekongkir.network.response.BiayaResponse

class SubBiayaAdapter(
        val listSubBiaya: List<BiayaResponse.BiayaRajaOngkir.dataResults.dataCosts>
): RecyclerView.Adapter<SubBiayaAdapter.vHolder>() {

    class vHolder(val binding: CustomListServiceBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = vHolder(
            CustomListServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: vHolder, position: Int) {
        val subBiaya = listSubBiaya[position]
        holder.binding.textDescription.setText( subBiaya.description )
        holder.binding.textService.setText( subBiaya.service )
        holder.binding.textValue.setText( subBiaya.cost[0].value.toString() )
        holder.binding.textEtd.setText( subBiaya.cost[0].etd )
    }

    override fun getItemCount() = listSubBiaya.size
}