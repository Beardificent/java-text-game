package com.becode;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //System objects
            //will look for user INputs.
        Scanner in = new Scanner(System.in);
            //random number generator
        Random rand = new Random();

        //Game variables
            //array that we fill with all kinds of enemies
        String[] enemies = {"Stormtrooper", "Arc Trooper", "Snow Trooper", "Assassin Trooper"};
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;

        //Player variables
        int playerHealth = 100;
        int playerAttackDamage = 50;
            //starting amount of potions
        int numberHealthPotions = 3;
        int healthPotionHealAmount = 30;
            //50 stands for % dropchance
        int healthPotionDropChance = 50;

        boolean running = true;

        System.out.println("Welcome to the dungeon!");

        GAME:
        while(running) {
            System.out.println("-----------------------------------------------------");
            //this will set the generated enemy's health to a random number between 0 and the value given               to the original variable
            int enemyHealth = rand.nextInt(maxEnemyHealth);
            // access to array, between [is the index we're using] - which is a random number                           between 0 and length of array.
            String enemy = enemies[rand.nextInt(enemies.length)];
            //"\n# " is a tab in the terminal and the one at the end acts as a linebreak
            System.out.println("\t# " + enemy + " appeared! #\n");
            //   # Skeleton appeared.
            //We're making sure that we first kill the current enemy before encountering another.
            label:
            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + playerHealth);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");
                //in.nextLine will return the input given by user upon pressing enter.
                String input = in.nextLine();
                switch (input) {
                    case "1":
                        int damageDealt = rand.nextInt(playerAttackDamage);
                        int damageTaken = rand.nextInt(enemyAttackDamage);

                        enemyHealth -= damageDealt;
                        playerHealth -= damageTaken;

                        System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                        System.out.println("\t> You receive " + damageTaken + " damage in retaliation!");

                        if (playerHealth < 1) {
                            System.out.println("\t> You are dead.");
                            break label;
                        }
                        break;
                    case "2":
                        if (numberHealthPotions > 0) {
                            if (playerHealth == 100) {
                                System.out.println("\t You reconsider and put the potion away");
                                break label;
                            }
                            playerHealth += healthPotionHealAmount;
                            if (playerHealth > 100) {
                                playerHealth = 100;
                            }
                            numberHealthPotions--;
                            System.out.println("\t You drink a health potion, healing yourself for "
                                    + healthPotionHealAmount + "."
                                    + "\n\t> You now have " + playerHealth + "HP."
                                    + "\n\t> You have " + numberHealthPotions + "health potions left.\n");
                        } else {
                            System.out.println("\t> You don't have any left! Defeat more enemies to get more!");
                        }
                        break;
                    case "3":
                        System.out.println("\t> You run away from the " + enemy + "!");
                        // By continuing our GAME, we will break out all the loops and restart it.
                        continue GAME;

                    default:
                        System.out.println("\t You don't know what to do.");
                        break;
                }
            }

            if (playerHealth < 1) {
                System.out.println("\t You managed to escape but are wounded");
                break;
            }
            if (enemyHealth < 0) {
                System.out.println("-----------------------------------------------------");
                System.out.println(" # " + enemy + " was defeated! # ");
                System.out.println(" # You have " + playerHealth + "HP left. # ");
                //generate a random number, if it is greater than the dropchance, award healthpotion
                if (rand.nextInt(100) > healthPotionDropChance) {
                    numberHealthPotions++;
                    System.out.println(" # The " + enemy + " dropped a health potion! # ");
                    System.out.println(" # You have " + numberHealthPotions + " healh potion(s). # ");
                }
            }
            System.out.println("-----------------------------------------------------");
            System.out.println("\t What would you like to do?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");

            String input = in.nextLine();
            switch (input) {
                case "1":
                    System.out.println("\t You continue your adventure");
                    break;
                case "2":
                    System.out.println("\t You exit the dungeon and survive");
                    break GAME;
                default:
                    System.out.println("\t You seem to be confused");
            }

        }
        System.out.println("##################");
        System.out.println("Thanks for playing!");
        System.out.println("##################");



    }
}
