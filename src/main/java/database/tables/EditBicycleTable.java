/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import mainClasses.Bicycle;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
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
    
   
    /*public void updateBicycle(String Bicyclename,String personalpage) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE Bicycles SET personalpage='"+personalpage+"' WHERE Bicyclename = '"+Bicyclename+"'";
        stmt.executeUpdate(update);
    }*/
    
    /*public void printBicycleDetails(String Bicyclename, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Bicycle WHERE Bicyclename = '" + Bicyclename + "' AND password='"+password+"'");
            while (rs.next()) {
                System.out.println("===Result===");
                DB_Connection.printResults(rs);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }*/
    
    /*public Bicycle databaseToBicycles(String Bicyclename, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Bicycles WHERE Bicyclename = '" + Bicyclename + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Bicycle Bicycle = gson.fromJson(json, Bicycle.class);
            return Bicycle;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
    
    /* public ArrayList<Bicycle> getAvailableKeepers(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Bicycle> keepers = new ArrayList<Bicycle>();
        ResultSet rs = null;
        try {
            //if(type=="catkeeper")
            if("all".equals(type))     
            rs = stmt.executeQuery("SELECT * FROM `Bicycles` WHERE  `Bicycles`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')\n" +"");
            else if ("catKeepers".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM `Bicycles` WHERE `Bicycles`.`catkeeper`='true' AND `Bicycles`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')");         
             else if ("dogKeepers".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM `Bicycles` WHERE `Bicycles`.`dogkeeper`='true' AND `Bicycles`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')");
        
           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Bicycle keeper = gson.fromJson(json, Bicycle.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
     
    /*public ArrayList<Bicycle> getKeepers(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Bicycle> keepers = new ArrayList<Bicycle>();
        ResultSet rs = null;
        try {
            if("catkeeper".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM Bicycles WHERE catkeeper= '" + "true" + "'");
            else if ("dogkeeper".equals(type))
                  rs = stmt.executeQuery("SELECT * FROM Bicycles WHERE dogkeeper= '" + "true" + "'");

           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Bicycle keeper = gson.fromJson(json, Bicycle.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
    
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
                 + "    Special_number INTEGER not null unique,"
                 + "PRIMARY KEY (special_number))";
        stmt.execute(query);
        stmt.close();
    }
    /*public Bicycle databaseToBicyclesBicyclename(String Bicyclename) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Bicycle WHERE Bicyclename = '" + Bicyclename + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Bicycle Bicycle = gson.fromJson(json, Bicycle.class);
            return Bicycle;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/

    /*public Bicycle databaseToBicyclesEmail(String email) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Bicycles WHERE email = '" + email + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Bicycle Bicycle = gson.fromJson(json, Bicycle.class);
            return Bicycle;
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
    public void addNewBicycle(Bicycle Bicycle) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " Bicycle (Vehicle_id,Special_number)"
                    + " VALUES ("
                    + "'" + Bicycle.getVehicle_id() + "',"
                    + "'" + Bicycle.getSpecial_number() + "',"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The Bicycle was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditBicycleTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}