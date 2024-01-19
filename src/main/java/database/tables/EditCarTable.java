/* csd5031 , csd5020 , csd4845
* HY-360 EditCarTable.java
 */
package database.tables;

import com.google.gson.Gson;
import mainClasses.Car;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EditCarTable {

 
    public void addcarFromJSON(String json) throws ClassNotFoundException{
        Car car = jsonTocar(json);
        addNewcar(car);
    }
    
    public Car jsonTocar(String json) {
         Gson gson = new Gson();

        Car car = gson.fromJson(json, Car.class);
        return car;
    }
    
    public String carToJSON(Car car) {
         Gson gson = new Gson();

        String json = gson.toJson(car, Car.class);
        return json;
    }
    
    
    public String databasecarToJSON(int car_id) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM car WHERE car id = '" + car_id);
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

     public void createcarTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE Car "
                + "(vehicle_id INTEGER not null unique, "
                + "    registration_number INTEGER not null,"
                + "    type VARCHAR(20), "
                + "    number_of_passengers INTEGER,"
                 + " FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id))";
        stmt.execute(query);
        stmt.close();
    }
    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addNewcar(Car car) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " car (vehicle_id,registration_number,type,number_of_passengers)"
                    + " VALUES ("
                    + "'" + car.getVehicle_id() + "',"
                    + "'" + car.getRegistration_number() + "',"
                    + "'" + car.getType() + "',"
                    + "'" + car.getNumber_of_passengers() + "'"
                    + ")";
            
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The car was successfully added in the database.");

         
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditCarTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Car> getAvailableCars() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Car> availableCars = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM Car WHERE vehicle_id IN(SELECT vehicle_id FROM Vehicle "
                    + "WHERE available = 'yes')");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Car car = gson.fromJson(json, Car.class
                );
                availableCars.add(car);
            }
            return availableCars;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

}