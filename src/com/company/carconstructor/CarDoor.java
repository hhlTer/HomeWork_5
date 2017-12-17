package com.company.carconstructor;

class CarDoor {
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
    void openDoor(){
        this.doorsClose = false;
    }
    void openWindow(){
        this.windowsClose = false;
    }
    void closeDoor(){
        this.doorsClose = true;
    }
    void closeWindow(){
        this.windowsClose = true;
    }

    void reverseDoor(){
        doorsClose = !doorsClose;
    }
    void reverseWindow(){
        windowsClose = !windowsClose;
    }

    void sysoutState(){
        System.out.println("Door " + (doorsClose ? "close" : "open" +
                        " , window " + (windowsClose ? "close" : "open")));
    }
}
