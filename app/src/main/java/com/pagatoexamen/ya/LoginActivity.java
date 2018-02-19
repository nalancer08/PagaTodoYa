package com.pagatoexamen.ya;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.tapadoo.alerter.Alerter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText password;
    private Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Init controls
        this.user = findViewById(R.id.user);
        this.password = findViewById(R.id.password);
        this.login_button = findViewById(R.id.login_button);
        this.login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        // Setting status bar color
        this.setStatusBarColor();
    }

    private void login() {

        // Getting user and password field
        String mUser = this.user.getText().toString();
        String mPassword = this.password.getText().toString();

        if (mUser.equals("")) {
            showError(this.user, "Debes escribir un usuario");
            return;
        }

        if (mPassword.equals("")) {
            showError(this.password, "Debes escribir una contraseña");
            return;
        }

        // Init the post params
        JSONObject params = new JSONObject();
        JSONObject data = new JSONObject();

        try {

            data.put("user", mUser);
            data.put("pass", mPassword);
            params.put("data" ,data);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Init loader
        final SweetAlertDialog loader = this.getLoader("Validando información");
        loader.show();


        String url = "https://agentemovil.pagatodo.com/AgenteMovil.svc/agMov/login";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        Log.d("DXGOP", "Respuesta : " + response);
                        SharedPreferences prefs = getSharedPreferences(getPackageName(), 0);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("login", response.toString());
                        editor.commit();
                        loader.cancel();
                        route();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                showError(null, "Ha ocurrido un error, intentalo mas tarde.");
                Log.d("DXGOP", "Error : " + error.toString());
                loader.cancel();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("SO ","Android");
                params.put("Version ","2.5.2");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void showError(View view, String message) {

        if (view != null) {
            YoYo.with(Techniques.Shake).playOn(view);
        }

        Alerter.create(this)
                .setTitle("Error")
                .setText(message)
                .setBackgroundColorRes(R.color.red)
                .show();
    }

    private SweetAlertDialog getLoader(String messasge) {

        SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(messasge);
        pDialog.setCancelable(false);
        return pDialog;
    }

    private void route() {

        startActivity(new Intent(LoginActivity.this, MarcasActivity.class));
        finish();
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