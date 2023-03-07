package main.com.shopping;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Food {
    ArrayList<String> foodList = new ArrayList<>();

    public Food(){
        try {
            File file = new File("list.txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                if(data.equals("Food:")) {
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

    public void ShowFood(){
        System.out.println("Food:");
        for (int i = 0; i < foodList.size(); ++i) {
            System.out.println((i + 1) + ". " + foodList.get(i));
        }
    }


    private void LoadItems(Scanner fileScanner){
        while(true){
            String data = fileScanner.nextLine();
            if(data.equals("Chemicals:")){
                break;
            }else{
                foodList.add(data);
            }
        }
    }
}
