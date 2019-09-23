package com.resultsgml.gra.window;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Klasa  odpowiadajaca za wczytanie obrazka poziomu z pliku.
 * @author Bartosz Kwietniewski
 *
 */
public class BufferedImageLoader
{
	
	private BufferedImage image;
	/**
	 * Metoda wczytujaca obraz z pliku.
	 * @param path sciezka do pliku obrazu.
	 * @return wczytany obraz.
	 */
	public BufferedImage loadImage(String path)
	{
		try 
		{
			image = ImageIO.read(new FileInputStream("resources" + path));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return image;
	}

}
