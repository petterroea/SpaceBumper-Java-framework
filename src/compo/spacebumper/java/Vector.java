package compo.spacebumper.java;

public class Vector {
	float x, y;
	public Vector(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	public double getLength()
	{
		return Math.sqrt((x*x) + (y*y));
	}
}
