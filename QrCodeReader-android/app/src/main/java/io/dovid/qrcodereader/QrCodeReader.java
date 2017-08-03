package io.dovid.qrcodereader;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import com.google.zxing.Result;


import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class QrCodeReader extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private static final int PERMESSO_FOTOCAMERA = 4725;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // Richiediamo i permessi a runtime (per tutti i dispositivi >= Android 6.0)
        if (ContextCompat.checkSelfPermission(QrCodeReader.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // richiediamo il permesso di utilizzare la fotocamera
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    1);
        }

        mScannerView = new ZXingScannerView(this);   // Inizializziamo la nostra scanner view
        setContentView(mScannerView); // settiamo la nostra scannerview come content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String url = rawResult.getText();
        System.out.println(url);
        if (!URLUtil.isValidUrl(url)) {
            AlertDialog dialog = new AlertDialog.Builder(this).
                    setTitle("Attenzione").
                    setMessage("Il qr code non contiene un url!").
                    setPositiveButton("Qr code", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).
                    show();
        } else {
            openBrowser(url);
        }

        mScannerView.resumeCameraPreview(this);
    }

    private void openBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}