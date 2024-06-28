package com.mycompany.cpfcnpj;

public class ValidadorCpfCnpj {

    public static int ObterPoss(long numero, int posicao) {
        String numeroValidar = Long.toString(numero);
        int tamanho = numeroValidar.length();

        if (posicao <= 0 || posicao > tamanho) {
            return -1;
        }

        char digitoChar = numeroValidar.charAt(tamanho - posicao);
        return Character.digit(digitoChar, 10);
    }

    public static boolean ValidadorCpf(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.length() != 11) {
            return false;
        }

        try {
            long cpfConvertido = Long.parseLong(cpf);

            int poss1 = ObterPoss(cpfConvertido, 1);
            int poss2 = ObterPoss(cpfConvertido, 2);
            int poss3 = ObterPoss(cpfConvertido, 3);
            int poss4 = ObterPoss(cpfConvertido, 4);
            int poss5 = ObterPoss(cpfConvertido, 5);
            int poss6 = ObterPoss(cpfConvertido, 6);
            int poss7 = ObterPoss(cpfConvertido, 7);
            int poss8 = ObterPoss(cpfConvertido, 8);
            int poss9 = ObterPoss(cpfConvertido, 9);

            int soma1 = (poss1 * 10) + (poss2 * 9) + (poss3 * 8) + (poss4 * 7) + (poss5 * 6) + (poss6 * 5)
                    + (poss7 * 4) + (poss8 * 3) + (poss9 * 2);
            int resto1 = soma1 % 11;
            int verificacao1 = (resto1 < 2) ? 0 : (11 - resto1);

            int soma2 = (poss1 * 11) + (poss2 * 10) + (poss3 * 9) + (poss4 * 8) + (poss5 * 7) + (poss6 * 6)
                    + (poss7 * 5) + (poss8 * 4) + (poss9 * 3) + (verificacao1 * 2);
            int resto2 = soma2 % 11;
            int verificacao2 = (resto2 < 2) ? 0 : (11 - resto2);

            return ObterPoss(cpfConvertido, 10) == verificacao1 && ObterPoss(cpfConvertido, 11) == verificacao2;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean ValidadorCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("[^\\d]", "");

        if (cnpj.length() != 14) {
            return false;
        }

        try {
            long cnpjConvertido = Long.parseLong(cnpj);

            int poss1 = ObterPoss(cnpjConvertido, 1);
            int poss2 = ObterPoss(cnpjConvertido, 2);
            int poss3 = ObterPoss(cnpjConvertido, 3);
            int poss4 = ObterPoss(cnpjConvertido, 4);
            int poss5 = ObterPoss(cnpjConvertido, 5);
            int poss6 = ObterPoss(cnpjConvertido, 6);
            int poss7 = ObterPoss(cnpjConvertido, 7);
            int poss8 = ObterPoss(cnpjConvertido, 8);
            int poss9 = ObterPoss(cnpjConvertido, 9);
            int poss10 = ObterPoss(cnpjConvertido, 10);
            int poss11 = ObterPoss(cnpjConvertido, 11);
            int poss12 = ObterPoss(cnpjConvertido, 12);
            int poss13 = ObterPoss(cnpjConvertido, 13);
            int poss14 = ObterPoss(cnpjConvertido, 14);

            int soma1 = (poss1 * 6) + (poss2 * 7) + (poss3 * 8) + (poss4 * 9) + (poss5 * 2) + (poss6 * 3) + (poss7 * 4) + (poss8 * 5) + (poss9 * 6);
            int resto1 = soma1 % 11;
            int verificacao1 = (resto1 < 2) ? 0 : (11 - resto1);

            int soma2 = (poss1 * 5) + (poss2 * 6) + (poss3 * 7) + (poss4 * 8) + (poss5 * 9) + (poss6 * 2) + (poss7 * 3) + (poss8 * 4) + (poss9 * 5) + (poss10 * 6);
            int resto2 = soma2 % 11;
            int verificacao2 = (resto2 < 2) ? 0 : (11 - resto2);

            return (poss13 == verificacao1) && (poss14 == verificacao2);

        } catch (NumberFormatException e) {
            System.out.println("O CNPJ " + cnpj + " é inválido");
            return false;
        }
    }
}
