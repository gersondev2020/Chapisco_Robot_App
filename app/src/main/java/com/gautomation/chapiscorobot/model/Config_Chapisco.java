package com.gautomation.chapiscorobot.model;

public class Config_Chapisco {

    private String VALORINICIAL;
    private String VALORFINAL;
    private String DISTANCIA_ENTRE_FRISOS;
    private String QTD_FRISOS;
    private String FIO_SOLDA_POR_FRISO;
    private String RPM_TAMBOR;
    private String RECUO_DO_Y_PARA_X;
    private String QTDPULSOSCLICY;
    private String QTDPULSOSCLICX;
    private String SPEEDMANUAL;
    private String PONTO_INICIAL_DE_X;
    private String TEMPOGIRO;
    private String FIOSOLDA;
    private String FRISOS;
    private boolean CHAPISCO_STATUS;
    private boolean DIRECAOOPRECAO;
    private String SPEEDAUT;
    private String STEPS_Y;
    private String STEPS_X;
    private String LARGURAFIO;

    public String getPULSO_POL() {
        return PULSO_POL;
    }

    private String PULSO_POL;

    public Boolean getST_AL_1() {
        return ST_AL_1;
    }

    public Boolean getST_AL_2() {
        return ST_AL_2;
    }

    private Boolean ST_AL_1;
    private Boolean ST_AL_2;



    public boolean isDIRECAOOPRECAO() {
        return DIRECAOOPRECAO;
    }

    public String getSPEEDAUT() {
        return SPEEDAUT;
    }

    public String getQTDPULSOSCLICY() {
        return QTDPULSOSCLICY;
    }

    public String getQTDPULSOSCLICX() {
        return QTDPULSOSCLICX;
    }

    public String getPONTO_INICIAL_DE_X() {
        return PONTO_INICIAL_DE_X;
    }

    public boolean isCHAPISCO_STATUS() {
        return CHAPISCO_STATUS;
    }

    public String getSTEPS_Y() {
        return STEPS_Y;
    }

    public String getSTEPS_X() {
        return STEPS_X;
    }

    public String getLARGURAFIO() {
        return LARGURAFIO;
    }

    public String getTEMPOGIRO() {
        return TEMPOGIRO;
    }

    public String getFIOSOLDA() {
        return FIOSOLDA;
    }

    public String getFRISOS() {
        return FRISOS;
    }

    public String getVALORINICIAL() {
        return VALORINICIAL;
    }

    public String getVALORFINAL() {
        return VALORFINAL;
    }

    public String getDISTANCIA_ENTRE_FRISOS() {
        return DISTANCIA_ENTRE_FRISOS;
    }
    public void setDISTANCIA_ENTRE_FRISOS(String DISTANCIA_ENTRE_FRISOS) {
        this.DISTANCIA_ENTRE_FRISOS = DISTANCIA_ENTRE_FRISOS;
    }
    public String getQTD_FRISOS() {
        return QTD_FRISOS;
    }

    public void setQTD_FRISOS(String QTD_FRISOS) {
        this.QTD_FRISOS = QTD_FRISOS;
    }

    public String getFIO_SOLDA_POR_FRISO() {
        return FIO_SOLDA_POR_FRISO;
    }

    public void setFIO_SOLDA_POR_FRISO(String FIO_SOLDA_POR_FRISO) {
        this.FIO_SOLDA_POR_FRISO = FIO_SOLDA_POR_FRISO;
    }

    public String getRPM_TAMBOR() {
        return RPM_TAMBOR;
    }

    public void setRPM_TAMBOR(String RPM_TAMBOR) {
        this.RPM_TAMBOR = RPM_TAMBOR;
    }

    public String getRECUO_DO_Y_PARA_X() {
        return RECUO_DO_Y_PARA_X;
    }

    public void setRECUO_DO_Y_PARA_X(String RECUO_DO_Y_PARA_X) {
        this.RECUO_DO_Y_PARA_X = RECUO_DO_Y_PARA_X;
    }
    public String getSPEEDMANUAL() {
        return SPEEDMANUAL;
    }
}
