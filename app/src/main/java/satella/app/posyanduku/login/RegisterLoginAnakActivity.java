package satella.app.posyanduku.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import satella.app.posyanduku.R;

public class RegisterLoginAnakActivity extends AppCompatActivity {

    private EditText edtUsername, edtEmail, edtPass;
    private Button btnRegist;
    private LinearLayout back;
    private ProgressBar mProgres;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register_login_anak);

        mAuth = FirebaseAuth.getInstance();

        edtUsername = findViewById(R.id.edt_username_anak);
        edtEmail = findViewById(R.id.edt_email_anak);
        edtPass = findViewById(R.id.edt_pass_anak);
        back = findViewById(R.id.back);
        btnRegist = findViewById(R.id.btn_register_anak);
        mProgres = findViewById(R.id.load_anak);

        setupFloatingLabelError();

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                inProgress(true);
                mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getApplicationContext(), "User registered succses",Toast.LENGTH_LONG).show();
                                inProgress(false);
                                regist();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(getApplicationContext(),"registered failed", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterLoginAnakActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private Boolean isEmpty(){
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

    private void inProgress(boolean b){
        if (b){
            mProgres.setVisibility(View.VISIBLE);
            btnRegist.setEnabled(false);
        }else {
            mProgres.setVisibility(View.GONE);
            btnRegist.setEnabled(true);
        }
    }

    private void setupFloatingLabelError() {
        final TextInputLayout floatingUsernameLabel = findViewById(R.id.username_text_input_layout);
        floatingUsernameLabel.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() > 0 && text.length() <= 4) {
                    floatingUsernameLabel.setError(getString(R.string.username_required));
                    floatingUsernameLabel.setErrorEnabled(true);
                } else {
                    floatingUsernameLabel.setErrorEnabled(false);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void regist(){
        edtPass.setText("");
        edtEmail.setText("");
        edtUsername.setText("");
    }


}
