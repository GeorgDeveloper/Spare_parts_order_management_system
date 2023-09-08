/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spare_parts_order_management_system;

import conect.ConnectDb;
import java.sql.SQLException;
import view.Hello_menu;

/**
 *
 * @author Егор
 */
public class Spare_parts_order_management_system {

    /**
     * @param args the command line arguments
     * Запуск программы
     */
    public static void main(String[] args) throws SQLException {
        new Hello_menu().start();
        
    }
    
}
