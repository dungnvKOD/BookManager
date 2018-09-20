package com.dungnv.myapp.model.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabase(context: Context) : SQLiteOpenHelper(context, DATA_BASE, null, VERSION) {


    companion object {
        const val DATA_BASE = "me"
        const val VERSION = 1

        const val TB_USER = "USER"

        const val U_USERNAME = "U_USERNAME"
        const val U_PASS = "U_PASS"
        const val U_NAME = "U_NAME"
        const val U_PHONEME = "U_PHONEME"

        const val TB_THE_LOAI_SACH = "THE_LOAI_SACH"

        const val TLS_IDTHELOAI = "TLS_IDTHELOAI"
        const val TLS_TENLOAISACH = "TLS_TENLOAISACH"
        const val TLS_MOTA = "TLS_MOTA"
        const val TLS_VITRI = "TLS_VITRI"

        const val TB_HOA_DON = "HOA_DON"

        const val HD_MAHOADON = "HD_MAHOADON"
        const val HD_NGAYMUA = "HD_NGAYMUA"

        const val TB_HOA_DON_CHI_TIET = "TB_HOA_DON_CHI_TIET"

        const val HDCT_MAHOADONCHITIET = "HDCT_MAHOADONCHITIET"
        const val HDCT_SOLUONGMUA = "HDCT_SOLUONGMUA"
        const val MASACH__HDCT = "MASACH__HDCT"
        const val MAHOADON_HDCT = "MAHOADON_HDCT"

        const val TB_SACH = "SACH"

        const val S_MASACH = "S_MASACH"
        const val S_TIEU_DE = "S_TIEU_DE"
        const val S_TAC_GIA = "S_TAC_GIA"
        const val S_NXB = "S_NXB"
        const val S_GIABIA = "S_GIABIA"
        const val S_SOLUONG = "S_SOLUONG"
        const val MATHELOAI_SACH = "MATHELOAI_SACH"

    }

    private val tbHoaDonChiTiet = "CREATE TABLE $TB_HOA_DON_CHI_TIET(" +
            "$HDCT_MAHOADONCHITIET TEXT PRIMARY KEY ," +
            "$HDCT_SOLUONGMUA INTEGER," +
            "$MAHOADON_HDCT TEXT ," +
            "$MASACH__HDCT TEXT," +
            "FOREIGN KEY($MAHOADON_HDCT) REFERENCES $TB_HOA_DON($HD_MAHOADON)," +
            " FOREIGN KEY($MASACH__HDCT) REFERENCES $TB_SACH($S_MASACH))"

    private val tbSach = "CREATE TABLE $TB_SACH(" +
            "$S_MASACH TEXT PRIMARY KEY ," +
            "$S_TIEU_DE TEXT ," +
            "$S_TAC_GIA TEXT," +
            "$S_NXB TEXT," +
            "$S_GIABIA TEXT," +
            "$S_SOLUONG INTEGER," +
            "$MATHELOAI_SACH TEXT ," +
            "FOREIGN KEY($MATHELOAI_SACH) REFERENCES $TB_THE_LOAI_SACH($TLS_IDTHELOAI))"

    private val tbMaHoaDon = "CREATE TABLE $TB_HOA_DON(" +
            "$HD_MAHOADON TEXT PRIMARY KEY," +
            "$HD_NGAYMUA TEXT)"

    private val tbTheLoaiSach = "CREATE TABLE $TB_THE_LOAI_SACH(" +
            "$TLS_IDTHELOAI TEXT PRIMARY KEY," +
            "$TLS_TENLOAISACH TEXT," +
            "$TLS_MOTA TEXT," +
            "$TLS_VITRI INTEGER)"

    private val tbUser = "CREATE TABLE $TB_USER(" +
            "$U_USERNAME TEXT PRIMARY KEY," +
            "$U_PASS TEXT," +
            "$U_NAME TEXT," +
            "$U_PHONEME TEXT)"

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(tbUser)
        db.execSQL(tbTheLoaiSach)
        db.execSQL(tbMaHoaDon)
        db.execSQL(tbSach)
        db.execSQL(tbHoaDonChiTiet)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db!!.execSQL("DROP TABLE IF EXISTS $TB_USER")
        onCreate(db)
    }

    fun open(): SQLiteDatabase {
        return writableDatabase
    }

}