package com.gautomation.chapiscorobot.ui.info;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gautomation.chapiscorobot.R;
import com.gautomation.chapiscorobot.api.Config_Chapisco_Service;
import com.gautomation.chapiscorobot.model.Config_Chapisco;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

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
                //Log.d(TAG, "run: ");
                getDados();
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
        Call<Config_Chapisco> call = getDados.RecuperaConfiguraoes();

        call.enqueue(new Callback<Config_Chapisco>() {
            @Override

            public void onResponse(Call<Config_Chapisco> call, Response<Config_Chapisco> response) {
                if (response.isSuccessful()) {
                    Config_Chapisco dados = response.body();
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
            public void onFailure(Call<Config_Chapisco> call, Throwable t) {

            }
        });
    }

}