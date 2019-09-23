package com.resultsgml.gra.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
/**
 * Klasa przy pomocy ktorej sa tworzone obiekty.
 * @author Bartosz Kwietniewski
 *
 */
public abstract class GameObject
{
	
	protected float x, y;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	/**
	 * Konstruktor klasy GameObject. Przypisanie wartosci parametrom.
	 * @param x wspolrzedna x obiektu.
	 * @param y	wspolrzedna y obiektu.
	 * @param id id obiektu.
	 */
	public GameObject(float x, float y, ObjectId id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	public float getX()
	{
		return x;
	}
	public  float getY()
	{
		return y;
	}
	public void setX(float x)
	{
		this.x = x;
	}
	public void setY(float y)
	{
		this.y = y;
	}
	public float getVelX()
	{
		return velX;
	}
	public float getVelY()
	{
		return velY;
	}
	public void setVelX(float velX)
	{
		this.velX = velX;
	}
	public void setVelY(float velY)
	{
		this.velY = velY;
	}
	/**
	 * 
	 * @return zwraca id obiektu.
	 */
	public ObjectId getId()
	{
		return id;
	}
	/**
	 * 
	 * @return zwraca wartosc spadania.
	 */
	public boolean isFalling() 
	{
		return falling;
	}

	public void setFalling(boolean falling)
	{
		this.falling = falling;
	}
	/**
	 * 
	 * @return zwraca wartosc skakania.
	 */
	public boolean isJumping() 
	{
		return jumping;
	}

	public void setJumping(boolean jumping)
	{
		this.jumping = jumping;
	}
	
}
