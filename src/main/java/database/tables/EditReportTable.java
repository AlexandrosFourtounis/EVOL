/* csd5031 , csd5020 , csd4845
* HY-360 EditReportTable.java
 */
package database.tables;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.Report;
import database.DB_Connection;


public class EditReportTable {
    public void addReportFromJSON(String json) throws ClassNotFoundException {
        Report user = jsonToReport(json);
        addReport(user);
    }

    public Report jsonToReport(String json) {
        Gson gson = new Gson();

        Report user = gson.fromJson(json, Report.class);
        return user;
    }

    public String ReportToJSON(Report user) {
        Gson gson = new Gson();

        String json = gson.toJson(user, Report.class);
        return json;
    }

 
    public Report databaseToReport(int report_id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Report WHERE report_id = '" + report_id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Report user = gson.fromJson(json, Report.class);
            return user;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String databaseReportToJSON(int report_id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM Report WHERE report_id = '" + report_id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void createReportTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE Report "
                + "(report_id INTEGER not null unique, "
                + " customer_id INTEGER not null, "
                + " vehicle_id INTEGER not null, "
                + " malfunction_description VARCHAR(100), "
                + " report_date DATE,"
                + " insurance_paid VARCHAR(20), "
                + " repair_cost DOUBLE, "
                + " PRIMARY KEY(report_id), "
                + " FOREIGN KEY(customer_id) REFERENCES Customer(customer_id), "
                + " FOREIGN KEY(vehicle_id) REFERENCES Vehicle(vehicle_id))";

        stmt.execute(query);
        stmt.close();
    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addReport(Report user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + "Report (report_id, malfunction_description, report_date, insurance_paid, repair_cost, customer_id, vehicle_id)"
                    + " VALUES ("
                    + "'" + user.getReport_id() + "',"
                    + "'" + user.getMalfunction_description() + "',"
                    + "'" + user.getReport_date() + "',"
                    + "'" + user.getInsurance_paid() + "',"
                    + "'" + user.getRepair_cost() + "',"
                    + "'" + user.getCustomer_id() + "',"
                    + "'" + user.getVehicle_id() + "'"
                    + ")";

            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The Report was successfully added in the database.");

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditReportTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
