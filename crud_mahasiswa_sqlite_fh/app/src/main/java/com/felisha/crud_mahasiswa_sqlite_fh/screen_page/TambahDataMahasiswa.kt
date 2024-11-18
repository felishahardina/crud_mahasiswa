package com.felisha.crud_mahasiswa_sqlite_fh.screen_page

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.felisha.crud_mahasiswa_sqlite_fh.R
import com.felisha.crud_mahasiswa_sqlite_fh.databinding.ActivityTambahDataMahasiswaBinding
import com.felisha.crud_mahasiswa_sqlite_fh.helper.DbHelper
import com.felisha.crud_mahasiswa_sqlite_fh.model.ModelMahasiswa

class TambahDataMahasiswa : AppCompatActivity() {

    //binding : cara ringkas untuk kita deklarasi variable dan widget
    private lateinit var binding: ActivityTambahDataMahasiswaBinding
    private lateinit var db : DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahDataMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = DbHelper(this,null)
        binding.btnTambahData.setOnClickListener{
            val nama = binding.txtInputNama.text.toString()
            val nim = binding.txtInputNIM.text.toString()

            //karena nim --> int jadi kita perlu convert dari string ke int
            //toInt()
            val dataMahasiswa = ModelMahasiswa(0, nama, nim.toInt(), "Manajemen Informatika" )
            db.insertDataMahasiswa(dataMahasiswa)
            finish();
            Toast.makeText(this, "Berhasil Tambah Data",
                Toast.LENGTH_LONG).show()
        }

    }
}