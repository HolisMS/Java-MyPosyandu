package satella.app.posyanduku.POSYANDU_PAGES.timbangan_pages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import satella.app.posyanduku.POSYANDU_PAGES.models.Timbang;
import satella.app.posyanduku.R;
import satella.app.posyanduku.models.Anak;

public class TimbanganActivity extends AppCompatActivity{

    private EditText kodePosyandu, noKK, noNIK, tanggalTimbang, tinggiBadan, beratBadan, lingkarKepala;
    public static EditText edtNama;
    private Button buttonSubmit;

    private DatabaseReference database;
    private FirebaseDatabase db;
    Timbang timbang;
    long maxId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timbangan);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Timbangan");
        }

        edtNama = findViewById(R.id.edtNama);
        tanggalTimbang = findViewById(R.id.editTanggal);
        tinggiBadan = findViewById(R.id.editTinggi);
        beratBadan = findViewById(R.id.editBerat);
        lingkarKepala = findViewById(R.id.editLingkar);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        db = FirebaseDatabase.getInstance();
        database = db.getReference().child("timbang");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxId = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timbang timbang = new Timbang();
                timbang.setNama(edtNama.getText().toString().trim());
                timbang.setTgl_timbang(tanggalTimbang.getText().toString().trim());
                timbang.setTinggi_timbang(tinggiBadan.getText().toString().trim());
                timbang.setBerat_badan(beratBadan.getText().toString().trim());
                timbang.setLinkar_badan(lingkarKepala.getText().toString().trim());

                if (!isEmpty(edtNama.getText().toString()) && !isEmpty(tanggalTimbang.getText().toString()) &&
                        !isEmpty(tinggiBadan.getText().toString()) && !isEmpty(beratBadan.getText().toString()) &&
                        !isEmpty(lingkarKepala.getText().toString())){
                    database.child(String.valueOf(maxId)).setValue(timbang);
                    submitTimbang();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Data tidak boleh kosong",Toast.LENGTH_LONG).show();
                    InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(edtNama.getWindowToken(),0);
                }

            }
        });

    }

    private Boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    private void submitTimbang(){
        edtNama.setText("");
        tanggalTimbang.setText("");
        tinggiBadan.setText("");
        beratBadan.setText("");
        lingkarKepala.setText("");
        Toast.makeText(getApplicationContext(),"Berhasil simpan data",Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_timbang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.scan_timbang:
                startActivity(new Intent(this, ScanBarcodeActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
