package com.gautomation.chapiscorobot.ui.controle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gautomation.chapiscorobot.R;
import com.gautomation.chapiscorobot.api.Config_Chapisco_Service;
import com.gautomation.chapiscorobot.model.Config_Chapisco;
import com.gautomation.chapiscorobot.model.ValorInicial;
import com.gautomation.chapiscorobot.model.ValorFinal;
import com.gautomation.chapiscorobot.model.ControManual;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class ControleFragment extends Fragment {

    private Retrofit retrofit;
    private String Valor = "0";
    //Timer myTimer = new Timer();
    private String ValorInicial = "0";
    private String ValorFinal = "0";
    private Button Ymais, Ymenos, Xmais, Xmenos, btngravainicio, btngravafinal, btnIniciar;
    private TextView txtStepsY, txtStepsX;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch SWdirecaodeoperaco;
    private boolean Ymais_parar;
    Timer myTimer = new Timer();
    private boolean Ymenos_parar;
    private boolean Xmais_parar;
    private boolean Xmenos_parar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_controle, container, false);

        Ymais = root.findViewById(R.id.btnYmais);
        Ymenos = root.findViewById(R.id.btnYmenos);
        Xmais = root.findViewById(R.id.btnXmais);
        Xmenos = root.findViewById(R.id.btnXmenos);
        btngravainicio = root.findViewById(R.id.btnGravarInicio);
        btngravafinal = root.findViewById(R.id.btnGravarFinal);
        txtStepsY = root.findViewById(R.id.txtStepsY);
        txtStepsX = root.findViewById(R.id.txtStepsX);
        btnIniciar = root.findViewById(R.id.btnIniciar);
        btnIniciar.setVisibility(View.INVISIBLE);
        SWdirecaodeoperaco = root.findViewById(R.id.sw);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.4.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        btngravainicio.setOnClickListener(v -> {
            GravaPontoInicial(Integer.parseInt(Valor));
        });
        btngravafinal.setOnClickListener(v -> {
            GravaPontoFinal(Integer.parseInt(Valor));
        });

        // BOTÃO MAIS Y ==============
        Ymais.setOnLongClickListener(v -> {
            ComandosManual(11);
            return false;
        });
        Ymais.setOnClickListener(v -> {
            ComandosManual(22);
            Ymais_parar = true;
        });
        // ============================
        // BOTÃO MENOS Y ==============
        Ymenos.setOnLongClickListener(v -> {
            ComandosManual(33);
            return false;
        });
        Ymenos.setOnClickListener(v -> {
            ComandosManual(44);
            Ymenos_parar = true;
        });
        // =============================

        // BOTÃO MAIS X ==============
        Xmais.setOnLongClickListener(v -> {
            ComandosManual(55);
            return false;
        });
        Xmais.setOnClickListener(v -> {
            ComandosManual(66);
            Xmais_parar = true;
        });
        // =============================
        // BOTÃO MENOS X ==============
        Xmenos.setOnLongClickListener(v -> {
            ComandosManual(77);
            return false;
        });
        Xmenos.setOnClickListener(v -> {
            ComandosManual(88);
            Xmenos_parar = true;
        });
        // ==============================
        btnIniciar.setOnClickListener(v -> ComandosManual(200));

        SWdirecaodeoperaco.setOnClickListener(v -> {
            ComandosManual(100);
        });

//        myTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                controledeparada();
//            }
//
//        }, 0, 100);

        return root;
    }
    void controledeparada(){
        if(Ymais_parar){
            ComandosManual(22);
            //Ymais_parar = false;
        }
        if(Ymenos_parar){
            ComandosManual(44);
            //Ymenos_parar = false;
        }
        if(Xmais_parar){
            ComandosManual(66);
            //Xmais_parar = false;
        }
        if(Xmenos_parar){
            ComandosManual(88);
            //Xmenos_parar = false;
        }
    }

    private void getDadosEsp() {
        Config_Chapisco_Service Config = retrofit.create(Config_Chapisco_Service.class);
        Call<Config_Chapisco> call = Config.RecuperaConfiguraoes();

        call.enqueue(new Callback<Config_Chapisco>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Config_Chapisco> call, Response<Config_Chapisco> response) {
                if (response.isSuccessful()) {
                    Config_Chapisco dados = response.body();
                    assert dados != null;
                    ValorInicial = dados.getVALORINICIAL();
                    ValorFinal = dados.getVALORFINAL();
                    btngravainicio.setText("Inicio Solda\n"+ValorInicial);
                    btngravafinal.setText("Final Solda\n"+ValorFinal);
                    int i1 = Integer.parseInt(ValorInicial);
                    int i2 = Integer.parseInt(ValorFinal);
                    if(i1 == 0 || i2 == 0 || i1 <= i2) {
                        btnIniciar.setVisibility(View.INVISIBLE);
                    }else{
                        btnIniciar.setVisibility(View.VISIBLE);
                    }
                    txtStepsY.setText("Eixo Y\n"+dados.getSTEPS_Y());
                    txtStepsX.setText("Eixo X\n"+dados.getSTEPS_X());
                    if(dados.isCHAPISCO_STATUS()){
                        btnIniciar.setBackgroundResource(R.color.BTNON);
                        btnIniciar.setText("Parar Chapisco ?");
                    }else{
                        btnIniciar.setBackgroundResource(R.color.BTNOFF);
                        btnIniciar.setText("Iniciar Chapisco ?");
                    }
                    if(dados.isDIRECAOOPRECAO()){
                        SWdirecaodeoperaco.setChecked(true);
                        Xmais.setText(">>");
                        Xmenos.setText("");
                    }else{
                        SWdirecaodeoperaco.setChecked(false);
                        Xmais.setText("");
                        Xmenos.setText("<<");
                    }
                }
            }
            @Override
            public void onFailure(Call<Config_Chapisco> call, Throwable t) {

            }
        });
    }
    private void ComandosManual(int v){
        Config_Chapisco_Service SalvaConfig = retrofit.create( Config_Chapisco_Service.class );
        Call<ControManual> call = SalvaConfig.Controles( v );

        call.enqueue(new Callback<ControManual>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ControManual> call, Response<ControManual> response) {
                if( response.isSuccessful() ){
                    ControManual CONTR = response.body();
                    assert CONTR != null;

                    if(CONTR.getSTEPS_Y() != null){
                        txtStepsY.setText("Eixo Y\n"+CONTR.getSTEPS_Y());
                        Valor = CONTR.getSTEPS_Y();
                    }
                    if(CONTR.getSTEPS_X() != null){
                        txtStepsX.setText("Eixo X\n"+CONTR.getSTEPS_X());
                    }
                    if(v == 200) {
                        if (CONTR.isCHAPISCO_STATUS()) {
                            btnIniciar.setBackgroundResource(R.color.BTNON);
                            btnIniciar.setText("Parar Chapisco ?");
                        } else {
                            btnIniciar.setBackgroundResource(R.color.BTNOFF);
                            btnIniciar.setText("Iniciar Chapisco ?");
                        }
                    }
                    if(v == 100){
                        if(CONTR.isDIRECAOOPRECAO()){
                            SWdirecaodeoperaco.setChecked(true);
                            Xmais.setText(">>");
                            Xmenos.setText("");
                        }else{
                            SWdirecaodeoperaco.setChecked(false);
                            Xmais.setText("");
                            Xmenos.setText("<<");
                        }
                    }
                    if(CONTR.getPARADOMAISY() != 1){
                        //ComandosManual(22);
                        Ymais_parar = false;
                    }
                    if(CONTR.getPARADOMENOSY() != 1){
                        //ComandosManual(44);
                        Ymenos_parar = false;
                    }
                    if(CONTR.getPARADOMAISX() != 1){
                        //ComandosManual(66);
                        Xmais_parar = false;
                    }
                    if(CONTR.getPARADOMENOSX() != 1){
                        //ComandosManual(88);
                        Xmenos_parar = false;
                    }
                    if(CONTR.isREINICIAR()){
                        Toast.makeText(getActivity(), "Dados Limpos",Toast.LENGTH_SHORT).show();
                        txtStepsY.setText("Eixo Y\n 0");
                        txtStepsX.setText("Eixo X\n 0");
                        ValorInicial = "0";
                        ValorFinal = "0";
                        btngravainicio.setText("Inicio Solda\n"+ValorInicial);
                        btngravafinal.setText("Final Solda\n"+ValorFinal);
                    }
                }
            }
            @Override
            public void onFailure(Call<ControManual> call, Throwable t) {
                SWdirecaodeoperaco.setChecked(false);
            }
        });
    }
    private void GravaPontoInicial(int v){
        Config_Chapisco_Service PontoInicial = retrofit.create( Config_Chapisco_Service.class );
        Call<ValorInicial> call = PontoInicial.gravavalorInicial( v );
        call.enqueue(new Callback<ValorInicial>() {
            @Override
            public void onResponse(Call<ValorInicial> call, Response<ValorInicial> response) {
                if( response.isSuccessful() ){
                    ValorInicial inicial = response.body();
                    assert inicial != null;
                    ValorInicial = inicial.getVALORINICIAL();
                    btngravainicio.setText("Inicio Solda Y\n"+ValorInicial);
                    int i1 = Integer.parseInt(ValorInicial);
                    int i2 = Integer.parseInt(ValorFinal);
                    if(i1 == 0 || i2 == 0 || i1 <= i2) {
                        btnIniciar.setVisibility(View.INVISIBLE);
                    }else{
                        btnIniciar.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onFailure(Call<ValorInicial> call, Throwable t) {

            }
        });
    }
    private void GravaPontoFinal(int v){
        Config_Chapisco_Service PontoFinal = retrofit.create( Config_Chapisco_Service.class );
        Call<ValorFinal> call = PontoFinal.gravavalorFinal( v );
        call.enqueue(new Callback<ValorFinal>() {
            @Override
            public void onResponse(Call<ValorFinal> call, Response<ValorFinal> response) {
                if( response.isSuccessful() ){
                    ValorFinal finall = response.body();
                    assert finall != null;
                    ValorFinal = finall.getVALORFINAL();
                    btngravafinal.setText("Final Solda Y\n"+ValorFinal);
                    int i1 = Integer.parseInt(ValorInicial);
                    int i2 = Integer.parseInt(ValorFinal);
                    if(i1 == 0 || i2 == 0 || i1 <= i2) {
                        btnIniciar.setVisibility(View.INVISIBLE);
                    }else{
                        btnIniciar.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onFailure(Call<ValorFinal> call, Throwable t) {

            }
        });
    }

    // =================== Menus =================================
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.reiniciar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.reinicar) {
            ComandosManual(300);
        }
        return super.onOptionsItemSelected(item);
    }
    // ============================================================
    @Override
    public void onResume() {
        getDadosEsp();
        super.onResume();
    }
    @Override
    public void onStop() {
        myTimer.cancel();
        super.onStop();
    }
    @Override
    public void onStart() {

        super.onStart();
    }
}