package com.gautomation.chapiscorobot.ui.controle;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
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

import androidx.annotation.BoolRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gautomation.chapiscorobot.R;
import com.gautomation.chapiscorobot.RecumperarDados;
import com.gautomation.chapiscorobot.api.Config_Chapisco_Service;
import com.gautomation.chapiscorobot.model.Get_Dados;
import com.gautomation.chapiscorobot.model.ValorInicial;
import com.gautomation.chapiscorobot.model.ValorFinal;
import com.gautomation.chapiscorobot.model.ControManual;

import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;


public class ControleFragment extends Fragment {

    private Retrofit retrofit;
    private String Valor = "0";
    //Timer myTimer = new Timer();
    private String ValorInicial = "0";
    private String ValorFinal = "0";
    private Button Ymais, Ymenos, Xmais, Xmenos, btngravainicio, btngravafinal, btnIniciar,
            btnArame1,
            btnArame2;
    private TextView txtStepsY, txtStepsX;
    private boolean StatusChapisco = false;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch SWdirecaodeoperaco;
    Timer myTimer = new Timer();
    private boolean flag;

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
        btnArame1 = root.findViewById(R.id.btnArame1);
        btnArame2 = root.findViewById(R.id.btnArame2);
        SWdirecaodeoperaco = root.findViewById(R.id.sw);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.4.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btngravainicio.setOnClickListener(v -> {
           if(Integer.parseInt(Valor) > Integer.parseInt(ValorFinal )) {
               GravaPontoInicial(Integer.parseInt(Valor));
           }else{
               Toast.makeText(getActivity(), "Posição Invalida",Toast.LENGTH_SHORT).show();
           }
        });
        btngravafinal.setOnClickListener(v -> {
            if(Integer.parseInt(Valor) < Integer.parseInt(ValorInicial)) {
                GravaPontoFinal(Integer.parseInt(Valor));
           }else{
                Toast.makeText(getActivity(), "Posição Invalida",Toast.LENGTH_SHORT).show();

            }
        });
        // BOTÃO MAIS Y ==============
        Ymais.setOnLongClickListener(v -> {
            if(!StatusChapisco){
                ComandosManual(11);
                flag = true;
            }

            return false;
        });
        Ymais.setOnClickListener(v -> {
            if(flag){
                ComandosManual(22);
                flag = false;
            }else{
                ComandosManual(222);
            }
        });
        // ============================
        // BOTÃO MENOS Y ==============
        Ymenos.setOnLongClickListener(v -> {
            if(!StatusChapisco) {
                ComandosManual(33);
                flag = true;
            }
            return false;
        });
        Ymenos.setOnClickListener(v -> {
            if(flag){
                ComandosManual(44);
                flag = false;
            }else{
                ComandosManual(444);
            }

        });
        // =============================

        // BOTÃO MAIS X ==============
        Xmais.setOnLongClickListener(v -> {
            if(!StatusChapisco) {
                ComandosManual(55);
                flag = true;
            }
            return false;
        });
        Xmais.setOnClickListener(v -> {
            if(flag){
                ComandosManual(66);
                flag = false;
            }else{
                ComandosManual(666);
            }
        });
        // =============================
        // BOTÃO MENOS X ==============
        Xmenos.setOnLongClickListener(v -> {
            if(!StatusChapisco) {
                ComandosManual(77);
                flag = true;
            }
            return false;
        });
        Xmenos.setOnClickListener(v -> {
            if(flag){
                ComandosManual(88);
                flag = false;
            }else{
                ComandosManual(888);
            }
        });
        // ==============================
        btnIniciar.setOnClickListener(v ->ComandosManual(200));

        // BOTÃO ARAME 1 ==============
        btnArame1.setOnLongClickListener(v -> {
            ComandosManual(110);
             return false;
        });
        btnArame1.setOnClickListener(v -> {ComandosManual(120);});

        // BOTÃO ARAME 2 ==============
        btnArame2.setOnLongClickListener(v -> {
            ComandosManual(130);
            return false;
        });
        btnArame2.setOnClickListener(v -> {ComandosManual(140);});


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


    private void getDadosEsp() {
        Config_Chapisco_Service Config = retrofit.create(Config_Chapisco_Service.class);
        Call<Get_Dados> call = Config.RecuperaConfiguraoes();

        call.enqueue(new Callback<Get_Dados>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Get_Dados> call, Response<Get_Dados> response) {
                if (response.isSuccessful()) {
                    Get_Dados dados = response.body();
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
                    Valor = dados.getSTEPS_Y();
                    txtStepsX.setText("Eixo X\n"+dados.getSTEPS_X());
                    if (dados.isCHAPISCO_STATUS()) {
                        //btnIniciar.setBackgroundResource(R.color.BTNOFF);
                        btnIniciar.setText("Parar Chapisco ?");
                        btnIniciar.setTextColor(getResources().getColor(R.color.BTNON));
                        StatusChapisco = true;
                    } else {
                        //btnIniciar.setBackgroundResource(R.color.BTNON);
                        btnIniciar.setText("Iniciar Chapisco ?");
                        btnIniciar.setTextColor(getResources().getColor(R.color.BTNOFF));
                        StatusChapisco = false;

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
            public void onFailure(Call<Get_Dados> call, Throwable t) {

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
                    int Codigo = response.code();
                    assert CONTR != null;
                    if(v == 22 && Codigo != 201){
                        ComandosManual(22);
                        Toast.makeText(getActivity(), "Codigo "+response.code(),Toast.LENGTH_SHORT).show();
                    }
                    if(v == 44 && Codigo != 201){
                        ComandosManual(44);
                        Toast.makeText(getActivity(), "Codigo "+response.code(),Toast.LENGTH_SHORT).show();
                    }
                    if(v == 66 && Codigo != 201){
                        ComandosManual(66);
                        Toast.makeText(getActivity(), "Codigo "+response.code(),Toast.LENGTH_SHORT).show();
                    }
                    if(v == 88 && Codigo != 201){
                        ComandosManual(88);
                        Toast.makeText(getActivity(), "Codigo "+response.code(),Toast.LENGTH_SHORT).show();
                    }
                    if(CONTR.getSTEPS_Y() != null){
                        txtStepsY.setText("Eixo Y\n"+CONTR.getSTEPS_Y());
                        Valor = CONTR.getSTEPS_Y();
                    }
                    if(CONTR.getSTEPS_X() != null){
                        txtStepsX.setText("Eixo X\n"+CONTR.getSTEPS_X());
                    }
                    if(v == 200) {
                        if (CONTR.isCHAPISCO_STATUS()) {
                            //btnIniciar.setBackgroundResource(R.color.BTNOFF);
                            btnIniciar.setText("Parar Chapisco ?");
                            btnIniciar.setTextColor(getResources().getColor(R.color.BTNON));
                            StatusChapisco = true;
                        } else {
                            //btnIniciar.setBackgroundResource(R.color.BTNON);
                            btnIniciar.setText("Iniciar Chapisco ?");
                            btnIniciar.setTextColor(getResources().getColor(R.color.BTNOFF));
                            StatusChapisco = false;
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
                Toast.makeText(getActivity(), "Falha",Toast.LENGTH_SHORT).show();

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