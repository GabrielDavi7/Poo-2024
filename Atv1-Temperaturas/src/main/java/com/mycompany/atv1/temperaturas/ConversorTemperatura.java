package com.mycompany.p1.temperaturas;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ConversorTemperatura {
    private double temperatura;
    private String unidade;

    public ConversorTemperatura(double temperatura, String unidade) {
        setTemperatura(temperatura, unidade);
    }

    public void setTemperatura(double temperatura, String unidade) {
        if (unidade.equalsIgnoreCase("Kelvin") && temperatura < 0) {
            throw new IllegalArgumentException("Temperatura em Kelvin não pode ser menor que zero.");
        }
        if (unidade.equalsIgnoreCase("Celsius") && temperatura < -273.15) {
            throw new IllegalArgumentException("Temperatura em Celsius não pode ser menor que -273.15.");
        }
        if (unidade.equalsIgnoreCase("Fahrenheit") && temperatura < -459.67) {
            throw new IllegalArgumentException("Temperatura em Fahrenheit não pode ser menor que -459.67.");
        }
        this.temperatura = temperatura;
        this.unidade = unidade;
    }

    public double getTemperatura() {
        return this.temperatura;
    }

    private double arredondar(double valor, int casasDecimais) {
        BigDecimal bd = new BigDecimal(valor).setScale(casasDecimais, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double celsiusParaKelvin() {
        if (!unidade.equalsIgnoreCase("Celsius")) {
            throw new IllegalArgumentException("Unidade de origem deve ser Celsius.");
        }
        return arredondar(temperatura + 273.15, 2);
    }

    public double celsiusParaFahrenheit() {
        if (!unidade.equalsIgnoreCase("Celsius")) {
            throw new IllegalArgumentException("Unidade de origem deve ser Celsius.");
        }
        return arredondar((temperatura * 9 / 5) + 32, 2);
    }

    public double kelvinParaCelsius() {
        if (!unidade.equalsIgnoreCase("Kelvin")) {
            throw new IllegalArgumentException("Unidade de origem deve ser Kelvin.");
        }
        return arredondar(temperatura - 273.15, 2);
    }

    public double kelvinParaFahrenheit() {
        if (!unidade.equalsIgnoreCase("Kelvin")) {
            throw new IllegalArgumentException("Unidade de origem deve ser Kelvin.");
        }
        return arredondar((temperatura - 273.15) * 9 / 5 + 32, 2);
    }

    public double fahrenheitParaCelsius() {
        if (!unidade.equalsIgnoreCase("Fahrenheit")) {
            throw new IllegalArgumentException("Unidade de origem deve ser Fahrenheit.");
        }
        return arredondar((temperatura - 32) * 5 / 9, 2);
    }

    public double fahrenheitParaKelvin() {
        if (!unidade.equalsIgnoreCase("Fahrenheit")) {
            throw new IllegalArgumentException("Unidade de origem deve ser Fahrenheit.");
        }
        return arredondar((temperatura - 32) * 5 / 9 + 273.15, 2);
    }
}