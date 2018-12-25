package PELEAS;

import java.util.ArrayList;

/**
 * Clase para ejecutar el juego en clase
 * @author kryon
 *
 */
public class PREXVol01{
	
	public static void main(String[] args) throws InterruptedException{
		
		/*
		 * 
		 * Aquí cargaríamos las clases de los jugadores que se presenten
		 * 
		 */
		
		ArrayList<BOT> ListaDeJugadores=new ArrayList<BOT>();
		
		/*
		 * 
		 * Aquí hay que cargar (.add) en la lista todos los jugadores que se hayan presentado
		 * 
		 * */
		
		
		UserInterface Juego=new UserInterface(ListaDeJugadores);
		
		Juego.LetsFight();
	}
}