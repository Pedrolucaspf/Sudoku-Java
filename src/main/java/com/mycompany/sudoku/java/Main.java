/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.sudoku.java;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option, i, j, num;
        SudokuBoard board = new SudokuBoard();
        while(true){
            System.out.println(" ");
            System.out.println("Opcoes:");
            System.out.println("1.Iniciar jogo");
            System.out.println("2.Inserir numero");
            System.out.println("3.Remover numero");
            System.out.println("4.Verificar tabela do jogo");
            System.out.println("5.Verificar status do jogo");
            System.out.println("6.Limpar tabela");
            System.out.println("7.Finalizar jogo");
            System.out.println("8.Sair");
            System.out.print("Digite o numero da opcao que deseja:");
            option = scanner.nextInt();
            if(option == 8){
                break;
            }
            switch(option){
                case(1):
                    board.InitBoard(args);
                    break;
                case(2):
                    System.out.print("Digite o numero da linha:");
                    i = scanner.nextInt();
                    if(i<1 || i>9){
                        System.out.println("Linha invalida.");
                    }
                    else{
                        System.out.print("Digite o numero da coluna:");
                        j = scanner.nextInt();
                        if(j<1 || j>9){
                           System.out.println("Coluna invalida.");
                        }
                        else{
                             System.out.print("Digite o numero a ser inserido:");
                            num = scanner.nextInt();
                            board.insertNumber(i-1, j-1, num);
                        }
                    }
                    break;
                case(3):
                    System.out.print("Digite o numero da linha:");
                    i = scanner.nextInt();
                    if(i<1 || i>9){
                        System.out.println("Linha invalida.");
                    }
                    else{
                        System.out.print("Digite o numero da coluna:");
                        j = scanner.nextInt();
                        if(j<1 || j>9){
                           System.out.println("Coluna invalida.");
                        }
                        else{
                            board.removeNumber(i-1, j-1);
                        }
                    }
                    break;
                case(4):
                    board.showTable();
                    break;
                case(5):
                    board.showStatus();
                    break;
                case(6):
                    board.clearBoard();
                    break;
                case(7):
                    board.finishGame();
                    break;
                default:
                    System.out.println("Operacao invalida.");
            }
        }
    }
}
