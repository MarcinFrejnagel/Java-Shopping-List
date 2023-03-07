package main.com.shopping;

import main.com.shopping.Category;
import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingList{

    public Food food;
    public Chemicals chemicals;
    public Automotive automotive;
    public HashMap<Category, ArrayList<String>> shopping;

    public ShoppingList() {
        food = new Food();
        chemicals = new Chemicals();
        automotive = new Automotive();
        shopping = new HashMap<>();
    }

    public void Add(Category cat, int index) {
        if (cat == Category.FOOD) {
            AddFood(food.foodList.get(index));
        } else if (cat == Category.CHEMICALS) {
            AddChemicals(chemicals.chemicalsList.get(index));
        } else if (cat == Category.AUTOMOTIVE) {
            AddAutomotive(automotive.automotiveList.get(index));
        }
    }

    public void Clear(){
        shopping.clear();
    }

    public void DeleteProductFromCategory(Category cat, int index){
        if(cat == Category.FOOD){
            DeleteFromFood(index);
        }else if(cat == Category.CHEMICALS){
            DeleteFromChemicals(index);
        }else if(cat == Category.AUTOMOTIVE){
            DeleteFromAutomotive(index);
        }
    }

    private void DeleteFromFood(int index) {
        if (shopping.containsKey(Category.FOOD)) {
            ArrayList<String> temp = shopping.get(Category.FOOD);
            temp.remove(index);
            shopping.put(Category.FOOD, temp);
        }
    }

    private void DeleteFromChemicals(int index) {
        if (shopping.containsKey(Category.CHEMICALS)) {
            ArrayList<String> temp = shopping.get(Category.CHEMICALS);
            temp.remove(index);
            shopping.put(Category.CHEMICALS, temp);
        }
    }

    private void DeleteFromAutomotive(int index) {
        if (shopping.containsKey(Category.AUTOMOTIVE)) {
            ArrayList<String> temp = shopping.get(Category.AUTOMOTIVE);
            temp.remove(index);
            shopping.put(Category.AUTOMOTIVE, temp);
        }
    }

    private void AddFood(String product) {
        ArrayList temp = new ArrayList();
        temp.add(product);

        if (!shopping.containsKey(Category.FOOD)) {
            shopping.put(Category.FOOD, temp);
        } else {
            temp.addAll(shopping.get(Category.FOOD));
            shopping.put(Category.FOOD, temp);
        }
    }

    private void AddChemicals(String product) {
        ArrayList temp = new ArrayList();
        temp.add(product);

        if (!shopping.containsKey(Category.CHEMICALS)) {
            shopping.put(Category.CHEMICALS, temp);
        } else {
            temp.addAll(shopping.get(Category.CHEMICALS));
            shopping.put(Category.CHEMICALS, temp);
        }
    }

    private void AddAutomotive(String product) {
        ArrayList temp = new ArrayList();
        temp.add(product);

        if (!shopping.containsKey(Category.AUTOMOTIVE)) {
            shopping.put(Category.AUTOMOTIVE, temp);
        } else {
            temp.addAll(shopping.get(Category.AUTOMOTIVE));
            shopping.put(Category.AUTOMOTIVE, temp);
        }
    }

    public void ShowList() {
        ShowCategory(Category.FOOD);
        ShowCategory(Category.CHEMICALS);
        ShowCategory(Category.AUTOMOTIVE);
    }

    public void ShowCategory(Category cat) {
        System.out.println(cat + ":");
        if (shopping.containsKey(cat)) {
            ArrayList<String> temp = new ArrayList();
            temp.addAll(shopping.get(cat));
            for (int i = 0; i < temp.size(); ++i) {
                System.out.println((i+1) + ". " + temp.get(i));
            }
        }
        System.out.println("\n");
    }
}
