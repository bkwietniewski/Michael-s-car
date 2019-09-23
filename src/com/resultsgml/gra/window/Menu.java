package com.resultsgml.gra.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.resultsgml.gra.window.Game.STATE;
/**
 * Klasa odpowiedzialna za dzialanie menu: wlaczenie gry, pokazanie statystyk oraz wyjscie
 *
 */
public class Menu extends MouseAdapter
{
	
	private Game game;
	private Handler handler;
	private BufferedImage levelStart;
	private HUD hud;
	private Stats stats;
	/**
	 * Konstruktor klasy Menu.
	 * @param game obiekt klasy Game.
	 * @param handler obiekt klasy Handler.
	 * @param hud obiekt klasy HUD.
	 * @param stats obiekt klasy Stats.
	 */
	public Menu(Game game, Handler handler, HUD hud, Stats stats) 
	{
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.stats = stats;
	}
	/**
	 * Metoda kontrolujaca strumien wejsca myszki (wcisniecie klawisza).
	 */
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		
		if(game.gameState == STATE.Menu)
		{
			/*play button*/
			if(mouseOver(mx, my, 250, 250, 300, 64))
			{
				BufferedImageLoader loader = new BufferedImageLoader();
				levelStart = loader.loadImage("/levelStart.png");
				
				game.gameState = STATE.Game;
				handler.loadImageLevel(levelStart);
			}
			
			/*stats button*/
			if(mouseOver(mx, my, 250, 350, 300, 64))
			{	
				game.gameState = STATE.Stats;
			}	
			
			/*quit button*/
			if(mouseOver(mx, my, 250, 450, 300, 64))
			{
				System.exit(1);
			}
		}
		
		/*stats "back" button*/
		if(game.gameState == STATE.Stats)
		{
			if(mouseOver(mx, my, 250, 500, 300, 64))
			{	
				game.gameState = STATE.Menu;
			}
		}
		
		/*end "back to menu" button*/
		if(game.gameState == STATE.End)
		{
			if(mouseOver(mx, my, 250, 500, 300, 64))
			{	
				game.gameState = STATE.Menu;
				Game.LEVEL = 0;
				hud.setTime(0);
			}
		}
	}
	/**
	 * Metoda kontrolujaca strumien wejscia myszki (puszczenie klawisza).
	 */
	public void mouseReleased(MouseEvent e)
	{
		
	}
	/**
	 * Metoda zwracajaca true lub false jesli kliknieto w wyznaczony obszar.
	 * @param mx wartosc x klikniecia
	 * @param my wartosc y klikniecia
	 * @param x wartosc x wyznaczonego obszaru
	 * @param y wartosc y wyznaczonego obszaru
	 * @param width szerokosc wyznaczonego obszaru
	 * @param height wysokosc wyznaczonego obszaru
	 */
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if(mx > x && mx < x + width)
			if(my > y && my < y + height)
				return true;
			else
				return false;
		else 
			return false;
	}
	/**
	 * Rekacja klasy Menu.
	 */
	public void tick()
	{
		
	}
	/**
	 * Metoda rysujaca obiekty (przyciski oraz teskt na przyciskach).
	 * @param g obiekt klasy Graphics.
	 */
	public void render(Graphics g)
	{
		if(game.gameState == STATE.Menu)
		{
			Font fnt = new Font("calibri", 1, 100);
			Font fnt2 = new Font("arial", 0, 40);
			
			g.setFont(fnt);
			g.setColor(Color.black);
			g.drawString("Menu", 275, 150);
			
			g.setFont(fnt2);
			g.drawRect(250, 250, 300, 64);
			g.drawString("Play", 360, 295);
			
			g.drawRect(250, 350, 300, 64);
			g.drawString("Stats", 355, 395);
			
			g.drawRect(250, 450, 300, 64);
			g.drawString("Quit", 360, 495);
		} 
		else if(game.gameState == STATE.Stats)
		{
			Font fnt = new Font("calibri", 1, 70);
			Font fnt2 = new Font("arial", 0, 40);
			
			g.setFont(fnt);
			g.setColor(Color.black);
			g.drawString("Top 5", 325, 100);
			
			g.setFont(fnt2);
			for(int i = 0; i < 5; i++)
				g.drawString(Integer.toString(stats.get(i)), 360, 200 + i * 50);
			
			g.drawRect(250, 500, 300, 64);
			g.drawString("Back", 355, 545);
		}
		else if(game.gameState == STATE.End)
		{
			Font fnt = new Font("calibri", 1, 70);
			Font fnt2 = new Font("arial", 0, 40);
			
			g.setFont(fnt);
			g.setColor(Color.black);
			g.drawString("Congratulations!", 160, 100);
			
			g.setFont(fnt2);			
			g.drawString("Your time is: " + hud.getTime(), 250, 330);
			
			g.drawRect(250, 500, 300, 64);
			g.drawString("Back to Menu", 280, 545);
		}
	}
	
	
}
