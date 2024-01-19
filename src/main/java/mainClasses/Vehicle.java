/* csd5031 , csd5020 , csd4845
* HY-360 Vehicle.java
 */
package mainClasses;


public class Vehicle {
    int vehicle_id;
    String available;
    String color, brand, driver;
    int autonomy, daily_rental_cost, daily_insurance_cost;

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getAutonomy() {
        return autonomy;
    }

    public void setAutonomy(int autonomy) {
        this.autonomy = autonomy;
    }

    public int getDaily_rental_cost() {
        return autonomy;
    }

    public void setDaily_rental_cost(int daily_rental_cost) {
        this.daily_rental_cost = daily_rental_cost;
    }

    public int getDaily_insurance_cost() {
        return autonomy;
    }

    public void setDaily_insurance_cost(int daily_insurance_cost) {
        this.daily_insurance_cost = daily_insurance_cost;
    }
}
