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

public class ArithmaticOperation {
    public boolean divedZiro = false;
    
//get numerical ansver of string exspresion
    public String Solution(String exp){
        Vector<String> EQN = new Vector<String>();
        // Stack for numbers: 'values'
        Stack<Double> values = new Stack<Double>();
        // Stack for Operators: 'ops'
        Stack<String> ops = new Stack<String>();
    
        exp = exp + "e";
        for(int i=0; i<exp.length(); i++){
            String Num ="";
            boolean IsGoWhile = false;
            while(!(exp.charAt(i)=='('||exp.charAt(i)==')'||exp.charAt(i)=='+'||
                    exp.charAt(i)=='-'||exp.charAt(i)=='*'||exp.charAt(i)=='/'||
                    exp.charAt(i)=='%'||exp.charAt(i)=='e')){
                //only number
                Num = Num + exp.charAt(i);
                i++;
                IsGoWhile = true;
            }
            
            if(IsGoWhile){
                EQN.add(Num);
                i--;
            }else{
                EQN.add(String.valueOf(exp.charAt(i)));
            }   
        }
        int size = EQN.size()-1;
        EQN.removeElementAt(size);
        
        for(int i=0; i<EQN.size(); i++){
            if(!("(".equals(EQN.get(i))||")".equals(EQN.get(i))||"+".equals(EQN.get(i))||"-".equals(EQN.get(i))||"*".equals(EQN.get(i))||"/".equals(EQN.get(i))||"%".equals(EQN.get(i)))){
                values.push(Double.parseDouble(EQN.get(i)));
                //System.out.println(EQN.get(i));
            }else if("(".equals(EQN.get(i))){
                ops.push(EQN.get(i));
            }else if(")".equals(EQN.get(i))){
                while(!"(".equals(ops.peek())){
                    values.push(ApplyOpp(ops.pop(), values.pop(), values.pop()));
                }
               ops.pop();
            }else if("+".equals(EQN.get(i))||"-".equals(EQN.get(i))||"*".equals(EQN.get(i))||
                    "/".equals(EQN.get(i))||"%".equals(EQN.get(i))){
                while(!ops.empty()&&hasPrecedence(EQN.get(i), ops.peek())){
                    values.push(ApplyOpp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(EQN.get(i));
            }
            
        }
        
        
        while(!ops.empty()){
            values.push(ApplyOpp(ops.pop(), values.pop(), values.pop()));
        }
        
        //System.out.println(EQN);
        double FinalAns =values.pop();
        
        if(divedZiro){
            return ("MATH ERROR");
        }else{
            return (String.valueOf(FinalAns));
        }
    }
    
    private double ApplyOpp(String op, double b, double a){
        switch (op)
        {
        case "+":
            return a + b;
        case "-":
            return a - b;
        case "*":
            return a * b;
        case "/":
            if (b == 0){
                divedZiro = true;
            }else{
                return a / b;
            }
            
        case "%":
            return a % b;
        }
        return 0;
    } 
    
    
    private boolean hasPrecedence(String op1, String op2){
        if("(".equals(op2)||")".equals(op2)){
            return false;
        }
        if(("%".equals(op1))&&("*".equals(op2)||"/".equals(op2)||"+".equals(op2)||"-".equals(op2))){
            return false;
        }else if(("*".equals(op1)||"/".equals(op1))&&("+".equals(op2)||"-".equals(op2))){
            return false;
        }else{
           return true;
        }
    }
    
}
