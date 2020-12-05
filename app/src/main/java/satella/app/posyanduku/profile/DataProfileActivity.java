package satella.app.posyanduku.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import net.glxn.qrgen.android.QRCode;
import net.glxn.qrgen.core.scheme.VCard;

import me.dm7.barcodescanner.core.BarcodeScannerView;
import satella.app.posyanduku.R;
import satella.app.posyanduku.models.Anak;

public class DataProfileActivity extends AppCompatActivity {

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, DataProfileActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_data_profile);

        ImageView myImage= findViewById(R.id.imageView);

        Anak anaks= (Anak) getIntent().getSerializableExtra("anak");
        String nama =anaks.getNamaLengkap();
        String tgl =anaks.getTglLahir();

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(nama,
                    BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            myImage.setImageBitmap(bitmap);

        }catch (WriterException e){
            e.printStackTrace();
        }


    }
}
