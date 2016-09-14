package fr.dinnerwolph.scopegamesapi.mysql.money;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ScopeCoinsSQL {
    private String url_base;
    private String host;
    private String name;
    private String username;
    private String password;
    private String table;
    private Connection connection;

    public ScopeCoinsSQL(String url_base, String host, String name, String username, String password, String table) {
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

    public void createAccount(UUID uuid) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT money FROM " + this.table + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                statement.close();
                statement = this.getConnection().prepareStatement("INSERT INTO " + this.table + "(uuid, money) VALUES (?,?)");
                statement.setString(1, uuid.toString());
                statement.setInt(2, 50);
                statement.executeUpdate();
                statement.close();
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Integer getScopes(UUID uuid) {
        int money = 50;
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement("SELECT 'money' FROM " + this.table + " WHERE uuid=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultSet.first();
                money = resultSet.getInt("money");
                preparedStatement.close();
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return money;
    }

    public void addScopes(UUID uuid, int money) {
        money += this.getScopes(uuid).intValue();
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement("UPDATE " + this.table + " SET `money`=? WHERE uuid=?");
            preparedStatement.setInt(1, money);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void removeScopes(UUID uuid, int money) {
        money = this.getScopes(uuid) - money;
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement("UPDATE `Scopes` SET `money`=? WHERE uuid=?");
            preparedStatement.setInt(1, money);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}