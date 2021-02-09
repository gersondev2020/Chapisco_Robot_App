package com.gautomation.chapiscorobot.model;

public class ControManual {
    private int CONTROLE;
    private boolean REINICIAR;
    private boolean DIRECAOOPRECAO;
    private boolean CHAPISCO_STATUS;
    private String STEPS_Y;
    private String STEPS_X;
    private int PARADOMAISY;
    private int PARADOMENOSY;
    private int PARADOMAISX;
    private int PARADOMENOSX;

    public int getPARADOMAISY() {
        return PARADOMAISY;
    }

    public int getPARADOMENOSY() {
        return PARADOMENOSY;
    }

    public int getPARADOMAISX() {
        return PARADOMAISX;
    }

    public int getPARADOMENOSX() {
        return PARADOMENOSX;
    }



    public boolean isREINICIAR() {
        return REINICIAR;
    }
    public boolean isDIRECAOOPRECAO() {
        return DIRECAOOPRECAO;
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
    public int getCONTROLE() {
        return CONTROLE;
    }
    public ControManual(int CONTROLE) {
        this.CONTROLE = CONTROLE;
    }



}
