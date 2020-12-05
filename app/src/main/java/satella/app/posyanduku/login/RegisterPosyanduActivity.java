package satella.app.posyanduku.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import satella.app.posyanduku.R;
import satella.app.posyanduku.models.Login;

public class RegisterPosyanduActivity extends AppCompatActivity {

    private LinearLayout back;
    private Button btnRegist;
    private EditText edtEmail, edtPass, edtId, edtNmaPosyandu, edtKetua;
    private EditText edtNoTelp, edtRW, edtKel, edtKec, edtKota, edtProv;
    private ProgressBar mProgres;

    private FirebaseAuth mAuth;
    private DatabaseReference database;
    private FirebaseDatabase db;

    Login login;
    long maxIdLog = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register_posyandu);

        back = findViewById(R.id.back);
        btnRegist = findViewById(R.id.btn_register_posy);
        edtEmail = findViewById(R.id.edt_email_posy);
        edtPass = findViewById(R.id.edt_pass_posy);
        edtId = findViewById(R.id.edt_idPosyandu);
        edtNmaPosyandu = findViewById(R.id.edt_nm_posyandu);
        edtKetua = findViewById(R.id.edt_nm_ketua);
        edtNoTelp = findViewById(R.id.edt_nomer_telp);
        edtRW = findViewById(R.id.edt_rw);
        edtKel = findViewById(R.id.edt_kel);
        edtKec = findViewById(R.id.edt_kec);
        edtKota = findViewById(R.id.edt_kota);
        edtProv = findViewById(R.id.edt_provinsi);
        mProgres = findViewById(R.id.progres);

        db = FirebaseDatabase.getInstance();
        database = db.getReference().child("posyandu");
        mAuth = FirebaseAuth.getInstance();

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login login = new Login();
                register();

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
                    database.child(String.valueOf(maxIdLog)).setValue(login);
                    regist();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Data tidak boleh kosong",Toast.LENGTH_LONG).show();
                    InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(edtId.getWindowToken(),0);
                }


            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void register(){
        if (isEmptyGist()) return;
        inProgress(true);
        mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPass.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getApplicationContext(), "User registered succses",Toast.LENGTH_LONG).show();
                        inProgress(false);

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                inProgress(false);
                Toast.makeText(getApplicationContext(),"registered failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private Boolean isEmptyGist(){
        if (TextUtils.isEmpty(edtEmail.getText().toString())){
            edtEmail.setError("Error !!");
            return true;
        }
        if (TextUtils.isEmpty(edtPass.getText().toString())){
            edtPass.setError("Error !!");
            return true;
        }
        return false;
    }
    private Boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    private void inProgress(boolean b){
        if (b){
            mProgres.setVisibility(View.VISIBLE);
            btnRegist.setEnabled(false);
        }else {
            mProgres.setVisibility(View.GONE);
            btnRegist.setEnabled(true);
        }
    }

    private void regist(){
        edtEmail.setText("");
        edtPass.setText("");
        edtId.setText("");
        edtNmaPosyandu.setText("");
        edtKetua.setText("");
        edtNoTelp.setText("");
        edtRW.setText("");
        edtKel.setText("");
        edtKec.setText("");
        edtKota.setText("");
        edtProv.setText("");

    }


}
