package com.gautomation.chapiscorobot.api;

import com.gautomation.chapiscorobot.model.Config_Chapisco;
import com.gautomation.chapiscorobot.model.ControManual;
import com.gautomation.chapiscorobot.model.SalvarConfig;
import com.gautomation.chapiscorobot.model.ValorFinal;
import com.gautomation.chapiscorobot.model.ValorInicial;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Config_Chapisco_Service {
    @GET("getdados")
    Call<Config_Chapisco> RecuperaConfiguraoes();

//    @POST("config")
//    Call<SalvarConfig> SalvaConfiguracoes(@Body SalvarConfig salvarConfig);
   @FormUrlEncoded
   @POST("config")
    Call<SalvarConfig> SalvaConfiguracoes(
            @Field("DISTANCIA_ENTRE_FRISOS") String DISTANCIA_ENTRE_FRISOS,
            @Field("QTD_FRISOS") String QTD_FRISOS,
            @Field("FIO_SOLDA_POR_FRISO") String FIO_SOLDA_POR_FRISO,
            @Field("RPM_TAMBOR") String RPM_TAMBOR,
            @Field("RECUO_DO_Y_PARA_X") String RECUO_DO_Y_PARA_X,
            @Field("QTDPULSOSCLICY") String QTDPULSOSCLICY,
            @Field("QTDPULSOSCLICX") String QTDPULSOSCLICX,
            @Field("SPEEDMANUAL") String SPEEDMANUAL,
            @Field("SPEEDAUT") String SPEEDAUT,
            @Field("ST_AL_1") Boolean ST_Alimentador1,
            @Field("ST_AL_2") Boolean ST_Alimentador2,
            @Field("PULSO_POL") int Pulso_Pol
    );
    @FormUrlEncoded
    @POST("controle")
    Call<ControManual> Controles(
            @Field("CONTROLE") int CONTROLE
    );
    @FormUrlEncoded
    @POST("gravainicial")
    Call<ValorInicial> gravavalorInicial(
            @Field("VALORINICIAL") int ValorInicio
    );
    @FormUrlEncoded
    @POST("gravafinal")
    Call<ValorFinal> gravavalorFinal(
            @Field("VALORFINAL") int ValorFinal
    );
}
