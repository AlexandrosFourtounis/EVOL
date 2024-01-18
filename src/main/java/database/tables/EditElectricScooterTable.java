/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import mainClasses.ElectricScooter;
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
    
   
    /*public void updateElectricScooter(String ElectricScootername,String personalpage) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE ElectricScooters SET personalpage='"+personalpage+"' WHERE ElectricScootername = '"+ElectricScootername+"'";
        stmt.executeUpdate(update);
    }*/
    
    /*public void printElectricScooterDetails(String ElectricScootername, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM ElectricScooter WHERE ElectricScootername = '" + ElectricScootername + "' AND password='"+password+"'");
            while (rs.next()) {
                System.out.println("===Result===");
                DB_Connection.printResults(rs);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }*/
    
    /*public ElectricScooter databaseToElectricScooters(String ElectricScootername, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM ElectricScooters WHERE ElectricScootername = '" + ElectricScootername + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            ElectricScooter ElectricScooter = gson.fromJson(json, ElectricScooter.class);
            return ElectricScooter;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
    
    /* public ArrayList<ElectricScooter> getAvailableKeepers(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<ElectricScooter> keepers = new ArrayList<ElectricScooter>();
        ResultSet rs = null;
        try {
            //if(type=="catkeeper")
            if("all".equals(type))     
            rs = stmt.executeQuery("SELECT * FROM `ElectricScooters` WHERE  `ElectricScooters`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')\n" +"");
            else if ("catKeepers".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM `ElectricScooters` WHERE `ElectricScooters`.`catkeeper`='true' AND `ElectricScooters`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')");         
             else if ("dogKeepers".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM `ElectricScooters` WHERE `ElectricScooters`.`dogkeeper`='true' AND `ElectricScooters`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')");
        
           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                ElectricScooter keeper = gson.fromJson(json, ElectricScooter.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
     
    /*public ArrayList<ElectricScooter> getKeepers(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<ElectricScooter> keepers = new ArrayList<ElectricScooter>();
        ResultSet rs = null;
        try {
            if("catkeeper".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM ElectricScooters WHERE catkeeper= '" + "true" + "'");
            else if ("dogkeeper".equals(type))
                  rs = stmt.executeQuery("SELECT * FROM ElectricScooters WHERE dogkeeper= '" + "true" + "'");

           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                ElectricScooter keeper = gson.fromJson(json, ElectricScooter.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
    
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

        String query = "CREATE TABLE Electric Scooter "
                + "(Vehicle_id INTEGER not null, "
                + "    special_number INTEGER not null unique,";
        stmt.execute(query);
        stmt.close();
    }
    /*public ElectricScooter databaseToElectricScootersElectricScootername(String ElectricScootername) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM ElectricScooter WHERE ElectricScootername = '" + ElectricScootername + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            ElectricScooter ElectricScooter = gson.fromJson(json, ElectricScooter.class);
            return ElectricScooter;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/

    /*public ElectricScooter databaseToElectricScootersEmail(String email) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM ElectricScooters WHERE email = '" + email + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            ElectricScooter ElectricScooter = gson.fromJson(json, ElectricScooter.class);
            return ElectricScooter;
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
    public void addNewElectricScooter(ElectricScooter ElectricScooter) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " ElectricScooter (Vehicle_id,Special_number)"
                    + " VALUES ("
                    + "'" + ElectricScooter.getVehicle_id() + "',"
                    + "'" + ElectricScooter.getSpecial_number() + "',"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The ElectricScooter was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditElectricScooterTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}