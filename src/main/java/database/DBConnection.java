package database;

import entity.Account;
import entity.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBConnection {
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class);
    private Connection conn = null;
    private ResultSet rs = null;

    private static PreparedStatement loadAllRoles;
    private static PreparedStatement loadAllLogins;
    private static PreparedStatement loadAccountByLogin;
    private static PreparedStatement loadRolesById;
    private static PreparedStatement getIdAccountRoles;


    public DBConnection(String url, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            loadPreparedStatements();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException - > DBConnection(String url); " + e);
        }
    }

    private void loadPreparedStatements() {
        try {
            loadAllRoles = conn.prepareStatement("SELECT * FROM roles");
            loadAllLogins = conn.prepareStatement("SELECT login, id FROM account");
            loadAccountByLogin = conn.prepareStatement("SELECT * FROM account WHERE login = ?");
            loadRolesById = conn.prepareStatement("SELECT * FROM account_role WHERE id =?");
            getIdAccountRoles = conn.prepareStatement("SELECT id_role FROM account_role WHERE id_account = ?");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void closePreparedStatements() {
        try {
            loadAllRoles.close();
            loadAllLogins.close();
            loadAccountByLogin.close();
            loadRolesById.close();
            getIdAccountRoles.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Role> getAllRoles() {
        rs = null;
        List<Role> result = new LinkedList<Role>();
        try {
            rs = loadAllRoles.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setId(rs.getInt("id_roles"));
                r.setName(rs.getString("name_roles"));
                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public List<Account> getAllLogins() {
        rs = null;
        List<Account> result = new LinkedList<Account>();
        try {
            rs = loadAllLogins.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("login"));
                account.setId(rs.getInt("id"));
                //account.setPassword(rs.getString("password"));
                result.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public Account getAccountByLogin(String login) {
        rs = null;
        // SELECT * FROM account WHERE login = ?
        Account result = new Account();
        try {
            loadAccountByLogin.setNString(1, login);
            rs = loadAccountByLogin.executeQuery();

            while (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setUsername(rs.getString("login"));
                result.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public List<Role> getRolesById(int id) {
        rs = null;
        List<Role> result = new LinkedList<Role>();
        try {
            //SELECT * FROM account_role WHERE id =?
            loadRolesById.setInt(1, id);
            rs = loadRolesById.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id_account"));
                role.setName(rs.getString("id_role"));
                result.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void close() {
        closePreparedStatements();
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            LOGGER.info("close() exeption " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Integer> getIdAccountRoles(int idAccount) {
        rs = null;
        List<Integer> idAccountRoles = new ArrayList<Integer>();
        try {
            getIdAccountRoles.setInt(1, idAccount);
            rs = getIdAccountRoles.executeQuery();

            while (rs.next()) {
                idAccountRoles.add(rs.getInt("id_role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAccountRoles;
    }


}
