package com.guessgame;
import com.game.db.GameDb;

import java.util.Random;

import java.util.Scanner;

public class Game {
    static Scanner sc = new Scanner(System.in);
    static int userChoice;
    static int computerChoice;
    static int numberOfGuesses = 0;
    static String player_name;
    static String player_username;

    Game(){
        Random random = new Random();
        computerChoice = random.nextInt(1, 101);
        // System.out.println(computerChoice);
        System.out.println("Rules 📑- \n" +
                "1.You have only 6 attempts to guess any number correctly ✅\n" +
                "2.Enter unique username 👽\n");
    }

    static void userInfo(){
        System.out.print("Enter Your Name to start the game:- ");
        player_name = sc.nextLine();
        System.out.println("Enter a unique username:- ");
        player_username = sc.nextLine();
    }

    static void takeUserInput(){
        System.out.println("Guess a number: ");
        userChoice = Integer.parseInt(sc.nextLine());
    }

    static boolean isCorrectGuess(){
        if(computerChoice == userChoice){
            System.out.println("You Guessed Correct");
            return true;
        }
        else if (computerChoice > userChoice) {
            System.out.println("Too Small");
            return false;
        }
        else{
            System.out.println("Too Large");
            return false;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        userInfo();

        GameDb db = new GameDb();

        db.insertPlayerData(player_name, player_username );
        System.out.println("-------------- Game Started --------------");
            while (computerChoice != userChoice) {
                numberOfGuesses++;
                if(numberOfGuesses < 7){
                    takeUserInput();
                    isCorrectGuess();
                }
                else{
                    System.out.println(" ❌ You have exceeded maximum number of attempts. ");
                   break;
                }
            }



        System.out.println("Computer choice was : " + computerChoice);
        System.out.println("Total Guesses = " +(numberOfGuesses - 1));
        System.out.println("\n");
        System.out.println("-------------- Game Finished --------------");
    }
}
