package satella.app.posyanduku.POSYANDU_PAGES.jadwal_pages;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import satella.app.posyanduku.R;

public class BuatJadwalActivity extends AppCompatActivity {
    private EditText btnDatePicker;
    private TextView tvDateresult;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat;
    private Spinner spinnerBulan;
    private Button btnSubmit;
    private DatabaseReference dbref;
    private FirebaseDatabase database;

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tvDateresult.setText(dateFormat.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_jadwal);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Buat jadwal");
        }

        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvDateresult = findViewById(R.id.edtTanggal);
        btnDatePicker = findViewById(R.id.edtTanggal);
        spinnerBulan = findViewById(R.id.spinner_bulan);
        btnSubmit = findViewById(R.id.btn_submitCalender);

        database = FirebaseDatabase.getInstance();
        dbref = database.getReference("jadwal");

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();;
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference nilai = dbref.push();

                String BulanNilai = spinnerBulan.getSelectedItem().toString();
                String TanggalNilai = tvDateresult.getText().toString();

                nilai.child("bulan").setValue(BulanNilai);
                nilai.child("tanggal").setValue(TanggalNilai);
                submit();
            }
        });

    }
    private void submit(){
        tvDateresult.setText("");
        spinnerBulan.setPrompt("");
        Toast.makeText(getApplicationContext(),"Berhasil simpan data",Toast.LENGTH_LONG).show();

    }
}
