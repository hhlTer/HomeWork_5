package com.company.carconstructor;

public class CarWheel {
    private double stateWheel;
    public CarWheel(){
        this.stateWheel = 1;
    }
    public CarWheel(double stateWheel){
        if ((stateWheel < 0)||(stateWheel > 1)) {
            System.out.println("Wrong parameter, wheel state installed to 0");
            this.stateWheel = 0;
        } else
            this.stateWheel = stateWheel;
    }
    public void installNewWheel(){
        this.stateWheel = 1;
    }
    public void eraseWheel(int precent){
        stateWheel -= ((double) precent/100);
        if (stateWheel < 0) stateWheel = 0;
    }
    public double getStateWheel(){
        return stateWheel;
    }
    public void sysoutState(){
        double tmp = Math.round((1 - stateWheel)*100);
        System.out.println("Wheel destroyed on " + (tmp + " precent"));
    }
}
