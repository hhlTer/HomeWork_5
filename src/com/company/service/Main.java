package com.company.service;
import com.company.carconstructor.Car;
import com.company.carconstructor.CarDoor;
import com.company.carconstructor.TypeEnjine;

import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here

        DialogService dialogService = new DialogService();
        //create and fill car from console
        Car car = dialogService.createCarDialog();
        String[][] ss = car.sysoutCarStatement();
        final int NAMES_COLUMNS = 0;
        final int DATA_CAR = 1;
        dialogService.printAsTable(ss[NAMES_COLUMNS], ss[DATA_CAR]);

        do {
            try {
            System.out.println("Choise action\n" +
                    "1. Change speed\n" +
                    "2. Put 1 passenger\n" +
                    "3. Get out 1 passenger\n" +
                    "4. Get out  all passengers\n" +
                    "5. Open/close the door\n" +
                    "6. Erase wheel\n" +
                    "7. Get out all wheel\n" +
                    "8. Sett wheel`s\n" +
                    "9. Get impossible maximum speed\n" +
                    "10. Exit");
            int choice = dialogService.scannerCorrectInt();
            if (choice < 1 || choice > 10) {
                System.out.println("Enter correct data!!!");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("Current speed: " + car.getCurrentSpeed());
                    System.out.println("Enter the speed change (- if decrement)");
                    int changeSpeed = dialogService.scannerCorrectInt();
                    try {
                        car.changeSpeed(changeSpeed);
                    } catch (DataCarExeption dataCarExeption) {
                        System.out.println(dataCarExeption.getMessage());
                        Thread.sleep(2499);
                        break;
                    }
                    break;
                case 2:
                    try {
                        car.putPassenger();
                        System.out.println("Passenger sit to car\n" +
                                "So, in car " + car.getCountOfPassenger() + " passenger");
                        Thread.sleep(2500);
                    } catch (DataCarExeption dataCarExeption) {
                        System.out.println(dataCarExeption.getMessage());
                        Thread.sleep(2500);
                    }
                    break;
                case 3:
                    try {
                        car.getoutPassenger();
                    } catch (DataCarExeption dataCarExeption) {
                        System.out.println(dataCarExeption.getMessage());
                        Thread.sleep(2000);
                    }
                    break;
                case 4:
                    car.getoutAllPassenger();
                    System.out.println("Car is empty");
                    Thread.sleep(1500);
                    break;
                case 5:
                    for (int i = 0; i < car.getCountOfDoors(); i++) {
                        car.getCarDoors(i).sysoutState();
                    }
                    System.out.println("[O]pen / [C]lose door?");
                    char answer = dialogService.scanActualAnswer("oc");
                    System.out.println("Choose nuomber of doors");
                    for (int i = 0; i < car.getCountOfDoors(); i++) {
                        System.out.println(i + 1);
                    }
                    int nomberOfDoor = dialogService.scannerCorrectInt();
                    if (nomberOfDoor > 0 & nomberOfDoor <= car.getCountOfDoors()) {
                        switch (answer) {
                            case 'o':
                                car.getCarDoors(nomberOfDoor - 1).openDoor();
                                break;
                            case 'c':
                                car.getCarDoors(nomberOfDoor - 1).closeDoor();
                                break;
                        }
                    } else System.out.println("Wrong number");
                    Thread.sleep(444);
                    break;
                case 6:
                    for (int i = 0; i < car.getCountOfWheels(); i++) {
                        car.getCarWheel(i).sysoutState();
                    }
                    System.out.println("Erase wheel?\n" +
                            "[Y]es / [N]o");
                    answer = dialogService.scanActualAnswer("yn");
                    if (answer == 'y') {
                        System.out.println("Choise number of wheel:");
                        for (int i = 0; i < car.getCountOfWheels(); i++) {
                            System.out.println(i + 1);
                        }
                        int nomberOfWheel = dialogService.scannerCorrectInt();
                        if (nomberOfWheel > 0 & nomberOfWheel <= car.getCountOfWheels()) {
                            System.out.println("Enter percent of destroyed wheel");
                            int prc = dialogService.scannerCorrectInt();
                            System.out.println(prc);
                            System.out.println(nomberOfWheel);
                            car.getCarWheel(nomberOfWheel-1).eraseWheel(prc);
                            car.getCarWheel(nomberOfWheel -1 ).sysoutState();
                        }
                    }
                    Thread.sleep(2500);
                    break ;
                case 7:
                    System.out.println("Get out all wheels?");
                    if (dialogService.scanActualAnswer("ys") == 'y'){
                        car.getoutAllWheels();
                        System.out.println("Done!");
                    }
                    Thread.sleep(1000);
                    break ;
                case 8:
                    System.out.println("How match new wheels set to car?");
                    int countWheel = dialogService.scannerCorrectInt();
                    if (countWheel <= 0){
                        System.out.println("Wrong data");
                        break;
                    } else {
                        car.setNewWheel(countWheel);
                    }
                    Thread.sleep(1000);
                    break ;
                case 9:
                    System.out.println("Possible maximum speed, considering wheel erase: ");
                    System.out.printf("%s km/h", car.getCurrentPossibleMaximumSpeed());
                    Thread.sleep(2500);
                    break;
                case 10:
                    System.exit(0);
                    break;
            }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }





}
