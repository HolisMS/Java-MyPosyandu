package satella.app.posyanduku.data_pages.data_orang_tua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import satella.app.posyanduku.R;
import satella.app.posyanduku.firebase_helper.DatabaseHelperBapak;
import satella.app.posyanduku.models.Anak;
import satella.app.posyanduku.models.Bapak;

public class UpdateBapakActivity extends AppCompatActivity {
    private EditText etKK, etNm, etKerja, etPend;
    private EditText etAlamat, etAgama, etTmptLahir, etTglLahir;
    private String key, kk, nama, kerja, pendidikan, alamat;
    private String agama, tmpLahir, tglLahir;
    private Button btnUpdate, btnDelete;

    private DatabaseReference database;
    private FirebaseDatabase db;
    Bapak bapak;
    long IdB = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bapak);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Kelola data");
        }

        etKK = findViewById(R.id.edt_NoKK);
        etNm = findViewById(R.id.edt_Nama_bpk);
        etKerja = findViewById(R.id.edt_pekerjaan_bpk);
        etPend = findViewById(R.id.edt_pendidikan_bpk);
        etAlamat = findViewById(R.id.edt_alamat_bpk);
        etAgama = findViewById(R.id.edt_agama_bpk);
        etTglLahir = findViewById(R.id.edt_tgl_lhr_bpk);
        etTmptLahir = findViewById(R.id.edt_tmp_lhr_bkk);
        btnUpdate = findViewById(R.id.btn_update_bpk);
        btnDelete = findViewById(R.id.btn_delete_bpk);

        db = FirebaseDatabase.getInstance();
        database = db.getReference().child("bapak");
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//                    IdB = (dataSnapshot.getChildrenCount());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        initView();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bapak bapak = new Bapak();
                bapak.setNoKkBapak(etKK.getText().toString().trim());
                bapak.setNama_bapak(etNm.getText().toString().trim());
                bapak.setPekerjaan(etKerja.getText().toString().trim());
                bapak.setPendidikan(etPend.getText().toString().trim());
                bapak.setAlamat(etAlamat.getText().toString().trim());
                bapak.setAgama(etAgama.getText().toString().trim());
                bapak.setTempat_lahir(etTmptLahir.getText().toString().trim());
                bapak.setTanggal_lahir(etTglLahir.getText().toString().trim());

                if (!isEmpty(etKK.getText().toString()) && !isEmpty(etNm.getText().toString()) &&
                        !isEmpty(etKerja.getText().toString()) && !isEmpty(etPend.getText().toString()) &&
                        !isEmpty(etAlamat.getText().toString()) && !isEmpty(etAgama.getText().toString()) &&
                        !isEmpty(etTmptLahir.getText().toString()) && !isEmpty(etTglLahir.getText().toString())){
                    database.child(String.valueOf(IdB)).setValue(bapak);
                    updateBapak();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Data tidak boleh kosong",Toast.LENGTH_LONG).show();
                    InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(etKK.getWindowToken(),0);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatabaseHelperBapak().deleteBapak(key, new DatabaseHelperBapak.DataStatusBapak() {
                    @Override
                    public void DataIsLoadBapak(List<Bapak> bapaks, List<String> keys) {

                    }

                    @Override
                    public void DataIsInsertBapak() {

                    }

                    @Override
                    public void DataIsUpdateBapak() {

                    }

                    @Override
                    public void DataIsDeleteBapak() {
                        Toast.makeText(getApplicationContext(),"Data berhasil dihapus",Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });
            }
        });

    }

    void initView(){
        key = getIntent().getStringExtra("key");
        kk = getIntent().getStringExtra("kk");
        nama = getIntent().getStringExtra("nma");
        kerja = getIntent().getStringExtra("kerja");
        tglLahir = getIntent().getStringExtra("tglLahir");
        tmpLahir = getIntent().getStringExtra("tmpLahir");
        pendidikan = getIntent().getStringExtra("pend");
        agama = getIntent().getStringExtra("agama");
        alamat = getIntent().getStringExtra("alamat");

        etKK.setText(kk);
        etNm.setText(nama);
        etTmptLahir.setText(tmpLahir);
        etTglLahir.setText(tglLahir);
        etAgama.setText(agama);
        etAlamat.setText(alamat);
        etKerja.setText(kerja);
        etPend.setText(pendidikan);
    }


    private Boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    private void updateBapak(){
        etKK.setText("");
        etNm.setText("");
        etKerja.setText("");
        etPend.setText("");
        etTglLahir.setText("");
        etAlamat.setText("");
        etAgama.setText("");
        etTmptLahir.setText("");
        Toast.makeText(getApplicationContext(),"Data berhasil diupdate",Toast.LENGTH_LONG).show();

    }
}
