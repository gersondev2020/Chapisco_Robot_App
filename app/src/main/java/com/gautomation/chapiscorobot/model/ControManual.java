package com.gautomation.chapiscorobot.model;

public class ControManual {
    private int CONTROLE;

    public boolean isREINICIAR() {
        return REINICIAR;
    }

    private boolean REINICIAR;
    public boolean isDIRECAOOPRECAO() {
        return DIRECAOOPRECAO;
    }

    private boolean DIRECAOOPRECAO;

    public boolean isCHAPISCO_STATUS() {
        return CHAPISCO_STATUS;
    }

    private boolean CHAPISCO_STATUS;

    public String getSTEPS_Y() {
        return STEPS_Y;
    }

    public String getSTEPS_X() {
        return STEPS_X;
    }

    private String STEPS_Y;
    private String STEPS_X;

    public int getCONTROLE() {
        return CONTROLE;
    }



    public ControManual(int CONTROLE) {
        this.CONTROLE = CONTROLE;
    }



}
