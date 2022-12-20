import java.util.Scanner;
/*
 * TicTacToe
 * Authors:
 * Artyom Generalov 319525671
 * Itamar Abir 208273169
 * 
 * */
public class MainTicTac {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		System.out.println("To play Player VS Computer type 1. To see a simulation of two computers, type 2:");
		int choice = in.nextInt();
		switch(choice) {
		case 1:
			UserGame g = new UserGame();
			g.playGame();
			break;
		case 2:
			SelfGame e = new SelfGame();
			e.playGame();
			break;
		default:
			System.out.println("You've chosen an invalid option. bye.");
			break;
		}
	}
}
