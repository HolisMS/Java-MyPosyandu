package satella.app.posyanduku.POSYANDU_PAGES.timbangan_pages;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;
import com.sachinvarma.easypermission.EasyPermissionInit;
import com.sachinvarma.easypermission.EasyPermissionList;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import satella.app.posyanduku.models.Anak;

public class ScanBarcodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);


        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Scan barcode");
        }

        ///permission camera
        List<String> permission = new ArrayList<>();
        permission.add(EasyPermissionList.CAMERA);
        new EasyPermissionInit(ScanBarcodeActivity.this, permission);

    }


    @Override
    public void handleResult(Result result) {
        Anak anak = new Anak();
        String nama = anak.getNamaLengkap();
        String tgl = anak.getTglLahir();

        TimbanganActivity.edtNama.setText(result.getText());
        onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }



}
