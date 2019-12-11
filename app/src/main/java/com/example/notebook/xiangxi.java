package com.example.notebook;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class xiangxi extends AppCompatActivity  {
    TextView tv1,tv2,tv3,tv4;
    EditText dateds2,datee2,name2,con2;
    private Note[] data={};
    Note note=new Note();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiangxi);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        final Bundle bundle=getIntent().getExtras();
        tv1.setText(bundle.getString("start"));
        tv2.setText(bundle.getString("end"));
        tv3.setText(bundle.getString("name"));
        tv4.setText(bundle.getString("detail"));
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.xs,menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.huishou:
                Intent intent = new Intent(xiangxi.this, huishou.class);
                startActivity(intent);
                break;
            case R.id.xiugai:
                AlertDialog.Builder builder=new AlertDialog.Builder(xiangxi.this);
                LayoutInflater inflater=getLayoutInflater();
                final View view=inflater.inflate(R.layout.changenote,null);
                dateds2=view.findViewById(R.id.da1);
                datee2=view.findViewById(R.id.da2);
                name2=view.findViewById(R.id.name2);
                con2=view.findViewById(R.id.con2);
                dateds2.setText(String.valueOf(tv1.getText().toString()));
           datee2.setText(String.valueOf(tv2.getText().toString()));
            name2.setText(String.valueOf(tv3.getText().toString()));
                 con2.setText(String.valueOf(tv4.getText().toString()));
                builder.setView(view).setTitle("修改备忘录")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override

                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (dateds2.getText().toString().isEmpty() || datee2.getText().toString().isEmpty()
                                        || name2.getText().toString().isEmpty() || con2.getText().toString().isEmpty()) {
                                    Toast.makeText(xiangxi.this, "请填写完整", Toast.LENGTH_SHORT).show();
                                } else {
                                    Note note = new Note();
                                    NoteAction noteAction2=new NoteAction(xiangxi.this);
                                    note.setStart(dateds2.getText().toString());
                                    Log.d("ceshi ",note.getStart());
                                    note.setEnd(datee2.getText().toString());
                                    note.setName(name2.getText().toString());
                                    note.setDetail(con2.getText().toString());
                                    noteAction2.changeNote(note);
                                    Toast.makeText(xiangxi.this, "修改成功", Toast.LENGTH_SHORT).show();
                                    Intent in=new Intent(xiangxi.this,MainActivity.class);
                                    startActivity(in);

                                }
                            }
                        });
                builder.show();
                break;
                case R.id.shanchu:
                    Note note = new Note();
                    NoteAction noteAction=new NoteAction(xiangxi.this);
                    note.setStart(tv1.getText().toString());
                    note.setEnd(tv2.getText().toString());
                    note.setName(tv3.getText().toString());
                    note.setDetail(tv4.getText().toString());
                    noteAction.addMyNote(note);
                    noteAction.deleteNote(tv3.getText().toString());
                    Toast.makeText(xiangxi.this, "删除成功", Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(xiangxi.this,MainActivity.class);
                    startActivity(in);
                    break;
        }
        return true;
    }
}
