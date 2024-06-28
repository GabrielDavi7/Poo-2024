package com.mycompany.atv2.temperaturasestatico;


public class Atv2TemperaturasEstatico {

    public static void main(String[] args) {
        try {
            // Celsius
            double kelvinC = ConversorTemperaturaEstatico.celsiusParaKelvin(30);
            double fahrenheitC = ConversorTemperaturaEstatico.celsiusParaFahrenheit(30);
            System.out.println("====================Caso Celsius====================\n");
            System.out.println("Temperatura em Celsius: 30");
            System.out.println("Temperatura de Celsius para Kelvin: " + kelvinC);
            System.out.println("Temperatura de Celsius para Fahrenheit: " + fahrenheitC);

            // Kelvin
            double celsiusK = ConversorTemperaturaEstatico.kelvinParaCelsius(305.15);
            double fahrenheitK = ConversorTemperaturaEstatico.kelvinParaFahrenheit(305.15);
            System.out.println("\n=====================Caso Kelvin=====================\n");
            System.out.println("Temperatura em Kelvin: 305.15");
            System.out.println("Temperatura de Kelvin para Celsius: " + celsiusK);
            System.out.println("Temperatura de Kelvin para Fahrenheit: " + fahrenheitK);

            // Fahrenheit
            double celsiusF = ConversorTemperaturaEstatico.fahrenheitParaCelsius(88);
            double kelvinF = ConversorTemperaturaEstatico.fahrenheitParaKelvin(88);
            System.out.println("\n===================Caso Fahrenheit===================\n");
            System.out.println("Temperatura em Fahrenheit: 88");
            System.out.println("Temperatura de Fahrenheit para Celsius: " + celsiusF);
            System.out.println("Temperatura de Fahrenheit para Kelvin: " + kelvinF);

            // inválidos
            ConversorTemperaturaEstatico.kelvinParaCelsius(-1);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
