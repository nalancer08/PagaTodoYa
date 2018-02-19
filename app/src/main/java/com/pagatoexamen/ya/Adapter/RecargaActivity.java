package com.pagatoexamen.ya.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pagatoexamen.ya.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RecargaActivity extends AppCompatActivity {

    private TextView back;
    private ImageView logo;
    private EditText phone;
    private EditText money;
    private Button continue_button;

    private Bitmap logo_bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga);
        this.setStatusBarColor();

        // Init controls
        this.back = findViewById(R.id.back_button);
        this.logo = findViewById(R.id.logo);
        this.phone = findViewById(R.id.phone);
        this.money = findViewById(R.id.money);
        this.continue_button = findViewById(R.id.continue_button);

        // Init logo image
        this.initLogo();

        // Init continue button
        this.setContinueButton();
    }

    private void initLogo() {

        String marca = "ic_" + getIntent().getStringExtra("tipo");
        int id = getResources().getIdentifier(marca,"drawable", getPackageName());
        this.logo_bm = BitmapFactory.decodeStream(getResources().openRawResource(id));
        this.logo.setImageBitmap(this.logo_bm);

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setContinueButton() {

        this.continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(RecargaActivity.this);
                dialog.setContentView(R.layout.dialog_recarga);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setLayout(getX() - 20, getY() - threeRuleY(250));
                //dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_borders);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                //dialog.getWindow().getDecorView().setBackgroundResource(R.color.transparent);
                dialog.show();

                // Setting dialog
                TextView hour = dialog.findViewById(R.id.hour);
                hour.setText(Calendar.getInstance().getTime().toString());

                TextView date = dialog.findViewById(R.id.date);
                date.setText(new SimpleDateFormat("MMM d, yyyy").toString());

                ImageView logo = dialog.findViewById(R.id.logo);
                logo.setImageBitmap(logo_bm);

                TextView phoneText = dialog.findViewById(R.id.phone);
                phoneText.setText(phone.getText());

                TextView mount = dialog.findViewById(R.id.mount);
                mount.setText("$" + money.getText());

                Button cancel = dialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                Button acept = dialog.findViewById(R.id.acept);
                acept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

            }
        });

    }

    /**
     * View helpers
     **/

    protected int threeRuleY(int value) {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int heigth = size.y;

        return (heigth * value) / 1794;
    }

    protected int threeRuleX(int value) {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        return (width * value) / 1000;
    }

    /** Dialog helpers **/

    protected int getY() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    protected int getX() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
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