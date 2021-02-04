package com.gautomation.chapiscorobot.ui.config;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gautomation.chapiscorobot.model.Config_Chapisco;
import com.gautomation.chapiscorobot.api.Config_Chapisco_Service;
import com.gautomation.chapiscorobot.model.SalvarConfig;

import com.gautomation.chapiscorobot.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigFragment extends Fragment {

    private ConfigViewModel configViewModel;
    private Retrofit retrofit;
    EditText  QTD_FRISOS;
    EditText  FIO_SOLDA_POR_FRISO;
    EditText  RPM_TAMBOR;
    EditText  RECUO_DO_Y_PARA_X;
    EditText editPulsosonClicY;
    EditText editPulsosonClicX;
    EditText editPulsosPolegadas;
    TextView txtVelocidadeManual, txtVelocidadeAut;
    SeekBar SeekManual, SeekAuto;
    RadioButton Radio1pol, Radio1_5pol, Radio2pol, Radio2_5pol, Radio3pol;
    private Switch Alimentador1, Alimentador2;
    public int Polegadas_Pulso = 1239;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        configViewModel =
                new ViewModelProvider(this).get(ConfigViewModel.class);
        View root = inflater.inflate(R.layout.fragment_config, container, false);

        Radio1pol = root.findViewById(R.id.radio1pol);
        Radio1_5pol = root.findViewById(R.id.radio1_5pol);
        Radio2pol = root.findViewById(R.id.radio2pol);
        Radio2_5pol = root.findViewById(R.id.radio2_5pol);
        Radio3pol = root.findViewById(R.id.radio3pol);
        QTD_FRISOS = root.findViewById(R.id.editQTD_FRISOS);
        FIO_SOLDA_POR_FRISO = root.findViewById(R.id.editFIO_SOLDA_POR_FRISO);
        RPM_TAMBOR = root.findViewById(R.id.editRPM_TAMBOR);
        RECUO_DO_Y_PARA_X = root.findViewById(R.id.editRECUO_DO_Y_PARA_X);
        editPulsosonClicY = root.findViewById(R.id.editPulsosonClicY);
        editPulsosonClicX = root.findViewById(R.id.editPulsosonClicX);
        editPulsosPolegadas = root.findViewById(R.id.editPulsosPolegadas);
        txtVelocidadeManual = root.findViewById(R.id.txtVelocidadeManual);
        txtVelocidadeAut = root.findViewById(R.id.txtVelocidadeAut);
        SeekAuto = root.findViewById(R.id.seekBarAuto);
        SeekManual = root.findViewById(R.id.seekBarSpeedManual);
        Alimentador1 = root.findViewById(R.id.alimentador1);
        Alimentador2 = root.findViewById(R.id.alimentador2);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.4.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        configViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                //textView.setText(s);
            }
        });
        //editPulsosPolegadas.setText(Polegadas_Pulso);

        return root;
    }
    @Override
    public void onResume() {
        getDadosEsp();
        super.onResume();
    }
    private void getDadosEsp(){
        Config_Chapisco_Service Config = retrofit.create( Config_Chapisco_Service.class );
        Call<Config_Chapisco> call = Config.RecuperaConfiguraoes();

        call.enqueue(new Callback<Config_Chapisco>() {
            @Override
            public void onResponse(Call<Config_Chapisco> call, Response<Config_Chapisco> response) {
                if( response.isSuccessful() ){
                    Config_Chapisco dados = response.body();
                    assert dados != null;
                    Polegadas_Pulso = Integer.parseInt(dados.getPULSO_POL());
                    DISTANCIA_ENTRE_FRISOS(Integer.parseInt(dados.getDISTANCIA_ENTRE_FRISOS()));
                    QTD_FRISOS.setText(dados.getQTD_FRISOS());
                    FIO_SOLDA_POR_FRISO.setText( dados.getFIO_SOLDA_POR_FRISO());
                    RPM_TAMBOR.setText( dados.getRPM_TAMBOR());
                    RECUO_DO_Y_PARA_X.setText( dados.getRECUO_DO_Y_PARA_X());
                    editPulsosonClicY.setText( dados.getQTDPULSOSCLICY());
                    editPulsosonClicX.setText( dados.getQTDPULSOSCLICX());
                    SeekManual.setProgress(Integer.parseInt(dados.getSPEEDMANUAL())/10);
                    SeekAuto.setProgress(Integer.parseInt(dados.getSPEEDAUT())/10);
                    txtVelocidadeManual.setText("Velocidade Automatico\n"+dados.getSPEEDMANUAL()+"%");
                    txtVelocidadeAut.setText("Velocidade Manual\n"+dados.getSPEEDAUT()+"%");
                    if(dados.getST_AL_1()){
                        Alimentador1.setChecked(true);
                    }else{
                        Alimentador1.setChecked(false);
                    }
                    if(dados.getST_AL_2()){
                        Alimentador2.setChecked(true);
                    }else{
                        Alimentador2.setChecked(false);
                    }
                    editPulsosPolegadas.setText(dados.getPULSO_POL());

                }
            }
            @Override
            public void onFailure(Call<Config_Chapisco> call, Throwable t) {

            }
        });
    }
    private void sendDadosEsp(){
        Polegadas_Pulso = Integer.parseInt(String.valueOf(editPulsosPolegadas.getText()));
        Config_Chapisco_Service SalvaConfig = retrofit.create( Config_Chapisco_Service.class );
        Call<SalvarConfig> call = SalvaConfig.SalvaConfiguracoes(
                String.valueOf(DISTANCIA_ENTRE_FRISOS()),
                QTD_FRISOS.getText().toString(),
                FIO_SOLDA_POR_FRISO.getText().toString(),
                RPM_TAMBOR.getText().toString(),
                RECUO_DO_Y_PARA_X.getText().toString(),
                editPulsosonClicY.getText().toString(),
                editPulsosonClicX.getText().toString(),
                String.valueOf(SeekManual.getProgress()),
                String.valueOf(SeekAuto.getProgress()),
                Alimentador1.isChecked(),
                Alimentador2.isChecked(),
                Polegadas_Pulso
        );

        call.enqueue(new Callback<SalvarConfig>() {
            @Override
            public void onResponse(Call<SalvarConfig> call, Response<SalvarConfig> response) {
                if( response.isSuccessful() ){
                    SalvarConfig salvarConfig = response.body();
                    DISTANCIA_ENTRE_FRISOS(Integer.parseInt(salvarConfig.getDISTANCIA_ENTRE_FRISOS()));
                    QTD_FRISOS.setText( salvarConfig.getQTD_FRISOS());
                    FIO_SOLDA_POR_FRISO.setText( salvarConfig.getFIO_SOLDA_POR_FRISO());
                    RPM_TAMBOR.setText( salvarConfig.getRPM_TAMBOR());
                    RECUO_DO_Y_PARA_X.setText( salvarConfig.getRECUO_DO_Y_PARA_X());
                    editPulsosonClicY.setText( salvarConfig.getQTDPULSOSCLICY());
                    editPulsosonClicX.setText( salvarConfig.getQTDPULSOSCLICX());
                    SeekManual.setProgress(Integer.parseInt(salvarConfig.getSPEEDMANUAL())/10);
                    SeekAuto.setProgress(Integer.parseInt(salvarConfig.getSPEEDAUT())/10);
                    txtVelocidadeManual.setText("Velocidade Automatico\n"+salvarConfig.getSPEEDMANUAL()+"%");
                    txtVelocidadeAut.setText("Velocidade Manual\n"+salvarConfig.getSPEEDAUT()+"%");
                    if(salvarConfig.getST_AL_1()){
                        Alimentador1.setChecked(true);
                    }else{
                        Alimentador1.setChecked(false);
                    }
                    if(salvarConfig.getST_AL_2()){
                        Alimentador2.setChecked(true);
                    }else{
                        Alimentador2.setChecked(false);
                    }
                    editPulsosPolegadas.setText(String.valueOf(salvarConfig.getPULSO_POL()));
                    Toast.makeText(getActivity(), "Dados Salvos",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SalvarConfig> call, Throwable t) {
                Toast.makeText(getActivity(), "Sem Conexão",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void DISTANCIA_ENTRE_FRISOS(int setaRadio){
        int pol = (int) (Polegadas_Pulso);
        int v1  = (int) (pol*1.5);
        int v2  = (int) (pol*2.5);

        if(setaRadio == pol){
            Radio1pol.setChecked(true);
        }else if(setaRadio == v1){
            Radio1_5pol.setChecked(true);
        }else if(setaRadio == pol*2){
            Radio2pol.setChecked(true);
        }else if(setaRadio == v2){
            Radio2_5pol.setChecked(true);
        }else if(setaRadio == pol*3){
            Radio3pol.setChecked(true);
        }else{
            Radio1pol.setChecked(true);
        }
    }

    private int DISTANCIA_ENTRE_FRISOS(){

        int pol = (int) (Polegadas_Pulso); //Integer.parseInt(String.valueOf(editPulsosPolegadas.getText())); //1239
        if(Radio1pol.isChecked()){
            return pol*1;
        }else if(Radio1_5pol.isChecked()){
            return (int) (pol*1.5);
        }else if(Radio2pol.isChecked()){
            return pol*2;
        }else if(Radio2_5pol.isChecked()){
            return (int) (pol*2.5);
        }else if(Radio3pol.isChecked()){
            return (int)(pol*3);
        }else{
            return 1239;
        }
    }

    // =================== Menus =================================
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.salvar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sairesalvar) {
            sendDadosEsp();

        }
        return super.onOptionsItemSelected(item);
    }
    // =============    ==============================================
}