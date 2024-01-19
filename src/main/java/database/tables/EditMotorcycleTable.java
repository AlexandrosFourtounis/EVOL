/* csd5031 , csd5020 , csd4845
* HY-360 EditMotorcycleTable.java
 */
package database.tables;

import com.google.gson.Gson;
import mainClasses.Motorcycle;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EditMotorcycleTable {

 
    public void addMotorcycleFromJSON(String json) throws ClassNotFoundException{
        Motorcycle Motorcycle = jsonToMotorcycle(json);
        addMotorcycle(Motorcycle);
    }
    
    public Motorcycle jsonToMotorcycle(String json){
         Gson gson = new Gson();

        Motorcycle Motorcycle = gson.fromJson(json, Motorcycle.class);
        return Motorcycle;
    }
    
    public String MotorcycleToJSON(Motorcycle Motorcycle){
         Gson gson = new Gson();

        String json = gson.toJson(Motorcycle, Motorcycle.class);
        return json;
    }
    
    
    public String databaseMotorcycleToJSON(int Motorcycle_id) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Motorcycle WHERE Motorcycle id = '" + Motorcycle_id);
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

     public void createMotorcyclesTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE Motorcycle "
                + "(Vehicle_id INTEGER not null UNIQUE,"
                 + " registration_number INTEGER,"
                 + " FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id))";
        stmt.execute(query);
        stmt.close();
    }
    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addMotorcycle(Motorcycle Motorcycle) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " Motorcycle (vehicle_id,registration_number)"
                    + " VALUES ("
                    + "'" + Motorcycle.getVehicle_id() + "',"
                    + "'" + Motorcycle.getRegistration_number() + "'"
                    + ")";
         
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The Motorcycle was successfully added in the database.");

           
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditMotorcycleTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

}