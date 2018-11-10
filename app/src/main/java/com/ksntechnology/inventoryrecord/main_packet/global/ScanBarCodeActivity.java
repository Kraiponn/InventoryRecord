package com.ksntechnology.inventoryrecord.main_packet.global;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;
import com.ksntechnology.inventoryrecord.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanBarCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        // Programmatically initialize the scanner view
        setContentView(mScannerView);
        // Set the scanner view as the content view
        mPlayer = MediaPlayer.create(
                getBaseContext(),
                R.raw.beep06
        );

        /*try {
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
        // Start camera on resume
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
        // Stop camera on pause
        mPlayer.stop();
        mPlayer.release();
    }

    @Override
    public void handleResult(Result result) {
        mPlayer.start();
        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
        Intent intent = new Intent();
        intent.putExtra("barcode", result.getText());
        setResult(RESULT_OK, intent);
        finish();
    }
}
