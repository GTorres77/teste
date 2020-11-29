package com.app.imomirandela;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author g_fcp
 */
public class utilizador {
    
    
    
    private String nome;
    private String email;
    private String password;
    private int telefone;
    
    
    public void setNome (String nome)
    {
        this.nome=nome;
    }
    
    
     public void setEmail(String email)
    {
        this.email=email;
    }
    
     public void setPassword (String password)
    {
        this.password=password;
    }
    
        public void setTelefone (int telefone)
    {
        this.telefone=telefone;
    }
        
        
        //get
        
        
        public String getNome()
        {
            return this.nome;
        }
        
        public String getEmail()
        {
             return this.email;
        }
        
        
        public String getPassword()
        {
             return this.password;
        }
        
        public int getTelefone()
        {
            return this.telefone;
        }
        
}
