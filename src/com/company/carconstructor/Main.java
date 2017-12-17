package com.company.carconstructor;

import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Car car = createCarDialog();
        car.sysoutCarStatement();

        do {
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
            break;

        } while (true);

    }
    private static char getActualAnswer(String s){
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
    static Car createCarDialog(){

        int maximumSpeed;
        double acceleration;
        int roominess;
        int countOfPassenger;
        int currentSpeed;
        TypeEnjine typeEnjine;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello!\n" +
                "Create a car [Y]es / [N]o?\n" +
                ":>\n");
        if (getActualAnswer("yn") == 'n'){
            System.out.println("Bye!");
            System.exit(0);
        }
//ENGINE
        System.out.println("Enter engine type: \n" +
                "[G]as, [D]iesel");
        if (getActualAnswer("gd") == 'g')
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
    private static int scannerCorrectInt(){
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            return scannerCorrectInt();
        }
    }
    private static double scannerCorrectDouble(){
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextDouble();
        }catch (Exception e){
            return scannerCorrectDouble();
        }
    }
}
