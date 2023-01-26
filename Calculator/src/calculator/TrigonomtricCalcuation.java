/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author jeewanth
 */

import java.util.*;
import java.io.*;
import java.lang.Math;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class TrigonomtricCalcuation {
    
    private static final DecimalFormat df = new DecimalFormat("0.000000000");
    //get ansver of trigonomatric functions
    public String Solution(String exp){
        double Number1=0,Number2=0;
        boolean IsAnser = false;
        
        //for sin cos tan cot
        String selector1 ="";
        String Num1 ="";
        if(exp.length()>3){
            for(int i=0; i<3; i++){
                selector1 = selector1 + exp.charAt(i);
            }
            
            if("sin".equals(selector1)||"cos".equals(selector1)
                    ||"tan".equals(selector1)||"cot".equals(selector1)){
                for(int i=3; i<exp.length(); i++){
                    Num1 = Num1 + exp.charAt(i);
                }
                try{
                   Number1 = Double.parseDouble(Num1); 
                } catch (NumberFormatException e){
                    IsAnser = true;
                }
                
            }
        }
        
        
        //for asin acos
        String selector2 ="";
        String Num2 ="";
        if(exp.length()>4){
            for(int i=0; i<4; i++){
                selector2 = selector2 + exp.charAt(i);
            }
            
            if("asin".equals(selector2)||"acos".equals(selector2)){
                for(int i=4; i<exp.length(); i++){
                    Num2 = Num2 + exp.charAt(i);
                }
                try{
                   Number2 = Double.parseDouble(Num2); 
                } catch (NumberFormatException e){
                    IsAnser = true;
                }
                
            }
        }
        
        //selection
        double ans = 0;
        
        
        if("sin".equals(selector1)){
            ans = Math.sin(Math.toRadians(Number1));
        }else if("cos".equals(selector1)){
            ans = Math.cos(Math.toRadians(Number1));
        }else if("tan".equals(selector1)){
            if(Number1==90){
               IsAnser = true; 
            }else{
                ans = Math.tan(Math.toRadians(Number1));
            }
        }else if("cot".equals(selector1)){
            ans = Math.cos(Math.toRadians(Number1))/Math.sin(Math.toRadians(Number1));
        }else if("asin".equals(selector2)){
            ans = Math.toDegrees(Math.asin(Number2));
        }else if("acos".equals(selector2)){
            ans = Math.toDegrees(Math.acos(Number2));
        }else{
            IsAnser = true;
        }
        
        if(IsAnser){
            return "MATH ERROR";
        }else{
            return String.valueOf(df.format(ans));
        }
    }
}
