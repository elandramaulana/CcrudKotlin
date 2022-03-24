package com.example.ccrudkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddDataActivity : AppCompatActivity(), View.OnClickListener {

//    private lateinit var dbMhs : DatabaseReference
    private lateinit var etNama : EditText
    private lateinit var etNoBp : EditText
    private lateinit var etJurusan : EditText
    private lateinit var addButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        etNama = findViewById(R.id.et_Nama)
        etNoBp = findViewById(R.id.et_Bp)
        etJurusan = findViewById(R.id.et_jurusan)
        addButton = findViewById(R.id.btn_add)

        addButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        addData()
    }
    private fun addData(){
        val nama = etNama.text.toString().trim()
        val bp = etNoBp.text.toString().trim()
        val jurusan = etJurusan.text.toString().trim()

        if(nama.isEmpty()){
            etNama.error = "Nama tidak boleh kosong!"
            return
        }
        if(bp.isEmpty()){
            etNama.error = "No Bp tidak boleh kosong!"
            return
        }
        if(jurusan.isEmpty()){
            etNama.error = "Jurusan tidak boleh kosong!"
            return
        }
        val dbMhs : DatabaseReference = FirebaseDatabase.getInstance().getReference("mahasiswa")

        val mhsId = dbMhs.push().key

        val mhs = Mahasiswa(mhsId, nama, bp, jurusan)

        if(mhsId != null){
            dbMhs.child(mhsId).setValue(mhs).addOnCompleteListener{Toast.makeText(applicationContext, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()}
        }
    }

}