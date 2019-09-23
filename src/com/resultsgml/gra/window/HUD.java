package com.resultsgml.gra.window;

import java.awt.Color;
import java.awt.Graphics;
/**
 * Klasa odpowiadajaca za wyswietlanie poziomu, czasu oraz liczenie czasu.
 * @author Bartosz Kwietniewski
 *
 */
public class HUD 
{

	private int time = 0;
	/**
	 * Metoda okreslajaca reakcje HUD (zwiekszanie licznika czasu).
	 */
	public void tick() 
	{
		time++;
	}
	/**
	 * Metoda rysujaca obiekt.
	 * @param g obiekt klasy Graphics.
	 */
	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		g.drawString("TIME: " + time, 40, 50);
		g.drawString("LEVEL: " + (Game.LEVEL + 1), 40, 70);
	}
	/**
	 * Zwracanie wartosci time.
	 * @return czas gry.
	 */
	public int getTime() 
	{
		return time;
	}
	/**
	 * Przypisanie wartosci time.
	 * @param time czas gry.
	 */
	public void setTime(int time) 
	{
		this.time = time;
	}
	
	
	
}
