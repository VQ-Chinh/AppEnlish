package com.thelazyteam.SQLLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.thelazyteam.entity.Grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AM on 5/23/2017.
 */

public class GrammarDataBase  extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 1;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "AppEnglish";


    // Tên bảng: Note.
    private static final String TABLE_NOTE = "Grammar";

    private static final String COLUMN_NOTE_NAME ="Note_Name";
    private static final String COLUMN_NOTE_FORM ="Note_Form";

    private Context context;
    public GrammarDataBase(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context  = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script tạo bảng.
        String script = "CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_NOTE_NAME + " TEXT PRIMARY KEY," + COLUMN_NOTE_FORM + " TEXT" +
                ")";
        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
        // Chạy lệnh tạo bảng.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);


        // Và tạo lại.
        onCreate(db);
    }

    public void addDefault() {


        Grammar te1 = new Grammar();
        te1.setName("abc");
        te1.setForm("abc1");
        Grammar te2 = new Grammar();
        te2.setName("abc2");
        te2.setForm("abc2");


        addGrammar(te1);
        addGrammar(te2);
    }

    public void addGrammar(Grammar obj){
        Log.i(TAG, "MyDatabaseHelper.addGrammar ... " + obj.getName());

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_NAME, obj.getName());
        values.put(COLUMN_NOTE_FORM, obj.getForm());


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_NOTE, null, values);


        // Đóng kết nối database.
        db.close();
    }


    public List<Grammar> getAllGrammar(){
        Log.i(TAG, "MyDatabaseHelper.getAllGrammar ... ");

        List<Grammar> list = new ArrayList<Grammar>();

        String sql = "SELECT * FROM " + TABLE_NOTE;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Grammar note = new Grammar();
                note.setName(cursor.getString(0));
                note.setForm(cursor.getString(1));
                // Thêm vào danh sách.
                list.add(note);
            } while (cursor.moveToNext());
        }



        // Đóng kết nối database.
        db.close();

        return list;
    }

}
