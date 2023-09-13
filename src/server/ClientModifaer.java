package server;

import Commands.Command;
import Commands.Receiver;
import json.JSONOutput;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ClientModifaer {
    private String fileName;

    public ClientModifaer(String fileName){
        this.fileName = fileName;
    }

    public void receive(){

        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(9875);
        } catch (SocketException e) {
            System.out.println("can not start server");
            System.exit(0);
        }

        while(true) {
            try {
                if(System.in.available() > 0){
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();
                    if(line.equals("exit")) {
                        JSONOutput jot = new JSONOutput(fileName);
                        jot.writeJSON();
                        System.exit(0);
                    }
                    else if (line.equals("save")){
                        JSONOutput jot = new JSONOutput(fileName);
                        jot.writeJSON();
                    }
                }
            } catch (IOException e) {
                System.out.println("Something went wrong while realising whether or not the server require to stop");
            }
            try {
                // Reading the request (no connection in UDP)
                byte[] array = new byte[4096];

                DatagramPacket datagramPacket = new DatagramPacket(array, array.length);

                datagramSocket.setSoTimeout(100);
                try{
                    datagramSocket.receive(datagramPacket);

                }catch(SocketTimeoutException e){
                   continue;
                }

                ByteArrayInputStream bais = new ByteArrayInputStream(datagramPacket.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);

                Command command = (Command) ois.readObject();
                System.out.println("recieved from client");

                // Proccessing the command
                Receiver receiver = new Receiver(command.runCommand(Main.arrayList));

                //Sending results to the client
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(receiver);


                byte[] message = baos.toByteArray();
                DatagramPacket packet = new DatagramPacket(message, message.length, datagramPacket.getAddress(), datagramPacket.getPort());
                datagramSocket.send(packet);
                System.out.println("Message's sent to client");


            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
                System.out.println("Something went wrong while receiving data from a client");
            }
        }

    }

}
