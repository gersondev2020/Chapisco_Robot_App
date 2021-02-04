package com.gautomation.chapiscorobot.model;

public class SalvarConfig {

    public SalvarConfig(
            String DISTANCIA_ENTRE_FRISOS,
            String QTD_FRISOS,
            String FIO_SOLDA_POR_FRISO,
            String RPM_TAMBOR,
            String RECUO_DO_Y_PARA_X,
            String QTDPULSOSCLICY,
            String QTDPULSOSCLICX,
            String SPEEDMANUAL,
            String SPEEDAUT,
            Boolean ST_AL_1,
            Boolean ST_AL_2,
            int Pulso_Pol
    ) {
        this.DISTANCIA_ENTRE_FRISOS = DISTANCIA_ENTRE_FRISOS;
        this.QTD_FRISOS = QTD_FRISOS;
        this.FIO_SOLDA_POR_FRISO = FIO_SOLDA_POR_FRISO;
        this.RPM_TAMBOR = RPM_TAMBOR;
        this.RECUO_DO_Y_PARA_X = RECUO_DO_Y_PARA_X;
        this.QTDPULSOSCLICY = QTDPULSOSCLICY;
        this.QTDPULSOSCLICX = QTDPULSOSCLICX;
        this.SPEEDMANUAL = SPEEDMANUAL;
        this.SPEEDAUT = SPEEDAUT;
        this.ST_AL_1 = ST_AL_1;
        this.ST_AL_2 = ST_AL_2;
        this.PULSO_POL = Pulso_Pol;
    }
    private String DISTANCIA_ENTRE_FRISOS;
    private String QTD_FRISOS;
    private String FIO_SOLDA_POR_FRISO;
    private String RPM_TAMBOR;
    private String RECUO_DO_Y_PARA_X;
    private String QTDPULSOSCLICY;
    private String QTDPULSOSCLICX;
    private String SPEEDMANUAL;
    private Boolean ST_AL_1;
    private Boolean ST_AL_2;
    private int PULSO_POL;

    public int getPULSO_POL() {
        return PULSO_POL;
    }

    public Boolean getST_AL_1() {
        return ST_AL_1;
    }

    public Boolean getST_AL_2() {
        return ST_AL_2;
    }

    public String getSPEEDMANUAL() {
        return SPEEDMANUAL;
    }

    public String getSPEEDAUT() {
        return SPEEDAUT;
    }

    private String SPEEDAUT;

    public String getQTDPULSOSCLICY() {
        return QTDPULSOSCLICY;
    }

    public String getQTDPULSOSCLICX() {
        return QTDPULSOSCLICX;
    }

    public String getDISTANCIA_ENTRE_FRISOS() {
        return DISTANCIA_ENTRE_FRISOS;
    }


    public String getQTD_FRISOS() {
        return QTD_FRISOS;
    }

    public String getFIO_SOLDA_POR_FRISO() {
        return FIO_SOLDA_POR_FRISO;
    }

    public String getRPM_TAMBOR() {
        return RPM_TAMBOR;
    }

    public String getRECUO_DO_Y_PARA_X() {
        return RECUO_DO_Y_PARA_X;
    }



}
