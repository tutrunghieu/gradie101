package ds.gradies;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MaskRectangle extends Mask 
{
	private Rectangle mask;

	public void setRectangle(int x, int y, int w, int h) 
	{
		mask = new Rectangle(x, y, w, h);
	}
	
	public Rectangle getRectangle()
	{
		return mask;
	}
	
	public MaskRectangle(BufferedImage ik) 
	{
		setRectangle(1, 1, ik.getWidth()-2, ik.getHeight()-2);
	}

	@Override
	public boolean get(int x, int y) 
	{
		return mask.contains(x, y);
	}
	
}
