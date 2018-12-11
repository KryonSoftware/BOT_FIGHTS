package PELEAS;

/*---------------------------------------------------------------------------Hecho por KRYON------------------------------------------------------------------------------*/


/*ARREGLAR COLISIONES ENTIDADES. ARREGLAR IMPRIMACIONES TANTO DE BALAS COMO DE ENTIDADES CUANDO SE MUEVAN EN CONTRA DEL SENTIDO DE UPDATEMAP. OJO COLISIONES BALAS TAMBIÉN*/


//Creamos objeto que generará el mapa

public class GameEngine {
	
	//Generamos una serie de variables que usaremos en métodos y demás herramientas
	
	private boolean mapSlotGenerated=true;
	private boolean mapFlagGenerated=true;
	private int redZoneCalls=0;
	private boolean gameEnded=false;
	private int P1Kill=0;
	private int P2Kill=0;
	private String winner="NO ONE";
	private int turn=0;
	private boolean player1StartingPos=true;
	private boolean player2StartingPos=true;
	private String BOT1Name, BOT2Name;
	private boolean PJ1Red,PJ2Red, PJ1Stunned=false, PJ2Stunned=false;
	
	//888888888888888888888888888888888888888888888888888888888888888888888888kkkkkkkkk
	
	public GameEngine(String bot1, String bot2) {
		BOT1Name=bot1;
		BOT2Name=bot2;
		
	}
		
		/*creamos el objeto <<slot>>, que será cada una de las casillas de nuestro mapa. Incluye dentro los "subobjetos" <<bullet>> y <<player>>.
		 Estas casillas servirán para indicar si hay un árbol, un arbusto, un agujero, un disparo, zona roja, una bandera o un jugador en esa posición*/
	
	private class bullet{
		
		private boolean bulletHere=false, justUpdated=false;
		
		private char bulletDir;

		public boolean isBulletHere() {
			return bulletHere;
		}

		public void setBulletHere(boolean bulletHere) {
			this.bulletHere = bulletHere;
		}

		public char getBulletDir() {
			return bulletDir;
		}

		public void setBulletDir(char bulletDir) {
			this.bulletDir = bulletDir;
		}

		public boolean isJustUpdated() {
			return justUpdated;
		}

		public void setJustUpdated(boolean justUpdated) {
			this.justUpdated = justUpdated;
		}
		
	}
	
	private class player{
		
		private boolean playerHere=false, justUpdated=false, carryFlagPJ1=false, carryFlagPJ2=false;
		
		private char playerDir;

		public boolean isPlayerHere() {
			return playerHere;
		}

		public void setPlayerHere(boolean playerHere) {
			this.playerHere = playerHere;
		}

		public boolean isJustUpdated() {
			return justUpdated;
		}

		public void setJustUpdated(boolean justUpdated) {
			this.justUpdated = justUpdated;
		}

		public char getPlayerDir() {
			return playerDir;
		}

		public void setPlayerDir(char playerDir) {
			this.playerDir = playerDir;
		}

		public boolean isCarryFlagPJ1() {
			return carryFlagPJ1;
		}

		public void setCarryFlagPJ1(boolean carryFlagPJ1) {
			this.carryFlagPJ1 = carryFlagPJ1;
		}

		public boolean isCarryFlagPJ2() {
			return carryFlagPJ2;
		}

		public void setCarryFlagPJ2(boolean carryFlagPJ2) {
			this.carryFlagPJ2 = carryFlagPJ2;
		}
		
	}
	
	private class slot {
			
		//Creamos booleans que determinan qué hay en cada posición del mapa
		
			private boolean treeTF=false, bushTF=false, holeTF=false,
					flagP1TF=false, flagP2TF=false, redZone=false;
			
			//Creamos una instancia de bullet que nos pasará la info de si hay bala o no y su dirección y de quién es
			
			private bullet shotPJ1=new bullet();
			private bullet shotPJ2=new bullet();
			
			//ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff
			
			private player player1=new player();
			private player player2=new player();
			
			//Métodos para modificar los subobjetos de slot desde GameEngine*********************************************************************!!!!!!!!!!!!!!!!!!
			
			public void moveBullet(int who, char D) {
				
				switch (who) {
				case 1:
					
					shotPJ1.setBulletHere(true);
					shotPJ1.setBulletDir(D);
					
					break;
				
				case 2:
				
					shotPJ2.setBulletHere(true);
					shotPJ2.setBulletDir(D);
				
			}
					
				
			}
			
			public void removeBullet(int who) {
				
				switch (who) {
				case 1:
					
					shotPJ1.setBulletHere(false);
					
					break;
				
				case 2:
				
					shotPJ2.setBulletHere(false);
				
			}
				
			}
			
			public void updateBulletStatus(int who, boolean tf) {
				
				switch (who) {
				case 1:
					
					shotPJ1.setJustUpdated(tf);
					
					break;
				
				case 2:
				
					shotPJ2.setJustUpdated(tf);
				
			}
				
			}
			
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
			public boolean isHoleTF() {
				return holeTF;
			}
			public void setHoleTF(boolean holeTF) {
				this.holeTF = holeTF;
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
			
		}
	
	public int nextTurn(){
		turn++;
		return turn;
	}
	public int getTurn() {
		return turn;
	}
	public boolean isGameEnded() {
		return gameEnded;
	}
	public void setGameEnded(boolean gameEnded) {
		this.gameEnded = gameEnded;
	}
	public boolean isPJ1Stunned() {
		return PJ1Stunned;
	}

	public void setPJ1Stunned(boolean stunned) {
		this.PJ1Stunned = stunned;
	}
	public boolean isPJ2Stunned() {
		return PJ2Stunned;
	}

	public void setPJ2Stunned(boolean stunned) {
		this.PJ2Stunned = stunned;
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
			
				//Decidimos aleatoriamente si se coloca un árbol o un arbusto o un agujero
			
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
		
		player1StartingPos=true;
		player2StartingPos=true;
		
		while(player1StartingPos) {
			
			x=(int)(Math.random() * 50);
			y=(int)(Math.random() * 50);
			
			if (combatMapSlot[x][y].isBushTF()==false && combatMapSlot[x][y].isTreeTF()==false && combatMapSlot[x][y].isHoleTF()==false && combatMapSlot[x][y].isFlagP1TF()==false) {
			
				combatMapSlot[x][y].player1.setPlayerHere(true);
				player1StartingPos=false;
				
			}
			
		}
		
		while(player2StartingPos) {
			
			x=(int)(Math.random() * 50);
			y=(int)(Math.random() * 50);
			
			if (combatMapSlot[x][y].isBushTF()==false && combatMapSlot[x][y].isTreeTF()==false && combatMapSlot[x][y].isHoleTF()==false && combatMapSlot[x][y].isFlagP1TF()==false && combatMapSlot[x][y].player1.isPlayerHere()==false) {
			
				combatMapSlot[x][y].player2.setPlayerHere(true);
				player2StartingPos=false;
				
			}
			
		}
		
	}

		//Método para crear el mapa con colores dependiendo de qué elemento se encuentra en cada punto
	
	public void generateColorMap(int x, int y) {
		
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
					if(combatMapSlot[x][y].isRedZone()==true) {
						combatMapScreen[x][y]="\033[1;41m   \033[0m";
					}
					if(combatMapSlot[x][y].shotPJ1.isBulletHere()==true) {
						combatMapScreen[x][y]="\033[1;47m\033[1;34m + \033[0m";
					}
					if(combatMapSlot[x][y].shotPJ2.isBulletHere()==true) {
						combatMapScreen[x][y]="\033[1;47m\033[1;35m + \033[0m";
					}
					if(combatMapSlot[x][y].player1.isPlayerHere()==true) {
						combatMapScreen[x][y]="\033[1;44mIA1\033[0m";
					}
					if(combatMapSlot[x][y].player2.isPlayerHere()==true) {
						combatMapScreen[x][y]="\033[1;45mIA2\033[0m";
					}
					if(combatMapSlot[x][y].player1.isCarryFlagPJ2()==true) {
						combatMapScreen[x][y]="\033[1;44m-2-\033[0m";
					}
					if(combatMapSlot[x][y].player2.isCarryFlagPJ1()==true) {
						combatMapScreen[x][y]="\033[1;45m-1-\033[0m";
					}
		
	}

		//Método para crear todo el conjunto del mapa y printearlo
	
	public void printMap() {
		
		System.out.println("\033[H\033[2J");
		
		initializeCombatMapSlot();
		initializeCombatMapScreen();
		generateForest();
		for(int y=0;y<50;y++) {
			for(int x=0;x<50;x++) {
				generateColorMap(x,y);
				System.out.print(combatMapScreen[x][y]);
				if(x==49) {
					System.out.print("\n");
				}
			}
		}
	}

	public void updateShots(int x,int y) throws IllegalArgumentException {
		
		if(combatMapSlot[x][y].isRedZone()==false) {
		
		if(combatMapSlot[x][y].shotPJ1.isBulletHere()==true && combatMapSlot[x][y].shotPJ1.isJustUpdated()==false) {
			
			switch (combatMapSlot[x][y].shotPJ1.getBulletDir()) {
				
				case 'N':
					
					try {
						
						combatMapSlot[x][y-1].moveBullet(1,'N');
						combatMapSlot[x][y-1].updateBulletStatus(1, true);
						combatMapScreen[x][y]="\033[1;47m   \033[0m";
						combatMapSlot[x][y].removeBullet(1);
						
						/*
						 * 
						 * combatMapSlot[x][y-1].moveBullet(1,'N');
						 * 
						 * removeBullet(x,y,1);
						 * 
						 * combatMapScreen[x][y]="\033[1;47m   \033[0m";
						 * 
						 * */
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
												
						combatMapScreen[x][y]="\033[1;47m   \033[0m";
						combatMapSlot[x][y].shotPJ1.setBulletHere(false);
						
					}
					
					break;
					
				case 'S':
					
					try {
						
						combatMapSlot[x][y+1].shotPJ1.setBulletHere(true);
						combatMapSlot[x][y].shotPJ1.setBulletHere(false);
						combatMapSlot[x][y+1].shotPJ1.setJustUpdated(true);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].shotPJ1.setBulletHere(false);
						
					}
					
					break;
					
				case 'E':
					
					try {
						
						combatMapSlot[x+1][y].shotPJ1.setBulletHere(true);
						combatMapSlot[x][y].shotPJ1.setBulletHere(false);
						combatMapSlot[x+1][y].shotPJ1.setJustUpdated(true);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].shotPJ1.setBulletHere(false);
						
					}
					
					break;
					
				case 'W':
					
					try {
						
						combatMapSlot[x-1][y].shotPJ1.setBulletHere(true);
						combatMapSlot[x][y].shotPJ1.setBulletHere(false);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].shotPJ1.setBulletHere(false);
						
					}
					
					break;
					
				default:
						
					throw new IllegalArgumentException(String.format("HUGE ERROR happened on method updateShots(int x, int y)."+
					"\nPossibly a non acceptable CHAR was inputed on the bullet direction of shotPJ1: "+combatMapSlot[x][y].shotPJ1.getBulletDir()));
					
			}
			
		}
			
		if(combatMapSlot[x][y].shotPJ1.isBulletHere()==true && combatMapSlot[x][y].shotPJ1.isJustUpdated()==true) {
			
			combatMapSlot[x][y].updateBulletStatus(1, false);
		}
		
		if(combatMapSlot[x][y].shotPJ2.isBulletHere()==true && combatMapSlot[x][y].shotPJ2.isJustUpdated()==false) {
			
			switch (combatMapSlot[x][y].shotPJ2.getBulletDir()) {
				
				case 'N':
					
					try {
						
						combatMapSlot[x][y-1].shotPJ2.setBulletHere(true);
						combatMapSlot[x][y].shotPJ2.setBulletHere(false);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].shotPJ2.setBulletHere(false);
						
					}
					
					break;
					
				case 'S':
					
					try {
						
						combatMapSlot[x][y+1].shotPJ2.setBulletHere(true);
						combatMapSlot[x][y].shotPJ2.setBulletHere(false);
						combatMapSlot[x][y+1].shotPJ2.setJustUpdated(true);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].shotPJ2.setBulletHere(false);
						
					}
					
					break;
					
				case 'E':
					
					try {
						
						combatMapSlot[x+1][y].shotPJ2.setBulletHere(true);
						combatMapSlot[x][y].shotPJ2.setBulletHere(false);
						combatMapSlot[x+1][y].shotPJ2.setJustUpdated(true);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].shotPJ2.setBulletHere(false);
						
					}
					
					break;
					
				case 'W':
					
					try {
						
						combatMapSlot[x-1][y].shotPJ2.setBulletHere(true);
						combatMapSlot[x][y].shotPJ2.setBulletHere(false);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].shotPJ2.setBulletHere(false);
						
					}
					
					break;
					
				default:
					
					throw new IllegalArgumentException(String.format("HUGE ERROR happened on method updateShots(int x, int y)."+
					"\nPossibly a non acceptable CHAR was inputed on the bullet direction of shotPJ2"));
					
			}
		}
			
		if(combatMapSlot[x][y].shotPJ2.isBulletHere()==true && combatMapSlot[x][y].shotPJ2.isJustUpdated()==true) {
			
			combatMapSlot[x][y].shotPJ2.setJustUpdated(false);
		}
		}
		
		if(combatMapSlot[x][y].isRedZone()==true) {
			
			combatMapSlot[x][y].shotPJ1.setBulletHere(false);
			combatMapSlot[x][y].shotPJ1.setJustUpdated(false);
			combatMapSlot[x][y].shotPJ2.setBulletHere(false);
			combatMapSlot[x][y].shotPJ2.setJustUpdated(false);
			
		}
			
	}
	
	public void updateMovements(int x,int y) {
		/*gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg*/
		if(combatMapSlot[x][y].player1.isPlayerHere()==true && combatMapSlot[x][y].player1.isJustUpdated()==false) {
			
			switch (combatMapSlot[x][y].player1.getPlayerDir()) {
				
				case 'N':
					
					try {
						
						combatMapSlot[x][y-1].player1.setPlayerHere(true);
						combatMapSlot[x][y].player1.setPlayerHere(false);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].player1.setPlayerHere(false);
						
					}
					
					break;
					
				case 'S':
					
					try {
						
						combatMapSlot[x][y+1].player1.setPlayerHere(true);
						combatMapSlot[x][y].player1.setPlayerHere(false);
						combatMapSlot[x][y+1].player1.setPlayerHere(true);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].player1.setPlayerHere(false);
						
					}
					
					break;
					
				case 'E':
					
					try {
						
						combatMapSlot[x+1][y].player1.setPlayerHere(true);
						combatMapSlot[x][y].player1.setPlayerHere(false);
						combatMapSlot[x+1][y].player1.setPlayerHere(true);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].player1.setPlayerHere(false);
						
					}
					
					break;
					
				case 'W':
					
					try {
						
						combatMapSlot[x-1][y].player1.setPlayerHere(true);
						combatMapSlot[x][y].player1.setPlayerHere(false);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].player1.setPlayerHere(false);
						
					}
					
					break;
					
				case 'Q':
					
					combatMapSlot[x][y].player1.setPlayerHere(true);
					
					break;
					
				default:
						
					combatMapSlot[x][y].player1.setPlayerHere(true);
					//print???
					
			}
			
		}
			
		if(combatMapSlot[x][y].player1.isPlayerHere()==true && combatMapSlot[x][y].player2.isJustUpdated()==true) {
			
			combatMapSlot[x][y].player1.setJustUpdated(false);
		}
		
		if(combatMapSlot[x][y].player2.isPlayerHere()==true && combatMapSlot[x][y].player2.isJustUpdated()==false) {
			
			switch (combatMapSlot[x][y].player2.getPlayerDir()) {
				
				case 'N':
					
					try {
						
						combatMapSlot[x][y-1].player2.setPlayerHere(true);
						combatMapSlot[x][y].player2.setPlayerHere(false);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].player2.setPlayerHere(false);
						
					}
					
					break;
					
				case 'S':
					
					try {
						
						combatMapSlot[x][y+1].player2.setPlayerHere(true);
						combatMapSlot[x][y].player2.setPlayerHere(false);
						combatMapSlot[x][y+1].player2.setJustUpdated(true);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].player2.setPlayerHere(false);
						
					}
					
					break;
					
				case 'E':
					
					try {
						
						combatMapSlot[x+1][y].player2.setPlayerHere(true);
						combatMapSlot[x][y].player2.setPlayerHere(false);
						combatMapSlot[x+1][y].player2.setJustUpdated(true);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].player2.setPlayerHere(false);
						
					}
					
					break;
					
				case 'W':
					
					try {
						
						combatMapSlot[x-1][y].player2.setPlayerHere(true);
						combatMapSlot[x][y].player2.setPlayerHere(false);
						
					}
					
					catch(ArrayIndexOutOfBoundsException e) {
						
						combatMapSlot[x][y].player2.setPlayerHere(false);
						
					}
					
					break;
					
				case 'Q':
					
					combatMapSlot[x][y].player2.setPlayerHere(true);
					
					break;
					
				default:
						
					combatMapSlot[x][y].player2.setPlayerHere(true);
					//print???
					
			}
					
		}
			
		if(combatMapSlot[x][y].player2.isPlayerHere()==true && combatMapSlot[x][y].player2.isJustUpdated()==true) {
			
			combatMapSlot[x][y].player2.setJustUpdated(false);
		}
			
	}
	
	public void updateMap() {
		
		System.out.println("\033[H\033[2J");
		
		for(int y=0;y<50;y++) {
			for(int x=0;x<50;x++) {
				
				updateShots(x,y);
				
				updateMovements(x,y);
				
				generateColorMap(x,y);
				
				System.out.print(combatMapScreen[x][y]);
				
				if(x==49) {
					System.out.print("\n");
				}
				if(combatMapSlot[x][y].isFlagP1TF()==true && combatMapSlot[x][y].isRedZone()==true) {
					gameEnded=true;
				}
				if(combatMapSlot[x][y].isFlagP2TF()==true && combatMapSlot[x][y].isRedZone()==true) {
					gameEnded=true;
				}
				if(combatMapSlot[x][y].player1.isCarryFlagPJ2()==true && combatMapSlot[x][y].isFlagP1TF()==true) {
					winner=BOT1Name;
					gameEnded=true;
				}
				if(combatMapSlot[x][y].player2.isCarryFlagPJ1()==true && combatMapSlot[x][y].isFlagP2TF()==true) {
					winner=BOT2Name;
					gameEnded=true;
				}
				if(combatMapSlot[x][y].player1.isPlayerHere()==true && combatMapSlot[x][y].isRedZone()==true) {
					
					PJ1Red=true;
					
				}
				if(combatMapSlot[x][y].player2.isPlayerHere()==true && combatMapSlot[x][y].isRedZone()==true) {
					
					PJ2Red=true;
					
				}
				if(PJ1Red==true && PJ2Red==true) {
					winner="BOTH AI ENTERED ON RED ZONE. NO ONE";
					gameEnded=true;
				}
				if(PJ1Red==true && PJ2Red==false){
					winner=BOT2Name;
					gameEnded=true;
				}
				if(PJ2Red==true && PJ1Red==false){
					winner=BOT1Name;
					gameEnded=true;
				}
			}
		}
		System.out.println("Player 1 killed "+P1Kill+" times. \t Player 2 killed "+P2Kill+" times");
		System.out.println("Turn:"+turn);
		if(gameEnded==true) {
			System.out.println(winner+" WON!");
		}
	}

	public void actionPlayers(char m, char n) {
		
		for(int y=0;y<50;y++) {
			for(int x=0;x<50;x++) {
		
				if(combatMapSlot[x][y].player1.isPlayerHere()==true) {
		
					switch(m) {
		
					case '8':
						
						combatMapSlot[x][y-1].moveBullet(1,'N');
						combatMapSlot[x][y-1].updateBulletStatus(1, true);
			
						break;
			
					case '2':
			
						combatMapSlot[x][y+1].shotPJ1.setBulletHere(true);
						combatMapSlot[x][y+1].shotPJ1.setBulletDir('S');
						combatMapSlot[x][y+1].shotPJ1.setJustUpdated(true);
			
						break;
			
					case '6':
			
						combatMapSlot[x+1][y].shotPJ1.setBulletHere(true);
						combatMapSlot[x+1][y].shotPJ1.setBulletDir('E');
						combatMapSlot[x+1][y].shotPJ1.setJustUpdated(true);
			
						break;
			
					case '4':
			
						combatMapSlot[x-1][y].shotPJ1.setBulletHere(true);
						combatMapSlot[x-1][y].shotPJ1.setBulletDir('W');
						combatMapSlot[x-1][y].shotPJ1.setJustUpdated(true);
			
						break;
			
					default:
			
						combatMapSlot[x][y].player1.setPlayerDir(m);
			
						;
					}	
				}
				if(combatMapSlot[x][y].player2.isPlayerHere()==true) {
					
					switch(n) {
		
					case '8':
			
						combatMapSlot[x][y-1].shotPJ2.setBulletHere(true);
						combatMapSlot[x][y-1].shotPJ2.setBulletDir('N');
						combatMapSlot[x][y-1].shotPJ2.setJustUpdated(true);
			
						break;
			
					case '2':
			
						combatMapSlot[x][y+1].shotPJ2.setBulletHere(true);
						combatMapSlot[x][y+1].shotPJ2.setBulletDir('S');
						combatMapSlot[x][y+1].shotPJ2.setJustUpdated(true);
			
						break;
			
					case '6':
			
						combatMapSlot[x+1][y].shotPJ2.setBulletHere(true);
						combatMapSlot[x+1][y].shotPJ2.setBulletDir('E');
						combatMapSlot[x+1][y].shotPJ2.setJustUpdated(true);
			
						break;
			
					case '4':
			
						combatMapSlot[x-1][y].shotPJ2.setBulletHere(true);
						combatMapSlot[x-1][y].shotPJ2.setBulletDir('W');
						combatMapSlot[x-1][y].shotPJ2.setJustUpdated(true);
			
						break;
			
					default:
			
						combatMapSlot[x][y].player2.setPlayerDir(n);
			
						;
					}
				}
			}
	}
		
	}
	
	
	public void resetSlot(int x,int y) {
		
		combatMapSlot[x][y].setTreeTF(false);
		combatMapSlot[x][y].setBushTF(false);
		combatMapSlot[x][y].setHoleTF(false);
		combatMapSlot[x][y].shotPJ1.setBulletHere(false);
		combatMapSlot[x][y].shotPJ2.setBulletHere(false);
		
	}
	
	public void redZone() {
		
		redZoneCalls++;
		
			for(int x=0;x<50;x++) {
				resetSlot(x,redZoneCalls-1);
				combatMapSlot[x][redZoneCalls-1].setRedZone(true);
			}
			
			for(int x=0;x<50;x++) {
				resetSlot(x,49-(redZoneCalls-1));
				combatMapSlot[x][49-(redZoneCalls-1)].setRedZone(true);
			}
			
			for(int x=0;x<50;x++) {
				resetSlot(redZoneCalls-1, x);
				combatMapSlot[redZoneCalls-1][x].setRedZone(true);
			}
			
			for(int x=0;x<50;x++) {
				resetSlot(49-(redZoneCalls-1),x);
				combatMapSlot[49-(redZoneCalls-1)][x].setRedZone(true);
			}
			
	}
}