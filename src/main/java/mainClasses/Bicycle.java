/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;

/**
 *
 * @author georgia_tsanta
 */
public class Bicycle extends Vehicle {

    int special_number;

    public int getVehicle_id() {
        return super.vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getSpecial_number() {
        return special_number;
    }

    public void setSpecial_number(int special_number) {
        this.special_number = special_number;
    }

}
