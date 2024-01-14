/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import mainClasses.Customer;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandros_Fourtounis
 */
public class EditCustomerTable {

    public void addCustomerFromJSON(String json) throws ClassNotFoundException {
        Customer r = jsonToCustomer(json);
        createNewCustomer(r);
    }

    public Customer databaseToCustomer(int id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Customer WHERE customer_id= '" + id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Customer bt = gson.fromJson(json, Customer.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Customer jsonToCustomer(String json) {
        Gson gson = new Gson();
        Customer r = gson.fromJson(json, Customer.class);
        return r;
    }

    public String CustomerToJSON(Customer r) {
        Gson gson = new Gson();

        String json = gson.toJson(r, Customer.class);
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

    public void createCustomerTable() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String sql = "CREATE TABLE Customer "
                + "(customer_id INTEGER not NULL AUTO_INCREMENT, "
                + " registration_id INTEGER not NULL, "
                + " first_name VARCHAR(20) not NULL, "
                + " last_name VARCHAR(20), "
                + " street VARCHAR(20), "
                + " date_of_birth INTEGER, "
                + " drivers_licence_number INTEGER not NULL unique, "
                + " credit_card_number VARCHAR(20) not NULL unique, "
                + " expiration_date VARCHAR(20) DATE not NULL, "
                + " security_code INTEGER not NULL, "
                + " city VARCHAR(20), "
                + " region VARCHAR(20), "
                + " zip_code INTEGER, "
                + " age INTEGER, "
                + " PRIMARY KEY (customer_id),"
                + "FOREIGN KEY (registration_id) REFERENCES Registration(registration_id))";
        stmt.execute(sql);
        stmt.close();
        con.close();

    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewCustomer(Customer cust) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " Customer (first_name,last_name,street,date_of_birth,drivers_licence_number,credit_card_number,"
                    + "expiration_date,security_code,city,region,zip_code,age,registration_id)"
                    + " VALUES ("
                    + "'" + cust.getFirstname() + "',"
                    + "'" + cust.getLastname() + "',"
                    + "'" + cust.getStreet() + "',"
                    + "'" + cust.getDate_of_birth() + "',"
                    + "'" + cust.getDrivers_licence_number() + "',"
                    + "'" + cust.getCredit_card_number() + "',"
                    + "'" + cust.getExpiration_date() + "'"
                    + "'" + cust.getSecurity_code() + "'"
                    + "'" + cust.getCity() + "'"
                    + "'" + cust.getRegion() + "'"
                    + "'" + cust.getZip_code() + "'"
                    + "'" + (2024 - cust.getDate_of_birth()) + "'"
                    + "'" + cust.getRegistartion_id() + "'"
                    + ")";
            //stmt.execute(table);

            stmt.executeUpdate(insertQuery);
            System.out.println("# The customer was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditCustomerTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}