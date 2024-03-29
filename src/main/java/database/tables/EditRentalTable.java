/* csd5031 , csd5020 , csd4845
* HY-360 EditRentalTable.java
 */
package database.tables;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.Rental;
import database.DB_Connection;


public class EditRentalTable {

    public void addRentalFromJSON(String json) throws ClassNotFoundException {
        Rental user = jsonToRental(json);
        addRental(user);
    }

    public Rental jsonToRental(String json) {
        Gson gson = new Gson();

        Rental user = gson.fromJson(json, Rental.class);
        return user;
    }

    public String petOwnerToJSON(Rental user) {
        Gson gson = new Gson();

        String json = gson.toJson(user, Rental.class);
        return json;
    }


    public Rental databaseToRental(int rental_id, String last_name) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Rental WHERE rental_id = '" + rental_id + "' AND last_name='" + last_name + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Rental user = gson.fromJson(json, Rental.class);
            return user;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String databaseRentalToJSON(int rental_id, String last_name) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Rental WHERE rental_id = '" + rental_id + "' AND last_name='" + last_name + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void createRentalTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE Rental "
                + "(rental_id INTEGER not null, "
                + "    customer_id INTEGER not null, "
                + "    vehicle_id INTEGER not null, "
                + "    first_name VARCHAR(20) not null, "
                + "    last_name VARCHAR(20) not null, "
                + "    rental_date DATE, "
                + "    duration INTEGER, "
                + "    cost INTEGER,"
                + " FOREIGN KEY(customer_id) REFERENCES Customer(customer_id), "
                + " FOREIGN KEY(vehicle_id) REFERENCES Vehicle(vehicle_id), "
                + " PRIMARY KEY(rental_id))";
        stmt.execute(query);
        stmt.close();
    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addRental(Rental user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + "Rental (rental_id, first_name, last_name, rental_date, duration, cost, customer_id, vehicle_id)"
                    + " VALUES ("
                    + "'" + user.getRental_id() + "',"
                    + "'" + user.getFirst_name() + "',"
                    + "'" + user.getLast_name() + "',"
                    + "'" + user.getRental_date() + "',"
                    + "'" + user.getDuration() + "',"
                    + "'" + user.getCost() + "',"
                    + "'" + user.getCustomer_id() + "',"
                    + "'" + user.getVehicle_id() + "'"
                    + ")";

            
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The Rental was successfully added in the database.");

           
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditRentalTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
