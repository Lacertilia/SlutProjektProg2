import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Rasmus
 */
public class DBConnect {

    public void connect(String name, String operation) {
        String ip = "localhost";
        int port = 1234;
        Socket socket = null;

        try {
            socket = new Socket(ip,port);
        } catch (Exception e) {
            System.out.println("Client failed to connect");
            System.exit(0);
        }

        // GO
        if(operation.equals("Start")){
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println("Start");
                out.println(name);

                in.close();
                out.close();
                socket.close();
                System.out.println("Done!");
            } catch (Exception e) {
                System.out.println("Client failed to communicate");
            }
        }
        if(operation.equals("Save")){
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println("Save");
                out.println(name);

                in.close();
                out.close();
                socket.close();
                System.out.println("Done!");
            } catch (Exception e) {
                System.out.println("Client failed to communicate");
            }
        }

    }
}
