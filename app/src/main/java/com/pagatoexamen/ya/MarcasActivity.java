package com.pagatoexamen.ya;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.pagatoexamen.ya.Adapter.MarcasFragmentAdapter;
import com.pagatoexamen.ya.Fragment.EmptyFragment;
import com.pagatoexamen.ya.Fragment.RecargasFragment;

public class MarcasActivity extends AppCompatActivity {

    private MarcasFragmentAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcas);

        // Inti screen structure
        this.setStatusBarColor();
        this.init();
    }

    private void init() {

        // Init adapter, tabs and viewpager
        this.viewPager = findViewById(R.id.pager);
        this.adapter = new MarcasFragmentAdapter(getSupportFragmentManager());
        this.adapter.addFragment(new RecargasFragment(), "RECARGAS");
        this.adapter.addFragment(new EmptyFragment(), "RECAUDACIÓN");
        this.adapter.addFragment(new EmptyFragment(), "ADMINISTRACIÓN");
        this.viewPager.setAdapter(this.adapter);

        this.tabLayout = findViewById(R.id.marcas_tabs);
        this.tabLayout.setupWithViewPager(this.viewPager);
    }

    @SuppressLint("NewApi")
    protected void setStatusBarColor() {

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.rgb(2, 115, 175));
        }
    }
}