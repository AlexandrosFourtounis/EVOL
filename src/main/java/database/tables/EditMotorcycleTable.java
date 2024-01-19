/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author Alexandros_Fourtounis
 */
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
    
   
    /*public void updateMotorcycle(String Motorcyclename,String personalpage) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE Motorcycles SET personalpage='"+personalpage+"' WHERE Motorcyclename = '"+Motorcyclename+"'";
        stmt.executeUpdate(update);
    }*/
    
    /*public void printMotorcycleDetails(String Motorcyclename, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Motorcycle WHERE Motorcyclename = '" + Motorcyclename + "' AND password='"+password+"'");
            while (rs.next()) {
                System.out.println("===Result===");
                DB_Connection.printResults(rs);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }*/
    
    /*public Motorcycle databaseToMotorcycles(String Motorcyclename, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Motorcycles WHERE Motorcyclename = '" + Motorcyclename + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Motorcycle Motorcycle = gson.fromJson(json, Motorcycle.class);
            return Motorcycle;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
    
    /* public ArrayList<Motorcycle> getAvailableKeepers(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Motorcycle> keepers = new ArrayList<Motorcycle>();
        ResultSet rs = null;
        try {
            //if(type=="catkeeper")
            if("all".equals(type))     
            rs = stmt.executeQuery("SELECT * FROM `Motorcycles` WHERE  `Motorcycles`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')\n" +"");
            else if ("catKeepers".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM `Motorcycles` WHERE `Motorcycles`.`catkeeper`='true' AND `Motorcycles`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')");         
             else if ("dogKeepers".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM `Motorcycles` WHERE `Motorcycles`.`dogkeeper`='true' AND `Motorcycles`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')");
        
           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Motorcycle keeper = gson.fromJson(json, Motorcycle.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
     
    /*public ArrayList<Motorcycle> getKeepers(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Motorcycle> keepers = new ArrayList<Motorcycle>();
        ResultSet rs = null;
        try {
            if("catkeeper".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM Motorcycles WHERE catkeeper= '" + "true" + "'");
            else if ("dogkeeper".equals(type))
                  rs = stmt.executeQuery("SELECT * FROM Motorcycles WHERE dogkeeper= '" + "true" + "'");

           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Motorcycle keeper = gson.fromJson(json, Motorcycle.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
    
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
    /*public Motorcycle databaseToMotorcyclesMotorcyclename(String Motorcyclename) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Motorcycle WHERE Motorcyclename = '" + Motorcyclename + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Motorcycle Motorcycle = gson.fromJson(json, Motorcycle.class);
            return Motorcycle;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/

    /*public Motorcycle databaseToMotorcyclesEmail(String email) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Motorcycles WHERE email = '" + email + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Motorcycle Motorcycle = gson.fromJson(json, Motorcycle.class);
            return Motorcycle;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
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
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The Motorcycle was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditMotorcycleTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Motorcycle> getAvailableMotorcycles() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Motorcycle> availableMotorcycles = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM Motorcycle WHERE vehicle_id IN(SELECT vehicle_id FROM Vehicle "
                    + "WHERE available = 'yes')");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Motorcycle Motorcycle = gson.fromJson(json, Motorcycle.class
                );
                availableMotorcycles.add(Motorcycle);
            }
            return availableMotorcycles;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
   

}