/* csd5031 , csd5020 , csd4845
* HY-360 EditElectricScooterTable.java
 */
package database.tables;

import com.google.gson.Gson;
import mainClasses.ElectricScooter;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EditElectricScooterTable {

 
    public void addElectricScooterFromJSON(String json) throws ClassNotFoundException{
        ElectricScooter ElectricScooter = jsonToElectricScooter(json);
         addNewElectricScooter(ElectricScooter);
    }
    
    public ElectricScooter jsonToElectricScooter(String json) {
         Gson gson = new Gson();

        ElectricScooter ElectricScooter = gson.fromJson(json, ElectricScooter.class);
        return ElectricScooter;
    }
    
    public String ElectricScooterToJSON(ElectricScooter ElectricScooter) {
         Gson gson = new Gson();

        String json = gson.toJson(ElectricScooter, ElectricScooter.class);
        return json;
    }
    
    
    public String databaseElectricScooterToJSON(int Vehicle_id) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Electric Scooter WHERE Vehicle_id = '" + Vehicle_id);
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

     public void createElectricScooterTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

         String query = "CREATE TABLE Electric_Scooter "
                 + "("
                 + "special_number INTEGER not null unique,"
                 + "vehicle_id INTEGER not null, "
                 +"FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id))";
        stmt.execute(query);
        stmt.close();
    }
   
    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addNewElectricScooter(ElectricScooter ElectricScooter) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " Electric_Scooter (Vehicle_id,Special_number)"
                    + " VALUES ("
                    + "'" + ElectricScooter.getVehicle_id() + "',"
                    + "'" + ElectricScooter.getSpecial_number() + "'"
                    + ")";
           
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The ElectricScooter was successfully added in the database.");

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditElectricScooterTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ElectricScooter> getAvailableElectricScooters() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<ElectricScooter> availableElectricScooters = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM Electric_Scooter WHERE vehicle_id IN(SELECT vehicle_id FROM Vehicle "
                    + "WHERE available = 'yes')");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                ElectricScooter ElectricScooter = gson.fromJson(json, ElectricScooter.class
                );
                availableElectricScooters.add(ElectricScooter);
            }
            return availableElectricScooters;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

   

}