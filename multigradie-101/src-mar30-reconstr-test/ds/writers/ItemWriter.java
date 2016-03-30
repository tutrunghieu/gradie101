package ds.writers;

import java.io.File;

public abstract class ItemWriter 
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

	public abstract void writeHeader();

	public abstract void writeFooter(); 

	public abstract void writeItem(Object... objects);

}
