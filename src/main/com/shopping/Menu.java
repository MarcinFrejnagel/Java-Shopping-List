package main.com.shopping;

import main.com.shopping.Category;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public ShoppingList shoppingList;
    private final Scanner scanner;

    public Menu(){
        shoppingList = new ShoppingList();
        scanner = new Scanner(System.in);
        try {
            LoadListFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("Load from file failed");
        }
    }

    public void MenuHandler(){
        int choice = 1;
        while(choice != 7) {
            ShowActions();

            choice = getChoice(7);
            HandleChoice(choice);
        }
    }

    private void LoadListFromFile() throws FileNotFoundException {
        File file = new File("shopping_list.txt");
        if(file.exists()){
            Scanner fileScanner = new Scanner(file);

            FindInFile("Food:", fileScanner);

            LoadFromFileToList(Category.FOOD, "Chemicals:", fileScanner);
            LoadFromFileToList(Category.CHEMICALS, "Automotive:", fileScanner);
            LoadAutomotiveFromFile(fileScanner);
        }
    }

    private void LoadAutomotiveFromFile(Scanner fileScanner){
        ArrayList<String> list = new ArrayList<>();
        while(fileScanner.hasNextLine()){
            String data = fileScanner.nextLine();
            list.add(data);
        }
        shoppingList.shopping.put(Category.AUTOMOTIVE, list);
    }

    private void LoadFromFileToList(Category category, String limit, Scanner fileScanner){
        ArrayList <String> list = new ArrayList<>();
        while(fileScanner.hasNextLine()){
            String data = fileScanner.nextLine();
            if(data.equals(limit)){
                break;
            }
            list.add(data);
        }
        shoppingList.shopping.put(category, list);
    }

    private void FindInFile(String lineToFind, Scanner fileScanner){
        while (fileScanner.hasNextLine()) {
            String data = fileScanner.nextLine();
            if(data.equals(lineToFind)) {
                break;
            }
        }
    }

    private void HandleChoice(int choice){
        switch(choice){
            case 1:
                AddProduct();
                break;
            case 2:
                shoppingList.ShowList();
                break;
            case 3:
                ShowAllProductsCategory();
                break;
            case 4:
                shoppingList.Clear();
                break;
            case 5:
                DeleteAllCategory();
                break;
            case 6:
                DeleteProduct();
                break;
            case 7:
                SaveList();
                break;
        }
    }

    private void HandleShowAllProductsCategory(int choice){
        switch (choice){
            case 1:
                shoppingList.ShowCategory(Category.FOOD);
                break;
            case 2:
                shoppingList.ShowCategory(Category.CHEMICALS);
                break;
            case 3:
                shoppingList.ShowCategory(Category.AUTOMOTIVE);
                break;
        }
    }

    private void ShowAllAutomotive() {
        shoppingList.automotive.ShowAutomotive();
        int choice = getChoice(shoppingList.automotive.automotiveList.size());
        shoppingList.Add(Category.AUTOMOTIVE, choice-1);
    }

    private void ShowAllChemicals() {
        shoppingList.chemicals.ShowChemicals();
        int choice = getChoice(shoppingList.chemicals.chemicalsList.size());
        shoppingList.Add(Category.CHEMICALS, choice-1);
    }

    private void ShowAllFood() {
        shoppingList.food.ShowFood();
        int choice = getChoice(shoppingList.food.foodList.size());
        shoppingList.Add(Category.FOOD, choice-1);
    }

    private void AddProduct(){
        ShowCategories();
        int choice = getChoice(3);
        HandleAddProductCategoryChoice(choice);
    }

    private void HandleAddProductCategoryChoice(int choice) {
        switch(choice){
            case 1:
                ShowAllFood();
                break;
            case 2:
                ShowAllChemicals();
                break;
            case 3:
                ShowAllAutomotive();
                break;
        }
    }

    private void ShowAllProductsCategory(){
        ShowCategories();
        int choice = getChoice(3);
        HandleShowAllProductsCategory(choice);
    }

    private void DeleteAllCategory(){
        ShowCategories();
        int choice = getChoice(3);
        HandleDeleteCategoryChoice(choice);
    }

    private void HandleDeleteCategoryChoice(int choice) {
        switch(choice){
            case 1:
                shoppingList.shopping.remove(Category.FOOD);
                break;
            case 2:
                shoppingList.shopping.remove(Category.CHEMICALS);
                break;
            case 3:
                shoppingList.shopping.remove(Category.AUTOMOTIVE);
                break;
        }
    }



    private void DeleteProduct(){
        ShowCategories();
        int choice = getChoice(3);
        HandleDeleteProduct(choice);
    }

    private void HandleDeleteProduct(int choice) {
        switch(choice){
            case 1:
                HandleDeleteProductFromFood();
                break;

            case 2:
                HandleDeleteProductFromChemicals();
                break;

            case 3:
                HandleDeleteProductFromAutomotive();
                break;
        }
    }

    private void HandleDeleteProductFromFood(){
        shoppingList.ShowCategory(Category.FOOD);
        if(shoppingList.shopping.containsKey(Category.FOOD)) {
            int choice2 = getChoice(shoppingList.shopping.get(Category.FOOD).size());
            shoppingList.DeleteProductFromCategory(Category.FOOD, choice2-1);
        }
    }

    private void HandleDeleteProductFromChemicals(){
        shoppingList.ShowCategory(Category.CHEMICALS);
        if(shoppingList.shopping.containsKey(Category.CHEMICALS)) {
            int choice2 = getChoice(shoppingList.shopping.get(Category.CHEMICALS).size());
            shoppingList.DeleteProductFromCategory(Category.CHEMICALS, choice2-1);
        }
    }

    private void HandleDeleteProductFromAutomotive(){
        shoppingList.ShowCategory(Category.AUTOMOTIVE);
        if(shoppingList.shopping.containsKey(Category.AUTOMOTIVE)) {
            int choice2 = getChoice(shoppingList.shopping.get(Category.AUTOMOTIVE).size());
            shoppingList.DeleteProductFromCategory(Category.AUTOMOTIVE, choice2-1);
        }
    }

    private void SaveList(){
        File file = new File("shopping_list.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }else{
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write("");
                    writer.close();
                } catch (IOException e) {
                    System.out.println("An error occured while cleaning up the file: " + e.getMessage());
                }
            }

            SaveListToFile();

        } catch (IOException e) {
            System.out.println("An error occured while creating file " + e.getMessage());
        }
    }

    private void SaveListToFile() {
        try {
            FileWriter writer = new FileWriter("shopping_list.txt");
            SaveFoodToFile(writer);
            SaveChemicalsToFile(writer);
            SaveAutomotiveToFile(writer);
            writer.close();
            System.out.println("\nShopping list saved to file");
        } catch (IOException e) {
            System.out.println("An error occured while saving shopping list to file");
            e.printStackTrace();
        }
    }

    private void SaveFoodToFile(FileWriter writer) throws IOException {
        writer.write("Food:\n");

        if(shoppingList.shopping.containsKey(Category.FOOD)) {
            ArrayList<String> temp = shoppingList.shopping.get(Category.FOOD);
            for(int i = 0; i < temp.size(); ++i){
                writer.write(temp.get(i) + "\n");
            }
        }
    }

    private void SaveChemicalsToFile(FileWriter writer) throws IOException {
        writer.write("Chemicals:\n");

        if(shoppingList.shopping.containsKey(Category.CHEMICALS)) {
            ArrayList<String> temp = shoppingList.shopping.get(Category.CHEMICALS);
            for(int i = 0; i < temp.size(); ++i){
                writer.write(temp.get(i) + "\n");
            }
        }
    }

    private void SaveAutomotiveToFile(FileWriter writer) throws IOException {
        writer.write("Automotive:\n");

        if(shoppingList.shopping.containsKey(Category.AUTOMOTIVE)) {
            ArrayList<String> temp = shoppingList.shopping.get(Category.AUTOMOTIVE);
            for(int i = 0; i < temp.size(); ++i){
                writer.write(temp.get(i) + "\n");
            }
        }
    }

    private void ShowCategories(){
        System.out.println("Choose category:");
        System.out.println("1 - Food");
        System.out.println("2 - Chemicals");
        System.out.println("3 - Automotive");
    }
    private void ShowActions(){
        System.out.println("Choose action:");
        System.out.println("1 - Add product");
        System.out.println("2 - Show all products on shopping list");
        System.out.println("3 - Show all products on shopping list of specific category");
        System.out.println("4 - Delete all products from shopping list");
        System.out.println("5 - Delete all products from shopping list of specific category");
        System.out.println("6 - Delete product from shopping list");
        System.out.println("7 - Save shopping list and exit");
    }


    private int getChoice(int max){
        int result;

        while(true) {
            CheckForNumber();
            result = scanner.nextInt();
            if(result >= 1 && result <= max) {
                break;
            }
            System.out.println("Incorrect choice - maximum possible is: " + max + "\nMinimum is: " + 1);
        }

        System.out.println("");
        return result;
    }

    private void CheckForNumber(){
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a number");
            scanner.next();
        }
    }
}
