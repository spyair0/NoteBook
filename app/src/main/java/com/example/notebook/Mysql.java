package com.example.notebook;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

    public class Mysql extends SQLiteOpenHelper {
        private Context mContext;
        public Mysql(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            mContext=context;
        }

        public static final String create_Note="create table Note ("
                +"starttime text primary key ,"
                +"endtime text,"
                +"name text,"
                +"detail text)";

        public static final String create_Mynote="create table MyNote ("
                +"mystarttime text primary key ,"
                +"myendtime text,"
                +"myname text,"
                +"mydetail text)";

        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(create_Note);
            db.execSQL(create_Mynote);
            Toast.makeText(mContext,"crrate succeede",Toast.LENGTH_SHORT).show();
        }
    }


