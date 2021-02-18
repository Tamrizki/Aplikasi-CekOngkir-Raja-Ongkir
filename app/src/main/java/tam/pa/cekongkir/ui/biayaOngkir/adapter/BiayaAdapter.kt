package tam.pa.cekongkir.ui.biayaOngkir.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tam.pa.cekongkir.databinding.CustomListBiayaBinding
import tam.pa.cekongkir.network.response.BiayaResponse

class BiayaAdapter(
        val listBiaya: ArrayList<BiayaResponse.BiayaRajaOngkir.dataResults>
        ): RecyclerView.Adapter<BiayaAdapter.vHolder>() {
        lateinit var subAdapter: SubBiayaAdapter
        class vHolder(val binding: CustomListBiayaBinding): RecyclerView.ViewHolder(binding.root){
                fun bind(ekspedisi: BiayaResponse.BiayaRajaOngkir.dataResults) {
                        val subAdapter = SubBiayaAdapter( ekspedisi.costs )
                        binding.listService.adapter = subAdapter
                }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = vHolder(
                CustomListBiayaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

        override fun onBindViewHolder(holder: vHolder, position: Int) {
                val ekspedisi = listBiaya[position]
                holder.binding.textCode.setText( ekspedisi.code )
                holder.binding.textName.setText( ekspedisi.name )
                holder.bind( ekspedisi )
        }

        override fun getItemCount() = listBiaya.size

        fun setData(data: List<BiayaResponse.BiayaRajaOngkir.dataResults>){
                listBiaya.clear()
                listBiaya.addAll( data )
                notifyDataSetChanged()
        }
}