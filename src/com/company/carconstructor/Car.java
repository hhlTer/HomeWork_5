package com.company.carconstructor;

import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;
import java.util.Date;

public class Car {
    private final Date date;
    private TypeEnjine typeEnjine;
    private int maximumSpeed;
    private double acceleration;
    private int roominess;
    private int countOfPassenger;
    private int currentSpeed;
    private CarDoor[] carDoors;
    private CarWheel[] carWheels;

    Car(Date date,
               TypeEnjine typeEnjine,
               int maximumSpeed,
               double acceleration,
               int roominess,
               int countOfPassenger,
               int currentSpeed) {
        this.date = date;
        this.typeEnjine = typeEnjine;
        this.maximumSpeed = maximumSpeed;
        this.acceleration = acceleration;
        this.roominess = roominess;
        this.countOfPassenger = countOfPassenger;
/*
 *      currentSpeed cannot be biggest than 0 if count of passenger = 0
 *      or if wheel = 0 - so constructor create array with new wheels;
 */
        setNewWheel(4);
        this.currentSpeed = countOfPassenger == 0 ? 0 : currentSpeed;
        
    }

    public Car(Date date) {
        this.date = date;
    }

/*
 *  changeSpeed(int speed): speed cannot be smallest than 0;
 */
    public void changeSpeed(int speed){
        int spd = this.currentSpeed;
        spd += speed;
        currentSpeed = spd < 0 ? 0 : spd;
    }
    public void addPassenger(){
        if (countOfPassenger == roominess){
            System.out.println("Car is full");
            return;
        }
        countOfPassenger++;

    }
    public void getPassenger(){
        if (countOfPassenger > 0) countOfPassenger--;
        else System.out.println("Car is empty");
    }
    public void getoutAllPassenger(){
        countOfPassenger = 0;
        System.out.println("Car is empty");
    }
    public void setNewWheel(int countOfWheels){
        if (carWheels == null) carWheels = new CarWheel[0];
        int temp = carWheels.length;
        carWheels = Arrays.copyOf(carWheels, temp + countOfWheels);
        for (int i = temp; i < carWheels.length; i++) {
            carWheels[i] = new CarWheel();
            carWheels[i].installNewWheel();
        }
    }

    public CarDoor getCarDoors(DoorWheelIndex index) {
        switch (index){
            case FRONTLEFT: return carDoors[0];
            case FRONTRIGHT: return carDoors[1];
            case REARLEFT: return carDoors[2];
            case REARRIGHT: return carDoors[3];
            default: return null;
        }
    }
    public CarDoor getCarDoors(int index) {
        try {
            return carDoors[index];
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Door not exist");
            return null;
        }
    }
    public CarWheel getCarWheel(DoorWheelIndex index){
        if (index == DoorWheelIndex.FRONTLEFT) {
            return carWheels[0];
        } else if (index == DoorWheelIndex.FRONTRIGHT) {
            return carWheels[1];
        } else if (index == DoorWheelIndex.REARLEFT) {
            return carWheels[2];
        } else if (index == DoorWheelIndex.REARRIGHT) {
            return carWheels[3];
        } else {
            return null;
        }
    }
    public CarWheel getCarWheel(int index){
        try {
            return carWheels[index];
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Wheel not found");
            return null;
        }
        }

    public void setCountCarDoors(int count) {
        carDoors = new CarDoor[count];
        for (int i = 0; i < count; i++) {
            carDoors[i] = new CarDoor();
        }
    }

    int getCurrentPossibleMaximumSpeed(){
        //if (countOfPassenger == 0) return 0;
        double minWheel = 1;
        for (int i = 0; i < carWheels.length; i++) {
            double wheelState = carWheels[i].getStateWheel();
            if (minWheel > wheelState) minWheel = wheelState;
        }
        return (int)(minWheel * maximumSpeed);
    }
    String sysoutCarStatement(){
        String[] s1 = new String[7];
        String[] s2 = new String[7];
        String[] s = {
                "engine type ", "" + typeEnjine,
                "maximum speed ", "" + maximumSpeed,
                "accelerate 0 - 100 km/h (sec)", "" + acceleration,
                "roominess of passenger: ", "" + roominess,
                "passengers in car: ", "" + countOfPassenger,
                "maximum Possible speed: ", "" + getCurrentPossibleMaximumSpeed(),
                "current speed: ", "" + currentSpeed
        };
        for (int i = 0; i < 7; i++) {
            s1[i] = s[i*2];
            s2[i] = s[i*2 + 1];
        }
        try {
            Thread.sleep(999);
            for (int i = 0; i < s.length; i++) {
                System.out.print(s[i]);
                Thread.sleep(999);
                System.out.println(s[++i]);
                Thread.sleep(400);
            }
            if (carDoors != null)
                System.out.printf("Car have %s door\n", carDoors.length);
            if (carWheels != null) {
            Thread.sleep(999);
                System.out.println("State of wheels:");
                for (int i = 0; i < carWheels.length; i++) {
                    System.out.print(i+1 + ": ");
                    carWheels[i].sysoutState();
                    Thread.sleep(400);
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        printAsTable(s1, s2);
    }



    private static void printAsTable(String[] namesCol, String[] datas){
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
    }
}
