/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.init;

import static database.DB_Connection.getInitialConnection;
import database.tables.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/*
 *
 * @author micha
 */
public class InitDatabase {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        InitDatabase init = new InitDatabase();
        init.initDatabase();
        init.initTables();
        init.addToDatabaseExamples();
        //init.updateRecords();
        //init.databaseToJSON();

        //  init.dropDatabase();
        // init.deleteRecords();
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
        //Users

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


        EditReportTable rep = new EditReportTable();
        rep.addReportFromJSON(Resources.Report1);
        rep.addReportFromJSON(Resources.Report2);
        rep.addReportFromJSON(Resources.Report3);
        rep.addReportFromJSON(Resources.Report4);
        rep.addReportFromJSON(Resources.Report5);

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

        EditRentalTable ren = new EditRentalTable();
        ren.addRentalFromJSON(Resources.rental1);
        ren.addRentalFromJSON(Resources.rental2);
        ren.addRentalFromJSON(Resources.rental3);
        ren.addRentalFromJSON(Resources.rental4);
        ren.addRentalFromJSON(Resources.rental5);

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

    /*
    public void databaseToJSON() throws ClassNotFoundException, SQLException {
//       //Get info of Pet Owner
        EditPetOwnersTable eut = new EditPetOwnersTable();
        PetOwner su = eut.databaseToPetOwners("mountanton", "ab$12345");
        String json = eut.petOwnerToJSON(su);
        System.out.println("Pet Owner\n" + json + "\n");

        //Get Pet of Owner
        EditPetsTable editpets = new EditPetsTable();
        Pet pet = editpets.petOfOwner("4");
        String petjson = editpets.petToJSON(pet);
        System.out.println("Pet of Owner 4\n" + petjson + "\n");

        //Get Pets that are cats
        ArrayList<Pet> cats = new ArrayList<Pet>();
        cats = editpets.databaseToPets("cat");
        Gson gson1 = new Gson();
        JsonArray catsJSON = gson1.toJsonTree(cats).getAsJsonArray();
        System.out.println("Cats\n" + catsJSON + "\n");

        //Get info of Pet Keeper
        EditPetKeepersTable editkeepers = new EditPetKeepersTable();
        PetKeeper Keeper = editkeepers.databaseToPetKeepers("catmary", "ab$111111");
        String keeperJSON = editkeepers.petKeeperToJSON(Keeper);
        System.out.println("Pet Keeper\n" + keeperJSON + "\n");

        //all catkeepers
        ArrayList<PetKeeper> catKeepers = new ArrayList<PetKeeper>();
        catKeepers = editkeepers.getKeepers("catkeeper");
        Gson gson2 = new Gson();
        JsonArray catKeepersJSON = gson2.toJsonTree(catKeepers).getAsJsonArray();
        System.out.println("Cat Keepers\n" + catKeepersJSON + "\n");

        //all dogkeepers
        ArrayList<PetKeeper> dogKeepers = new ArrayList<PetKeeper>();
        dogKeepers = editkeepers.getKeepers("dogkeeper");
        Gson gson3 = new Gson();
        JsonArray dogKeepersJSON = gson3.toJsonTree(dogKeepers).getAsJsonArray();
        System.out.println("Dog Keepers\n" + dogKeepersJSON + "\n");

        //all available Keepers
        ArrayList<PetKeeper> availableKeepers = new ArrayList<PetKeeper>();
        availableKeepers = editkeepers.getAvailableKeepers("all");
        Gson gson4 = new Gson();
        JsonArray availableKeepersJSON = gson4.toJsonTree(availableKeepers).getAsJsonArray();
        System.out.println("All available Keepers\n" + availableKeepersJSON + "\n");

        //all available catKeepers
        ArrayList<PetKeeper> availableCatKeepers = new ArrayList<PetKeeper>();
        availableCatKeepers = editkeepers.getAvailableKeepers("catKeepers");
        Gson gson5 = new Gson();
        JsonArray availableCatKeepersJSON = gson5.toJsonTree(availableCatKeepers).getAsJsonArray();
        System.out.println("All available CAT Keepers\n" + availableCatKeepersJSON + "\n");

        //all available DOG KEEPERS
        ArrayList<PetKeeper> availableDogKeepers = new ArrayList<PetKeeper>();
        availableDogKeepers = editkeepers.getAvailableKeepers("dogKeepers");
        Gson gson6 = new Gson();
        JsonArray availableDogKeepersJSON = gson6.toJsonTree(availableDogKeepers).getAsJsonArray();
        System.out.println("All available DOG Keepers\n" + availableDogKeepersJSON + "\n");

        // all messages of a booking
        EditMessagesTable editmessages = new EditMessagesTable();
        ArrayList<Message> messagesOfBooking = new ArrayList<Message>();
        messagesOfBooking = editmessages.databaseToMessage(1);
        Gson gson7 = new Gson();
        JsonArray messagesOfBookingJSON = gson7.toJsonTree(messagesOfBooking).getAsJsonArray();
        System.out.println("All MESSAGES OF Booking 1\n" + messagesOfBookingJSON + "\n");

//all reviews for a keeper
        EditReviewsTable ertab = new EditReviewsTable();
        ArrayList<Review> revs = ertab.databaseTokeeperReviews("1");
        Gson gson8 = new Gson();
        JsonArray jsonrevs = gson8.toJsonTree(revs).getAsJsonArray();
        System.out.println("Reviews for Keeper 1\n" + jsonrevs + "\n");
    }

    public void updateRecords() throws ClassNotFoundException, SQLException {
        EditPetOwnersTable es = new EditPetOwnersTable();
        es.updatePetOwner("mountanton", "http://users.ics.forth/mountant");

    }

    public void deleteRecords() throws ClassNotFoundException, SQLException {

        EditPetsTable eb = new EditPetsTable();
        String pet_id = "1";
        //   eb.deletePet(pet_id);
    }

} */
}
