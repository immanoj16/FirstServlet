package com.first.manoj;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by kanhu on 27/4/17.
 */

@WebServlet(name = "com.first.manoj.DemoServ")
public class DemoServ extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        String keyName = request.getParameter("key");

        String passphrase = request.getParameter("passphrase");

        String cpassphrase = request.getParameter("cpassphrase");

        new ToJSON();


        if (passphrase.equals(cpassphrase)) {

            String command[] = {"ssh-keygen", "-t", "rsa"};
            Process process = new ProcessBuilder(command).start();

            OutputStream outputStream = process.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println(keyName);
            printStream.flush();
            printStream.println("y");
            printStream.flush();
            printStream.println(passphrase);
            printStream.flush();
            printStream.println(passphrase);
            printStream.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String cOutput;
            while ((cOutput = bufferedReader.readLine()) != null)
                System.out.println(cOutput);
            printWriter.println("Successfully key is created...");
        }
        else {
            printWriter.println("passphrase is not matched...");
        }
        printWriter.close();
    }
}
