package PELEAS;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Clase que hace de interfaz de usuario y que media entre el GameEngine y los BOTS. Realizado única y exclusivamente utilizando herramientas vistas en clase
 * @author kryon
 *
 */
public class UserInterface {

	GameEngine FightMap;
	ArrayList<BOT> PlayerList;
	Scanner input=new Scanner(System.in);
	int P1Selection,P2Selection;
	char P1Dec,P2Dec;
	private boolean exit=false,retry=true;
	private String keepPlaying;
	private int turnCounter=0;
	
	/**
	 * Constructor de la clase. Le pasamos la ArrayList de BOTs. BOT es la superclass, pero una lista puede contener clases hijas, 
	 * de modo que todo lo que le pasemos serán clases hijas (cada clase de cada participante)
	 * @param ListaDefinitiva ArrayList de subclases de la superclase "BOT"
	 * @throws InterruptedException Excepción que es de obligatorio uso cuando se usa la función sleep()
	 */
	public UserInterface(ArrayList<BOT> ListaDefinitiva) throws InterruptedException {
		
		//Le pasamos la lista a la clase interna de gestión "PList"
		
		PlayerList= ListaDefinitiva;
		
	}
	
	/**
	 * Método para filtrar los inputs del usuario cuando selecciona qué IAs se van a enfrentar
	 * @param min Número mínimo a introducir
	 * @param max Número máximo a introducir
	 * @return Integer (Elección -valor- aceptable dentro de los marcos fijados)
	 */
	public int inputIntMinMax(int min,int max) {
		boolean aceptable=true;
		int x=0;
		while(aceptable) {
			try {
				x=input.nextInt();
				if(x>=min && x<=max) {
					aceptable=false;
				}
				else {
					System.out.println("Introduce a correct selection");
					aceptable=true;
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Please, introduce an acceptable character");
				input.next();
				aceptable=true;
			}
		}
		return x;
	}
	/**
	 * Función que hace de menú donde se muestran las IAs de los jugadores y se seleccionan dos para enfrentarse entre sí
	 * @throws InterruptedException
	 */
	public void menu() throws InterruptedException {
		
		//Secuencia de escape que limpia la pantalla
		
		System.out.println("\033[H\033[2J");
		
		System.out.println("\nWELCOME TO:");
		
		System.out.println("\n" + 
				"                                                                                                                                                                                       \n" + 
				"                                                                                                                                                                                       \n" + 
				"BBBBBBBBBBBBBBBBB        OOOOOOOOO     TTTTTTTTTTTTTTTTTTTTTTT     FFFFFFFFFFFFFFFFFFFFFFIIIIIIIIII      GGGGGGGGGGGGGHHHHHHHHH     HHHHHHHHHTTTTTTTTTTTTTTTTTTTTTTT   SSSSSSSSSSSSSSS \n" + 
				"B::::::::::::::::B     OO:::::::::OO   T:::::::::::::::::::::T     F::::::::::::::::::::FI::::::::I   GGG::::::::::::GH:::::::H     H:::::::HT:::::::::::::::::::::T SS:::::::::::::::S\n" + 
				"B::::::BBBBBB:::::B  OO:::::::::::::OO T:::::::::::::::::::::T     F::::::::::::::::::::FI::::::::I GG:::::::::::::::GH:::::::H     H:::::::HT:::::::::::::::::::::TS:::::SSSSSS::::::S\n" + 
				"BB:::::B     B:::::BO:::::::OOO:::::::OT:::::TT:::::::TT:::::T     FF::::::FFFFFFFFF::::FII::::::IIG:::::GGGGGGGG::::GHH::::::H     H::::::HHT:::::TT:::::::TT:::::TS:::::S     SSSSSSS\n" + 
				"  B::::B     B:::::BO::::::O   O::::::OTTTTTT  T:::::T  TTTTTT       F:::::F       FFFFFF  I::::I G:::::G       GGGGGG  H:::::H     H:::::H  TTTTTT  T:::::T  TTTTTTS:::::S            \n" + 
				"  B::::B     B:::::BO:::::O     O:::::O        T:::::T               F:::::F               I::::IG:::::G                H:::::H     H:::::H          T:::::T        S:::::S            \n" + 
				"  B::::BBBBBB:::::B O:::::O     O:::::O        T:::::T               F::::::FFFFFFFFFF     I::::IG:::::G                H::::::HHHHH::::::H          T:::::T         S::::SSSS         \n" + 
				"  B:::::::::::::BB  O:::::O     O:::::O        T:::::T               F:::::::::::::::F     I::::IG:::::G    GGGGGGGGGG  H:::::::::::::::::H          T:::::T          SS::::::SSSSS    \n" + 
				"  B::::BBBBBB:::::B O:::::O     O:::::O        T:::::T               F:::::::::::::::F     I::::IG:::::G    G::::::::G  H:::::::::::::::::H          T:::::T            SSS::::::::SS  \n" + 
				"  B::::B     B:::::BO:::::O     O:::::O        T:::::T               F::::::FFFFFFFFFF     I::::IG:::::G    GGGGG::::G  H::::::HHHHH::::::H          T:::::T               SSSSSS::::S \n" + 
				"  B::::B     B:::::BO:::::O     O:::::O        T:::::T               F:::::F               I::::IG:::::G        G::::G  H:::::H     H:::::H          T:::::T                    S:::::S\n" + 
				"  B::::B     B:::::BO::::::O   O::::::O        T:::::T               F:::::F               I::::I G:::::G       G::::G  H:::::H     H:::::H          T:::::T                    S:::::S\n" + 
				"BB:::::BBBBBB::::::BO:::::::OOO:::::::O      TT:::::::TT           FF:::::::FF           II::::::IIG:::::GGGGGGGG::::GHH::::::H     H::::::HH      TT:::::::TT      SSSSSSS     S:::::S\n" + 
				"B:::::::::::::::::B  OO:::::::::::::OO       T:::::::::T           F::::::::FF           I::::::::I GG:::::::::::::::GH:::::::H     H:::::::H      T:::::::::T      S::::::SSSSSS:::::S\n" + 
				"B::::::::::::::::B     OO:::::::::OO         T:::::::::T           F::::::::FF           I::::::::I   GGG::::::GGG:::GH:::::::H     H:::::::H      T:::::::::T      S:::::::::::::::SS \n" + 
				"BBBBBBBBBBBBBBBBB        OOOOOOOOO           TTTTTTTTTTT           FFFFFFFFFFF           IIIIIIIIII      GGGGGG   GGGGHHHHHHHHH     HHHHHHHHH      TTTTTTTTTTT       SSSSSSSSSSSSSSS   \n" + 
				"                                                                                                                                                                                       \n" + 
				"                                                                                                                                                                                       \n" + 
				"                                                                                                                                                                                       \n" + 
				"                                                                                                                                                                                       \n" + 
				"                                                                                                                                                                                       \n" + 
				"                                                                                                                                                                                       \n" + 
				"                                                                                                                                                                                       \n" + 
				"\nKryonSoftwares");
		
		//Hacemos esperar tres segundos antes de limpiar el título inicial
		
		Thread.sleep(3000);
		
		System.out.println("\033[H\033[2J");
		
		//Procedemos a mostrar la lista completa de jugadores y a dejar al usuario seleccionar al PJ1
		
		System.out.println("\n\nChoose the player 1: ");
		
		for(int x=0;x<PlayerList.size();x++) {
			
			System.out.println("["+x+"] "+PlayerList.get(x).getName());
				
		}
		
		P1Selection=inputIntMinMax(0,(PlayerList.size()-1));
		
		
		System.out.println("\033[H\033[2J");
		
		//Procedemos a mostrar la lista completa de jugadores y a dejar al usuario seleccionar al PJ2
		
		System.out.println("\n\nChoose the player 2: ");
		
		for(int x=0;x<PlayerList.size();x++) {
			
			System.out.println("["+x+"] "+PlayerList.get(x).getName());
				
		}
		
		P2Selection=inputIntMinMax(0,(PlayerList.size()-1));
		
		//Inicializamos el GameEngine llamado FightMap pasándole los nombres de las dos IAs seleccionadas
		
		FightMap= new GameEngine(player1SelectionName(),player2SelectionName());
		
	}
	/**
	 * Método para extraer el nombre de una IA
	 * @return P1Name
	 */
	public String player1SelectionName() {
		
		String P1Name="";
		
		P1Name=PlayerList.get(P1Selection).getName();
		
		return P1Name;
	
	}
	/**
	 * Método para extraer el nombre de una IA
	 * @return P2Name
	 */
	public String player2SelectionName() {
			
		String P2Name="";
		
		P2Name=PlayerList.get(P2Selection).getName();
		
		
		return P2Name;
		
	}
	/**
	 * Método que llama a cada una de las dos IAs que están jugando y les pide que le devuelva un char, que es su próxima acción
	 * @param takeDecission (es una copia del estado actual de FightMap para que las IAs vean qué está pasando y qué va apasar a continuación)
	 */
	public void PlayersDecissions(GameEngine takeDecission) {
		
		
		P1Dec=PlayerList.get(P1Selection).getDecission(takeDecission);
		
		P2Dec=PlayerList.get(P2Selection).getDecission(takeDecission);
		
	}
	/**
	 * Método que llama a PlayerDecissions y que después pasa a FightMap las decisiones que PlayerDecissions acaba de escribir en P1Dec y P2Dec
	 * @param actualStatus
	 */
	public void PlayersActions(GameEngine actualStatus) {
		
		PlayersDecissions(actualStatus);
		
				FightMap.actionPlayers(P1Dec, P2Dec);
		
	}
	/**
	 * Método que ejecuta la partida en sí
	 * @throws InterruptedException
	 */
	public void LetsFight() throws InterruptedException {
		
		//"Mientras se quiera seguir jugando haz lo siguiente..."
		
		while(exit==false) {
			
			menu();
		
			//Inicializamos el mapa del juego
			
		FightMap.printMap();
		
		//Ponemos el contador de subturnos (que controla la aparición de las RedZones) a cero
		
		turnCounter=0;
		
		//"Mientras no se haya acabado la partida haz lo siguiente..."
		
		while(FightMap.isGameEnded()==false) {
			
			//Que los jugadores transmitan su decisión
			
			PlayersActions(FightMap);
			
			//Cuando haya pasado un minuto comienza a ir ejecutando la zona roja
			
			if(turnCounter>119) {
				if(turnCounter%10==0) {
					FightMap.redZone();
				}
			}
			
			//Proceso que ejecuta cada avance de las balas dando una décima de segundo de espera entre cada movimiento de éstas
			
			Thread.sleep(100);
			FightMap.updateMap();
			Thread.sleep(100);
			FightMap.updateMap();
			Thread.sleep(100);
			FightMap.updateMap();
			Thread.sleep(100);
			FightMap.updateMap();
			Thread.sleep(100);
			FightMap.updateMap();
			
			//Aumentamos el contador de subturno
			
			turnCounter++;
			
			//Volvemos a empezar con una nueva decisión de los jugadores (ha pasado medio segundo)
			
		}
		
		//Menú para decidir si seguimos jugando o salimos del juego
		
		System.out.println("\n\nDo you want to fight again? [Y]/[N]");
		
		retry=true;

			while(retry) {
				
				input.nextLine();
				
					keepPlaying=input.next().toUpperCase();
					
					if(keepPlaying.equals("Y") ){
							exit=false;
							retry=false;
						}
					if(keepPlaying.equals("N")) {
							
							System.out.println("\nSee you soon!\n" + 
									"                                                                                                                                                                                       \n" + 
									"                                                                                                                                                                                       \n" + 
									"BBBBBBBBBBBBBBBBB        OOOOOOOOO     TTTTTTTTTTTTTTTTTTTTTTT     FFFFFFFFFFFFFFFFFFFFFFIIIIIIIIII      GGGGGGGGGGGGGHHHHHHHHH     HHHHHHHHHTTTTTTTTTTTTTTTTTTTTTTT   SSSSSSSSSSSSSSS \n" + 
									"B::::::::::::::::B     OO:::::::::OO   T:::::::::::::::::::::T     F::::::::::::::::::::FI::::::::I   GGG::::::::::::GH:::::::H     H:::::::HT:::::::::::::::::::::T SS:::::::::::::::S\n" + 
									"B::::::BBBBBB:::::B  OO:::::::::::::OO T:::::::::::::::::::::T     F::::::::::::::::::::FI::::::::I GG:::::::::::::::GH:::::::H     H:::::::HT:::::::::::::::::::::TS:::::SSSSSS::::::S\n" + 
									"BB:::::B     B:::::BO:::::::OOO:::::::OT:::::TT:::::::TT:::::T     FF::::::FFFFFFFFF::::FII::::::IIG:::::GGGGGGGG::::GHH::::::H     H::::::HHT:::::TT:::::::TT:::::TS:::::S     SSSSSSS\n" + 
									"  B::::B     B:::::BO::::::O   O::::::OTTTTTT  T:::::T  TTTTTT       F:::::F       FFFFFF  I::::I G:::::G       GGGGGG  H:::::H     H:::::H  TTTTTT  T:::::T  TTTTTTS:::::S            \n" + 
									"  B::::B     B:::::BO:::::O     O:::::O        T:::::T               F:::::F               I::::IG:::::G                H:::::H     H:::::H          T:::::T        S:::::S            \n" + 
									"  B::::BBBBBB:::::B O:::::O     O:::::O        T:::::T               F::::::FFFFFFFFFF     I::::IG:::::G                H::::::HHHHH::::::H          T:::::T         S::::SSSS         \n" + 
									"  B:::::::::::::BB  O:::::O     O:::::O        T:::::T               F:::::::::::::::F     I::::IG:::::G    GGGGGGGGGG  H:::::::::::::::::H          T:::::T          SS::::::SSSSS    \n" + 
									"  B::::BBBBBB:::::B O:::::O     O:::::O        T:::::T               F:::::::::::::::F     I::::IG:::::G    G::::::::G  H:::::::::::::::::H          T:::::T            SSS::::::::SS  \n" + 
									"  B::::B     B:::::BO:::::O     O:::::O        T:::::T               F::::::FFFFFFFFFF     I::::IG:::::G    GGGGG::::G  H::::::HHHHH::::::H          T:::::T               SSSSSS::::S \n" + 
									"  B::::B     B:::::BO:::::O     O:::::O        T:::::T               F:::::F               I::::IG:::::G        G::::G  H:::::H     H:::::H          T:::::T                    S:::::S\n" + 
									"  B::::B     B:::::BO::::::O   O::::::O        T:::::T               F:::::F               I::::I G:::::G       G::::G  H:::::H     H:::::H          T:::::T                    S:::::S\n" + 
									"BB:::::BBBBBB::::::BO:::::::OOO:::::::O      TT:::::::TT           FF:::::::FF           II::::::IIG:::::GGGGGGGG::::GHH::::::H     H::::::HH      TT:::::::TT      SSSSSSS     S:::::S\n" + 
									"B:::::::::::::::::B  OO:::::::::::::OO       T:::::::::T           F::::::::FF           I::::::::I GG:::::::::::::::GH:::::::H     H:::::::H      T:::::::::T      S::::::SSSSSS:::::S\n" + 
									"B::::::::::::::::B     OO:::::::::OO         T:::::::::T           F::::::::FF           I::::::::I   GGG::::::GGG:::GH:::::::H     H:::::::H      T:::::::::T      S:::::::::::::::SS \n" + 
									"BBBBBBBBBBBBBBBBB        OOOOOOOOO           TTTTTTTTTTT           FFFFFFFFFFF           IIIIIIIIII      GGGGGG   GGGGHHHHHHHHH     HHHHHHHHH      TTTTTTTTTTT       SSSSSSSSSSSSSSS   \n" + 
									"                                                                                                                                                                                       \n" + 
									"                                                                                                                                                                                       \n" + 
									"                                                                                                                                                                                       \n" + 
									"                                                                                                                                                                                       \n" + 
									"                                                                                                                                                                                       \n" + 
									"                                                                                                                                                                                       \n" + 
									"                                                                                                                                                                                       \n" + 
									"\nKryonSoftwares");
							Thread.sleep(1000);
							exit=true;
							retry=false;
					}
					if(!(keepPlaying.equals("N")) && !(keepPlaying.equals("Y"))) {
						System.out.println("Introduce only <<Y>> or <<N>>");
						exit=false;
						retry=true;
					}
				}
			}
		}
	
}
