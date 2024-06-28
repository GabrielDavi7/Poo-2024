package com.mycompany.atv2.temperaturasestatico;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ConversorTemperaturaEstatico {

    private static double arredondar(double valor, int casasDecimais) {
        BigDecimal bd = new BigDecimal(valor).setScale(casasDecimais, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double celsiusParaKelvin(double temperatura) {
        if (temperatura < -273.15) {
            throw new IllegalArgumentException("Temperatura em Celsius não pode ser menor que -273.15.");
        }
        return arredondar(temperatura + 273.15, 2);
    }

    public static double celsiusParaFahrenheit(double temperatura) {
        if (temperatura < -273.15) {
            throw new IllegalArgumentException("Temperatura em Celsius não pode ser menor que -273.15.");
        }
        return arredondar((temperatura * 9 / 5) + 32, 2);
    }

    public static double kelvinParaCelsius(double temperatura) {
        if (temperatura < 0) {
            throw new IllegalArgumentException("Temperatura em Kelvin não pode ser menor que zero.");
        }
        return arredondar(temperatura - 273.15, 2);
    }

    public static double kelvinParaFahrenheit(double temperatura) {
        if (temperatura < 0) {
            throw new IllegalArgumentException("Temperatura em Kelvin não pode ser menor que zero.");
        }
        return arredondar((temperatura - 273.15) * 9 / 5 + 32, 2);
    }

    public static double fahrenheitParaCelsius(double temperatura) {
        if (temperatura < -459.67) {
            throw new IllegalArgumentException("Temperatura em Fahrenheit não pode ser menor que -459.67.");
        }
        return arredondar((temperatura - 32) * 5 / 9, 2);
    }

    public static double fahrenheitParaKelvin(double temperatura) {
        if (temperatura < -459.67) {
            throw new IllegalArgumentException("Temperatura em Fahrenheit não pode ser menor que -459.67.");
        }
        return arredondar((temperatura - 32) * 5 / 9 + 273.15, 2);
    }
}
