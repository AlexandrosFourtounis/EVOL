/* csd5031 , csd5020 , csd4845
* HY-360 Bicycle.java
 */
package mainClasses;

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
