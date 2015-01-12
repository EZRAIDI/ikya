package partie4;

import java.net.*;
import java.io.*;

public class GestionRequete implements Runnable 
{
    Socket socket;

    public GestionRequete(Socket socket) throws Exception 
    {
        this.socket = socket;
    }

    @Override
    public void run() 
    {
        try 
        {
            processRequest();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }

    }   

    private void processRequest() throws Exception 
    {
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String requestLine = br.readLine();
        
        String[] tokens = requestLine.split(" ");
        String fileName = tokens[1];

        fileName = "." + fileName;

        FileInputStream fis = null;
        boolean fileExists = true;

        try 
        {
            fis = new FileInputStream(fileName);
        } 
        catch (FileNotFoundException e) 
        {
            fileExists = false;
        }

        if (fileExists) 
        {
            System.out.println("Le fichier est bien present");
            sendBytes(fis, os);
            fis.close();
            System.out.println("Le fichier a bien ete envoye");
        } 
        else 
        {
           System.out.println("Non je n ai pas ce fichier");
        }

        os.close();
        br.close();
        socket.close();

    }

    private static void sendBytes(FileInputStream fis, OutputStream os)
            throws Exception 
            {
        
        byte[] buffer = new byte[1024];
        int bytes = 0;
        
        while ((bytes = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytes);
        }
    }

}
