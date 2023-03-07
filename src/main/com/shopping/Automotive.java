package main.com.shopping;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Automotive {
    ArrayList<String> automotiveList = new ArrayList<>();

    public Automotive(){
        try {
            File file = new File("list.txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                if(data.equals("Automotive:")) {
                    break;
                }
            }

            LoadItems(fileScanner);

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void ShowAutomotive(){
        System.out.println("AUTOMOTIVE:");
        for (int i = 0; i < automotiveList.size(); ++i) {
            System.out.println((i+1) + ". " + automotiveList.get(i));
        }
    }


    private void LoadItems(Scanner fileScanner){
        while(fileScanner.hasNextLine()){
            String data = fileScanner.nextLine();
            automotiveList.add(data);
        }
    }
}
