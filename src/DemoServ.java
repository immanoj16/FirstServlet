import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by kanhu on 27/4/17.
 */

@WebServlet(name = "DemoServ")
public class DemoServ extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        String keyName = request.getParameter("key");
        printWriter.println("Key name: " + keyName);

        String passphrase = request.getParameter("passphrase");
        printWriter.println("Passphrase: " + passphrase);

        String cpassphrase = request.getParameter("cpassphrase");
        printWriter.println("Confirm passphrase: " + cpassphrase);

        // Runtime.getRuntime().exec("/usr/bin/gnome-terminal ls -la");

        // String command= "/usr/bin/gnome-terminal" + "ssh-keygen -t rsa";
        // Runtime rt = Runtime.getRuntime();
        // Process pr = rt.exec(new String[]{"/usr/bin/gnome-terminal", "-c", "exit"});

        String[] commands = new String[]{"ssh-keygen", "-t", "rsa"};

        ProcessBuilder processBuilder = new ProcessBuilder(commands);

        Process process = processBuilder.start();
        OutputStream outputStream = process.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        printStream.println(keyName);
        printStream.flush();
        printStream.println("y");
        printStream.flush();
        printStream.println(passphrase);
        printStream.flush();
        printStream.println(cpassphrase);
        printStream.flush();

        printWriter.close();
    }
}
