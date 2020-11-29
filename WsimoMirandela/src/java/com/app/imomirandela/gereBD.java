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
import javax.jws.WebParam;

/**
 *
 * @author g_fcp
 */
public class gereBD {
    
    public gereBD() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }
    
    public int Login(String nome, String pass) {  
        try {
            Connection conexao;
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/imo_app?user=root&password=");

            Statement stmt = conexao.createStatement();
            String query = "SELECT * FROM utilizadores WHERE nome= '" + nome + "'  and  pass ='" + pass + "' ";

            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println(" login com sucesso");

                return rs.getInt("idu"); // login efetuado com sucesso retorna nº de cliente
            } else {
                System.out.println("erro ao efectuar login");

                return -1; // -1 erro 
            }

        } catch (SQLException ex) {
            Logger.getLogger(gereBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    public String MarcarVisita(int idu, String ref_imo, String data, String hora) {

        try {
            Connection conexao;
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/imo_app?user=root&password=");

            Statement stmt = conexao.createStatement();
            String query = " insert into marcavisita (uid,ref_imo,data,hora) values ('" + idu + "','" + ref_imo + "','" + data + "','" + hora + "')";

            ResultSet rs = stmt.executeQuery(query);

            return "visita marcada com sucesso";

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao ligar a BD");
        }

        return "erro ao efetuar a marcação";
    }
    
    public String RegistrarUtilizador(String nome, String email, String pass, int telefone) {

        try {
            Connection conexao;
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/imo_app?user=root&password=");

            Statement stmt = conexao.createStatement();
            String query = " insert into utilizadores (nome,email,pass,telefone) values ('" + nome + "','" + email + "','" + pass + "'," + telefone + ")";

            ResultSet rs = stmt.executeQuery(query);

            return "cliente inserido com sucesso";
        } catch (SQLException ex) {
            System.out.println("Erro ao ligar a BD");
        }

        return "erro ao registrar cliente";
    }
    
    public ArrayList<casas> VerTabelaPreco() {

        try {
            ArrayList<casas> Acasas = new ArrayList<casas>();
            
            Connection conexao;
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/imo_app?user=root&password=");
            
            Statement stmt = conexao.createStatement();
            
            
            String query="SELECT * FROM casas ";
             ResultSet rs=stmt.executeQuery(query);
             
             
              while (rs.next()) 
               {
                  casas casass = new casas();
                  casass.setRef_imo(rs.getString("ref_imo"));
                  casass.setCasasID(casass.getRef());
                  casass.setQuartos(rs.getInt("quartos"));
                  casass.setAno(rs.getInt("ano"));
                  casass.setPreco(rs.getInt("preco"));
                  
                   Acasas.add(casass);
               }
            
            return Acasas;
        } catch (SQLException ex) {
            Logger.getLogger(gereBD.class.getName()).log(Level.SEVERE, null, ex);
        }
          return null;
    }
    
    public ArrayList<String> VerRefs() {

        try {
            ArrayList<String> casas = new ArrayList<String>();
            
            Connection conexao;
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/imo_app?user=root&password=");
            
            Statement stmt = conexao.createStatement();
            
            String query="SELECT ref_imo FROM casas ";
             ResultSet rs=stmt.executeQuery(query);
             
             
              while (rs.next()) 
               {
                  casas.add(rs.getString("ref_imo"));
               }
            
            return casas;
        } catch (SQLException ex) {
            Logger.getLogger(gereBD.class.getName()).log(Level.SEVERE, null, ex);
        }
          return null;
    }
    
    public ArrayList<marcacoes> VerMarcacoes(String uid) {
        
        try {
            ArrayList<marcacoes> Amarcacoes = new ArrayList<>();
            
            Connection conexao;
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/imo_app?user=root&password=");
            
            Statement stmt = conexao.createStatement();
            
            String query="SELECT * FROM marcavisita where uid ='"+uid+"'  ";
                
            ResultSet rs=stmt.executeQuery(query);
             
             
             while (rs.next()) 
               {
                  marcacoes marcacoess = new marcacoes();
                  marcacoess.setClienteID(rs.getString("uid"));
                  marcacoess.setRef_Imo(rs.getString("ref_imo"));
                  marcacoess.setData(rs.getString("data"));
                  marcacoess.setHora(rs.getString("hora"));
                  
                   Amarcacoes.add(marcacoess);
               }
            return  Amarcacoes;
        } catch (SQLException ex) {
            System.out.println("Erro ao ligar a BD");
        }
          return null;    
    }
    
    public String AdicionarCasa(String ref_imo, int quartos, int ano, int preco) {
    
    
        try {
            Connection conexao;
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/imo_app?user=root&password=");
            
            Statement stmt = conexao.createStatement();
            
            String query = "INSERT INTO casas(ref_imo,quartos,ano,preco) VALUES ('"+ref_imo+"','"+quartos+"','"+ano+"','"+preco+"')";
           boolean rs = stmt.execute(query);
            
            return "casa inserido com sucesso";
           
        } catch (SQLException ex) {
            Logger.getLogger(gereBD.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
}
    
    public String RemoverCasa(@WebParam(name = "ref_imo") String ref_imo) {
    
        try {
            Connection conexao;
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/imo_app?user=root&password=");
            
            Statement stmt = conexao.createStatement();
            
          
             String query ="SELECT * FROM casas WHERE idc= '"+ ref_imo + "' ";
           
            ResultSet rs=stmt.executeQuery(query);
            
            if(rs.next())
            {
                System.out.println(" casa existe");
                
               String query1 = "delete from  casas where idc= '"+ref_imo+"'";
               boolean rs1 = stmt.execute(query1);
            
           
           
            return "casa eliminada com sucesso";
            }
            
            else
            {
             return "erro ao eliminar casa";   
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(gereBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    
    public ArrayList<marcacoes> VerTodasMarcacoesPorData(String data) {
       
   
          try {
            ArrayList<marcacoes> Amarcacoes = new ArrayList<>();
            Connection conexao;
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/imo_app?user=root&password=");

            Statement stmt = conexao.createStatement();
            String query = "SELECT * FROM marcavisita WHERE data='" + data + "' ";
            System.out.println("data "+data);
            ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    
                marcacoes marcacoess = new marcacoes();
                marcacoess.setClienteID(rs.getString("uid"));
                marcacoess.setRef_Imo(rs.getString("ref_imo"));
                marcacoess.setData(rs.getString("data"));
                marcacoess.setHora(rs.getString("hora"));
                
               
                Amarcacoes.add(marcacoess);
    
            }

            return Amarcacoes;
        } catch (SQLException ex) {
            System.out.println("Erro ao ligar a BD");
        }
      
                  return null;      
   
   }
    
    public String AlterarPrecoCasa(int idc,  int preco) {
        
        try {
            Connection conexao;
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/imo_app?user=root&password=");
            
            
            Statement stmt = conexao.createStatement();
            String query = " UPDATE  casas SET preco='" + preco + "' WHERE idc='"+idc+"' ";
            
            boolean rs = stmt.execute(query);
            // System.out.println("resutado "+resultado);
            
            return "preco atualizado";
        } catch (SQLException ex) {
            Logger.getLogger(gereBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
    }
    
    public int VerificarMarcacoes( String data, String hora) {
        
        try {
            Connection conexao;
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/imo_app?user=root&password=");

            Statement stmt = conexao.createStatement();
            String query = "SELECT * FROM marcavisita WHERE data= '" + data + "'  and  hora ='" + hora + "' ";

            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                

                return -1; // ja existe
            } else {
                System.out.println("erro ao efectuar login");
                
                return 1; // nao existe 
            }
        } catch (SQLException ex) {
            Logger.getLogger(gereBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
