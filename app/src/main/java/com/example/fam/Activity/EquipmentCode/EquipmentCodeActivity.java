package com.example.fam.Activity.EquipmentCode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fam.Activity.EquipmentDetails.EquipmentDetailsActivity;
import com.example.fam.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EquipmentCodeActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private TextView tvToolbarTitle,tvEquipmentName;
    private Button btnBarcodeReader,btnShowEquipmentDetails;
    private EditText etBarcode;
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_code);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        configureLayout();
    }

    private void configureLayout() {
        //configure Toolbar
        toolbar = findViewById(R.id.equipmentCodeToolbar);
        tvToolbarTitle = toolbar.findViewById(R.id.equipmentCodeToolbarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //configure Button
        btnBarcodeReader = findViewById(R.id.btnBarcodeReader);
        btnBarcodeReader.setOnClickListener(this);
        btnShowEquipmentDetails = findViewById(R.id.btnShowEquipmentDetails);
        btnShowEquipmentDetails.setOnClickListener(this);

        //configure EditText
        etBarcode = findViewById(R.id.etBarcode);

        //configure TextView
        tvEquipmentName = findViewById(R.id.tvEquipmentName);

        //configure QRScanner
        qrScan = new IntentIntegrator(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null) {
            if (result.getContents() == null) {
                tvEquipmentName.setVisibility(View.VISIBLE);
                tvEquipmentName.setText("نتیجه ای یافت نشد!");
            } else {
                etBarcode.setText(result.getContents());
                tvEquipmentName.setVisibility(View.VISIBLE);
                tvEquipmentName.setText("این بارکد مربوط به تجهیز ... میباشد.");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBarcodeReader) {
            qrScan.initiateScan();
        }
        else if (v.getId() == R.id.btnShowEquipmentDetails) {
            startActivity(new Intent(EquipmentCodeActivity.this, EquipmentDetailsActivity.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
