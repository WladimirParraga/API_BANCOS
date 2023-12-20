package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import WebServices.WebService;
import java.util.Map;
import java.util.HashMap;
import WebServices.Asynchtask;
public class listabanco extends AppCompatActivity implements Asynchtask{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listabanco);

        Map<String, String> datos = new HashMap<>();


        WebService ws = new WebService(
                "https://api-uat.kushkipagos.com/transfer/v1/bankList",
                datos,
                listabanco.this,
                listabanco.this);

        ws.execute("GET", "Public-Merchant-Id", "84e1d0de1fbf437e9779fd6a52a9ca18");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtBancos = (TextView)findViewById(R.id.txtbanco);

        String lstBancos="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstBancos = lstBancos + "\n" +
                    banco.getString("name").toString();
        }
        txtBancos.setText("Respuesta WS Lista de Bancos" + lstBancos);

    }
}