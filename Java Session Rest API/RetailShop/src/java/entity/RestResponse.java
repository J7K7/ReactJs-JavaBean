/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Abhay
 */
public class RestResponse implements Serializable {
    public String Message;
    
    public RestResponse(){
        
    }
    
    public RestResponse(String Message){
        this.Message = Message;
    }
}
