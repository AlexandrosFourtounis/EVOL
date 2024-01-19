/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import database.tables.EditCarTable;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Car;


@WebServlet("/GetAvailableCars")
public class GetAvailableCarsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Fetch available cars from the database
            ArrayList<Car> availableCars = new EditCarTable().getAvailableCars();

            // Convert the list of cars to JSON
            Gson gson = new Gson();
            String json = gson.toJson(availableCars);

            // Send the JSON response
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"error\": \"An error occurred while fetching available cars\"}");
        }
    }
}
