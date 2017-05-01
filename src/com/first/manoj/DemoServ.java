package com.first.manoj;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

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

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        String keyName = request.getParameter("key");
        printWriter.println(keyName);

        String passPhrase = request.getParameter("passphrase");
        printWriter.println(passPhrase);

        String cPassPhrase = request.getParameter("cpassphrase");
        printWriter.println(cPassPhrase);

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";

        if(br != null) {
            json = br.readLine();
        }

        printWriter.println(json);
        /*
        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        // 3. Convert received JSON to Article
        try {
            keyPhrase = mapper.readValue(json, KeyPhrase.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }

        // 4. Set response type to JSON
        response.setContentType("application/json");

        // 6. Send List<Article> as JSON to client
        mapper.writeValue(keyPhrase);*/


        if (passPhrase.equals(cPassPhrase)) {

            String command[] = {"ssh-keygen", "-t", "rsa"};
            Process process = new ProcessBuilder(command).start();

            OutputStream outputStream = process.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println(keyName);
            printStream.flush();
            printStream.println("y");
            printStream.flush();
            printStream.println(passPhrase);
            printStream.flush();
            printStream.println(passPhrase);
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
