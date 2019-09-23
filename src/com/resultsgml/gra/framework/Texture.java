package com.resultsgml.gra.framework;

import java.awt.image.BufferedImage;

import com.resultsgml.gra.window.BufferedImageLoader;
/**
 * Klasa przyporzadkowujaca obiektom ich tekstury wczytywane z pliku.
 * @author Bartosz Kwietniewski
 *
 */
public class Texture 
{
	
	SpriteSheet bs, ps, ms, fs;
	
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage meta_sheet = null;
	private BufferedImage finish_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[1];
	public BufferedImage[] meta = new BufferedImage[1];
	public BufferedImage[] finish = new BufferedImage[1];
	/**
	 * Konstruktor otwiera obraz z pliku danego rodzaju obiektu.
	 */
	public Texture()
	{
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try
		{
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
			meta_sheet = loader.loadImage("/meta_sheet.png");
			finish_sheet = loader.loadImage("/finish_sheet.png");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		ms = new SpriteSheet(meta_sheet);
		fs = new SpriteSheet(finish_sheet);
		
		getTextures();
	}
	/**
	 * Metoda przyporzadkowujaca kazdemu rodzajowi obiektu dana teksture.
	 */
	private void getTextures()
	{
		block[0] = bs.grabImage(1, 1, 32, 32);		// Block
		block[1] = bs.grabImage(2, 1, 32, 32);		// GlueBlock
		
		player[0] = ps.grabImage(1, 1, 128, 256);		// Player
		meta[0] = ms.grabImage(1, 1, 128, 256);		// Meta
		finish[0] = fs.grabImage(1, 1, 256, 128);		// FinishCar
	}
	
}
