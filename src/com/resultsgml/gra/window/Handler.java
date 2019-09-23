package com.resultsgml.gra.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.resultsgml.gra.framework.GameObject;
import com.resultsgml.gra.framework.ObjectId;
import com.resultsgml.gra.object.Block;
import com.resultsgml.gra.object.FinishCar;
import com.resultsgml.gra.object.GlueBlock;
import com.resultsgml.gra.object.Meta;
import com.resultsgml.gra.object.Player;
/**
 * Klasa przechowujaca wszystkie obiekty w liscie.
 * @author Bartosz Kwietniewski
 *
 */
public class Handler 
{
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	private BufferedImage level2, level3, level4, level5;
	/**
	 * Konstruktor klasy Handler. Wczytuje poziomy z pliku.
	 */
	public Handler()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		level2 = loader.loadImage("/level2.png");
		level3 = loader.loadImage("/level3.png");
		level4 = loader.loadImage("/level4.png");
		level5 = loader.loadImage("/level5.png");
	}
	/**
	 * Metoda umowzliwiajaca poruszanie obiektami.
	 */
	public void tick()
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	/**
	 * Metoda rysujaca obiekty.
	 * @param g obiekt klasy Graphics.
	 */
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	/**
	 * Metoda zamieniajaca wczytany obraz poziomu na elementy danego typu w grze.
	 * Dla kazdego koloru przyporzadkowywany jest inny typ obiektu.
	 * @param image obiekt klasy BufferedImage.
	 */
	public void loadImageLevel(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < h; xx++)
		{
			for(int yy = 0; yy < w; yy++)
			{
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xFF;
				int green = (pixel >> 8) & 0xFF;
				int blue = (pixel) & 0xFF;
				
				if(red == 255 && green == 255 && blue == 255)
					addObject(new Block(xx*32, yy*32, ObjectId.Block));		// Block
				
				if(red == 0 && green == 0 && blue == 255)
					addObject(new Player(xx*32, yy*32, this, ObjectId.Player));		// Player
				
				if(red == 255 && green == 0 && blue == 0)
					addObject(new Meta(xx*32, yy*32, ObjectId.Meta));		// Meta
				
				if(red == 0 && green == 255 && blue == 0)
					addObject(new GlueBlock(xx*32, yy*32, ObjectId.GlueBlock));		// GlueBlock
				
				if(red == 255 && green == 255 && blue == 0)
					addObject(new FinishCar(xx*32, yy*32, ObjectId.FinishCar));		// FinishCar
			}
		}
	}
	/**
	 * Metoda odpowiadajaca za zmienianie poziomu.
	 */
	public void changeLevel()
	{
		clearLevel();
		
		switch(Game.LEVEL)
		{
		case 1:
			loadImageLevel(level2);
			break;
			
		case 2:
			loadImageLevel(level3);
			break;
			
		case 3:
			loadImageLevel(level4);
			break;
			
		case 4:
			loadImageLevel(level5);
			break;
		}
	}
	/**
	 * Dodanie obiektu do listy obiektow.
	 * @param object obiekt klasy GameObject.
	 */
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	/**
	 * Usuniecie obiektu z listy obiektow.
	 * @param object obiekt klasy GameObject.
	 */
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	/**
	 * Usuniecie listy obiektow
	 */
	public void clearLevel()
	{
		object.clear();
	}
	
}
