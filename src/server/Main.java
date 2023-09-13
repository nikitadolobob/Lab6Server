package server;

import json.JSONInput;
import model.Movie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Movie> arrayList = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Server is running");

        File file;
        if(args.length > 0){
            file = new File(args[0]);
            if(file.exists()){
                JSONInput ji = new JSONInput(args[0]);
                ji.readJSON();
            }
            else {
                System.out.println("The file the path is refering to does not exist");
                System.exit(-1);
            }
        }
        else{
            file = new File("file.json");
            if(!file.exists()){
                try {
                    boolean success = file.createNewFile();
                    if(!success){
                        System.out.println("File failed to reproduce");
                        System.exit(-1);
                    }

                } catch (IOException e) {
                    System.out.println("File failed to reproduce");
                    System.exit(-1);
                }
            }
            else{
                JSONInput ji = new JSONInput("file.json");
                ji.readJSON();
            }
        }
        ClientModifaer clientModifaer = new ClientModifaer(file.getAbsolutePath());
        clientModifaer.receive();
    }
}