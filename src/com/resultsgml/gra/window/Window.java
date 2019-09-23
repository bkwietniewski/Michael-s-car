package com.resultsgml.gra.window;

import java.awt.Dimension;
import javax.swing.JFrame;
/**
 * Klasa odpowiadajaca za utworzenie okna gry, nadanie tytulu i uruchomienie gry.
 * @author Bartosz Kwietniewski
 *
 */
public class Window 
{
	/**
	 * Konstruktor klasy Window.
	 * @param w szerokosc okna.
	 * @param h wysokosc okna.
	 * @param tittle tytul okna.
	 * @param game obiekt klasy Game (tworzy gre).
	 */
	public Window (int w, int h, String tittle, Game game)
	{
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		
		JFrame frame = new JFrame(tittle);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
}
