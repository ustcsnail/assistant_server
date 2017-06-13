package dbc;


import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection{
    private static final String DBDRIVER="com.mysql.jdbc.Driver";
    private static final String DBURL="jdbc:mysql://localhost:3306/assistant?useUnicode=true&characterEncoding=GBK";
    private static final String DBUSER="root";
    private static final String DBPASSWORD="111111";
    private Connection conn=null;
    public DatabaseConnection()throws Exception {
        try{
            Class.forName(DBDRIVER);
            this.conn=DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
        }
        catch(Exception e){
            throw e;
        }
    } 
    public Connection getConnection(){
        return this.conn;
    }
    public void close() throws Exception{
        if(this.conn!=null){
            try{
                this.conn.close();
            } catch(Exception e){
                throw e;
            }
        }
    }
}
