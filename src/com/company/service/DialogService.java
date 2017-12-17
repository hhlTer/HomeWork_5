package com.company.service;

import com.company.carconstructor.Car;
import com.company.carconstructor.TypeEnjine;

import java.util.Date;
import java.util.Scanner;

class DialogService {
    Car createCarDialog(){
        int maximumSpeed;
        double acceleration;
        int roominess;
        int countOfPassenger;
        int currentSpeed;
        TypeEnjine typeEnjine;

        System.out.println("Hello!\n" +
                "Create a car [Y]es / [N]o?\n" +
                ":>\n");
        if (scanActualAnswer("yn") == 'n'){
            System.out.println("Bye!");
            System.exit(0);
        }
//ENGINE
        System.out.println("Enter engine type: \n" +
                "[G]as, [D]iesel");
        if (scanActualAnswer("gd") == 'g')
            typeEnjine = TypeEnjine.GAS;
        else
            typeEnjine = TypeEnjine.DIESEL;
//SPEED
        System.out.println("Enter maximum speed:");
        maximumSpeed = 0;
        do {
            maximumSpeed = scannerCorrectInt();
        }while (maximumSpeed <= 0);
//ACCELERATION
        System.out.println("Enter accelerate:");
        acceleration = 0;
        do {
            acceleration = scannerCorrectDouble();
        }while (acceleration <= 0);
//ROOMINESS
        System.out.println("Enter roominess of car");
        roominess = 0;
        do {
            roominess = scannerCorrectInt();
        }while (roominess <= 0);
//COUNTofPASSENGER
        countOfPassenger = 0;
        System.out.println("Enter count of passenger");
        do {
            countOfPassenger = scannerCorrectInt();
            if (countOfPassenger > roominess) System.out.println("Count biggest than roominess");
        } while ((countOfPassenger < 0)||(countOfPassenger > roominess));
//CURRENTspeed
        System.out.println("Enter a current speed");
        currentSpeed = 0;
        do {
            currentSpeed = scannerCorrectInt();
            if (currentSpeed > maximumSpeed) System.out.println("Speed cannt be biggest than maximum speed of car");
        } while ((currentSpeed < 0)|(currentSpeed > maximumSpeed));
//COUNTofDOOR
        System.out.println("Enter count of door");
        int countOfDoor;
        do {
            countOfDoor = scannerCorrectInt();
        }while (countOfDoor < 1);
        Car car = new Car(new Date(),
                typeEnjine,
                maximumSpeed,
                acceleration,
                roominess,
                countOfPassenger,
                currentSpeed);
        car.setCountCarDoors(countOfDoor);
        return car;
    }
    void printAsTable(String[] namesCol, String[] datas){
//sets lohg of table
        final int COUNT_COL = namesCol.length;
        int lengthTable;
        int lengthColum = 0;
        int lengthDatas = 0;
        for (int i = 0; i < COUNT_COL; i++) {
            if (namesCol[i].length() > lengthColum) lengthColum = namesCol[i].length();
            if (datas[i].length() > lengthDatas) lengthDatas = datas[i].length();
        }
        lengthTable = lengthColum + 2 + lengthDatas + 2;

        for (int i = 0; i < COUNT_COL; i++) {
            for (int j = 0; j < lengthTable+2; j++) {
                System.out.print('-');
            }
            System.out.println();
            System.out.print("| " + namesCol[i]);
            for (int j = namesCol[i].length(); j < lengthColum; j++) {
                System.out.print(' ');
            }
            System.out.print(" | " + datas[i]);
            for (int j = datas[i].length(); j < lengthDatas+1; j++) {
                System.out.print(' ');
            }
            System.out.println('|');
        }
        for (int j = 0; j < lengthTable+2; j++) {
            System.out.print('-');
        }
        System.out.println();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    int scannerCorrectInt(){
        System.out.println(":>");
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            return scannerCorrectInt();
        }
    }
    double scannerCorrectDouble(){
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextDouble();
        }catch (Exception e){
            return scannerCorrectDouble();
        }
    }
    char scanActualAnswer(String s){
        char[] chars = s.toCharArray();
        char c;
        boolean b = true;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(":>");
            c = scanner.nextLine().toLowerCase().charAt(0);
            for (char c1:
                    chars) {
                if (c == c1) {
                    b = false;
                    break;
                }
            }
        } while (b);
        return c;
    }
}
