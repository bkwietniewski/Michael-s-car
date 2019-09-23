package com.resultsgml.gra.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.resultsgml.gra.framework.GameObject;
import com.resultsgml.gra.framework.ObjectId;
import com.resultsgml.gra.framework.Texture;
import com.resultsgml.gra.window.Game;
/**
 * Klasa specyfikacji obiektu FinishCar.
 * @author Bartosz Kwietniewski
 *
 */
public class FinishCar extends GameObject
{
	
	Texture tex = Game.getInstance();
	/**
	 * Konstruktor klasy FinishCar.
	 * @param x wspolrzedna x umieszczenia obiektu.
	 * @param y wspolrzedna y umieszczenia obiektu.
	 * @param id identyfikator obiektu.
	 */
	public FinishCar(float x, float y, ObjectId id)
	{
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object)
	{
		
	}
	/**
	 * Metoda rysujaca obiekt (nadanie tekstury).
	 */
	public void render(Graphics g) 
	{
		g.drawImage(tex.finish[0], (int)x, (int)y, 96, 64, null);
	}
	/**
	 * Metoda tworzaca granice obiektu (potrzebne do kolizji).
	 */
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 96, 64);
	}
	
}
