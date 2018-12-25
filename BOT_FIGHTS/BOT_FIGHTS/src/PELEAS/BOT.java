package PELEAS;


/**
 * Superclase que cada jugador debe extender en su clase IA
 * @author kryon
 *
 */
public class BOT {

	//Nombre que tendrá la IA. Saldrá en el menú de selección y en la pantalla al finalizar la partida (si ha ganado)
	
	private String name="";
	
	/**
	 * Método para leer el nombre de cada IA
	 * @return name (Nombre de la IA en concreto)
	 */
	public String getName() {
		return name;
	}
	/**
	 * Método para recoger el char que es la decisión de cada IA teniendo en cuenta el estado actual de la partida
	 * @param map (Estado actual del mapa de la partida en juego)
	 * @return decission (Char) ['N' (Mover hacia el norte) || 'S' (Mover hacia el sur) || 'E' (Mover hacia el este) || 'W' (Mover hacia el oeste) || 'P' (No mover) ||
	 *  '8' (Disparar hacia el norte) || '2' (Disparar hacia el sur) || '6' (Disparar hacia el este) || '4' (Disparar hacia el oeste)]
	 */
	public char getDecission(GameEngine map) {
		
		char decission='?';
		
		return decission;
	}
	
	
}
