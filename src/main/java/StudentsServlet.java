import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.PreparedStatement ;
import java.sql.ResultSet ;
import java.sql.SQLException ;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/university";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "2121";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Student> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "SELECT * FROM students";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String course = resultSet.getString("course");
                    students.add(new Student(id, name, course));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("students", students);
        request.getRequestDispatcher("templates/students.jsp").forward(request, response);
    }
}

