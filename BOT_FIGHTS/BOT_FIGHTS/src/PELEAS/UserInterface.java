package PELEAS;

import java.util.InputMismatchException;
import java.util.Scanner;
                                                 /*ARREGLAR SELECCIONES DE MENÚ URGENTEMENTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE*/
public class UserInterface {

	GameEngine FightMap;
	PList PlayerList;
	Scanner input=new Scanner(System.in);
	int P1Selection;
	int P2Selection;
	char P1Dec;
	char P2Dec;
	private boolean exit=false;
	private String keepPlaying;
	private boolean retry=true;
	private int turnCounter=0;
	
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
					System.out.println("Introduce an correct selection");
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
	
	public UserInterface(BOT1 P1,BOT2 P2,BOT3 P3,BOT4 P4) throws InterruptedException {
		
		PlayerList= new PList();
		PlayerList.setPList(P1,P2,P3,P4);
		
	}
	
	public class PList{
		
		BOT1 P1;
		BOT2 P2;
		BOT3 P3;
		BOT4 P4;
		
		public void setPList(BOT1 P1,BOT2 P2,BOT3 P3,BOT4 P4) {
			
			this.P1=P1;
			this.P2=P2;
			this.P3=P3;
			this.P4=P4;
			
		}
	}
	
	public void menu() throws InterruptedException {
		
		FightMap= new GameEngine(player1SelectionName(),player2SelectionName());
		
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
		
		Thread.sleep(3000);
		
		System.out.println("\033[H\033[2J");
		
		
		
		System.out.println("\n\nChoose the player 1: \n\n\n\n[1]"+PlayerList.P1.getName()+"\n"+
				"\n[2]"+PlayerList.P2.getName()+"\n"+
				"\n[3]"+PlayerList.P3.getName()+"\n"+
				"\n[4]"+PlayerList.P4.getName());
		
		P1Selection=inputIntMinMax(1,4);
		
		
		System.out.println("\033[H\033[2J");
		
		System.out.println("\n\nChoose the player 2: \n\n\n\n[1]"+PlayerList.P1.getName()+"\n"+
				"\n[2]"+PlayerList.P2.getName()+"\n"+
				"\n[3]"+PlayerList.P3.getName()+"\n"+
				"\n[4]"+PlayerList.P4.getName());
		
		P2Selection=inputIntMinMax(1,4);
		
		
	}
	
	public String player1SelectionName() {
		
		String P1Name="";
		
		switch(P1Selection) {
		
		case 1:
			P1Name=PlayerList.P1.getName();
			break;
		case 2:
			P1Name=PlayerList.P2.getName();
			break;
		case 3:
			P1Name=PlayerList.P3.getName();
			break;
		case 4:
			P1Name=PlayerList.P4.getName();
			break;
		default:
			System.out.println("Huge error happened on MENU method in FIGHT class");
		}
		
		return P1Name;
	
	}
		
		public String player2SelectionName() {
			
			String P2Name="";
			
			switch(P2Selection) {
			
			case 1:
				P2Name=PlayerList.P1.getName();
				break;
			case 2:
				P2Name=PlayerList.P2.getName();
				break;
			case 3:
				P2Name=PlayerList.P3.getName();
				break;
			case 4:
				P2Name=PlayerList.P4.getName();
				break;
			default:
				System.out.println("Huge error happened on MENU method in FIGHT class");
		
		}
		
		return P2Name;
		
	}
	
	public void PlayersDecissions(GameEngine takeDecission) {
		
		switch(P1Selection) {
		
			case 1:
				P1Dec=PlayerList.P1.getDecission(takeDecission);
				break;
			case 2:
				P1Dec=PlayerList.P2.getDecission(takeDecission);
				break;
			case 3:
				P1Dec=PlayerList.P3.getDecission(takeDecission);
				break;
			case 4:
				P1Dec=PlayerList.P4.getDecission(takeDecission);
				break;
			default:
				System.out.println("Huge error happened on MENU method in FIGHT class");
		
		}
		
		switch(P2Selection) {
		
			case 1:
				P2Dec=PlayerList.P1.getDecission(takeDecission);
				break;
			case 2:
				P2Dec=PlayerList.P2.getDecission(takeDecission);
				break;
			case 3:
				P2Dec=PlayerList.P3.getDecission(takeDecission);
				break;
			case 4:
				P2Dec=PlayerList.P4.getDecission(takeDecission);
				break;
			default:
				System.out.println("Huge error happened on MENU method in FIGHT class");
		}
		
		
	}
	
	public void PlayersActions(GameEngine actualStatus) {
		
		PlayersDecissions(actualStatus);
		
				FightMap.actionPlayers(P1Dec, P2Dec);
		
	}
	
	public void LetsFight() throws InterruptedException {
		
		while(exit==false) {
			
			menu();
		
		FightMap.printMap();
		
		while(FightMap.isGameEnded()==false) {//////está aquí el problema del loop?**********************************************************
			
			
			PlayersActions(FightMap);
			if(turnCounter>119) {
				if(turnCounter%10==0) {
					FightMap.redZone();
				}
			}
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
			turnCounter++;
			
		}
		
		System.out.println("\n\nDo you want to fight again? [Y]/[N]");
		
		retry=true;

			while(retry) {
				
				input.next();//COMPRPBAR SI ESTO HA ARREGLADO EL PROBLEMAAAAAA***************************************************
					keepPlaying=input.next().toUpperCase();
					System.out.println(keepPlaying);
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
