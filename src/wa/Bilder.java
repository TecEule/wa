package wa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Bilder extends JPanel{

	private static String _pictureUrl = "";
	public Bilder (String picturePath)
	{
		_pictureUrl = picturePath;
		
		setLayout(null);
		setPreferredSize(new Dimension(800,600));		
	}
	
	@Override
	public void paint(Graphics gr)
	{
		Graphics2D g = (Graphics2D)gr;
		g.setColor(Color.DARK_GRAY);
		g.fill(g.getClipBounds());
		
		g.drawImage(load(_pictureUrl), 0,0,null);
		
	}
	
	public static BufferedImage load(String picturePath)
	{
		try
		{
			BufferedImage img = ImageIO.read(Bilder.class.getResourceAsStream(picturePath));
			return img;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;		
	}
	
}
