package fr.dinnerwolph.scopegamesapi.mysql.grade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GradeSQL {
    private String url_base;
    private String host;
    private String name;
    private String username;
    private String password;
    private String table;
    private Connection connection;

    public GradeSQL(String url_base, String host, String name, String username, String password, String table) {
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
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT `rank_id` FROM `User` WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                statement.close();
                statement = this.getConnection().prepareStatement("INSERT INTO `User`(`uuid`, `rank_id`) VALUES (?,?)");
                statement.setString(1, uuid.toString());
                statement.setInt(2, 1);
                statement.executeUpdate();
                statement.close();
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public String getRank(UUID uuid) {
        String rank = "INCONNUS AU BATAILLON";
        try {
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT `rank_id` FROM `User` WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int rank_id = resultSet.getInt("rank_id");
                statement.close();
                statement = this.getConnection().prepareStatement("SELECT `rank_name` FROM `Rank` WHERE `id_rank`=?");
                statement.setInt(1, rank_id);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    rank = resultSet.getString("rank_name");
                }
                statement.close();
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return rank;
    }

    public Integer getRankId(UUID uuid) {
        Integer rank = 0;
        try {
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT `rank_id` FROM `User` WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rank = resultSet.getInt("rank_id");
                statement.close();
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return rank;
    }

    public void setRank(UUID uuid, Integer rankid) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement("UPDATE `User` SET `rank_id`=? WHERE `uuid`=?");
            statement.setInt(1, rankid);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public String getRankPrefix(UUID uuid) {
        String prefix = "INCONNUS AU BATAILLON";
        int rank = this.getRankId(uuid);
        try {
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT `rank_prefix` FROM `Rank` WHERE `id_rank`=?");
            statement.setInt(1, rank);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                prefix = resultSet.getString("rank_prefix");
            }
            statement.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return prefix;
    }

    public boolean Baned(UUID uuid) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT `ban` FROM `User` WHERE `uuid`=?");
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("ban") == 1) {
                    statement.close();
                    return true;
                }
                statement.close();
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}