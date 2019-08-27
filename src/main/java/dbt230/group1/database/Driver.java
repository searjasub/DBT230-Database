package dbt230.group1.database;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Driver {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static Jedis jedis = new Jedis("localhost");
    private static int id = 1;
    private static int nextId = 1;

    public static void main(String[] args) throws IOException {

        boolean isDone = false;
        System.out.println("Welcome to the sample app for MongoDB\n");
        while (!isDone) {
            System.out.println(
                    "What would you like to do?\n" +
                            "[0] Create\n" +
                            "[1] Read\n" +
                            "[2] Update\n" +
                            "[3] Delete\n" +
                            "[5] Exit");

            int selection = Integer.parseInt(in.readLine());
            switch (selection) {
                case 0:
                    createContact(promptForName(), promptForLastName(), promptForHireYear());
                    break;
                case 1:
                    readContact(promptForId());
                    break;
                case 2:
                    updateContact(promptForId());
                    break;
                case 3:
                    deleteContact();
                    break;
                case 4:

                    break;
                case 5:
                    isDone = true;
                    break;
            }
        }
    }

    private static void deleteContact() {

    }

    private static void updateContact(int promptForId) {
    }

    private static void readContact(int id) {
        System.out.println("Contact N°"+id + ":\n"+jedis.get("employee" + id) + "\n");
    }

    private static void createContact(String name, String lastName, int hireYear) {

//        Employee employee = new Employee(id, name, lastName, hireYear);
        jedis.set("employee" + nextId, "" + name + " " + lastName + " " + hireYear);
        id++;
        nextId++;
    }

    private static int promptForId() throws IOException {
        System.out.println("Please enter the id of the contact: ");
        return Integer.parseInt(in.readLine());
    }

    private static String promptForName() throws IOException {
        System.out.println("First Name: ");
        return in.readLine();
    }

    private static String promptForLastName() throws IOException {
        System.out.println("Last Name: ");
        return in.readLine();
    }

    private static int promptForHireYear() throws IOException {
        System.out.println("Hire date: ");
        return Integer.parseInt(in.readLine());
    }

}
