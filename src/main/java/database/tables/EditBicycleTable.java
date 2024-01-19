/* csd5031 , csd5020 , csd4845
* HY-360 EditBicycleTable.java
 */
package database.tables;

import com.google.gson.Gson;
import mainClasses.Bicycle;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EditBicycleTable {

 
    public void addBicycleFromJSON(String json) throws ClassNotFoundException{
        Bicycle Bicycle = jsonToBicycle(json);
         addNewBicycle(Bicycle);
    }
    
    public Bicycle jsonToBicycle(String json) {
         Gson gson = new Gson();

        Bicycle Bicycle = gson.fromJson(json, Bicycle.class);
        return Bicycle;
    }
    
    public String BicycleToJSON(Bicycle Bicycle) {
         Gson gson = new Gson();

        String json = gson.toJson(Bicycle, Bicycle.class);
        return json;
    }
    
   
  
    public String databaseBicycleToJSON(int Vehicle_id) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Bicycle WHERE Vehicle_id = '" + Vehicle_id);
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

     public void createBicycleTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE Bicycle "
                + "(Vehicle_id INTEGER not null, "
                 + "Special_number INTEGER not null unique,"
                 + " FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id))";
        stmt.execute(query);
        stmt.close();
    }
  
    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addNewBicycle(Bicycle Bicycle) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " Bicycle (Vehicle_id,Special_number)"
                    + " VALUES ("
                    + "'" + Bicycle.getVehicle_id() + "',"
                    + "'" + Bicycle.getSpecial_number() + "'"
                    + ")";
           
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The Bicycle was successfully added in the database.");

            
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditBicycleTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Bicycle> getAvailableBicycle() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Bicycle> availableBicycle = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM Bicycle WHERE vehicle_id IN(SELECT vehicle_id FROM Vehicle "
                    + "WHERE available = 'yes')");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Bicycle Bicycle = gson.fromJson(json, Bicycle.class
                );
                availableBicycle.add(Bicycle);
            }
            return availableBicycle;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

}