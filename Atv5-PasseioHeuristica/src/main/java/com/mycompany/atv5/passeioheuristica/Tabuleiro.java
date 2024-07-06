package com.mycompany.atv5.passeioheuristica;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private int[][] tabuleiro;
    private int tabuleiroTam = 8;
    private int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    private int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
    private int[][] acessibilidade = {
        {2, 3, 4, 4, 4, 4, 3, 2},
        {3, 4, 6, 6, 6, 6, 4, 3},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {3, 4, 6, 6, 6, 6, 4, 3},
        {2, 3, 4, 4, 4, 4, 3, 2}
    };
    int quantidadeMov = 0;
    
    public Tabuleiro() {
        tabuleiro = new int[8][8];
    }
    
    public boolean moverCavalo(int linhaAtual, int colunaAtual) {
        int proximaContagem = 1;
        tabuleiro[linhaAtual][colunaAtual] = proximaContagem;
        
        if (moverCavaloAuxiliar(linhaAtual, colunaAtual, proximaContagem + 1)) {
            return true;
        } else {
            System.out.println("Não foi possível completar o passeio do cavalo.");
            return false;
        }
    }
    
    private boolean moverCavaloAuxiliar(int linhaAtual, int colunaAtual, int proximaContagem) {
        if (proximaContagem > tabuleiroTam * tabuleiroTam) {
            return true;
        }
        
        List<int[]> movimentos = obterMovimentosValidos(linhaAtual, colunaAtual);
        
        movimentos.sort((a, b) -> Integer.compare(acessibilidade[a[0]][a[1]], acessibilidade[b[0]][b[1]]));
        
        for (int[] movimento : movimentos) {
            int proximaLinha = movimento[0];
            int proximaColuna = movimento[1];
            
            tabuleiro[proximaLinha][proximaColuna] = proximaContagem;
            quantidadeMov++;
            
            // Atualiza acessibilidade
            atualizarAcessibilidade(proximaLinha, proximaColuna, -1);
            
            if (moverCavaloAuxiliar(proximaLinha, proximaColuna, proximaContagem + 1)) {
                return true;
            } else {
                tabuleiro[proximaLinha][proximaColuna] = 0;
                quantidadeMov--;
                
                // Restaura acessibilidade
                atualizarAcessibilidade(proximaLinha, proximaColuna, 1);
            }
        }
        
        return false;
    }
    
    private List<int[]> obterMovimentosValidos(int linhaAtual, int colunaAtual) {
        List<int[]> movimentosValidos = new ArrayList<>();
        
        for (int i = 0; i < 8; i++) {
            int proximaLinha = linhaAtual + vertical[i];
            int proximaColuna = colunaAtual + horizontal[i];
            
            if (movimentoValido(proximaLinha, proximaColuna)) {
                movimentosValidos.add(new int[] {proximaLinha, proximaColuna});
            }
        }
        
        return movimentosValidos;
    }
    
    private boolean movimentoValido(int linha, int coluna) {
        return linha >= 0 && linha < tabuleiroTam && coluna >= 0 && coluna < tabuleiroTam && tabuleiro[linha][coluna] == 0;
    }
    
    private void atualizarAcessibilidade(int linha, int coluna, int valor) {
        for (int i = 0; i < 8; i++) {
            int proximaLinha = linha + vertical[i];
            int proximaColuna = coluna + horizontal[i];
            
            if (proximaLinha >= 0 && proximaLinha < tabuleiroTam && proximaColuna >= 0 && proximaColuna < tabuleiroTam) {
                acessibilidade[proximaLinha][proximaColuna] += valor;
            }
        }
    }
    
    public void imprimirTabuleiro() {
        for (int i = 0; i < tabuleiroTam; i++) {
            for (int j = 0; j < tabuleiroTam; j++) {
                System.out.printf("%3d ", tabuleiro[i][j]);
            }
            System.out.println();
        }
    }
    
    public void imprimirQuantidadeMov() {
        System.out.printf("\nQuantidade de movimentos: " + quantidadeMov);
    }
}