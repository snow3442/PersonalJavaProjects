package travelagency;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    private Connection taConnection;
    private PreparedStatement stmt = null;

    public DataBase() throws SQLException {
        initializeDB();
    }

    private void initializeDB() {
        try {
            taConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/TravelAgencyDB", "snow3442", "330006");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Connection failed");
        }
    }

    public ResultSet getResultByName(String packageName) throws SQLException {
        return ResultByImageUrl(packageName);
    }

    private ResultSet ResultByName(String packageName) throws SQLException {
        stmt = taConnection.prepareStatement("SELECT * FROM bookinginfo WHERE name = ?");
        stmt.setString(1, packageName);
        ResultSet rset = stmt.executeQuery();
        return rset;
    }

    public ResultSet getResultByImageUrl(String imageUrl) throws SQLException {
        return ResultByImageUrl(imageUrl);
    }

    private ResultSet ResultByImageUrl(String imageUrl) throws SQLException {
        stmt = taConnection.prepareStatement("SELECT * FROM bookinginfo WHERE imageurl = ?");
        stmt.setString(1, imageUrl);
        ResultSet rset = stmt.executeQuery();
        return rset;
    }

    public Connection getTaConnection() {
        return taConnection;
    }

    public void setTaConnection(Connection taConnection) {
        this.taConnection = taConnection;
    }

}
