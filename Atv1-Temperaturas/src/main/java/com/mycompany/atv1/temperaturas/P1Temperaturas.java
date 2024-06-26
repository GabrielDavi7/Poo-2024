package com.mycompany.p1.temperaturas;
public class P1Temperaturas {

    public static void main(String[] args) {
        try {
            ConversorTemperatura conversor = new ConversorTemperatura(30, "Celsius");
            
            //Celsius
            double kelvinC = conversor.celsiusParaKelvin();
            double fahrenheitC = conversor.celsiusParaFahrenheit();
            System.out.println("====================Caso Celsius====================\n");
            System.out.println("Temperatura em Celsius: " + conversor.getTemperatura());
            System.out.println("Temperatura de Celsius para Kelvin: " + kelvinC);
            System.out.println("Temperatura de Celsius para Fahrenheit: " + fahrenheitC);

            //Kelvin
            conversor.setTemperatura(305.15, "Kelvin");
            double celsiusK = conversor.kelvinParaCelsius();
            double fahrenheitK = conversor.kelvinParaFahrenheit();
            System.out.println("\n=====================Caso Kelvin=====================\n");
            System.out.println("Temperatura em Kelvin: " + conversor.getTemperatura());
            System.out.println("Temperatura de Kelvin para Celsius: " + celsiusK);
            System.out.println("Temperatura de Kelvin para Fahrenheit: " + fahrenheitK);

            //Fahrenheit
            conversor.setTemperatura(88, "Fahrenheit");
            double celsiusF = conversor.fahrenheitParaCelsius();
            double kelvinF = conversor.fahrenheitParaKelvin();
            System.out.println("\n===================Caso Fahrenheit===================\n");
            System.out.println("Temperatura em Fahrenheit: " + conversor.getTemperatura());
            System.out.println("Temperatura de Fahrenheit para Celsius: " + celsiusF);
            System.out.println("Temperatura de Fahrenheit para Kelvin: " + kelvinF);

            //inválidos
            conversor.setTemperatura(-1, "Kelvin");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}

