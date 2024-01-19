/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import database.tables.EditElectricScooterTable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.ElectricScooter;

@WebServlet("/GetAvailableElectricScooters")
public class GetAvailableElectricScooters extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            EditElectricScooterTable editElectricScooterTable = new EditElectricScooterTable();
            ArrayList<ElectricScooter> availableElectricScooters = editElectricScooterTable.getAvailableElectricScooters();

            Gson gson = new Gson();
            String json = gson.toJson(availableElectricScooters);

            response.getWriter().write(json);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetAvailableElectricScooters.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write("Error retrieving available electric scooters");
        }
    }
}
