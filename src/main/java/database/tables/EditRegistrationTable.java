/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.Registration;

/**
 *
 * @author Alexandros_Fourtounis
 */
public class EditRegistrationTable {
    public void addRegistrationFromJSON(String json) throws ClassNotFoundException {
        Registration r = jsonToRegistration(json);
        createNewRegistration(r);
    }

    public Registration databaseToRegistration(int id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Registration WHERE registration_id= '" + id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Registration bt = gson.fromJson(json, Registration.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Registration jsonToRegistration(String json) {
        Gson gson = new Gson();
        Registration r = gson.fromJson(json, Registration.class);
        return r;
    }

    public String RegistrationToJSON(Registration r) {
        Gson gson = new Gson();

        String json = gson.toJson(r, Registration.class);
        return json;
    }

//    public void updateCustomer(int customer_id,  String status) throws SQLException, ClassNotFoundException {
//        Connection con = DB_Connection.getConnection();
//        Statement stmt = con.createStatement();
//        String updateQuery = "UPDATE Customer SET status='"+status+"' WHERE booking_id= '"+bookingID+"'";
//        stmt.executeUpdate(updateQuery);
//        stmt.close();
//        con.close();
//    }
    public void createRegistrationTable() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String sql = "CREATE TABLE Registration "
                + "(registration_id INTEGER not null AUTO_INCREMENT, "
                + " customer_id INTEGER not null, "
                + " username VARCHAR(20) not null unique, "
                + " pass VARCHAR(20) not null, "
                + " registration_date DATE, "
                + " PRIMARY KEY(registration_id), "
                + "FOREIGN KEY(customer_id) REFERENCES Customer(customer_id))";
        stmt.execute(sql);
        stmt.close();
        con.close();

    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewRegistration(Registration reg) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " Registration (registration_id,customer_id,username,pass,registration_date,customer_id)"
                    + " VALUES ("
                    + "'" + reg.getRegistartion_id() + "',"
                    + "'" + reg.getCustomer_id() + "',"
                    + "'" + reg.getUsername() + "',"
                    + "'" + reg.getPassword() + "',"
                    + "'" + reg.getRegistration_date() + "',"
                    + "'" + reg.getCustomer_id() + "'"
                    + ")";
            //stmt.execute(table);

            stmt.executeUpdate(insertQuery);
            System.out.println("# The registration was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditRegistrationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
