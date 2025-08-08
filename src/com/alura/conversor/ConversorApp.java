package com.alura.conversor;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) {
        String moneda = "USD";
        Scanner scanner = new Scanner(System.in);

        var opcion = 0;
        do {
            try {
                mostrarMenu();
                opcion = scanner.nextInt();

                if (opcion == 7){
                    break;
                }

                System.out.println("ingrese el importe a convertir: ");
                Double monto = scanner.nextDouble();

                String monedaDeConversion = "USD";
                switch (opcion){
                    case 1:
                        moneda = "USD";
                        monedaDeConversion = "ARS";
                        break;
                    case 2:
                        moneda = "ARS";
                        monedaDeConversion = "USD";
                        break;
                    case 3:
                        moneda = "USD";
                        monedaDeConversion = "BRL";
                        break;
                    case 4:
                        moneda = "BRL";
                        monedaDeConversion = "USD";
                        break;
                    case 5:
                        moneda = "USD";
                        monedaDeConversion = "COP";
                        break;
                    case 6:
                        moneda = "COP";
                        monedaDeConversion = "USD";
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida del menu");
                        continue;
                }

                ApiClient apiClient = new ApiClient();
                JsonObject conversiones = apiClient.obtenerTasasDeCambio(moneda);

                Double monedaAux = conversiones.get(moneda).getAsDouble();
                Double conversiones2 = conversiones.get(monedaDeConversion).getAsDouble();
                Double total = monedaAux * monto * conversiones2;

                System.out.println("El valor " + monto + " [" + moneda + "] " +
                        "corresponde al valor final de =>>> " + total + " [" + monedaDeConversion + "]");

            } catch (IOException e) {
                System.out.println("Error de conexión con la API: " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("Operación interrumpida: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("*************************************************************");
        System.out.println("Sea bienvenido/a al Conversor de Moneda =] \n");
        System.out.println("1) Dolar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dolar");
        System.out.println("3) Dolar =>> Real brasileño");
        System.out.println("4) Real brasileño =>> Dolar");
        System.out.println("5) Dolar =>> Peso colombiano");
        System.out.println("6) Peso colombiano =>> Dolar");
        System.out.println("7) Salir");
        System.out.println("Elija una opcion valida: ");
        System.out.println("*************************************************************");
    }
}