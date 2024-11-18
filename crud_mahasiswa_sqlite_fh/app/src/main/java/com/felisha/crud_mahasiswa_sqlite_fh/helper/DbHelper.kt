package com.felisha.crud_mahasiswa_sqlite_fh.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.felisha.crud_mahasiswa_sqlite_fh.model.ModelMahasiswa

class DbHelper (context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){

        fun closeDatabase(){
            if(database.isOpen && databaseOpen){
                database.close()
                databaseOpen = false

                Log.i("Database", "Database Closed")
            }
        }


    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAMA_MAHASISWA + " TEXT," +
                NIM + " TEXT," +
                JURUSAN + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our queryx
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addName(nama_mahasiswa : String, nim : String, jurusan : String ){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(NAMA_MAHASISWA, nama_mahasiswa)
        values.put(NIM, nim)
        values.put(JURUSAN, jurusan)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }
//    //get all data
      fun getAllDataMahasiswa() : List<ModelMahasiswa> {
          val mahasiswaList = mutableListOf<ModelMahasiswa>()
          val db = readableDatabase
          val query = "SELECT * FROM $TABLE_NAME"
          val cursor = db.rawQuery(query, null)

          while (cursor.moveToNext()){
              val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COL))
              val namaMahasiswa = cursor.getString(cursor.getColumnIndexOrThrow(NAMA_MAHASISWA))
              val nim = cursor.getInt(cursor.getColumnIndexOrThrow(NIM))
              val jurusan = cursor.getString(cursor.getColumnIndexOrThrow(JURUSAN))

              Log.d("Databasehelper", "Fecthed ID : $id, namamahasiswa: $namaMahasiswa")
              val nMahasiswa = ModelMahasiswa(id, namaMahasiswa, nim, jurusan)
              mahasiswaList.add(nMahasiswa)
          }
          cursor.close()
          db.close()
          return mahasiswaList
      }

//    fun getAllData() : MutableList<ModelMahasiswa>{
//        if(!databaseOpen){
//            database = INSTANCE.writableDatabase
//            databaseOpen = true //db nya kita open
//
//            Log.i("Database", "Database Open")
//        }
//
//        val data: MutableList<ModelMahasiswa> = ArrayList()
//        val cursor = database.rawQuery("SELECT * FROM" + TABLE_NAME, null)
//        cursor.use { cur ->
//            if(cursor.moveToFirst()){
//                do{
//                    val mahasiswa = ModelMahasiswa()
//                    mahasiswa.id = cur.getInt(cur.getColumnIndex(ID_COL))
//                    mahasiswa.nama = cur.getInt(cur.getColumnIndex(NAMA_MAHASISWA))
//                    mahasiswa.nim = cur.getInt(cur.getColumnIndex(NIM))
//                    mahasiswa.jurusan = cur.getInt(cur.getColumnIndex(JURUSAN))
//                    data.add(mahasiswa)
//                }while (cursor.moveToNext())
//            }
//        }
//        return data
//    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "DB_MAHASISWA"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "tb_mahasiswa"

        // below is the variable for id column
        val ID_COL = "id"
        val NAMA_MAHASISWA = "nama_mahasiswa"
        val NIM = "nim"
        val JURUSAN = "jurusan"

        private lateinit var database: SQLiteDatabase
        private var databaseOpen : Boolean = false
        private lateinit var INSTANCE: DbHelper
    }
    fun insertDataMahasiswa(mhs: ModelMahasiswa) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(NAMA_MAHASISWA, mhs.nama)
            put(NIM, mhs.nim)
            put(JURUSAN, mhs.jurusan)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
}