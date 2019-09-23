package com.resultsgml.gra.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.resultsgml.gra.framework.GameObject;
import com.resultsgml.gra.framework.ObjectId;
import com.resultsgml.gra.framework.Texture;
import com.resultsgml.gra.window.Game;
import com.resultsgml.gra.window.Handler;
/**
 * Klasa specyfikacji obiektu Player.
 * @author Bartosz Kwietniewski
 *
 */
public class Player extends GameObject 
{

	private float width = 32, height = 64;
	private float gravity = 0.5f;
	private final float MAX_SPEED_FALLING = 10;
	
	private Handler handler;
	
	Texture tex = Game.getInstance();
	/**
	 * Konstruktor klasy Player.
	 * @param x wspolrzedna x umieszczenia obiektu.
	 * @param y wspolrzedna y umieszczenia obiektu.
	 * @param handler obiekt klasy Handler.
	 * @param id identyfikator obiektu.
	 */
	public Player(float x, float y, Handler handler, ObjectId id) 
	{
		super(x, y, id);
		this.handler = handler;
	}
	/**
	 * Metoda zachowania obiektu klasy Player (umowzliwiajaca poruszanie obiektem).
	 * @param object obiekt z listy obiektow.
	 */
	public void tick(LinkedList<GameObject> object) 
	{
		x += velX;
		y += velY;
		
		if(falling || jumping)
		{
			velY += gravity;
			
			if(velY > MAX_SPEED_FALLING)
				velY = MAX_SPEED_FALLING;
		}
		
		Collision(object);
	}
	/**
	 * Metoda indetyfikujaca reakcje obiektu Player na kolizje z innymi obiektami
	 * @param object obiekt z listy obiektow.
	 */
	private void Collision(LinkedList<GameObject> object)
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block)		// Block
			{
				/*top*/
				if(getBoundsTop().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY()+32;
					velY = 0;
				}				
				
				/*bottom*/
				if(getBounds().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY() - height;	    /*wyrownanie krawedzi obiektow (nie nachodza na siebie)*/
					velY = 0;
					falling = false;
					jumping = false;
				}
				else
					falling = true;
				
				/*left*/
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX() + 32;
				}	
				
				/*right*/
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX() - width;
				}	
			} 
			else if(tempObject.getId() == ObjectId.Meta)		// Meta
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					Game.LEVEL++;
					handler.changeLevel();
				}
			}
			else if(tempObject.getId() == ObjectId.GlueBlock)				// GlueBlock
			{
				if(getBoundsTop().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY();
					velY = 0;
				}
			}
			else if(tempObject.getId() == ObjectId.FinishCar)				// FinishCar
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					Game.LEVEL = -1;
				}
			}
		}
	}
	/**
	 * Metoda rysujaca obiekt (nadanie tekstury).
	 * @param g obiekt klasy Graphics.
	 */
	public void render(Graphics g) 
	{
		g.drawImage(tex.player[0], (int)x, (int)y, 32, 64, null);
	}
	/**
	 * Metoda tworzaca dolna granice obiektu (potrzebne do kolizji).
	 */
	public Rectangle getBounds() 
	{
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	/**
	 * Metoda tworzaca gorna granice obiektu (potrzebne do kolizji).
	 */
	public Rectangle getBoundsTop() 
	{
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int)y, (int)width/2, (int)height/2);
	}
	/**
	 * Metoda tworzaca lewa granice obiektu (potrzebne do kolizji).
	 */
	public Rectangle getBoundsLeft() 
	{
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}
	/**
	 * Metoda tworzaca prawa granice obiektu (potrzebne do kolizji).
	 */
	public Rectangle getBoundsRight() 
	{
		return new Rectangle((int) ((int)x+width-5), (int)y+5, (int)5, (int)height-10);
	}

}
