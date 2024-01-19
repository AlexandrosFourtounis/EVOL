



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/HY360_2024";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String postalCode = request.getParameter("postalCode");
        String dob = request.getParameter("dob");
        String age = request.getParameter("age");
        String licenseNumber = request.getParameter("licenseNumber");
        String creditCardNumber = request.getParameter("creditCardNumber");
        String expirationDate = request.getParameter("expirationDate");
        String securityCode = request.getParameter("securityCode");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {

                String sql = "INSERT INTO Customer (customer_id,registration_id,first_name, last_name, street, city, date_of_birth, drivers_licence_number, credit_card_number, expiration_date, security_code, city, region, zip_code, age) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, "111");
                    preparedStatement.setString(2, "333");
                    preparedStatement.setString(3, firstName);
                    preparedStatement.setString(4, lastName);
                    preparedStatement.setString(5, street);
                    preparedStatement.setString(6, city);
                    preparedStatement.setString(7, region);
                    preparedStatement.setString(8, postalCode);
                    preparedStatement.setString(9, dob);
                    preparedStatement.setString(10, age);
                    preparedStatement.setString(11, licenseNumber);
                    preparedStatement.setString(12, creditCardNumber);
                    preparedStatement.setString(13, expirationDate);
                    preparedStatement.setString(14, securityCode);


                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        out.println("<h2>Registration Summary</h2>");
                        out.println("<p>First Name: " + firstName + "</p>");
                        out.println("<p>Last Name: " + lastName + "</p>");
                        out.println("<p>Street: " + street + "</p>");
                        out.println("<p>City: " + city + "</p>");
                        out.println("<p>Region: " + region + "</p>");
                        out.println("<p>Postal Code: " + postalCode + "</p>");
                        out.println("<p>Date of Birth: " + dob + "</p>");
                        out.println("<p>Age: " + age + "</p>");
                        out.println("<p>Driver's License Number: " + licenseNumber + "</p>");
                        out.println("<p>Credit Card Number: " + creditCardNumber + "</p>");
                        out.println("<p>Expiration Date: " + expirationDate + "</p>");
                        out.println("<p>Security Code: " + securityCode + "</p>");


                        out.println("<p>Registration successful!</p>");
                    } else {
                        out.println("<p>Error occurred during registration</p>");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<p>Error occurred during registration</p>");
        }

        out.println("</body></html>");
    }
}
