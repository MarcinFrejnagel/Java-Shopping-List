package main.com.shopping;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Chemicals {
    ArrayList<String> chemicalsList = new ArrayList<>();

    public Chemicals(){
        try {
            File file = new File("list.txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                if(data.equals("Chemicals:")) {
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

    public void ShowChemicals(){
        System.out.println("CHEMICALS:");
        for (int i = 0; i < chemicalsList.size(); ++i) {
            System.out.println((i+1) + ". " + chemicalsList.get(i));
        }
    }


    private void LoadItems(Scanner fileScanner){
        while(true){
            String data = fileScanner.nextLine();
            if(data.equals("Automotive:")){
                break;
            }else{
                chemicalsList.add(data);
            }
        }
    }
}
