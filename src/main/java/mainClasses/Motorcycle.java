/* csd5031 , csd5020 , csd4845
* HY-360 Motorcycle.java
 */
package mainClasses;

public class Motorcycle extends Vehicle {

    int registration_number;

    public int getVehicle_id() {
        return super.vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(int registration_number) {
        this.registration_number = registration_number;
    }

}
