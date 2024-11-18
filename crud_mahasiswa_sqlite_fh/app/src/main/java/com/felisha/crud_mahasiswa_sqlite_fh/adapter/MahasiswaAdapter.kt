package com.felisha.crud_mahasiswa_sqlite_fh.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.felisha.crud_mahasiswa_sqlite_fh.DetailMahasiswa
import com.felisha.crud_mahasiswa_sqlite_fh.R
import com.felisha.crud_mahasiswa_sqlite_fh.model.ModelMahasiswa

class MahasiswaAdapter(
    private var listMahasiswa: List<ModelMahasiswa>,
    private var context: Context
)  : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>(){
    class MahasiswaViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val txtNama : TextView = itemView.findViewById(R.id.txtItemNamaMahasiswa)
        val txtJurusan : TextView = itemView.findViewById(R.id.txtItemJurusan)
        val txtNim : TextView = itemView.findViewById(R.id.txtItemNimMahasiswa)
        val cardMahasiswa : CardView = itemView.findViewById(R.id.cardMahasiswa)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_mahasiswa,
            parent, false
            )
        return MahasiswaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val nMahasiswa = listMahasiswa[position]
        holder.txtNim.text = nMahasiswa.nim.toString()
        holder.txtNama.text = nMahasiswa.nama
        holder.txtJurusan.text = nMahasiswa.jurusan

        holder.cardMahasiswa.setOnClickListener(){
            val intent = Intent(context, DetailMahasiswa::class.java)

            intent.putExtra("nama", listMahasiswa[position].nama)
            intent.putExtra("nim", listMahasiswa[position].nim)
            intent.putExtra("jurusan", listMahasiswa[position].jurusan)
            context.startActivity(intent)

            Toast.makeText(context,listMahasiswa[position].nama, Toast.LENGTH_SHORT).show()
        }
    }
    //ini untuk refresh data
    fun refreshData (newMahasiswa: List<ModelMahasiswa>){
        listMahasiswa = newMahasiswa
        notifyDataSetChanged()
    }
}