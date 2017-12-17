package com.company.carconstructor;

public class CarDoor {
    private boolean doorsClose;
    private boolean windowsClose;
    CarDoor(){
        doorsClose = true;
        windowsClose = true;
    }
    CarDoor(boolean doorsClose, boolean windowsClose){
        this.doorsClose = doorsClose;
        this.windowsClose = windowsClose;
    }

    public boolean getStatementDoor(){ return doorsClose; }
    public boolean getStatementWindow(){ return windowsClose; }

    public void openDoor(){
        this.doorsClose = false;
    }
    public void openWindow(){
        this.windowsClose = false;
    }
    public void closeDoor(){
        this.doorsClose = true;
    }
    public void closeWindow(){
        this.windowsClose = true;
    }

    public void reverseDoor(){
        doorsClose = !doorsClose;
    }
    public void reverseWindow(){
        windowsClose = !windowsClose;
    }

    public void sysoutState(){
        System.out.println("Door " + (doorsClose ? "close" : "open" +
                        " , window " + (windowsClose ? "close" : "open")));
    }

}
