package compo.spacebumper.java;

import java.io.IOException;
import java.util.Random;

public class MyAI extends Client{
	public void RunAi()throws IOException, InterruptedException 
	{
		setName("StupidAI");
		char[][] map = WaitForMap();
		System.out.println("Got map");
		while(Connected())
		{
			GameState state = getState();
			Random rand = new Random();
			this.move(rand.nextFloat(), rand.nextFloat());
			Thread.sleep(200);
		}
		System.out.println("Not connected anymore :(");
	}
	public MyAI() throws IOException, InterruptedException {
        super();
    }
	public static void main(String[] args)
	{
		System.out.println("Started");
		try {
			MyAI ai = new MyAI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
