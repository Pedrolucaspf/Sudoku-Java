/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudoku.java;


public class NumberSpace {
    int num;
    boolean fixed;
    
    public NumberSpace(){
        this.num = 0;
        this.fixed = false;
    }
    
    public int init(int num, boolean fixed){
        if(num<1 || num>9){
            System.out.println("Numero invalido.");
            return 0;
        }
        this.num = num;
        this.fixed = fixed;
        return 1;
    }
    
    public int modify(int num){
        if(this.fixed == true){
            System.out.println("Numero fixado, nao pode ser modificado.");
            return 0;
        }
        if(num<1 || num>9){
            System.out.println("Numero invalido.");
            return 0;
        }

        this.num = num;
        return 1;
    }
    
    public int clear(){
        if(this.fixed == true){
            return 0;
        }
        this.num = 0;
        return 1;
    }
}
