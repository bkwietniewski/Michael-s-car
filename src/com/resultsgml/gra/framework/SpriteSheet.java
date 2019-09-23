package com.resultsgml.gra.framework;

import java.awt.image.BufferedImage;
/**
 * Klasa odpowiedzialna za pobranie fragmentu tekstur obiektow.
 * @author Bartosz Kwietniewski
 *
 */
public class SpriteSheet 
{
	
	private BufferedImage image;
	/**
	 * Konstruktor klasy SpriteSheet.
	 * @param image obiekt klasy BufferedImage.
	 */
	public SpriteSheet(BufferedImage image)
	{
		this.image = image;
	}
	/**
	 * Metoda okreslajaca wspolrzedne pobrania fragmentu obrazu.
	 * @param col numer kolumny wczytywanego obrazu (wspolrzedna x).
	 * @param row numer wiersza wczytywanego obrazu (wspolrzedna y).
	 * @param width szerokosc obrazka do wczytania.
	 * @param height wysokosc obrazka do wczytania.
	 * @return obiekt klasy BufferedImage.
	 */
	public BufferedImage grabImage(int col, int row, int width, int height)
	{
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
	}
	
}
