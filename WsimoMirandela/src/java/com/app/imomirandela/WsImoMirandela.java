/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.imomirandela;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author g_fcp
 */
@WebService(serviceName = "WsImoMirandela")
public class WsImoMirandela {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "MarcarVisita")
    public String MarcarVisita(@WebParam(name = "idu") int idu, @WebParam(name = "ref_imo") String ref_imo, @WebParam(name = "data") String data, @WebParam(name = "hora") String hora) {
        try {
            //TODO write your implementation code here:
            
            gereBD bd = new gereBD();
            return bd.MarcarVisita(idu, ref_imo, data, hora);
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WsImoMirandela.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @WebMethod(operationName = "Login")
    public int Login(@WebParam(name = "nome") String nome, @WebParam(name = "pass") String pass) {
        try {
            //TODO write your implementation code here:
            
            gereBD bd = new gereBD();
            return bd.Login(nome, pass);
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WsImoMirandela.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -10;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "RegistrarUtilizador")
    public String RegistrarUtilizador(@WebParam(name = "nome") String nome, @WebParam(name = "email") String email, @WebParam(name = "pass") String pass, @WebParam(name = "telefone") int telefone) {
        try {
            //TODO write your implementation code here:
            
            gereBD bd =new gereBD();
            
            return bd.RegistrarUtilizador(nome, email, pass, telefone);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WsImoMirandela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "erro";
    }
    
    @WebMethod(operationName = "VerRefs")
    public ArrayList<String> VerRefs() {
         try {
            //TODO write your implementation code here:
            gereBD bd = new gereBD();
            
            return bd.VerRefs() ;
        } catch (ClassNotFoundException ex) {
           Logger.getLogger(WsImoMirandela.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "VerTabelaPreco")
    public ArrayList<casas> VerTabelaPreco() {
         try {
            //TODO write your implementation code here:
            gereBD bd = new gereBD();
            
            
            return bd.VerTabelaPreco() ;
        } catch (ClassNotFoundException ex) {
           Logger.getLogger(WsImoMirandela.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    /**
     * Web service operation
     * @param uid
     * @return 
     */
    @WebMethod(operationName = "VerMarcacoes")
    public ArrayList<marcacoes> VerMarcacoes(@WebParam(name = "uid") String uid) {
        try {
            //TODO write your implementation code here:
            gereBD bd = new gereBD();
            return bd.VerMarcacoes(uid);
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(WsImoMirandela.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Web service operation
     * @param ref_imo
     * @param quartos
     * @param ano
     * @param preco
     * @return 
     */
    @WebMethod(operationName = "AdicionarCasa")
    public String AdicionarCasa(@WebParam(name = "ref_imo") String ref_imo, @WebParam(name = "quartos") int quartos, @WebParam(name = "ano") int ano, @WebParam(name = "preco") int preco) {
        try {
            //TODO write your implementation code here:
            gereBD bd = new gereBD();
            return bd.AdicionarCasa(ref_imo, quartos, ano, preco);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WsImoMirandela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "RemoverCasa")
    public String RemoverCasa(@WebParam(name = "id") String id) {
        try {
            //TODO write your implementation code here:
            gereBD bd = new gereBD();
            return bd.RemoverCasa(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WsImoMirandela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Web service operation
     * @param data
     * @return 
     */
    @WebMethod(operationName = "VerTodasMarcacoesPorData")
    public ArrayList<marcacoes> VerTodasMarcacoesPorData(@WebParam(name = "data") String data) {
        try {
            //TODO write your implementation code here: 
            
           
            gereBD bd = new gereBD();
            return bd.VerTodasMarcacoesPorData(data);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WsImoMirandela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Web service operation
     * @param idc
     * @param preco
     * @return 
     */
    @WebMethod(operationName = "AlterarPrecoCasa")
    public String AlterarPrecoCasa(@WebParam(name = "idc") int idc, @WebParam(name = "preco") int preco) {
        try {
            //TODO write your implementation code here:
            gereBD bd = new gereBD();
            return bd.AlterarPrecoCasa(idc, preco);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WsImoMirandela.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "VerificarMarcacoes")
    public int VerificarMarcacoes(@WebParam(name = "data") String data, @WebParam(name = "hora") String hora) {
        try {
            //TODO write your implementation code here:
            
            gereBD bd = new gereBD();
           return bd.VerificarMarcacoes(data, hora);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WsImoMirandela.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0;
    }
}
