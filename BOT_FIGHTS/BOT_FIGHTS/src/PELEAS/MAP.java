package PELEAS;

//---------------------------------------Hecho por KRYON----------------------------------------------------------------\\

//Creamos objeto que generará el mapa

public class MAP {
	
	//Generamos una serie de variables que usaremos en métodos y demás herramientas
	
	private boolean mapSlotGenerated=true;
	private boolean mapFlagGenerated=true;
		
		/*creamos el objeto "slot", que será cada una de las casillas de nuestro mapa.
		 Estas casillas servirán para indicar si hay un muro, una ventana o un jugador en esa posición*/
		
	private class slot {
			
		//Creamos booleans que determinan qué hay en cada posición del mapa
		
			boolean treeTF=false, bushTF=false, holeTF=false, shotTF=false,
					flagP1TF=false, flagP2TF=false, player1TF=false, player2TF=false, 
					P1TrailTF=false, P2TrailTF=false, redZone=false;
			
			//Creamos getters y setters
			
			public boolean isTreeTF() {
				return treeTF;
			}
			public void setTreeTF(boolean treeTF) {
				this.treeTF = treeTF;
			}
			public boolean isBushTF() {
				return bushTF;
			}
			public void setBushTF(boolean bushTF) {
				this.bushTF = bushTF;
			}
			public boolean isPlayer1TF() {
				return player1TF;
			}
			public void setPlayer1TF(boolean player1TF) {
				this.player1TF = player1TF;
			}
			public boolean isPlayer2TF() {
				return player2TF;
			}
			public void setPlayer2TF(boolean player2TF) {
				this.player2TF = player2TF;
			}
			public boolean isHoleTF() {
				return holeTF;
			}
			public void setHoleTF(boolean holeTF) {
				this.holeTF = holeTF;
			}
			public boolean isShotTF() {
				return shotTF;
			}
			public void setShotTF(boolean shotTF) {
				this.shotTF = shotTF;
			}
			public boolean isFlagP1TF() {
				return flagP1TF;
			}
			public void setFlagP1TF(boolean flagP1TF) {
				this.flagP1TF = flagP1TF;
			}
			public boolean isFlagP2TF() {
				return flagP2TF;
			}
			public void setFlagP2TF(boolean flagP2TF) {
				this.flagP2TF = flagP2TF;
			}
			public boolean isRedZone() {
				return redZone;
			}
			public void setRedZone(boolean redZone) {
				this.redZone = redZone;
			}
			public boolean isP1TrailTF() {
				return P1TrailTF;
			}
			public void setP1TrailTF(boolean P1TrailTF) {
				this.P1TrailTF = P1TrailTF;
			}
			public boolean isP2TrailTF() {
				return P2TrailTF;
			}
			public void setP2TrailTF(boolean P2TrailTF) {
				this.P2TrailTF = P2TrailTF;
			}	
			
		}
				
		//Array bidimensional que será nuestro mapa hecho de casillas
		
	slot[][] combatMapSlot=new slot[50][50];
		
		//Array bidimensional que será nuestra reproducción a colores de lo que hay en cada slot del mapa
		
	String[][] combatMapScreen=new String[50][50];
		
		//Métodos para inicializar las arrays
	
	public void initializeCombatMapSlot() {
		
		//Inicializamos la array
		
		for(int x=0;x<50;x++) {
			for(int y=0;y<50;y++) {
				combatMapSlot[x][y]=new slot();
			}
		}
	}

	public void initializeCombatMapScreen() {
		
		//Inicializamos la array
		
		for(int x=0;x<50;x++) {
			for(int y=0;y<50;y++) {
				combatMapScreen[x][y]="\033[1;47m   \033[0m";
			}
		}
	}
		//Método que genera el bosque y coloca los true de cada elemento en combatMapSlot
		
	public void generateForest() {
		int x;
		int y;
		int treeOrbushOrhole;
		int randForest=(int)(Math.random() * 200 + 50);
		
		//Creamos un número aleatorio de árboles y arbustos entre 50 y 100
		
		for(int i=0;i<randForest;i++) {
		
		/*Que busque posiciones hasta encontrar una FALSE y entonces la marque como TRUE.
		Así evitamos que sobreescriba sobre una posición ya usada*/
			
			mapSlotGenerated=true;
		
			while (mapSlotGenerated) {
			
				x=(int)(Math.random() * 50);
				y=(int)(Math.random() * 50);
			
				//Decidimos aleatoriamente si se coloca un árbol o un arbusto
			
				treeOrbushOrhole=(int)(Math.random() * 6);
			
				//0-2 arbusto. 3-4 árbol. 5 agujero
			
					if (combatMapSlot[x][y].isBushTF()==false && combatMapSlot[x][y].isTreeTF()==false && combatMapSlot[x][y].isHoleTF()==false) {
						if(treeOrbushOrhole<3) {
							combatMapSlot[x][y].setBushTF(true);
						}
						if(treeOrbushOrhole>2 && treeOrbushOrhole<5){
							combatMapSlot[x][y].setTreeTF(true);
						}
						if(treeOrbushOrhole==5){
							combatMapSlot[x][y].setHoleTF(true);
						}
					
						mapSlotGenerated=false;
					}
					else {
						mapSlotGenerated=true;
					}
				if(i==(randForest-1)) {
					
					for(int flagPos=0;flagPos<2;flagPos++) {
						
						if(flagPos==0) {
							while(mapFlagGenerated) {
								
								x=(int)(Math.random() * 50);
								y=(int)(Math.random() * 50);
								
								if (combatMapSlot[x][y].isBushTF()==false && combatMapSlot[x][y].isTreeTF()==false && combatMapSlot[x][y].isHoleTF()==false) {
								
									combatMapSlot[x][y].setFlagP1TF(true);
									mapFlagGenerated=false;
									
								}
								
							}
						}
						else {
							mapFlagGenerated=true;
							
							while(mapFlagGenerated) {
								
								x=(int)(Math.random() * 50);
								y=(int)(Math.random() * 50);
								
								if (combatMapSlot[x][y].isBushTF()==false && combatMapSlot[x][y].isTreeTF()==false && combatMapSlot[x][y].isHoleTF()==false && combatMapSlot[x][y].isFlagP1TF()==false) {
								
									combatMapSlot[x][y].setFlagP2TF(true);
									mapFlagGenerated=false;
									
								}
								
							}
						}
					}
				}
			}
		}
	}

		//Método para crear el mapa con colores dependiendo de qué elemento se encuentra en cada punto
	
	public void generateColorMap() {
		
		for(int x=0;x<50;x++) {
			for(int y=0;y<50;y++) {
					if(combatMapSlot[x][y].isBushTF()==true) {
						combatMapScreen[x][y]="\033[42m   \033[0m";
					}
					if(combatMapSlot[x][y].isTreeTF()==true) {
						combatMapScreen[x][y]="\033[41m   \033[0m";
					}
					if(combatMapSlot[x][y].isHoleTF()==true) {
						combatMapScreen[x][y]="\033[40m   \033[0m";
					}
					if(combatMapSlot[x][y].isFlagP1TF()==true) {
						combatMapScreen[x][y]="\033[1;47m\033[1;34m-1-\033[0m";
					}
					if(combatMapSlot[x][y].isFlagP2TF()==true) {
						combatMapScreen[x][y]="\033[1;47m\033[1;35m-2-\033[0m";
					}
			}
		}
		
	}

		//Método para crear todo el conjunto del mapa y printearlo
	
	public void printMap() {
		
		generateForest();
		generateColorMap();
		for(int y=0;y<50;y++) {
			for(int x=0;x<50;x++) {
				System.out.print(combatMapScreen[x][y]);
				if(x==49) {
					System.out.print("\n");
				}
			}
		}
	}

	public static void main(String[] args) {
		MAP prueba=new MAP();
		prueba.initializeCombatMapSlot();
		prueba.initializeCombatMapScreen();
		prueba.printMap();
	}
	
}