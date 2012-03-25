package compo.spacebumper.java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
public abstract class Client {
	private Socket client;
	private BufferedReader reader;
	private PrintWriter writer;
	private byte[] data = new byte[256];
	private InetSocketAddress endPoint;
	public char[][] WaitForMap() {
		try {
		char[][] map = null;
		boolean end = false;
		int r = 0;
		while(!end)
		{
			String line = reader.readLine();
			if(line == null)
			{
				continue;
			}
			String[] msg = line.split(" ");
			if(msg[0] == "BEGIN_MAP")
			{
				map = new char[Integer.parseInt(msg[1])][Integer.parseInt(msg[2])];
			}
			else if(msg[0] == "END_MAP")
			{
				end = true;
			}
			else
			{
				if(map != null)
				{
					for(int i = 0; i < line.length(); i++)
					{
						map[i][r] = line.charAt(i);
					}
					r++;
				}
			}
		}
		return map;
		}catch(Exception e) {
			return null;
		}
	}
	public GameState getState()
	{
		try {
			writer.println("GET_STATE");
			writer.flush();
			GameState state = new GameState();
			boolean end = false;
			while(!end)
			{
				String line = reader.readLine();
				if(line == null)
				{
					continue;
				}
				String[] message = line.split(" ");
				if(message[0] == "BEGIN_STATE") //In Java 7, you can use switch cases for Strings. I am not using it so people with Java 6 can use it.
				{
					state.Iteration = Integer.parseInt(message[1]);
				}
				else if(message[0] == "END_STATE")
				{
					end = true;
				}
				else if(message[0] == "BUMPERSHIP")
				{
					state.bumperships.add(new BumperShip(Float.parseFloat(message[1]),
							Float.parseFloat(message[2]),
							Float.parseFloat(message[3]),
							Float.parseFloat(message[4]),
							Float.parseFloat(message[5])));
				}
				else if(message[0] == "STAR")
				{
					state.stars.add(new Vector(Float.parseFloat(message[1]), Float.parseFloat(message[2])));
				}
				else if(message[0] == "YOU")
				{
					state.meIndex = Integer.parseInt(message[1]);
				}
			}
			return state;
			
		} catch(Exception e)
		{
			return null;
		}
	}
	public void setName(String name)
	{
		writer.println("NAME " + name);
		writer.flush();
	}
	public boolean move(float x, float y)
	{
		try {
		writer.println("ACCELERATION " + String.valueOf(x) + " " + String.valueOf(y));
		writer.flush();
		String response;
		response = reader.readLine();
		return response == "OK";
		} catch (IOException e) {
			return false;
		}
	}
	public boolean Connected()
	{
		return client.isConnected() && !client.isClosed() && !writer.checkError();
	}
	public Client() throws IOException, InterruptedException {
        // Connect to server
        client = new Socket();
        endPoint = new InetSocketAddress("127.0.0.1", 1986);
        client.connect(endPoint);
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        writer = new PrintWriter(client.getOutputStream(), true);

        // Runs the implementation in MyAI.cs
        RunAi();
    }
	public abstract void RunAi() throws IOException, InterruptedException;
}
