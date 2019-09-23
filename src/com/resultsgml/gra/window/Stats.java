package com.resultsgml.gra.window;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Klasa odpowiadajaca za wczytanie i zapisywanie statystyk do listy i pliku.
 * @author Bartosz Kwietniewski
 *
 */
public class Stats 
{
	
	private List<Integer> stats = new ArrayList<Integer>();
	/**
	 * Wczytanie statystyk z pliku do listy.
	 */
    public Stats()
    {
    	FileReader fileReader = null;
    	BufferedReader br = null;
        
    	try 
        {
        	fileReader = new FileReader("resources\\liczby.txt");
        	br = new BufferedReader(fileReader);
            String linia = null;

            while ((linia = br.readLine()) != null) 
            {
                int liczba = Integer.parseInt(linia.trim());
                addToArray(liczba);
            }
        } 
        catch (Exception e) 
        {
            System.err.println("Wystapil blad przy wczytywaniu danych");
            e.printStackTrace();
        }
    	finally
    	{
    		try 
    		{
				fileReader.close();
				br.close();
			} 
    		catch (IOException e) 
    		{
                System.err.println("Wystapil blad przy wczytywaniu danych");
				e.printStackTrace();
			}
    	}
    }
    /**
	 *Dodanie wyniku do listy.
	 *@param liczba wartosc typu int.
	 */
	public void addToArray(int liczba) 
	{
        stats.add(liczba);
    }
	/**
	 * Pobranie wyniku z listy.
	 * @param indeks numer elementu listy.
	 * @return indeks elementu listy.
	 */
    public int get(int indeks) 
    {
    	sort();
        return stats.get(indeks);
    }
    /**
     * Sortowanie listy z wynikami.
     */
    public void sort()
    {
    	Collections.sort(stats);
    }
    /**
     * Pobranie wielkosci listy z wynikami.
     * @return wielkosc listy.
     */
    public int getSize() 
    {
        return stats.size();
    }
    /**
     * Zapis wyniku do pliku oraz dodanie do listy.
     * @param liczba wartosc typu int wyniku.
     */
    public void writeToFile(int liczba)
    {    	addToArray(liczba); 
	 
    	FileWriter fileWriter = null;    	BufferedWriter bufferedWriter = null;
	      	try     	{    		fileWriter = new FileWriter("resources\\liczby.txt", true);
    		bufferedWriter = new BufferedWriter(fileWriter);
    		bufferedWriter.newLine();
    		bufferedWriter.append(String.valueOf(liczba));
			    	} 
    	catch (IOException e) 
    	{
            System.err.println("Wystapil blad przy zapisywaniu danych");
    		e.printStackTrace();
		} 
    	finally
    	{
    		try 
    		{
    			bufferedWriter.close();
    			fileWriter.close();
			} 
    		catch (IOException e) 
    		{
                System.err.println("Wystapil blad przy zapisywaniu danych");
				e.printStackTrace();
			}
    	}	 }
	
    
}
