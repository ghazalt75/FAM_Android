package com.example.fam.Activity.Repair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fam.R;
import com.example.fam.Activity.EquipmentCode.EquipmentCodeActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RepairActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private TextView tvToolbarTitle;
    private ImageButton imgEquipments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        configureLayout();
    }

    private void configureLayout() {
        //initialize toolbar
        toolbar = findViewById(R.id.repairToolbar);
        tvToolbarTitle = toolbar.findViewById(R.id.repairToolbarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initialize ImageButton
        imgEquipments = findViewById(R.id.imgEquipments);
        imgEquipments.setOnClickListener(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgEquipments) {
            startActivity(new Intent(RepairActivity.this, EquipmentCodeActivity.class));
        }
    }
}
