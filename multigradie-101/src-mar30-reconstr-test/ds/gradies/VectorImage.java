package ds.gradies;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class VectorImage {
	protected int nrows;
	protected int ncols;
	protected int nlayers;
	protected double[][][] pixels;


	public int getWidth() {
		return ncols;
	}	
	
	public int getHeight() {
		return nrows;
	}	
	
	public int getDepth() 
	{
		return nlayers;
	}	
		
	public VectorImage(int rows, int cols) 
	{
		nrows = rows;
		ncols = cols;
		nlayers = 3;
		pixels = new double[rows][cols][nlayers]; 
	}

	public VectorImage(int rows, int cols, int nl) 
	{
		nrows = rows;
		ncols = cols;
		nlayers = nl;
		pixels = new double[rows][cols][nlayers]; 
	}
	
	public VectorImage(VectorImage g) 
	{
		nrows = g.nrows;
		ncols = g.ncols;
		nlayers = g.nlayers;
		pixels = new double[g.nrows][g.ncols][g.nlayers]; 
	}

	public VectorImage(BufferedImage img) throws Exception
	{
		int Rx = img.getWidth(), Ry = img.getHeight();
		
		nrows = Ry;
		ncols = Rx;
		nlayers = 3;
		pixels = new double[Ry][Rx][nlayers]; 
		
		for(int y=0; y<Ry; y++)
		for(int x=0; x<Rx; x++)
		{
			 Color c = new Color( img.getRGB(x, y) );
			 pixels[y][x][0] = c.getRed();
			 pixels[y][x][1] = c.getGreen();
			 pixels[y][x][2] = c.getBlue();
		}
		
		return;		
	}

	public void set(int x, int y, double[] v) 
	{
		pixels[y][x] = v;
	}	
	
	public double[] get(int x, int y) 
	{
		return pixels[y][x];
	}

	public BufferedImage toBufferedImage()
	{
		int Rx = ncols, Ry = nrows;
		BufferedImage res = new BufferedImage(Rx, Ry, BufferedImage.TYPE_INT_ARGB);
		
		for(int y=0; y<Ry; y++)
		for(int x=0; x<Rx; x++)
		{
			 res.setRGB(x, y, color(pixels[y][x]).getRGB());
		}
		
		return res;
	}
	
	public static Color color(double[] c) 
	{
		for(int k=0; k<c.length; k++)
		{
			if(c[k]<0) c[k] = 0;
			if(c[k]>255) c[k] = 255;
		}
		
		return new Color((int)c[0], (int)c[1], (int)c[2]);
	}	

	public double[][][] getImageData() 
	{
		return pixels;
	}

}
