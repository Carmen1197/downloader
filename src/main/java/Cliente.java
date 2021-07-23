
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author milu
 */
public class Cliente {
    
    public static void main(String[] args){
        
        JFrame panel = new JFrame("Bienvenido");
        panel.setSize(600,500);
        panel.setLayout(new BoxLayout(panel.getContentPane(), BoxLayout.Y_AXIS));
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel Titulo = new JLabel("Bienvenido al sistema Descargador");
        Titulo.setFont(new Font("Arial", Font.BOLD, 25));
        Titulo.setBorder(new EmptyBorder(20,0,10,0));
        Titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel text =new JPanel ();
        JLabel Titu = new JLabel("Escriba el nombre del archivo:");
        Titu.setFont(new Font("Arial", Font.BOLD, 25));
        Titu.setBorder(new EmptyBorder(20,0,10,0));
        Titu.setAlignmentX(Component.CENTER_ALIGNMENT);
        
       /* JPanel PTexto = new JPanel();
        JTextField texto = new JTextField();
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setPreferredSize(new Dimension(50, 40));
        texto.setFont(new Font("Arial", Font.BOLD, 14));
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);*/
       
       JPanel ptext = new JPanel();
       JTextField texto = new JTextField();
       texto.setPreferredSize(new Dimension(500, 40));
       texto.setFont(new Font("Arial", Font.BOLD, 14));
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);
      
        JPanel Pboton = new JPanel();
        Pboton.setBorder(new EmptyBorder(75,0,10,0));
        JButton button = new JButton("Descargar");
        button.setPreferredSize(new Dimension(150, 75));
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.add(Titu);
        ptext.add(texto);
        Pboton.add(button);
        
        Scanner sc = new Scanner(System.in);
        String ip = JOptionPane.showInputDialog("Ingresa la ip");
          button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nombre = texto.getText();
                if (texto.getText().isEmpty()){
                     Titu.setText("¡¡¡Escriba el nombre del archivo primero!!!");
                }else {
                    try {
             
                         Socket SckClient = new Socket(ip, 5000);

                         DataOutputStream Output = new DataOutputStream(SckClient.getOutputStream());

                         byte[] ArrayBytes = new byte[1024];
                         BufferedInputStream BuffInput = new BufferedInputStream(SckClient.getInputStream());

                         DataInputStream DInputS = new DataInputStream(SckClient.getInputStream());

                         Output.writeUTF(nombre);

                         String Directorio = DInputS.readUTF();
                       
                        BufferedOutputStream BuffOutput = new BufferedOutputStream(new FileOutputStream(Directorio)); //rut en ves de Directorii
                        int ValorNum = BuffInput.read(ArrayBytes);
                        BuffOutput.write(ArrayBytes, 0, ValorNum);

                         while ((ValorNum = BuffInput.read(ArrayBytes)) != -1) {
                             if (ValorNum == -1) {
                                  break;
                                }
                        BuffOutput.write(ArrayBytes, 0, ValorNum);
                        System.out.println(ValorNum);
            }

            BuffOutput.close();
            DInputS.close();

            JOptionPane.showMessageDialog(null, "se ha enviado el archivo con exito");

            panel.dispose();
        } catch (Exception ValorExcecion) {
            System.err.println(ValorExcecion.getCause());

        }

     }
                }
                 
   });
      
        
        panel.add(Titulo);
        panel.add(text);
        panel.add(ptext);
        panel.add(Pboton);
        panel.setVisible(true);
   
    }
    
}
