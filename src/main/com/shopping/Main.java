package main.com.shopping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var menu = new Menu();
        menu.MenuHandler();
    }
}