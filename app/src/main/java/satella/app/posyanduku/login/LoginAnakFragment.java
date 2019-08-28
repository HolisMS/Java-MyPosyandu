package satella.app.posyanduku.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import satella.app.posyanduku.MainActivity;
import satella.app.posyanduku.R;

public class LoginAnakFragment extends Fragment {

    private LinearLayout push;
    private EditText edtPass, edtEmail;
    private Button btnLogin;
    private ProgressBar mProgres;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_anak, container, false);

        mAuth = FirebaseAuth.getInstance();

        push = view.findViewById(R.id.push);
        edtPass =  view.findViewById(R.id.edt_pass_anak);
        mProgres = view.findViewById(R.id.load_anak);
        edtEmail = view.findViewById(R.id.edt_email_anak);
        btnLogin = view.findViewById(R.id.btn_login_anak);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                inProgress(true);
                mAuth.signInWithEmailAndPassword(edtEmail.getText().toString(),edtPass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getContext(), "User signned in",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                return;
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(getContext(),"sign in failed", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterLoginAnakActivity.class);
                startActivity(intent);
            }
        });


        return view;
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
            btnLogin.setEnabled(false);
            push.setEnabled(false);
        }else {
            mProgres.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            push.setEnabled(true);
        }
    }


}
