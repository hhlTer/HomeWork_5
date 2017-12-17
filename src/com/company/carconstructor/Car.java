package com.company.carconstructor;

import com.company.service.DataCarExeption;
import com.company.service.TypeExeption;
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

    public Car(Date date,
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

/*
 *  setters and getters
 *
 *  setSpeed: accepts speed (increment or decrement) and check:
 *      speed < 0 | speed > maximum | in car not exist driver,
 *      + trows DataCarExeption
 */
    public void setSpeed(int speed)throws DataCarExeption{
        if(countOfPassenger == 0) {
            currentSpeed = 0;
            throw new DataCarExeption("In car not exists driver", TypeExeption.DRIVER_MISSED);
        }
        if (speed > getCurrentPossibleMaximumSpeed()) {
            throw new DataCarExeption("Speed cannot be bigger than possible maximum speed" +
                    " (" + getCurrentPossibleMaximumSpeed() + ")", TypeExeption.SPEED_BIGGER_MAXIMUM);
        }
        if (speed < 0) {
            throw new DataCarExeption("Speed cannot be smaller than 0, speed set into 0 km/h",
                    TypeExeption.SPEED_SMALLER_ZERO);
        }
        currentSpeed = speed < 0 ? 0 : speed;
    }
    public int getCurrentSpeed(){return currentSpeed;}
    public int getCountOfPassenger(){return countOfPassenger;}
    public void setNewWheel(int countOfWheels){
        if (carWheels == null) carWheels = new CarWheel[0];
        int temp = carWheels.length;
        carWheels = Arrays.copyOf(carWheels, temp + countOfWheels);
        for (int i = temp; i < carWheels.length; i++) {
            carWheels[i] = new CarWheel();
            carWheels[i].installNewWheel();
        }
    }
    public CarDoor[] getDoors(){return carDoors;}
    public CarWheel[] getCarWheels(){return carWheels;}

    public int getCountOfDoors(){
        if (carDoors != null) return carDoors.length;
        return 0;
    }
    public int getCountOfWheels(){
        if (carWheels != null) return carWheels.length;
        return 0;
    }


    public void changeSpeed(int speed) throws DataCarExeption{
        int spd = this.currentSpeed;
        spd += speed;
        setSpeed(spd);
        currentSpeed = spd < 0 ? 0 : spd;
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
    public int getCurrentPossibleMaximumSpeed(){
        //if (countOfPassenger == 0) return 0;
        double minWheel = 1;
        for (int i = 0; i < carWheels.length; i++) {
            double wheelState = carWheels[i].getStateWheel();
            if (minWheel > wheelState) minWheel = wheelState;
        }
        return (int)(minWheel * maximumSpeed);
    }
    public String[][] sysoutCarStatement(){
        String[][] ss = {{"engine type ",
                "maximum speed ",
                "accelerate 0 - 100 km/h (sec)",
                "roominess of passenger: ",
                "passengers in car: ",
                "maximum Possible speed: ",
                "current speed: "},
        {       "" + typeEnjine,
                "" + maximumSpeed,
                "" + acceleration,
                "" + roominess,
                "" + countOfPassenger,
                "" + getCurrentPossibleMaximumSpeed(),
                "" + currentSpeed
        }};
        return ss;
    }

    public void getoutAllPassenger(){
        countOfPassenger = 0;
    }
    public void putPassenger() throws DataCarExeption{
        if (countOfPassenger == roominess){
            throw  new DataCarExeption("Car is full", TypeExeption.CAR_FULL);
        }
        countOfPassenger++;
    }
    public void getoutPassenger() throws DataCarExeption{
        if (countOfPassenger > 0) countOfPassenger--;
        else throw new DataCarExeption("Car is empty", TypeExeption.CAR_EMPTY);
    }
    public void getoutAllWheels(){
        carWheels = null;
    }

}
