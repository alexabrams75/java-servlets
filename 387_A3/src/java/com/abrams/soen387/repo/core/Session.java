/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abrams.soen387.repo.core;

import java.io.FileReader;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;


/**
 *
 * @author Alex
 */
public class Session {
    private String user;
    private String password;
    private boolean loggedIn = false;
    
    public Session(){};
    
    public Session(String user, String password){
        login(user, password);
    }
    
    public String getCurrentUser(){ 
        if (user == null) 
            {return "No user logged in";}
        else return user;
    }
    
    public boolean isUserLoggedIn() { return loggedIn; }
    
    public void login(String user, String password) {
       try{
        Object obj = new JSONParser().parse(new FileReader("C:\\Users\\Alex\\Documents\\NetBeansProjects\\BookBusinessCore\\src\\core\\users.json"));
        JSONObject jo = (JSONObject) obj;
        String storedUser = (String) jo.get("user");
        String storedPassword = (String) jo.get("password");
        if (storedUser.equals(user) && storedPassword.equals(password)) {
               loggedIn = true;
               this.user = user;
               System.out.println("Logged in succesfully");
         }
         else { System.out.println("Incorrect credentials"); }
       }
       catch(Exception e){
        e.printStackTrace();
       }
    }    
    public void logout(){
        if (loggedIn) { loggedIn = false; }
    }
       
}
   
    

