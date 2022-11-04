package com.example.ltdd_sqliteqlsv_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText hoTen124, maSV124, ngaySinh124;
    Button them, sua, xoa, xem;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hoTen124 = findViewById(R.id.hoTen124);
        maSV124= findViewById(R.id.maSV124);
        ngaySinh124 = findViewById(R.id.ngaySinh124);
        them = findViewById(R.id.btnThem);
        sua = findViewById(R.id.btnSua);
        xoa = findViewById(R.id.btnXoa);
        xem = findViewById(R.id.btnXem);
        DB = new DBHelper(this);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTenTXT = hoTen124.getText().toString();
                String maSVTXT = maSV124.getText().toString();
                String ngaySinhTXT = ngaySinh124.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(hoTenTXT, maSVTXT, ngaySinhTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Chưa thêm sinh viên", Toast.LENGTH_SHORT).show();
            }        });
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTenTXT = hoTen124.getText().toString();
                String maSVTXT = maSV124.getText().toString();
                String ngaySinhTXT = ngaySinh124.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(hoTenTXT, maSVTXT, ngaySinhTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Sinh viên đã sửa", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Sinh viên chưa được sửa", Toast.LENGTH_SHORT).show();
            }        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTenTXT = hoTen124.getText().toString();
                Boolean checkudeletedata = DB.deletedata(hoTenTXT);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "Sinh viên đã xoá", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Sinh viên chưa được xoá", Toast.LENGTH_SHORT).show();
            }        });

        xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "Chưa có sinh viên", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Họ và tên :"+res.getString(0)+"\n");
                    buffer.append("Mã sinh viên :"+res.getString(1)+"\n");
                    buffer.append("Ngày sinh :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Sinh viên");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}