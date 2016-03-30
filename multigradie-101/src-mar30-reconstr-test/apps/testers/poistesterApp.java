package apps.testers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import ds.gradies.Mask;
import ds.gradies.MaskRectangle;
import ds.gradies.PoisEditor;
import ds.gradies.PoisEditorJacobi;
import ds.gradies.PoisEditorJacobiMulti;
import ds.writers.ItemWriter;

public class poistesterApp 
{
	public void test() throws Exception
	{
		List<File> files = poistesterConsts.getImageFiles();
		
		ItemWriter out = new ItemWriter(poistesterConsts.getTable1());
		out.writeHeader();
		
		PoisEditor p1 = new PoisEditorJacobi();
		PoisEditor p2 = new PoisEditorJacobiMulti();
		
		for(File fk: files)
		{
			BufferedImage ik = ImageIO.read(fk);
			Mask mk = new MaskRectangle(ik);
			
			BufferedImage i1 = p1.reconstructImage(ik, mk);
			double s1 = PoisEditor.similar(i1, ik);
					
			BufferedImage i2 = p2.reconstructImage(ik, mk);			
			double s2 = PoisEditor.similar(i2, ik);
			
			out.writeItem(fk.getName(), ik, i1, i2, s1, s2);
		}
		out.writeFooter();
		out.close();		
	}
	
	public static void main(String[] args) throws Exception
	{
		(new poistesterApp()).test();
	}

}
