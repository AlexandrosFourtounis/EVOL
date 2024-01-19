/* csd5031 , csd5020 , csd4845
* HY-360 InitDatabase.java
 */
package database.init;

import static database.DB_Connection.getInitialConnection;
import database.tables.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class InitDatabase {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        InitDatabase init = new InitDatabase();
        init.initDatabase();
        init.initTables();
        init.addToDatabaseExamples();

    }

    public void dropDatabase() throws SQLException, ClassNotFoundException {
        Connection conn = getInitialConnection();
        Statement stmt = conn.createStatement();
        String sql = "DROP DATABASE HY360_2024";
        stmt.executeUpdate(sql);
        System.out.println("Database dropped successfully...");
    }

    public void initDatabase() throws SQLException, ClassNotFoundException {
        Connection conn = getInitialConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE DATABASE HY360_2024");
        stmt.close();
        conn.close();
    }

    public void initTables() throws SQLException, ClassNotFoundException {

        EditRegistrationTable ert = new EditRegistrationTable();
        ert.createRegistrationTable();

        EditCustomerTable cus = new EditCustomerTable();
        cus.createCustomerTable();

        EditVehicleTable veh = new EditVehicleTable();
        veh.createVehiclesTable();

        EditReportTable rep = new EditReportTable();
        rep.createReportTable();

        EditRentalTable ren = new EditRentalTable();
        ren.createRentalTable();

        EditCarTable eut = new EditCarTable();
        eut.createcarTable();

        EditMotorcycleTable mot = new EditMotorcycleTable();
        mot.createMotorcyclesTable();

        EditElectricScooterTable elc = new EditElectricScooterTable();
        elc.createElectricScooterTable();

        EditBicycleTable bic = new EditBicycleTable();
        bic.createBicycleTable();
    }

    public void addToDatabaseExamples() throws ClassNotFoundException, SQLException {
    

        EditRegistrationTable ebt = new EditRegistrationTable();
        ebt.addRegistrationFromJSON(Resources.registration1);
        ebt.addRegistrationFromJSON(Resources.registration2);
        ebt.addRegistrationFromJSON(Resources.registration3);
        ebt.addRegistrationFromJSON(Resources.registration4);
        ebt.addRegistrationFromJSON(Resources.registration5);

        EditCustomerTable editc = new EditCustomerTable();
        editc.addCustomerFromJSON(Resources.CustomertoJSON1);
        editc.addCustomerFromJSON(Resources.CustomertoJSON2);
        editc.addCustomerFromJSON(Resources.CustomertoJSON3);
        editc.addCustomerFromJSON(Resources.CustomertoJSON4);
        editc.addCustomerFromJSON(Resources.CustomertoJSON5);

        EditVehicleTable vhe = new EditVehicleTable();
        vhe.addVehicleFromJSON(Resources.vehicle1);
        vhe.addVehicleFromJSON(Resources.vehicle2);
        vhe.addVehicleFromJSON(Resources.vehicle3);
        vhe.addVehicleFromJSON(Resources.vehicle4);
        vhe.addVehicleFromJSON(Resources.vehicle5);
        vhe.addVehicleFromJSON(Resources.vehicle6);
        vhe.addVehicleFromJSON(Resources.vehicle7);
        vhe.addVehicleFromJSON(Resources.vehicle8);
        vhe.addVehicleFromJSON(Resources.vehicle9);
        vhe.addVehicleFromJSON(Resources.vehicle10);
        vhe.addVehicleFromJSON(Resources.vehicle11);
        vhe.addVehicleFromJSON(Resources.vehicle12);
        vhe.addVehicleFromJSON(Resources.vehicle13);
        vhe.addVehicleFromJSON(Resources.vehicle14);
        vhe.addVehicleFromJSON(Resources.vehicle15);
        vhe.addVehicleFromJSON(Resources.vehicle16);
        vhe.addVehicleFromJSON(Resources.vehicle17);
        vhe.addVehicleFromJSON(Resources.vehicle18);
        vhe.addVehicleFromJSON(Resources.vehicle19);
        vhe.addVehicleFromJSON(Resources.vehicle20);

        EditReportTable rep = new EditReportTable();
        rep.addReportFromJSON(Resources.Report1);
        rep.addReportFromJSON(Resources.Report2);
        rep.addReportFromJSON(Resources.Report3);
        rep.addReportFromJSON(Resources.Report4);
        rep.addReportFromJSON(Resources.Report5);

        EditRentalTable ren = new EditRentalTable();
        ren.addRentalFromJSON(Resources.rental1);
        ren.addRentalFromJSON(Resources.rental2);
        ren.addRentalFromJSON(Resources.rental3);
        ren.addRentalFromJSON(Resources.rental4);
        ren.addRentalFromJSON(Resources.rental5);
        ren.addRentalFromJSON(Resources.rental6);
        ren.addRentalFromJSON(Resources.rental7);
        ren.addRentalFromJSON(Resources.rental8);
        ren.addRentalFromJSON(Resources.rental9);
        ren.addRentalFromJSON(Resources.rental10);
        ren.addRentalFromJSON(Resources.rental11);
        ren.addRentalFromJSON(Resources.rental12);
        ren.addRentalFromJSON(Resources.rental13);
        ren.addRentalFromJSON(Resources.rental14);
        ren.addRentalFromJSON(Resources.rental15);
        ren.addRentalFromJSON(Resources.rental16);
        ren.addRentalFromJSON(Resources.rental17);
        ren.addRentalFromJSON(Resources.rental18);
        ren.addRentalFromJSON(Resources.rental19);
        ren.addRentalFromJSON(Resources.rental20);

        EditCarTable eut = new EditCarTable();
        eut.addcarFromJSON(Resources.CartoJSON1);
        eut.addcarFromJSON(Resources.CartoJSON2);
        eut.addcarFromJSON(Resources.CartoJSON3);
        eut.addcarFromJSON(Resources.CartoJSON4);
        eut.addcarFromJSON(Resources.CartoJSON5);


        EditMotorcycleTable motr = new EditMotorcycleTable();
        motr.addMotorcycleFromJSON(Resources.MotorcycletoJSON1);
        motr.addMotorcycleFromJSON(Resources.MotorcycletoJSON2);
        motr.addMotorcycleFromJSON(Resources.MotorcycletoJSON3);
        motr.addMotorcycleFromJSON(Resources.MotorcycletoJSON4);
        motr.addMotorcycleFromJSON(Resources.MotorcycletoJSON5);

        EditBicycleTable bicy = new EditBicycleTable();
        bicy.addBicycleFromJSON(Resources.Bicycle1);
        bicy.addBicycleFromJSON(Resources.Bicycle2);
        bicy.addBicycleFromJSON(Resources.Bicycle3);
        bicy.addBicycleFromJSON(Resources.Bicycle4);
        bicy.addBicycleFromJSON(Resources.Bicycle5);

        EditElectricScooterTable elec = new EditElectricScooterTable();
        elec.addElectricScooterFromJSON(Resources.ElectricScooter1);
        elec.addElectricScooterFromJSON(Resources.ElectricScooter2);
        elec.addElectricScooterFromJSON(Resources.ElectricScooter3);
        elec.addElectricScooterFromJSON(Resources.ElectricScooter4);
        elec.addElectricScooterFromJSON(Resources.ElectricScooter5);
    }

}
