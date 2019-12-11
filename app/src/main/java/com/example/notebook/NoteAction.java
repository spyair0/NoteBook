package com.example.notebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class NoteAction {
    SQLiteDatabase db;
    private Context m;

    public NoteAction(Context context) {
        Mysql mysql = new Mysql(context, "Note", null, 1);
        db = mysql.getWritableDatabase();
        m = context;
    }
    public void addNote(Note note) {
        ContentValues values=new ContentValues();
        values.put("starttime",note.getStart());
        values.put("endtime",note.getEnd());
        values.put("name",note.getName());
        values.put("detail",note.getDetail());
        db.insert("Note",null,values);
        values.clear();
      }
    public void addMyNote(Note note) {
        ContentValues values=new ContentValues();
        values.put("mystarttime",note.getStart());
        values.put("myendtime",note.getEnd());
        values.put("myname",note.getName());
        values.put("mydetail",note.getDetail());
        db.insert("MyNote",null,values);
        values.clear();
    }
    public void changeNote(Note note) {
        ContentValues values=new ContentValues();
        values.put("starttime",note.getStart());
        values.put("endtime",note.getEnd());
        values.put("name",note.getName());
        values.put("detail",note.getDetail());
        db.update("Note",values,"starttime=?",new String[]{note.getStart()});
    }
    public void changeMyNote(Note note) {
        ContentValues values=new ContentValues();
        values.put("mystarttime",note.getStart());
        values.put("myendtime",note.getEnd());
        values.put("myname",note.getName());
        values.put("mydetail",note.getDetail());
        db.update("MyNote",values,"mystarttime=?",new String[]{note.getStart()});
    }
    public void deleteNote(String name){
        db.delete("Note","name=?",new String[]{name});
    }
    public void deleteMyNote(String myname){
        db.delete("MyNote","myname=?",new String[]{myname});
    }
    public Note[] findNote(){
      Note note=new Note();
      Note[] notearray={};
        Cursor cursor =  db.query("Note",null, null,null,null,null,null);
        if(cursor.getCount()>0){
            notearray=new Note[cursor.getCount()];
            while(cursor.moveToNext()){
               note.setStart(cursor.getString(cursor.getColumnIndex("starttime")));
                note.setEnd(cursor.getString(cursor.getColumnIndex("endtime")));
                note.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
                note.setName(cursor.getString(cursor.getColumnIndex("name")));
                notearray[cursor.getPosition()]=note;
                note=new Note();
            }
        }
        cursor.close();
        return notearray;
    }
    public Note[] findMyNote(){
        Note note=new Note();
        Note[] notearray={};
        Cursor cursor =  db.query("MyNote",null, null,null,null,null,null);
        if(cursor.getCount()>0){
            notearray=new Note[cursor.getCount()];
            while(cursor.moveToNext()){
                note.setStart(cursor.getString(cursor.getColumnIndex("mystarttime")));
                note.setEnd(cursor.getString(cursor.getColumnIndex("myendtime")));
                note.setDetail(cursor.getString(cursor.getColumnIndex("mydetail")));
                note.setName(cursor.getString(cursor.getColumnIndex("myname")));
                notearray[cursor.getPosition()]=note;
                note=new Note();
            }
        }
        cursor.close();
        return notearray;
    }
}
