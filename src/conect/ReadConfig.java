/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Егор
 */
public class ReadConfig {
    //файл, который хранит свойства нашего проекта
    private String url;
    private String username;
    private String password; 

    public ReadConfig() throws IOException {
    }

    
    File file = new File("./config.properties");

//создаем объект Properties и загружаем в него данные из файла.
    Properties properties = new Properties();
    
    public void getData() throws IOException{
        try {
            properties.load(new FileReader(file));
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    
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
