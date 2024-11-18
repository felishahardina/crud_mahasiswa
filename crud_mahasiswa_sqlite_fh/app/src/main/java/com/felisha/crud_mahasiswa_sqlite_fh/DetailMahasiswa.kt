package com.felisha.crud_mahasiswa_sqlite_fh

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailMahasiswa : AppCompatActivity() {

    private lateinit var txtDetailNamaMahasiswa : TextView
    private lateinit var txtDetailNimMahasiswa : TextView
    private lateinit var txtDetailJurusanMahasiswa: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_mahasiswa)

        txtDetailNamaMahasiswa = findViewById(R.id.txtDetailNama)
        txtDetailNimMahasiswa = findViewById(R.id.txtDetailNim)
        txtDetailJurusanMahasiswa = findViewById(R.id.txtDetailJurusan)

        //get data
        val namaMahasiswa = intent.getStringExtra("nama")
        val nimMahasiswa = intent.getIntExtra("nim", 0).toString()
        val jurusanMahasiswa = intent.getStringExtra("jurusan")

        //set data ke widget
        txtDetailNamaMahasiswa.setText(namaMahasiswa)
        txtDetailNimMahasiswa.setText(nimMahasiswa)
        txtDetailJurusanMahasiswa.setText(jurusanMahasiswa)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}