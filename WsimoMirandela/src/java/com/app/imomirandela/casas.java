/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.imomirandela;

/**
 *
 * @author g_fcp
 */
public class casas {
    
    private String idc;
    private String ref_imo;
    private int quartos;
    private int ano;
    private int preco;

    public void setCasasID(String idc) {
        this.idc = idc;
    }

    public void setRef_imo(String ref_imo) {
        this.ref_imo = ref_imo;
    }

    public void setQuartos(int quartos) {
        this.quartos = quartos;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    //get
    public String getCasasID() {
        return this.idc;
    }

    public int getQuartos() {
        return this.quartos;
    }

    public int getAno() {
        return this.ano;
    }

    public int getPreco() {
        return this.preco;
    }
    
    public String getRef() {
        return this.ref_imo;
    }
}