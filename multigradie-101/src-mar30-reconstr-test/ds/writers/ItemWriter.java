package ds.writers;

import java.io.File;

public class ItemWriter 
{
	protected File target;

	public ItemWriter(File f) 
	{
		target = f;
	}
	
	public File close() 
	{
		return target;
	}

	public void writeHeader() {
	}

	public void writeFooter() 
	{
	}


	public void writeItem(Object... objects)
	{
		
	}

}
