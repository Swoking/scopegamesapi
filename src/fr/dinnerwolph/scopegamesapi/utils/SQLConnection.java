package fr.dinnerwolph.scopegamesapi.utils;

import fr.dinnerwolph.scopegamesapi.mysql.grade.GradeSQL;
import fr.dinnerwolph.scopegamesapi.mysql.money.ScopeCoinsSQL;
import fr.dinnerwolph.scopegamesapi.mysql.money.ScopeSQL;
import fr.dinnerwolph.scopegamesapi.mysql.perm.PermSQL;

public class SQLConnection {
    public static ScopeSQL scopesql = new ScopeSQL("jdbc:mysql://", "127.0.0.1:3306", "scopegamessql", "root", "sgsp", "Scopes");
    public static ScopeCoinsSQL scopecoinssql = new ScopeCoinsSQL("jdbc:mysql://", "127.0.0.1:3306", "scopegamessql", "root", "sgsp", "ScopeCoins");
    public static GradeSQL gradesql = new GradeSQL("jdbc:mysql://", "127.0.0.1:3306", "scopegamessql", "root", "sgsp", "User");
    public static PermSQL permSQL = new PermSQL("jdbc:mysql://", "127.0.0.1:3306", "scopegamessql", "root", "sgsp", "Perm");

    public static void Connection() {
        gradesql.Connection();
        scopesql.Connection();
        scopecoinssql.Connection();
        permSQL.Connection();
    }

    public static void Deconnection() {
        scopesql.Deconnection();
        scopecoinssql.Deconnection();
        gradesql.Deconnection();
        permSQL.Deconnection();
    }
}