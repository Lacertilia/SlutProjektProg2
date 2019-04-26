import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

/**
 *
 * @author Rasmus
 */
public class Server {

    public static final String DEFAULT_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static String hostname = LoginInfo.getHostName();
    public static String dbName = LoginInfo.getDbName();
    public static int dbPort = 3306;
    public static final String DEFAULT_URL = "jdbc:mysql://"+ hostname +":"+dbPort+"/"+dbName;
    private static final String DEFAULT_USERNAME = LoginInfo.getUsername();
    private static final String DEFAULT_PASSWORD = LoginInfo.getPassword();


    public static void main(String[] args){
        int port = 1234;
        ServerSocket serverSocket;
        Socket socket;
        System.out.println("Server started.");

        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for connections!");
                socket = serverSocket.accept();
                // Go
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Client connected!");

                //Protocol
                String operation = in.readLine();
                String name = in.readLine();

                Connection connection = null;

                try {
                    Class.forName(DEFAULT_DRIVER_CLASS);
                    connection = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);

                    // SQL queries goes here
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    if(operation.equals("Start")){
                        ps = connection.prepareStatement("SELECT * FROM user (username) WHERE ('" + name + "')");
                        rs = ps.executeQuery();

                        while (rs.next()) {
                            String firstName = rs.getString("Fname");
                            String address = rs.getString("Address");
                            System.out.println(firstName + " , " + address);
                        }

                    }

                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                } catch (Exception e) {

                }

                if(operation.equals("Save")){

                }
            }
        } catch (Exception e) {
            System.out.println("Server fail");
        }
    }
}