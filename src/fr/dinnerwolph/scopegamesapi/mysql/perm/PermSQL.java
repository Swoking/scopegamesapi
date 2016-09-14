package fr.dinnerwolph.scopegamesapi.mysql.perm;

import fr.dinnerwolph.scopegamesapi.mysql.grade.GradeSQL;
import fr.dinnerwolph.scopegamesapi.utils.SQLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PermSQL {
    private String url_base;
    private String host;
    private String name;
    private String username;
    private String password;
    private String table;
    private Connection connection;

    public PermSQL(String url_base, String host, String name, String username, String password, String table) {
        this.url_base = url_base;
        this.host = host;
        this.name = name;
        this.username = username;
        this.password = password;
        this.table = table;
    }

    public void Connection() {
        if (!this.isConnected()) {
            try {
                this.connection = DriverManager.getConnection(this.url_base + this.host + "/" + this.name, this.username, this.password);
            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void Deconnection() {
        if (this.isConnected()) {
            try {
                this.connection.close();
            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    private boolean isConnected() {
        try {
            if (this.connection == null || this.connection.isClosed() || !this.connection.isValid(5)) {
                return false;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public boolean hasPermission(UUID uuid, String permission) {
        try {
            String perm;
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT `perm` FROM `RankPerm` WHERE `Rank_id`=?");
            statement.setInt(1, SQLConnection.gradesql.getRankId(uuid));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                perm = resultSet.getString("perm");
                if (!perm.equals(permission)) continue;
                statement.close();
                return true;
            }
            statement.close();
            statement = this.getConnection().prepareStatement("SELECT `perm` FROM `Perm` WHERE `uuid`=?");
            statement.setString(1, uuid.toString());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                perm = resultSet.getString("perm");
                if (!perm.equals(permission)) continue;
                statement.close();
                return true;
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}