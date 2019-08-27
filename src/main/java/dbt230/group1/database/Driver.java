package dbt230.group1.database;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static Jedis jedis = new Jedis("localhost");
    private static int id = 1;
    private static int nextId = 1;

    public static void main(String[] args) throws IOException {
        saveIds();

        boolean isDone = false;
        System.out.println("Welcome to the sample app for Redis Database\n");
        while (!isDone) {
            System.out.println(
                    "What would you like to do?\n" +
                            "[0] Create\n" +
                            "[1] Read\n" +
                            "[2] Update\n" +
                            "[3] Delete\n" +
                            "[4] Reset Database\n" +
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
                    deleteContact(promptForId());
                    break;
                case 4:
                    reset();
                    break;
                case 5:
                    isDone = true;
                    break;
            }
        }
    }

    private static void saveIds() {
        id = Integer.parseInt(jedis.get("id"));
        nextId = Integer.parseInt(jedis.get("nextId"));
    }

    private static void reset() throws IOException {
        System.out.println("Are you 1000% sure you want to delete the database?\n" +
                "[0] NO\n" +
                "[1] YES");
        String rawAnswer = in.readLine();
        int selection = Integer.parseInt(rawAnswer);

        if (selection == 1) {
            jedis.flushDB();
            System.out.println("\n ****** Database Reseted ******\n\n");
        }
    }

    private static void deleteContact(int id) {
        jedis.del("employee" + id);
        System.out.println("\nContact "+ id + " has been delete\n");
    }

    private static void updateContact(int id) throws IOException {
        System.out.println("What would you like to change?");
        System.out.println(
                "[0] First Name\n" +
                        "[1] Last Name\n" +
                        "[2] Hire Year");
        int selection = Integer.parseInt(in.readLine());
        String[] parts = jedis.get("employee" + id).split(" ");
        switch (selection) {
            case 0:
                String newName = promptForName();
                jedis.set("employee" + id, "" + newName + " " + parts[1] + " " + parts[2]);
                break;
            case 1:
                String newLastName = promptForLastName();
                jedis.set("employee" + id, "" + parts[0] + " " + newLastName + " " + parts[2]);
                break;
            case 2:
                int newYear = promptForHireYear();
                jedis.set("employee" + id, "" + parts[0] + " " + parts[1] + " " + newYear);
                break;
        }
    }

    private static void readContact(int id) {
        if (jedis.get("employee" + id) == null) {
            System.out.println("\nContact with id: " + id + " does not exist.\n");
        } else {
            System.out.println("\nContact N°" + id + ":\n" + jedis.get("employee" + id) + "\n");
        }
    }

    private static void createContact(String name, String lastName, int hireYear) {

        jedis.set("employee" + nextId, "" + name + " " + lastName + " " + hireYear);
        jedis.set("id", "" + id++);
        jedis.set("nextId", "" + nextId++);
        saveIds();
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
