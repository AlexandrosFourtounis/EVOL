/* csd5031 , csd5020 , csd4845
* HY-360 EditVehicleTable.java
 */
package database.tables;

import com.google.gson.Gson;
import mainClasses.Vehicle;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EditVehicleTable {

 
    public void addVehicleFromJSON(String json) throws ClassNotFoundException{
         Vehicle vehicle=jsonToVehicle(json);
        addNewVehicle(vehicle);
    }
    
    public Vehicle jsonToVehicle(String json){
         Gson gson = new Gson();

        Vehicle vehicle = gson.fromJson(json, Vehicle.class);
        return vehicle;
    }
    
    public String VehicleToJSON(Vehicle vehicle){
         Gson gson = new Gson();

        String json = gson.toJson(vehicle, Vehicle.class);
        return json;
    }
    
    
    public String databaseVehicleToJSON(int vehicle_id) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Vehicle WHERE vehicle id = '" + vehicle_id);
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

     public void createVehiclesTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE Vehicle "
                + "(vehicle_id INTEGER not null unique, "
                + "    color VARCHAR(20),"
                + "    brand VARCHAR(20),	"
                + "    autonomy INTEGER,"
                + "    daily_rental_cost INTEGER,"
                + "    daily_insurance_cost INTEGER,"
                + "    available VARCHAR(20),"
                + " PRIMARY KEY (vehicle_id))";
        stmt.execute(query);
        stmt.close();
    }
    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addNewVehicle(Vehicle vehicle) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available)"
                    + " VALUES ("
                    + "'" + vehicle.getVehicle_id() + "',"
                    + "'" + vehicle.getColor() + "',"
                    + "'" + vehicle.getBrand() + "',"
                    + "'" + vehicle.getAutonomy() + "',"
                    + "'" + vehicle.getDaily_rental_cost() + "',"
                    + "'" + vehicle.getDaily_insurance_cost() + "',"
                    + "'" + vehicle.getAvailable() + "'"
                    + ")";
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The vehicle was successfully added in the database.");

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditVehicleTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}