
import java.net.*;
import java.io.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author milu
 */
public class Servidor {
    
     public static void main(String[] args)
    {
    
        ServerSocket SckServer; 
        Socket Sck; 
        
        DataInputStream Input;
        DataOutputStream Output;
        
        BufferedInputStream BuffInput; 
        BufferedOutputStream BuffOutput; 
        
        int ValorNum; 
        byte[] ArrayBytesServer; 
        
        //Fichero a transferir                 '
        final String directorio = "//../home/milu/Downloads/"; 
        
      
        try
        {
            try
            {
              
                SckServer = new ServerSocket(5000);
                Sck = SckServer.accept();
                           
              Input = new DataInputStream(Sck.getInputStream());
              Output = new DataOutputStream(Sck.getOutputStream());
              String nombre = Input.readUTF();
                           
              System.out.println(nombre);
              
                           
                final File DirectorioDeArchi = new File(directorio+nombre);          
                
                BuffInput = new BufferedInputStream(new FileInputStream(DirectorioDeArchi));
                BuffOutput = new BufferedOutputStream(Sck.getOutputStream());
                
                //El nombre del fichero se envia  
                
                Output.writeUTF(DirectorioDeArchi.getName());
                
                //El fichero se envia
                ArrayBytesServer = new byte[8192];
            
                  while ((ValorNum = BuffInput.read(ArrayBytesServer)) != -1)
                  {
                    BuffOutput.write(ArrayBytesServer, 0, ValorNum);
                  }

                
                  BuffInput.close();
                  BuffOutput.close();

            }//Cierre del try interno
            catch(Exception ValorExcepcion)
            {
                System.err.println(ValorExcepcion);
            
            }//Cierre del catch interno
        
        }//Cierre del try principal
        catch(Exception ValorExcepcion)
        {
            System.err.println(ValorExcepcion);
            
        }//Cierre del catch principal
        
        
    }
 
}
