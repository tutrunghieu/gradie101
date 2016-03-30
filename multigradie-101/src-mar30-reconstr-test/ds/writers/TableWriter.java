package ds.writers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class TableWriter extends ItemWriter {

	protected PrintWriter out;
	
	public TableWriter(File f) throws Exception
	{
		super(f);
		out = new PrintWriter(f);
	}

	@Override
	public void writeHeader() 
	{
		out.println("<table border=1>");
	}

	@Override
	public void writeFooter() 
	{
		out.println("</table>");
	}

	@Override
	public void writeItem(Object... args) 
	{
		out.println("<tr>");
		
		for(int k=0; k<args.length; k++)
		{
			out.print("<td>");
			
			Object ak = args[k];
			if(ak == null) out.println("&nbsp;");
			else if(ak instanceof BufferedImage) insertImage((BufferedImage)ak);
			else out.print(ak);
			
			out.print("</td>");
		}

		out.println("</tr>");
	}

	private int figure = 0;
	
	private void insertImage(BufferedImage ak) 
	{
		File fk = new File(target.getParentFile().getAbsolutePath() + "/images/image-"+ (figure++) +".png");
		
		try { ImageIO.write(ak, "png", fk); }
		catch(Exception xp) {}
		
		out.print("<img src='images/" + fk.getName() + "'>");
	}

}
