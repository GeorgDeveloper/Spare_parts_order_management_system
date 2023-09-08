/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.AdminSetting;
import view.CustomerView;

/**
 *
 * @author Егор
 * Класс подключения к БД
 */
public class ConnectDb {

    // + добавить запрос имени БД
    
    private Connection Con = null;
    private String url;
    private String username;
    private String password;

    public ConnectDb() {
        try {
            ReadConfig config = new ReadConfig();
            config.getData();
            url = config.getUrl();
            username = config.getUsername();
            password = config.getPassword();
        } catch (IOException ex) {
            Logger.getLogger(ConnectDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getCon() throws SQLException {
        Con = DriverManager.getConnection(url, username, password);
        return Con;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    

}
