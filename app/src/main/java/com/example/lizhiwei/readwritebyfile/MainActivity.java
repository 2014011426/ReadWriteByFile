package com.example.lizhiwei.readwritebyfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private final String tag = "myTag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testFile(View v){
        switch(v.getId()){
            //读取文件
            case R.id.buttonRead:
                InputStream in = null;
                try{
                    FileInputStream fileInputStream = openFileInput("myFile");
                    int length = fileInputStream.available();
                    byte[] bytes = new byte[length];
                    in = new BufferedInputStream(fileInputStream);

                    //int c;
                    //StringBuilder stringBuilder = new StringBuilder("");
                    try{
                        /*
                        while((c=in.read())!=-1){
                            stringBuilder.append((char)c);
                        }
                        */
                        //一次性读完所有的二进制串，解决汉字乱码问题
                        in.read(bytes);
                        String con = new String(bytes,"UTF-8");
                        Toast.makeText(MainActivity.this,con,Toast.LENGTH_SHORT).show();
                    }finally {
                        if(in!=null)
                            in.close();
                    }
                }catch (Exception e){
                    Log.d(tag,"读取文件出错");
                }
                break;

            //写入文件
            case R.id.buttonWrite:
                OutputStream out = null;
                try{
                    FileOutputStream fileOutputStream = openFileOutput("myFile",MODE_APPEND);
                    out = new BufferedOutputStream(fileOutputStream);
                    String str = "学号：2014011426\n姓名：李志伟\n";
                    try{
                        out.write(str.getBytes(StandardCharsets.UTF_8));
                        Toast.makeText(MainActivity.this,"写入文件成功",Toast.LENGTH_SHORT).show();
                    }finally {
                        if(out!=null)
                            out.close();

                    }
                }catch (Exception e){
                    Log.d(tag,"写入文件出错");
                }
                break;
        }
    }
}
