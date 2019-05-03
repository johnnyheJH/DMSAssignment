/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Iris-
 */
@Stateless
@LocalBean
public class CustomerSession {

    String sqlQuery;
    ResultSet resultSet;
    Connection connection;
    Statement statement;
    String dbURL = "jdbc:derby://localhost:1527/DMSDB;" + 
                "create=true;user=dms;password=dms2018";
    List<String> list=null;
    Customer customer;

    public CustomerSession() {
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(dbURL);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CustomerSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void createTable() {
        try {
            checkTableExisting("CUSTOMER");
            sqlQuery = "CREATE TABLE CUSTOMER " + "(USERNAME VARCHAR(20) PRIMARY KEY," +
                    " PASSWORD VARCHAR(20))";
            statement.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void register(String username, String password){
                
        try {
            sqlQuery = "INSERT INTO CUSTOMER VALUES" +
                "('"+username+"', '"+password+"')";
            statement.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerSession.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
              connection.close();  
            }   	  	
   	    catch(SQLException se){ }
        }
    }
    
    public boolean login(String username, String password){
        String rightPassword = null;
        try {
            sqlQuery = "SELECT * FROM CUSTOMER WHERE USERNAME = '"+username+"'";
            resultSet = statement.executeQuery(sqlQuery);
            if(resultSet.next()){
                rightPassword = resultSet.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerSession.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
              connection.close();  
            }   	  	
   	    catch(SQLException se){ }
        }
        
        return password.equals(rightPassword);
       
    }
    
    private void checkTableExisting(String newTableName) {
        try {
            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);
            Statement dropStatement = null;

            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                System.out.println("found: " + tableName);
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + "  needs to be deleted");
                    String sqlDropTable = "DROP TABLE " + newTableName;
                    dropStatement = connection.createStatement();
                    dropStatement.executeUpdate(sqlDropTable);
                    System.out.println("table deleted");
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
            if (dropStatement != null) {
                dropStatement.close();
            }

        } catch (SQLException ex) {
        }

    }
}
