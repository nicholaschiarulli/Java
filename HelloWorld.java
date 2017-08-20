import java.util.Scanner;

public class HelloWorld{
	public static void main(String[] args){
		
		Scanner user_input = new Scanner(System.in);
		


		System.out.print("What is your name? ");
		String name = user_input.nextLine();	
		System.out.println("Hello " + name);



	}
}
