package com.mycompany.atv4.passeiodocavalo;
        
public class Atv4PasseioDoCavalo {
    public static void main(String[] args) {
     
        Tabuleiro tabuleiro = new Tabuleiro();
        
        int LinhaInicio =0;
        int ColuInicio =0;
        
        tabuleiro.moverCavalo(LinhaInicio, ColuInicio);
        tabuleiro.imprimirTabuleiro();
        tabuleiro.imprimirQuantidadeMov();
    }
}