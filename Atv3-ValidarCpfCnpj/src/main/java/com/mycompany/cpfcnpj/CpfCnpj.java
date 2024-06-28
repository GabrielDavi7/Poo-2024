        package com.mycompany.cpfcnpj;


        public class CpfCnpj {

            public static void main(String[] args) {

                String Cpf1 = "14230703058";
                String Cpf2 = "142.307.030-58";
                String Cpf3 = "230703058";
                String Cpf4 = "002.307.030-58";
                String Cpf5 = "456789014";

                System.out.println("O Cpf1:"+Cpf1+" è "+(ValidadorCpfCnpj.ValidadorCpf(Cpf1) ? "Valido" : "Invalido" ));
                System.out.println("O Cpf2:"+Cpf2+" è "+(ValidadorCpfCnpj.ValidadorCpf(Cpf2) ? "Valido" : "Invalido" ));
                System.out.println("O Cpf3:"+Cpf3+" è "+(ValidadorCpfCnpj.ValidadorCpf(Cpf3) ? "Valido" : "Invalido" ));
                System.out.println("O Cpf4:"+Cpf4+" è "+(ValidadorCpfCnpj.ValidadorCpf(Cpf4) ? "Valido" : "Invalido" ));
                System.out.println("O Cpf5:"+Cpf5+" è "+(ValidadorCpfCnpj.ValidadorCpf(Cpf5) ? "Valido" : "Invalido\n" ));
                
                
                String Cnpj1 = "35.017.120/0001-86";
                String Cnpj2 = "35017120000186";
                String Cnpj3 = "35.017.120/0001-88";
                
                System.out.println("O Cnpj:"+Cnpj1+" è "+(ValidadorCpfCnpj.ValidadorCnpj(Cnpj1) ? "Valido" : "Invalido" ));
                System.out.println("O Cnpj:"+Cnpj2+" è "+(ValidadorCpfCnpj.ValidadorCnpj(Cnpj2) ? "Valido" : "Invalido" ));
                System.out.println("O Cnpj:"+Cnpj3+" è "+(ValidadorCpfCnpj.ValidadorCnpj(Cnpj3) ? "Valido" : "Invalido" ));              
                             
            }
        }
