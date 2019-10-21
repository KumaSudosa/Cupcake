package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 @author Gruppe 3
 */
public class DB {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static Connection conn;

    public static Connection getConnection() throws SQLException {

        // Gather information for connection for the propeties file.
        String propertiesFilePath = propertiesFilePath();

        try (FileInputStream f = new FileInputStream(propertiesFilePath)) {
            Properties pros = new Properties();
            pros.load(f);
            URL = pros.getProperty("url");
            USER = pros.getProperty("user");
            PASSWORD = pros.getProperty("password");
        } catch (IOException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        conn = DriverManager.getConnection(URL, USER, PASSWORD);

        return conn;
    }

    /**
     * This code is an abomination unto all that is holy about programming, born by a mix of sickness, suffering, cheap soda and immense anger, and is the most crude method of which the problem of finding the path to the db.properties file could be solved. This would have been so much easier, and better in every way, if it had been solved by the use of a classLoader or ClassPath method, but since we do not know how to use those, smashing the head against the keyboard was the best solution available.
     *
     * @author by much pain and suffering, Michael N. Korsgaard
     * @return a string of the path for the db.properties file.
     */
    public static String propertiesFilePath() {
        String[] classPath = DB.class.getResource("").toString().split("/");
        String path = "";
        for (String string : classPath) {
            if (!string.equals("file:")) {
                path += string + "\\";
            }
            if (string.equals("CupcakeProject")) {
                break;
            }
        }
        path += "\\src\\main\\java\\persistence\\db.properties";
        return path;
    }
}
