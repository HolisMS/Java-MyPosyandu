package satella.app.posyanduku.data_pages.data_orang_tua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import satella.app.posyanduku.R;
import satella.app.posyanduku.firebase_helper.DatabaseHelperBapak;
import satella.app.posyanduku.firebase_helper.DatabaseHelperIbu;
import satella.app.posyanduku.models.Bapak;
import satella.app.posyanduku.models.Ibu;

public class UpdateIbuActivity extends AppCompatActivity {

    private EditText etKK, etNm, etKerja, etPend;
    private EditText etAlamat, etAgama, etTmptLahir, etTglLahir;
    private String key, kk, nama, kerja, pendidikan, alamat;
    private String agama, tmpLahir, tglLahir;
    private Button btnUpdate, btnDelete;

    private DatabaseReference database;
    private FirebaseDatabase db;
    Ibu ibu;
    long IdB = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ibu);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Kelola data");
        }

        etNm = findViewById(R.id.edt_Nama_ibu);
        etKerja = findViewById(R.id.edt_pekerjaan_ibu);
        etPend = findViewById(R.id.edt_pendidikan_ibu);
        etAlamat = findViewById(R.id.edt_alamat_ibu);
        etAgama = findViewById(R.id.edt_agama_ibu);
        etTglLahir = findViewById(R.id.edt_tgl_lhr_ibu);
        etTmptLahir = findViewById(R.id.edt_tmp_lhr_ibu);
        btnUpdate = findViewById(R.id.btn_update_ibu);
        btnDelete = findViewById(R.id.btn_delete_ibu);

        db = FirebaseDatabase.getInstance();
        database = db.getReference().child("ibu");

        initView();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ibu ibu = new Ibu();

                ibu.setNama_ibu(etNm.getText().toString().trim());
                ibu.setPekerjaan(etKerja.getText().toString().trim());
                ibu.setPendidikan(etPend.getText().toString().trim());
                ibu.setAlamat(etAlamat.getText().toString().trim());
                ibu.setAgama(etAgama.getText().toString().trim());
                ibu.setTempat_lahir(etTmptLahir.getText().toString().trim());
                ibu.setTanggal_lahir(etTglLahir.getText().toString().trim());

                if (!isEmpty(etNm.getText().toString()) &&
                        !isEmpty(etKerja.getText().toString()) && !isEmpty(etPend.getText().toString()) &&
                        !isEmpty(etAlamat.getText().toString()) && !isEmpty(etAgama.getText().toString()) &&
                        !isEmpty(etTmptLahir.getText().toString()) && !isEmpty(etTglLahir.getText().toString())){
                    database.child(String.valueOf(IdB)).setValue(ibu);
                    updateIbu();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Data tidak boleh kosong",Toast.LENGTH_LONG).show();
                    InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(etNm.getWindowToken(),0);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatabaseHelperIbu().deleteIbu(key, new DatabaseHelperIbu.DataStatusIbu() {
                    @Override
                    public void DataIsLoadIbu(List<Ibu> ibus, List<String> keys) {

                    }

                    @Override
                    public void DataIsInsertIbu() {

                    }

                    @Override
                    public void DataIsUpdateIbu() {

                    }

                    @Override
                    public void DataIsDeleteIbu() {
                        Toast.makeText(getApplicationContext(),"Data berhasil dihapus",Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });
            }
        });
    }

    void initView(){
        key = getIntent().getStringExtra("key");
        nama = getIntent().getStringExtra("nma");
        kerja = getIntent().getStringExtra("kerja");
        tglLahir = getIntent().getStringExtra("tglLahir");
        tmpLahir = getIntent().getStringExtra("tmpLahir");
        pendidikan = getIntent().getStringExtra("pend");
        agama = getIntent().getStringExtra("agama");
        alamat = getIntent().getStringExtra("alamat");

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

    private void updateIbu(){
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
