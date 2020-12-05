package com.example.kalkulatorjelek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button hitung;
    private DatabaseReference mDatabase;
    RadioGroup opsi;
    RadioButton tambah,kurang,bagi,kali;
    TextView hasil;
    EditText angka_pertama, angka_kedua;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Kalkulator> kalkulatorArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.dataList);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        myrecyclerview();
        getData();
        hitung = findViewById(R.id.btn_hitung);
        opsi = findViewById(R.id.opsi);
        tambah = findViewById(R.id.tambah);
        kurang = findViewById(R.id.kurang);
        bagi = findViewById(R.id.bagi);
        kali = findViewById(R.id.kali);
        hasil = findViewById(R.id.hasil);
        angka_pertama = findViewById(R.id.angka1);
        angka_kedua = findViewById(R.id.angka2);

        opsi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.tambah :
                        kurang.setChecked(false);
                        bagi.setChecked(false);
                        kali.setChecked(false);
                        break;
                    case R.id.kurang :
                        tambah.setChecked(false);
                        bagi.setChecked(false);
                        kali.setChecked(false);
                        break;
                    case R.id.bagi :
                        kurang.setChecked(false);
                        tambah.setChecked(false);
                        kali.setChecked(false);
                        break;
                    case R.id.angka2:
                        kurang.setChecked(false);
                        bagi.setChecked(false);
                        tambah.setChecked(false);
                        break;

                }
            }
        });


        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tambah.isChecked()) {
                    if ((angka_pertama.getText().length() > 0) && (angka_kedua.getText().length() > 0)) {
                        double angka1 = Double.parseDouble(angka_pertama.getText().toString());
                        double angka2 = Double.parseDouble(angka_kedua.getText().toString());
                        double result = angka1 + angka2;
                        hasil.setText(Double.toString(result));
                        tambahData(angka_pertama.getText().toString(), angka_kedua.getText().toString(), Double.toString(result), "+");
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, "Mohon masukkan Angka pertama & Kedua", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                else if (kurang.isChecked()) {
                    if ((angka_pertama.getText().length() > 0) && (angka_kedua.getText().length() > 0)) {
                        double angka1 = Double.parseDouble(angka_pertama.getText().toString());
                        double angka2 = Double.parseDouble(angka_kedua.getText().toString());
                        double result = angka1 - angka2;
                        hasil.setText(Double.toString(result));
                        tambahData(angka_pertama.getText().toString(), angka_kedua.getText().toString(), Double.toString(result), "-");
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, "Mohon masukkan Angka pertama & Kedua", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                else if (kali.isChecked()) {
                    if ((angka_pertama.getText().length() > 0) && (angka_kedua.getText().length() > 0)) {
                        double angka1 = Double.parseDouble(angka_pertama.getText().toString());
                        double angka2 = Double.parseDouble(angka_kedua.getText().toString());
                        double result = angka1 * angka2;
                        hasil.setText(Double.toString(result));
                        tambahData(angka_pertama.getText().toString(), angka_kedua.getText().toString(), Double.toString(result), "*");
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, "Mohon masukkan Angka pertama & Kedua", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                else if (bagi.isChecked()) {
                    if ((angka_pertama.getText().length() > 0) && (angka_kedua.getText().length() > 0)) {
                        double angka1 = Double.parseDouble(angka_pertama.getText().toString());
                        double angka2 = Double.parseDouble(angka_kedua.getText().toString());
                        double result = angka1 / angka2;
                        hasil.setText(Double.toString(result));
                        tambahData(angka_pertama.getText().toString(), angka_kedua.getText().toString(), Double.toString(result), "/");
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, "Mohon masukkan Angka pertama & Kedua", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }

        });
    }

    private void myrecyclerview() {
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void getData() {
        mDatabase.child("hasil_kalkulator")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        kalkulatorArrayList = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Kalkulator kalkulator = snapshot.getValue(Kalkulator.class);

                            kalkulator.setKey(snapshot.getKey());
                            kalkulatorArrayList.add(kalkulator);
                        }
                        adapter = new RecyclerViewAdapter(kalkulatorArrayList, MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void tambahData(String angka1, String angka2, String hasil, String method) {

        mDatabase.child("hasil_kalkulator").push().setValue(new Kalkulator(angka1, angka2, hasil, method));

    }
}