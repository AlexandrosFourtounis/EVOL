/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import database.tables.EditBicycleTable;
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
import mainClasses.Bicycle;

@WebServlet("/GetAvailableBicycles")
public class GetAvailableBicyclesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String[] args = null;

        try {
            EditBicycleTable bicycleTable = new EditBicycleTable();
            ArrayList<Bicycle> availableBicycles = bicycleTable.getAvailableBicycle();
            Gson gson = new Gson();
            String json = gson.toJson(availableBicycles);

            // Send the JSON response
            response.getWriter().write(json);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetAvailableBicyclesServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write("Error fetching available bicycles");
        }
    }
}
