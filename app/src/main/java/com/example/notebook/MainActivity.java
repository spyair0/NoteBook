package com.example.notebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Node;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView re,add;
ListView listView;
    EditText et,dates,datee,name1,con1;
    NoteAction noteAction;
    String[] new1={};
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
        NoteAction noteActiona=new NoteAction(MainActivity.this);
        data=noteActiona.findNote();
        String [] new1 =new String[data.length];
        for(int i=0;i<data.length;i++){
            new1[i]=data[i].getName();
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,new1);
        ListView listView=(ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.huishou:
               Intent intent1 = new Intent(MainActivity.this, huishou.class);
               startActivity(intent1);
                break;
            case R.id.ad:
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater=getLayoutInflater();
                final View view=inflater.inflate(R.layout.addnote,null);
                builder.setView(view).setTitle("添加备忘录")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override

                            public void onClick(DialogInterface dialogInterface, int i) {
                                dates = (EditText) view.findViewById(R.id.dates);
                                datee = (EditText) view.findViewById(R.id.datee);
                                name1 = (EditText) view.findViewById(R.id.name1);
                                con1 = (EditText) view.findViewById(R.id.con1);

                                if (dates.getText().toString().isEmpty() || datee.getText().toString().isEmpty()
                                        || name1.getText().toString().isEmpty() || con1.getText().toString().isEmpty()) {
                                    Toast.makeText(MainActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                                } else {
                                    Note note = new Note();
                                    note.setStart(dates.getText().toString());
                                    note.setEnd(datee.getText().toString());
                                    note.setName(name1.getText().toString());
                                    note.setDetail(con1.getText().toString());
                                    noteAction.addNote(note);
                                    Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Message message=new Message();
                                            message.what=UPDATE;
                                            handler.sendMessage(message);
                                        }
                                    }).start();
                                }
                            }
                        });
                builder.show();
                break;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteAction = new NoteAction(MainActivity.this);
        final Note note=new Note();
    init ();
    listView=findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();           //为bundle分配
                bundle.putString("name",data[i].getName());     //转入bundle
                bundle.putString("detail",data[i].getDetail());
                bundle.putString("start",data[i].getStart());
                bundle.putString("end",data[i].getEnd());
                Intent intent=new Intent(MainActivity.this,xiangxi.class);
                intent.putExtras(bundle);  //传输bundle--intent
            startActivity(intent);
            }
        });

    }
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
