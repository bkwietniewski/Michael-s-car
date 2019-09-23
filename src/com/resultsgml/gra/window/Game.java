package com.resultsgml.gra.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.resultsgml.gra.framework.KeyInput;
import com.resultsgml.gra.framework.Texture;
/**
 * Glowna klasa gry. Tutaj jest wywolywana funkcja main. 
 * @author Bartosz Kwietniewski
 *
 */
public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 2114577005642121823L;
	
	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	public static int LEVEL = 0;
	
	private BufferedImage levelStart;	
	/**
	 * Zbior mozliwych wartosci dla wywolan Menu, Stats, Game, End.
	 * @author Bartosz Kwietniewski
	 *
	 */
	public enum STATE
	{
		Menu,
		Stats,
		Game,
		End
	};
	
	public STATE gameState = STATE.Menu;
	
//	object
	Handler handler;
	HUD hud;
	Menu menu;
	Stats stats;
	static Texture tex;
	/**
	 * Metoda inicjuajaca dzialanie obiektow: pokazanie hud, wczytywanie pierwszego poziomu, inicjalizacja strumieni wejscia.
	 */
	private void init()
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		levelStart = loader.loadImage("/levelStart.png");	// loading the level
		
		handler = new Handler();
		hud = new HUD();
		stats = new Stats();
		menu = new Menu(this, handler, hud, stats);
		
		if(gameState == STATE.Game)
		{
			handler.loadImageLevel(levelStart);
		}
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
	}
	/**
	 * Metoda odpowiedzialna za dzialanie gry.
	 */
	public synchronized void start()
	{
		if(running) return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	/**
	 * Metoda odpowiedzialna za prawidlowe dzialanie, odswiezanie,
	 * wyswietlanie wszysztkich obiektow.
	 * Wywoluje metode init oraz pokazuje ilosc FPS.
	 */
	public void run()
	{
		init();
		this.requestFocus();		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	/**
	 * Metoda obslugujaca rodzaj dzialania danego elementu (obiektu) jak menu, statystyki oraz dzialanie licznika podczas gry.
	 * Zapisanie wyniku do pliku i listy.
	 */
	private void tick()
	{
		handler.tick();
		
		if(gameState == STATE.Game)
		{
			hud.tick();		
			
			if(LEVEL == -1)
			{
				stats.writeToFile(hud.getTime());
				handler.clearLevel();
				gameState = STATE.End;
			}
		}
		else if(gameState == STATE.Menu || gameState == STATE.End)
		{
			menu.tick();
		}
	}
	/**
	 * Metoda rysujaca elementy (obiekty) takie jak menu, statystyki, ekran konczacy z wynikiem.
	 */
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);		// liczba "wczytywanych" (ktore sa pozniej wyswietlane) obrazow
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//////////////////////////////////
		
		if(gameState == STATE.Game)
		{				
			g.setColor(Color.black);		// game background color
			g.fillRect(0, 0, getWidth(), getHeight());
			handler.render(g);
			hud.render(g);
		}
		else if(gameState == STATE.Menu || gameState == STATE.Stats || gameState == STATE.End)
		{
			g.setColor(Color.white);		// other background color
			g.fillRect(0, 0, getWidth(), getHeight());
			menu.render(g);
		}
		
		//////////////////////////////////
		g.dispose();
		bs.show();
		
	}
	/**
	 * Zwracanie obiektu klasy Texture.
	 * @return obiekt klasy Texture.
	 */
	public static Texture getInstance()
	{
		return tex;
	}
	/**
	 *Funkcja main, towrzenie okna.
	 *@param args .
	 */
	public static void main(String args[])
	{
		new Window(800, 600, "Michael's car", new Game());
	}
	
}
