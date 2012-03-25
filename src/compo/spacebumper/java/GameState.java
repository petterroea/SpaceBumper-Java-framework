package compo.spacebumper.java;

import java.util.LinkedList;

public class GameState {
	public LinkedList<BumperShip> bumperships;
	public LinkedList<Vector> stars;
	public int meIndex;
	public int Iteration;
	public BumperShip me()
	{
		return bumperships.get(meIndex);
	}
	public GameState()
	{
		bumperships = new LinkedList<BumperShip>();
		stars = new LinkedList<Vector>();
	}
}
