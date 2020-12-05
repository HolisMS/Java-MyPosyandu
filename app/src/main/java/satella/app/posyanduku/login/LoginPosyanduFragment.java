package satella.app.posyanduku.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import satella.app.posyanduku.MainActivity;
import satella.app.posyanduku.POSYANDU_PAGES.PosyanduMainActivity;
import satella.app.posyanduku.R;

public class LoginPosyanduFragment extends Fragment {

    private Button btnSign;
    private EditText edtPass, edtEmail;
    private ProgressBar mProgres;
    private LinearLayout push;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_posyandu, container, false);

        mAuth = FirebaseAuth.getInstance();

        edtPass = view.findViewById(R.id.edt_pass_posy);
        edtEmail = view.findViewById(R.id.edt_email_posy);
        btnSign = view.findViewById(R.id.btn_login_posy);
        mProgres = view.findViewById(R.id.load_anak);
        push = view.findViewById(R.id.push_posy);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                inProgress(true);
                mAuth.signInWithEmailAndPassword(edtEmail.getText().toString(),edtPass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getContext(), "User signned in",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getContext(), PosyanduMainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                getActivity().finish();
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
                Intent intent = new Intent(getContext(), RegisterPosyanduActivity.class);
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
            btnSign.setEnabled(false);
            push.setEnabled(false);
        }else {
            mProgres.setVisibility(View.GONE);
            btnSign.setEnabled(true);
            push.setEnabled(true);
        }
    }

}
