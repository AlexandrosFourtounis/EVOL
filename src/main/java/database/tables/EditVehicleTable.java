/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import mainClasses.Vehicle;
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
 * @author Mike
 */
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
    
   
    /*public void updateVehicle(String vehiclename,String personalpage) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE Vehicles SET personalpage='"+personalpage+"' WHERE vehiclename = '"+vehiclename+"'";
        stmt.executeUpdate(update);
    }*/
    
    /*public void printVehicleDetails(String vehiclename, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Vehicle WHERE vehiclename = '" + vehiclename + "' AND password='"+password+"'");
            while (rs.next()) {
                System.out.println("===Result===");
                DB_Connection.printResults(rs);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }*/
    
    /*public Vehicle databaseToVehicles(String vehiclename, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Vehicles WHERE vehiclename = '" + vehiclename + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Vehicle vehicle = gson.fromJson(json, Vehicle.class);
            return vehicle;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
    
    /* public ArrayList<Vehicle> getAvailableKeepers(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Vehicle> keepers = new ArrayList<Vehicle>();
        ResultSet rs = null;
        try {
            //if(type=="catkeeper")
            if("all".equals(type))     
            rs = stmt.executeQuery("SELECT * FROM `Vehicles` WHERE  `Vehicles`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')\n" +"");
            else if ("catKeepers".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM `Vehicles` WHERE `Vehicles`.`catkeeper`='true' AND `Vehicles`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')");         
             else if ("dogKeepers".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM `Vehicles` WHERE `Vehicles`.`dogkeeper`='true' AND `Vehicles`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')");
        
           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Vehicle keeper = gson.fromJson(json, Vehicle.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
     
    /*public ArrayList<Vehicle> getKeepers(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Vehicle> keepers = new ArrayList<Vehicle>();
        ResultSet rs = null;
        try {
            if("catkeeper".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM Vehicles WHERE catkeeper= '" + "true" + "'");
            else if ("dogkeeper".equals(type))
                  rs = stmt.executeQuery("SELECT * FROM Vehicles WHERE dogkeeper= '" + "true" + "'");

           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Vehicle keeper = gson.fromJson(json, Vehicle.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/
    
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
                + "    available BOOLEAN,"
                + "    gender  VARCHAR (7) not null,"
                + " PRIMARY KEY (vehicle_id))";
        stmt.execute(query);
        stmt.close();
    }
    /*public Vehicle databaseToVehiclesvehiclename(String vehiclename) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Vehicle WHERE vehiclename = '" + vehiclename + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Vehicle vehicle = gson.fromJson(json, Vehicle.class);
            return vehicle;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }*/

    /*public Vehicle databaseToVehiclesEmail(String email) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Vehicles WHERE email = '" + email + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Vehicle vehicle = gson.fromJson(json, Vehicle.class);
            return vehicle;
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
                    + "'" + vehicle.getAvailable() + "',"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The vehicle was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditVehiclesTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}