package apps.testers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class poistesterConsts {

	public static boolean DEBUG = true;

	public static List<File> getImageFiles()
	{
		List<File> res = new ArrayList<File>();
		res.add(new File("C:/Users/henrytu/Desktop/PossionImageEditing/image1.jpg"));
		res.add(new File("C:/Users/henrytu/Desktop/PossionImageEditing/image2.jpg"));		
		return res;
	}

	public static File getTable1()
	{
		return new File("C:/Users/henrytu/Desktop/temp-mar30/out.html");
	}

}
