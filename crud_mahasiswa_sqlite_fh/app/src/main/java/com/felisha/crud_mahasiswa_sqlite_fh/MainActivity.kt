package com.felisha.crud_mahasiswa_sqlite_fh

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.felisha.crud_mahasiswa_sqlite_fh.adapter.MahasiswaAdapter
import com.felisha.crud_mahasiswa_sqlite_fh.databinding.ActivityMainBinding
import com.felisha.crud_mahasiswa_sqlite_fh.helper.DbHelper
import com.felisha.crud_mahasiswa_sqlite_fh.screen_page.TambahDataMahasiswa

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db : DbHelper
    private lateinit var mahasiswaAdapter: MahasiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DbHelper(this, null)
        mahasiswaAdapter = MahasiswaAdapter(db.getAllDataMahasiswa(), this)

        binding.rvDataMahasiswa.layoutManager = LinearLayoutManager(this)
        binding.rvDataMahasiswa.adapter = mahasiswaAdapter

        //silahkan buat detail page
        //ketika di klik itemnya akan pindah ke page lain

        binding.btnPageTambah.setOnClickListener{
            val intent = Intent(this, TambahDataMahasiswa::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val newMahasiswa = db.getAllDataMahasiswa()
        mahasiswaAdapter.refreshData(newMahasiswa)
    }
}