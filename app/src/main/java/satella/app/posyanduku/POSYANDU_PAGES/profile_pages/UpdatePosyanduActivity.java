package satella.app.posyanduku.POSYANDU_PAGES.profile_pages;

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

import satella.app.posyanduku.R;
import satella.app.posyanduku.models.Login;

public class UpdatePosyanduActivity extends AppCompatActivity {

    private EditText edtId, edtNmaPosyandu, edtKetua;
    private EditText edtNoTelp, edtRW, edtKel, edtKec, edtKota, edtProv;
    private Button btnUpdate;

    private DatabaseReference database;
    private FirebaseDatabase db;
    Login login;
    long IdPoyandu = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_posyandu);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Kelola data");
        }

        edtId = findViewById(R.id.edt_idPosyandu);
        edtNmaPosyandu = findViewById(R.id.edt_nm_posyandu);
        edtKetua = findViewById(R.id.edt_nm_ketua);
        edtNoTelp = findViewById(R.id.edt_nomer_telp);
        edtRW = findViewById(R.id.edt_rw);
        edtKel = findViewById(R.id.edt_kel);
        edtKec = findViewById(R.id.edt_kec);
        edtKota = findViewById(R.id.edt_kota);
        edtProv = findViewById(R.id.edt_provinsi);
        btnUpdate = findViewById(R.id.btn_update_posy);

        db = FirebaseDatabase.getInstance();
        database = db.getReference().child("posyandu");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login login = new Login();
                login.setId_posyandu(edtId.getText().toString().trim());
                login.setNama_posyandu(edtNmaPosyandu.getText().toString().trim());
                login.setKetua(edtKetua.getText().toString().trim());
                login.setNoTelp(edtNoTelp.getText().toString().trim());
                login.setRw(edtRW.getText().toString().trim());
                login.setKelurahan(edtKel.getText().toString().trim());
                login.setKecamatan(edtKec.getText().toString().trim());
                login.setKota(edtKota.getText().toString().trim());
                login.setProvinsi(edtProv.getText().toString().trim());

                if (!isEmpty(edtId.getText().toString()) && !isEmpty(edtNmaPosyandu.getText().toString()) &&
                        !isEmpty(edtKetua.getText().toString()) && !isEmpty(edtNoTelp.getText().toString()) &&
                        !isEmpty(edtRW.getText().toString()) && !isEmpty(edtKel.getText().toString()) &&
                        !isEmpty(edtKec.getText().toString()) && !isEmpty(edtKota.getText().toString()) &&
                        !isEmpty(edtProv.getText().toString())){
                    database.child(String.valueOf(IdPoyandu)).setValue(login);
                    update();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Data tidak boleh kosong",Toast.LENGTH_LONG).show();
                    InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(edtId.getWindowToken(),0);
                }
            }
        });

    }

    private Boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }
    private void update(){
        edtId.setText("");
        edtNmaPosyandu.setText("");
        edtKetua.setText("");
        edtNoTelp.setText("");
        edtRW.setText("");
        edtKel.setText("");
        edtKec.setText("");
        edtKota.setText("");
        edtProv.setText("");
        Toast.makeText(getApplicationContext(),"Data berhasil diupdate",Toast.LENGTH_LONG).show();

    }

}
