package compo.spacebumper.java;

public class MyAI extends Client{
	public static void runAI()
	{
		setName("StupidAI");
		char[][] map = WaitForMap();
		while(Connected())
		{
			
		}
	}
	public static void main(String[] args)
	{
		MyAI ai = new MyAI();
		ai.start();
	}
}
