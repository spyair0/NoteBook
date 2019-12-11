package com.example.notebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class huishou extends AppCompatActivity  {
    private Note[] data={};
    public  static final int UPDATE=1;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE:
                    init();
                    break;
                default:
                    break;
            }
        }
    };
    public void init(){
        NoteAction noteActiona=new NoteAction(huishou.this);
        data=noteActiona.findMyNote();
        String [] new1 =new String[data.length];
        for(int i=0;i<data.length;i++){
            new1[i]=data[i].getName();
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(huishou.this,android.R.layout.simple_list_item_1,new1);
        ListView listView=(ListView)findViewById(R.id.listview2);
        listView.setAdapter(adapter);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.huishou);
        NoteAction noteAction=new NoteAction(huishou.this);
        final Note note=new Note();
        final Note[] notearray=noteAction.findMyNote();
        final String[] data=new String[notearray.length];
        for(int i=0;i<notearray.length;i++){
            data[i]=notearray[i].getName();
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(huishou.this,android.R.layout.simple_list_item_1,data);
        ListView listView=(ListView)findViewById(R.id.listview2);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, final View view, final int position, long l) {
        AlertDialog.Builder builder=new AlertDialog.Builder(huishou.this);
        LayoutInflater inflater=getLayoutInflater();
        final View view2=inflater.inflate(R.layout.delete,null);
        builder.setView(view2).setTitle("备忘录操作")
                .setPositiveButton("还原", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NoteAction noteAction1=new NoteAction(huishou.this);
                        note.setStart(notearray[position].getStart());
                        note.setEnd(notearray[position].getEnd());
                        note.setName(notearray[position].getName());
                        note.setDetail(notearray[position].getDetail());
                        noteAction1.addNote(note);
                        noteAction1.deleteMyNote(data[position]);
                        Toast.makeText(huishou.this, "还原成功"+data[position], Toast.LENGTH_SHORT).show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message message=new Message();
                                message.what=UPDATE;
                                handler.sendMessage(message);
                            }
                        }).start();
                        Intent in=new Intent(huishou.this,MainActivity.class);
                        startActivity(in);
                    }


                }
                );
        builder.setNegativeButton ("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                NoteAction noteAction1=new NoteAction(huishou.this);
                noteAction1.deleteMyNote(data[position]);
                Toast.makeText(huishou.this, "删除成功"+data[position], Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=UPDATE;
                        handler.sendMessage(message);
                    }
                }).start();
                Intent in=new Intent(huishou.this,MainActivity.class);
                startActivity(in);
            }
                });
        builder.show();

    }
});
    }
}
