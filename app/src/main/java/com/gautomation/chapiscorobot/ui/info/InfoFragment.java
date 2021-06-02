package com.gautomation.chapiscorobot.ui.info;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gautomation.chapiscorobot.R;
import com.gautomation.chapiscorobot.api.Config_Chapisco_Service;
import com.gautomation.chapiscorobot.model.Get_Dados;
import com.gautomation.chapiscorobot.RecumperarDados;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoFragment extends Fragment {
    private Retrofit retrofit2;
    Timer myTimer = new Timer();
    TextView txtPulsosY, txtFiosdesolda, txtPulsosX, txtFrisosSoladados, txtLarguraFio, txtTempoGiro, txtPontoInicialX, txtPontoInicialY;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        txtPulsosY = root.findViewById(R.id.txtPulsosY);
        txtPontoInicialY = root.findViewById(R.id.txtPontoInicialY);
        txtFiosdesolda = root.findViewById(R.id.txtFiosdesolda);
        txtPulsosX = root.findViewById(R.id.txtPulsosX);
        txtFrisosSoladados = root.findViewById(R.id.txtFrisosSoladados);
        txtLarguraFio = root.findViewById(R.id.txtLarguraFio);
        txtTempoGiro = root.findViewById(R.id.txtTempoGiro);
        txtPontoInicialX = root.findViewById(R.id.txtPontoInicialX);
        retrofit2 = new Retrofit.Builder()
                .baseUrl("http://192.168.4.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                getDados();
                Log.d("Controle", "Inf: ");
            }

        }, 0, 500);
        return root;
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onStop() {
        myTimer.cancel();
        super.onStop();
    }

    private void getDados() {

        Config_Chapisco_Service getDados = retrofit2.create(Config_Chapisco_Service.class);
        Call<Get_Dados> call = getDados.RecuperaConfiguraoes();

        call.enqueue(new Callback<Get_Dados>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Get_Dados> call, Response<Get_Dados> response) {
                if (response.isSuccessful()) {
                    Get_Dados dados = response.body();
                    assert dados != null;
                    txtPulsosY.setText(dados.getSTEPS_Y());
                    txtPontoInicialY.setText(dados.getVALORINICIAL()+"/"+dados.getVALORFINAL());
                    txtFiosdesolda.setText(dados.getFIOSOLDA() +" de "+ dados.getFIO_SOLDA_POR_FRISO());
                    txtPulsosX.setText(dados.getSTEPS_X());
                    txtPontoInicialX.setText(dados.getPONTO_INICIAL_DE_X());
                    txtLarguraFio.setText(dados.getLARGURAFIO());
                    txtFrisosSoladados.setText(dados.getFRISOS() +" de "+ dados.getQTD_FRISOS());
                    txtTempoGiro.setText(dados.getTEMPOGIRO()+ "s");


                }
            }
            @Override
            public void onFailure(Call<Get_Dados> call, Throwable t) {

            }
        });
    }

//    @SuppressLint("SetTextI18n")
//    private void Recumpera() {
//        if(recumperarDados.Dados() != null) {
//            txtPulsosY.setText(recumperarDados.Dados().getSTEPS_Y());
//            txtPontoInicialY.setText(recumperarDados.Dados().getVALORINICIAL()+"/"+recumperarDados.Dados().getVALORFINAL());
//            txtFiosdesolda.setText(recumperarDados.Dados().getFIOSOLDA() +" de "+ recumperarDados.Dados().getFIO_SOLDA_POR_FRISO());
//            txtPulsosX.setText(recumperarDados.Dados().getSTEPS_X());
//            txtPontoInicialX.setText(recumperarDados.Dados().getPONTO_INICIAL_DE_X());
//            txtLarguraFio.setText(recumperarDados.Dados().getLARGURAFIO());
//            txtFrisosSoladados.setText(recumperarDados.Dados().getFRISOS() +" de "+ recumperarDados.Dados().getQTD_FRISOS());
//            txtTempoGiro.setText(recumperarDados.Dados().getTEMPOGIRO()+ "s");
//        }
//    }

}