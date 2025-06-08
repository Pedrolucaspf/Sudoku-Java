/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudoku.java;

import static java.util.stream.Collectors.toMap;
import java.util.stream.Stream;


public class SudokuBoard {
    
    private NumberSpace boardNums[][] = new NumberSpace[9][9];
    public enum ESTADO {Nao_iniciado, Incompleto, Completo};
    private ESTADO estado;
    private int numPreenchidos;
    private final int preenchMax = 81;
    public SudokuBoard(){
         for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                this.boardNums[i][j] = new NumberSpace();
            }
        }
        this.estado = ESTADO.Nao_iniciado;
        numPreenchidos = 0;
    }
    
    public void InitBoard(String[] positions){
        if(this.estado != ESTADO.Nao_iniciado){
            System.out.println("Jogo ja iniciado.");
            return;
        }
        var pos = Stream.of(positions).collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                var positionConfig = pos.get("%s,%s".formatted(i, j));
                var num = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                numPreenchidos += this.boardNums[i][j].init(num, fixed);
            }
        }
        if(numPreenchidos == preenchMax){
            this.estado = ESTADO.Completo;
        }
        else{
            this.estado = ESTADO.Incompleto;
        }
    }
    
    public void insertNumber(int i, int j, int num){
        if(this.estado == ESTADO.Nao_iniciado){
            System.out.println("Jogo nao iniciado.");
            return;
        }
        numPreenchidos += this.boardNums[i][j].modify(num);
        if(numPreenchidos == preenchMax){
            this.estado = ESTADO.Completo;
        }
    }
    
    public void removeNumber(int i, int j){
        if(this.estado == ESTADO.Nao_iniciado){
            System.out.println("Jogo nao iniciado.");
            return;
        }
        int isClear = this.boardNums[i][j].clear();
        if(isClear == 0){
            System.out.println("Numero fixado, nao pode ser removido.");
        }
        else{
            numPreenchidos -= isClear;
            if(numPreenchidos < preenchMax){
                this.estado = ESTADO.Incompleto;
            }
        }
    }
    
    public void showTable(){
        if(this.estado == ESTADO.Nao_iniciado){
            System.out.println("Jogo nao iniciado.");
            return;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
               System.out.print(this.boardNums[i][j].num + " ");
               if(j%3==2){
                   System.out.print("|");
               }
            }
            System.out.println(" ");
            if(i%3==2){
               System.out.println("--------------------");
            }
        }
    }
    
    public boolean hasErrors(){
        int i, j, k;
        if(this.estado != ESTADO.Nao_iniciado){
            for(i=0; i<9; i++){
                for(j=0; j<9; j++){
                    for(k=i+1; k<9;k++){
                        if(this.boardNums[k][j].num == this.boardNums[i][j].num){
                            return true;
                        }
                    }
                    for(k=j+1; k<9;k++){
                        if(this.boardNums[i][k].num == this.boardNums[i][j].num){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
     public void showStatus(){
        System.out.println("O jogo atualmente se encontra no status:"+ this.estado);
        if(hasErrors()){
            System.out.println("O jogo contém erros");
        } else {
            System.out.println("O jogo não contém erros");
        }
     }
     
     public void clearBoard(){
        if(this.estado == ESTADO.Nao_iniciado){
            System.out.println("Jogo nao iniciado.");
            return;
        } 
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                 numPreenchidos -= this.boardNums[i][j].clear();
            }
        }
        this.estado = ESTADO.Incompleto;
     }
     
     public void finishGame(){
        if(this.estado == ESTADO.Nao_iniciado){
            System.out.println("Jogo nao iniciado.");
            return;
        }
        
        if(this.estado == ESTADO.Completo){
            if(hasErrors()){
                System.out.println("O jogo contém erros.");
            } 
            else{
                System.out.println("O jogo foi concluido sem erros. Parabens!");
            }
        }
        else{
            System.out.println("Ainda existem espacos nao preenchidos no tabuleiro.");
        }
     }
}
