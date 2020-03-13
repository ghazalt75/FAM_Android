package com.example.fam.Activity.EquipmentDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.example.fam.Adapter.EquipmentDetailsViewPagerAdapter;
import com.example.fam.Fragment.DocumentFragment;
import com.example.fam.Fragment.KeepingFragment;
import com.example.fam.Fragment.PlotFragment;
import com.example.fam.Fragment.RepairsFragment;
import com.example.fam.R;
import com.google.android.material.tabs.TabLayout;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EquipmentDetailsActivity extends AppCompatActivity {

    private RtlViewPager equipmentDetailsViewPager;
    private TabLayout equipmentDetailsTabLayout;
    private EquipmentDetailsViewPagerAdapter adapter;
    private Toolbar toolbar;
    private TextView tvToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_details);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        configureLayout();
    }

    private void configureLayout() {
        //configure Toolbar
        toolbar = findViewById(R.id.equipmentDetailsToolbar);
        tvToolbarTitle = toolbar.findViewById(R.id.equipmentDetailsToolbarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //configure TabLayout & ViewPager
        equipmentDetailsTabLayout = findViewById(R.id.equipmentDetailsTabLayout);
        equipmentDetailsViewPager = findViewById(R.id.equipmentDetailsViewPager);
        adapter = new EquipmentDetailsViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlotFragment(),getString(R.string.plot));
        adapter.addFragment(new RepairsFragment(),getString(R.string.repairs));
        adapter.addFragment(new KeepingFragment(),getString(R.string.keeping));
        adapter.addFragment(new DocumentFragment(),getString(R.string.document));
        equipmentDetailsViewPager.setAdapter(adapter);
        equipmentDetailsTabLayout.setupWithViewPager(equipmentDetailsViewPager);
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
}
