package com.mycompany.atv5.passeioheuristica;

public class Atv5PasseioHeuristica {
    public static void main(String[] args) {
        int passeiosCompletos = 0;
        
        for (int linhaInicio = 0; linhaInicio < 8; linhaInicio++) {
            for (int coluInicio = 0; coluInicio < 8; coluInicio++) {
                Tabuleiro tabuleiro = new Tabuleiro();
                
                System.out.println("\nIniciando passeio do cavalo na posição (" + linhaInicio + ", " + coluInicio + ")");
                boolean completo = tabuleiro.moverCavalo(linhaInicio, coluInicio);
                tabuleiro.imprimirTabuleiro();
                tabuleiro.imprimirQuantidadeMov();
                
                if (completo) {
                    passeiosCompletos++;
                }
            }
        }
        
        System.out.println("\nNúmero total de passeios completos: " + passeiosCompletos);
    }
}