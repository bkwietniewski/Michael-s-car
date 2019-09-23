package com.resultsgml.gra.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.resultsgml.gra.window.Handler;
/**
 * Klasa odpowiedzialna za sterowanie (odczyt z klawiatury).
 * @author Bartosz Kwietniewski
 *
 */
public class KeyInput extends KeyAdapter
{
	
	Handler handler;
	/**
	 * Konstruktor klasy KeyInput.
	 * @param handler obiekt klasy Handler.
	 */
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	/**
	 * Wcisniecie klawisza. Przeszukujemy obiekty z gry,
	 * jesli natrafimy na obiekt player to wykonujemy przesuniecie
	 */
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_D) tempObject.setVelX(5);
				if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
				if(key == KeyEvent.VK_W && !tempObject.isJumping()) // zapobiega wiecej niz jeden skok
				{
					tempObject.setJumping(true);
					tempObject.setVelY(-9);
				}
			}
		}
				
		
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}
	/**
	 * Reakcja na puszczenie klawisza.
	 */
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
			}
		}
	}

}
