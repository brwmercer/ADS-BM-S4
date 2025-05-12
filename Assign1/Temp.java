package Assign1;

import java.util.Scanner;

public class Temp{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("How many days of temperature data? ");
        int NUM_DAYS = input.nextInt();
        
        System.out.println("Calculating average temperature for " + NUM_DAYS + " days.");

        double[] temperatures = new double [NUM_DAYS];
        double sum = 0;

        for (int i = 0; i < NUM_DAYS; i++){
            System.out.print("Enter temperature for day " + (i + 1) + ": ");
            while(!input.hasNextDouble()){
                System.out.println("Error: Invalid input");
                input.next();
                System.out.print("Enter temperature for day " + (i + 1) + ": ");
            }
            temperatures[i] = input.nextDouble();
            sum = sum + temperatures[i];
        }
        double average = sum / NUM_DAYS;
        System.out.println("Average Temperature: " + average);

        int daysAboveAverage = 0;
        for(double temp : temperatures){
            if (temp > average) {
                    daysAboveAverage++;
            }
        }
        System.out.println ("Amount of days above average: " + daysAboveAverage);
    }
}