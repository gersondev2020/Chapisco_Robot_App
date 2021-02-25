package com.gautomation.chapiscorobot;

import android.annotation.SuppressLint;

import com.gautomation.chapiscorobot.api.Config_Chapisco_Service;
import com.gautomation.chapiscorobot.model.Get_Dados;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RecumperarDados {

    public Retrofit retro;
    public RecumperarDados(Retrofit retro) {
        this.retro = retro;
    }

    private Get_Dados dado;

        public Get_Dados Dados() {

            Config_Chapisco_Service Config = retro.create(Config_Chapisco_Service.class);
            Call<Get_Dados> call = Config.RecuperaConfiguraoes();

            call.enqueue(new Callback<Get_Dados>() {
                @Override
                public void onResponse(Call<Get_Dados> call, Response<Get_Dados> response) {
                    if (response.isSuccessful()) {
                        dado = response.body();
                        assert dado != null;
                    }
                }
                @Override
                public void onFailure(Call<Get_Dados> call, Throwable t) {

                }
            });
            return dado;
        }

}

