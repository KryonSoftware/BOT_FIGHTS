package PELEAS;

/*---------------------------------------------------------------------------Hecho por KRYON------------------------------------------------------------------------------*/


/*OJO COLISIONES BALAS TAMBIÉN*/


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
	private boolean PJ1Red,PJ2Red, PJ1Stunned=false, PJ2Stunned=false, PJ1JustRespawned=false, PJ2JustRespawned=false, PJ1JustStunned=false, PJ2JustStunned=false, PJ1Shooted=false, PJ2Shooted=false;
	
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
			
			public boolean isBulletHerePJ1() {
					
				return shotPJ1.isBulletHere();
				
			}
			
			public boolean isBulletHerePJ2() {
				
				return shotPJ2.isBulletHere();
			
			}
			
			public char getBulletDirPJ1() {
				
				return shotPJ1.getBulletDir();
				
			}
			
			public char getBulletDirPJ2() {
				
				return shotPJ2.getBulletDir();
				
			}
			
			public boolean isBulletUpdatedPJ1() {
				
				return shotPJ1.isJustUpdated();
				
			}
			
			public boolean isBulletUpdatedPJ2() {
				
				return shotPJ2.isJustUpdated();
				
			}
			
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
			
			//*****************************************************************************************************
			
		public boolean isPJ1Here() {
			
			return player1.isPlayerHere();
			
		}
		
		public boolean isPJ2Here() {
			
			return player2.isPlayerHere();
		
		}
		
		public char getPJ1Dir() {
			
			return player1.getPlayerDir();
			
		}
		
		public char getPJ2Dir() {
			
			return player2.getPlayerDir();
			
		}
		
		public boolean isPJ1Updated() {
			
			return player1.isJustUpdated();
			
		}
		
		public boolean isPJ2Updated() {
			
			return player2.isJustUpdated();
			
		}
		
		public void movePJ(int who, char D) {
			
			switch (who) {
			case 1:
				
				player1.setPlayerHere(true);
				player1.setPlayerDir(D);
				
				break;
			
			case 2:
			
				player2.setPlayerHere(true);
				player2.setPlayerDir(D);
			
		}
				
			
		}
		
		public void removePJ(int who) {
			
			switch (who) {
			case 1:
				
				player1.setPlayerHere(false);
				
				break;
			
			case 2:
			
				player2.setPlayerHere(false);
			
		}
			
		}
		
		public void updatePlayerStatus(int who, boolean tf) {
			
			switch (who) {
			case 1:
				
				player1.setJustUpdated(tf);
				
				break;
			
			case 2:
			
				player2.setJustUpdated(tf);
			
			}
			
		}
		
		public boolean isPJ1CarryFlagPJ2() {
			
			return player1.isCarryFlagPJ2();
			
		}
		
		public boolean isPJ2CarryFlagPJ1() {
			
			return player2.isCarryFlagPJ2();
			
		}
		
		public void setPJ1CarryFlagPJ2(boolean carry) {
			
			player1.setCarryFlagPJ2(carry);
			
		}
		
		public void setPJ2CarryFlagPJ1(boolean carry) {
			
			player2.setCarryFlagPJ1(carry);
			
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
	
	public boolean isPJ1JustRespawned() {
		return PJ1JustRespawned;
	}
	public void setPJ1JustRespawned(boolean pJ1JustRespawned) {
		PJ1JustRespawned = pJ1JustRespawned;
	}
	public boolean isPJ2JustRespawned() {
		return PJ2JustRespawned;
	}
	public void setPJ2JustRespawned(boolean pJ2JustRespawned) {
		PJ2JustRespawned = pJ2JustRespawned;
	}
	
	/*public int getPJ1KilledTurn() {
		return PJ1KilledTurn;
	}
	public void setPJ1KilledTurn(int pJ1KilledTurn) {
		PJ1KilledTurn = pJ1KilledTurn;
	}
	public int getPJ2KilledTurn() {
		return PJ2KilledTurn;
	}
	public void setPJ2KilledTurn(int pJ2KilledTurn) {
		PJ2KilledTurn = pJ2KilledTurn;
	}*/
				
		//Array bidimensional que será nuestro mapa hecho de casillas

	public boolean isPJ1JustStunned() {
		return PJ1JustStunned;
	}
	public void setPJ1JustStunned(boolean pJ1JustStunned) {
		PJ1JustStunned = pJ1JustStunned;
	}

	public boolean isPJ2JustStunned() {
		return PJ2JustStunned;
	}
	public void setPJ2JustStunned(boolean pJ2JustStunned) {
		PJ2JustStunned = pJ2JustStunned;
	}

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
			
				combatMapSlot[x][y].movePJ(1, 'P');
				player1StartingPos=false;
				
			}
			
		}
		
		while(player2StartingPos) {
			
			x=(int)(Math.random() * 50);
			y=(int)(Math.random() * 50);
			
			if (combatMapSlot[x][y].isBushTF()==false && combatMapSlot[x][y].isTreeTF()==false && combatMapSlot[x][y].isHoleTF()==false && combatMapSlot[x][y].isFlagP1TF()==false && combatMapSlot[x][y].isPJ1Here()==false) {
			
				combatMapSlot[x][y].movePJ(2, 'P');
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
					if(combatMapSlot[x][y].isBulletHerePJ1()==true) {
						combatMapScreen[x][y]="\033[1;47m\033[1;34m + \033[0m";
					}
					if(combatMapSlot[x][y].isBulletHerePJ2()==true) {
						combatMapScreen[x][y]="\033[1;47m\033[1;35m + \033[0m";
					}
					if(combatMapSlot[x][y].isPJ1Here()==true) {
						
						if(isPJ1Stunned()==true) {
							
							combatMapScreen[x][y]="\033[1;44m!!!\033[0m";
							
						}
						else {
							
						combatMapScreen[x][y]="\033[1;44mIA1\033[0m";
						
						}
					}
					if(combatMapSlot[x][y].isPJ2Here()==true) {
						
						if(isPJ2Stunned()==true) {
							
							combatMapScreen[x][y]="\033[1;45m!!!\033[0m";
							
						}
						else {
							
							combatMapScreen[x][y]="\033[1;45mIA2\033[0m";
						
						}
					}
					if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
						combatMapScreen[x][y]="\033[1;44m-2-\033[0m";
					}
					if(combatMapSlot[x][y].isPJ2CarryFlagPJ1()==true) {
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
		
		if(combatMapSlot[x][y].isTreeTF()==false) {
		
		if(combatMapSlot[x][y].isRedZone()==false) {
		
		if(combatMapSlot[x][y].isBulletHerePJ1()==true && combatMapSlot[x][y].isBulletUpdatedPJ1()==false) {
			
			try {
			
				switch (combatMapSlot[x][y].getBulletDirPJ1()) {
				
					case 'N':
							
						combatMapSlot[x][y-1].moveBullet(1,'N');
						combatMapScreen[x][y-1]="\033[1;47m\033[1;34m + \033[0m";
						combatMapScreen[x][y]="\033[1;47m   \033[0m";
						combatMapSlot[x][y].removeBullet(1);
						
						break;
					
					case 'S':
						
						combatMapSlot[x][y+1].moveBullet(1,'S');
						combatMapSlot[x][y+1].updateBulletStatus(1, true);
						combatMapScreen[x][y+1]="\033[1;47m\033[1;34m + \033[0m";
						combatMapScreen[x][y]="\033[1;47m   \033[0m";
						combatMapSlot[x][y].removeBullet(1);
						
						break;
					
					case 'E':

						combatMapSlot[x+1][y].moveBullet(1,'E');
						combatMapSlot[x+1][y].updateBulletStatus(1, true);
						combatMapScreen[x+1][y]="\033[1;47m\033[1;34m + \033[0m";
						combatMapScreen[x][y]="\033[1;47m   \033[0m";
						combatMapSlot[x][y].removeBullet(1);
					
						break;
					
					case 'W':
						
						combatMapSlot[x-1][y].moveBullet(1,'W');
						combatMapScreen[x-1][y]="\033[1;47m\033[1;34m + \033[0m";
						combatMapScreen[x][y]="\033[1;47m   \033[0m";
						combatMapSlot[x][y].removeBullet(1);
					
						break;
					
					default:
						
					throw new IllegalArgumentException(String.format("HUGE ERROR happened on method updateShots(int x, int y)."+
					"\nPossibly a non acceptable CHAR was inputed on the bullet direction of shotPJ1: "+combatMapSlot[x][y].getBulletDirPJ1()));
				}
			}
					
			catch(ArrayIndexOutOfBoundsException e) {
						
				combatMapScreen[x][y]="\033[1;47m   \033[0m";
				combatMapSlot[x][y].removeBullet(1);
						
			}
					
			}
			
			
		if(combatMapSlot[x][y].isBulletHerePJ1()==true && combatMapSlot[x][y].isBulletUpdatedPJ1()==true) {
			
			combatMapSlot[x][y].updateBulletStatus(1, false);
		}
		
		//PJ2*********
		
		if(combatMapSlot[x][y].isBulletHerePJ2()==true && combatMapSlot[x][y].isBulletUpdatedPJ2()==false) {
			
			try {
			
				switch (combatMapSlot[x][y].getBulletDirPJ2()) {
				
					case 'N':
							
						combatMapSlot[x][y-1].moveBullet(2,'N');
						combatMapScreen[x][y-1]="\033[1;47m\033[1;34m + \033[0m";
						combatMapScreen[x][y]="\033[1;47m   \033[0m";
						combatMapSlot[x][y].removeBullet(2);
						
						break;
					
					case 'S':
						
						combatMapSlot[x][y+1].moveBullet(2,'S');
						combatMapSlot[x][y+1].updateBulletStatus(2, true);
						combatMapScreen[x][y+1]="\033[1;47m\033[1;34m + \033[0m";
						combatMapScreen[x][y]="\033[1;47m   \033[0m";
						combatMapSlot[x][y].removeBullet(2);
						
						break;
					
					case 'E':

						combatMapSlot[x+1][y].moveBullet(2,'E');
						combatMapSlot[x+1][y].updateBulletStatus(2, true);
						combatMapScreen[x+1][y]="\033[1;47m\033[1;34m + \033[0m";
						combatMapScreen[x][y]="\033[1;47m   \033[0m";
						combatMapSlot[x][y].removeBullet(2);
					
						break;
					
					case 'W':
						
						combatMapSlot[x-1][y].moveBullet(2,'W');
						combatMapScreen[x-1][y]="\033[1;47m\033[1;34m + \033[0m";
						combatMapScreen[x][y]="\033[1;47m   \033[0m";
						combatMapSlot[x][y].removeBullet(2);
					
						break;
					
					default:
						
					throw new IllegalArgumentException(String.format("HUGE ERROR happened on method updateShots(int x, int y)."+
					"\nPossibly a non acceptable CHAR was inputed on the bullet direction of shotPJ2: "+combatMapSlot[x][y].getBulletDirPJ2()));
				}
			}
					
			catch(ArrayIndexOutOfBoundsException e) {
						
				combatMapScreen[x][y]="\033[1;47m   \033[0m";
				combatMapSlot[x][y].removeBullet(2);
						
			}
					
			}
		
		}
		}
		
		if(combatMapSlot[x][y].isBulletHerePJ2()==true && combatMapSlot[x][y].isBulletUpdatedPJ2()==true) {
			
			combatMapSlot[x][y].updateBulletStatus(2, false);
		}
		
		if(combatMapSlot[x][y].isTreeTF()==true && (combatMapSlot[x][y].isBulletHerePJ2()==true || combatMapSlot[x][y].isBulletHerePJ1()==true)) {
			
			combatMapSlot[x][y].removeBullet(1);
			combatMapSlot[x][y].updateBulletStatus(1, false);
			combatMapSlot[x][y].removeBullet(2);
			combatMapSlot[x][y].updateBulletStatus(2, false);
			
		}
		
		if(combatMapSlot[x][y].isRedZone()==true && (combatMapSlot[x][y].isBulletHerePJ2()==true || combatMapSlot[x][y].isBulletHerePJ1()==true)) {
			
			combatMapSlot[x][y].removeBullet(1);
			combatMapSlot[x][y].updateBulletStatus(1, false);
			combatMapSlot[x][y].removeBullet(2);
			combatMapSlot[x][y].updateBulletStatus(2, false);
			
		}
			
	}
	
	public void respawnPlayer(int who, int x, int y) {
		
		switch(who) {
		
		case 1:
			
			player1StartingPos=true;
			
			while(player1StartingPos) {
				
				int respawnx=(int)(Math.random() * 50);
				int respawny=(int)(Math.random() * 50);
				
				combatMapSlot[x][y].removePJ(1);
				
				if(!(respawnx==x) || !(respawny==y)) {
				
				if (combatMapSlot[respawnx][respawny].isBushTF()==false && combatMapSlot[respawnx][respawny].isTreeTF()==false && combatMapSlot[respawnx][respawny].isHoleTF()==false && combatMapSlot[respawnx][respawny].isFlagP1TF()==false && combatMapSlot[respawnx][respawny].isPJ2Here()==false) {
				
					combatMapSlot[respawnx][respawny].movePJ(1, 'P');
					setPJ1Stunned(true);
					setPJ1JustRespawned(true);
					combatMapScreen[respawnx][respawny]="\033[1;47m\033[1;34mIA1\033[0m";
					if(respawnx<=x && respawny<=y){
						
						setPJ1JustRespawned(false);
						
					}
					player1StartingPos=false;
					
				}
				}
				
			}
			
			break;
		
		case 2:
		
			player2StartingPos=true;
		
			while(player2StartingPos) {
			
				int respawnx=(int)(Math.random() * 50);
				int respawny=(int)(Math.random() * 50);
				
				combatMapSlot[x][y].removePJ(2);
			
				if(!(respawnx==x) || !(respawny==y)) {
			
					if (combatMapSlot[respawnx][respawny].isBushTF()==false && combatMapSlot[respawnx][respawny].isTreeTF()==false && combatMapSlot[respawnx][respawny].isHoleTF()==false && combatMapSlot[respawnx][respawny].isFlagP1TF()==false && combatMapSlot[respawnx][respawny].isPJ1Here()==false) {
			
						combatMapSlot[respawnx][respawny].movePJ(2, 'P');
						setPJ2Stunned(true);
						setPJ2JustRespawned(true);
						combatMapScreen[respawnx][respawny]="\033[1;47m\033[1;35mIA2\033[0m";
						if(respawnx<=x && respawny<=y){
							
							setPJ2JustRespawned(false);
							
						}
						player2StartingPos=false;
				
					}
				}
			
			}
		
			break;
	
		}
		
	}

	public void respawnFlag(int who, int x, int y) {
		
		switch(who) {
		
		case 1:
			
			player1StartingPos=true;
			
			while(player1StartingPos) {
				
				int respawnx=(int)(Math.random() * 50);
				int respawny=(int)(Math.random() * 50);
				
				if(!(respawnx==x) || !(respawny==y)) {
				
				if (combatMapSlot[respawnx][respawny].isBushTF()==false && combatMapSlot[respawnx][respawny].isTreeTF()==false && combatMapSlot[respawnx][respawny].isHoleTF()==false && combatMapSlot[respawnx][respawny].isFlagP2TF()==false && combatMapSlot[respawnx][respawny].isPJ1Here()==false && combatMapSlot[respawnx][respawny].isPJ2Here()==false) {
				
					combatMapSlot[respawnx][respawny].setFlagP1TF(true);
					combatMapScreen[respawnx][respawny]="\033[1;47m\033[1;34m-1-\033[0m";
					player1StartingPos=false;
					
				}
				}
				
			}
			
			break;
		
		case 2:
		
			player2StartingPos=true;
		
			while(player2StartingPos) {
			
				int respawnx=(int)(Math.random() * 50);
				int respawny=(int)(Math.random() * 50);
				
				if(!(respawnx==x) || !(respawny==y)) {
				
				if (combatMapSlot[respawnx][respawny].isBushTF()==false && combatMapSlot[respawnx][respawny].isTreeTF()==false && combatMapSlot[respawnx][respawny].isHoleTF()==false && combatMapSlot[respawnx][respawny].isFlagP1TF()==false && combatMapSlot[respawnx][respawny].isPJ1Here()==false && combatMapSlot[respawnx][respawny].isPJ2Here()==false) {
				
					combatMapSlot[respawnx][respawny].setFlagP2TF(true);
					combatMapScreen[respawnx][respawny]="\033[1;47m\033[1;35m-2-\033[0m";
					player1StartingPos=false;
					
				}
				}
				
			}
		
			break;
	
		}
		
	}
	
	public void updateMovements(int x,int y) {
		
		if(combatMapSlot[x][y].isRedZone()==false) {
			
			if(combatMapSlot[x][y].isPJ1Here()==true && (combatMapSlot[x][y].isBulletHerePJ2()==true || combatMapSlot[x][y].isHoleTF()==true))  {
				
				respawnPlayer(1,x,y);
				P1Kill++;
				if(combatMapSlot[x][y].isHoleTF()==true && combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
					
					respawnFlag(1,x,y);
					combatMapSlot[x][y].setPJ1CarryFlagPJ2(false);
					
				}
				if(combatMapSlot[x][y].isBulletHerePJ2()==true && combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
					
					combatMapSlot[x][y].setFlagP2TF(true);
					combatMapSlot[x][y].setPJ1CarryFlagPJ2(false);
					
				}
				if(combatMapSlot[x][y].isHoleTF()==true) {
					
					combatMapScreen[x][y]="\033[40m   \033[0m";
					
				}
				
			}
			
			if(combatMapSlot[x][y].isPJ2Here()==true && (combatMapSlot[x][y].isBulletHerePJ1()==true || combatMapSlot[x][y].isHoleTF()==true))  {
				
				respawnPlayer(2,x,y);
				P2Kill++;
				if(combatMapSlot[x][y].isHoleTF()==true && combatMapSlot[x][y].isPJ2CarryFlagPJ1()==true) {
					
					respawnFlag(2,x,y);
					combatMapSlot[x][y].setPJ2CarryFlagPJ1(false);
					
				}
				if(combatMapSlot[x][y].isBulletHerePJ1()==true && combatMapSlot[x][y].isPJ2CarryFlagPJ1()==true) {
					
					combatMapSlot[x][y].setFlagP1TF(true);
					combatMapSlot[x][y].setPJ2CarryFlagPJ1(false);
					
				}
				
			}
			
			if(combatMapSlot[x][y].isPJ1Here() && combatMapSlot[x][y].isFlagP2TF()==true) {
				
				combatMapSlot[x][y].setPJ1CarryFlagPJ2(true);
				combatMapSlot[x][y].setFlagP2TF(false);
				
			}
		
		if(combatMapSlot[x][y].isPJ1Here()==true && combatMapSlot[x][y].isPJ1Updated()==false && combatMapSlot[x][y].isBulletHerePJ2()==false && isPJ1Stunned()==false) {
			
			try {
			
				switch (combatMapSlot[x][y].getPJ1Dir()) {
				
					case 'N':
							
						if(combatMapSlot[x][y-1].isTreeTF()==true || combatMapSlot[x][y-1].isBushTF()==true || combatMapSlot[x][y].isPJ2Here()==true) {
							
							combatMapScreen[x][y]="\033[1;47m\033[1;34m!!!\033[0m";
							setPJ1JustStunned(true);
							setPJ1Stunned(true);
							
						}
						else {
							combatMapSlot[x][y-1].movePJ(1,'N');
							if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
								
								combatMapScreen[x][y-1]="\033[1;44m-2-\033[0m";
								combatMapSlot[x][y-1].setPJ1CarryFlagPJ2(true);
								
							}
							else {
								
							combatMapScreen[x][y-1]="\033[1;47m\033[1;34mIA1\033[0m";
							
							}
							combatMapScreen[x][y]="\033[1;47m   \033[0m";
							combatMapSlot[x][y].removePJ(1);
						}
						
						break;
					
					case 'S':
						
						if(combatMapSlot[x][y+1].isTreeTF()==true || combatMapSlot[x][y+1].isBushTF()==true || combatMapSlot[x][y].isPJ2Here()==true) {
							
							combatMapScreen[x][y]="\033[1;47m\033[1;34m!!!\033[0m";
							setPJ1JustStunned(true);
							setPJ1Stunned(true);
							
						}
						
						else {
							combatMapSlot[x][y+1].movePJ(1,'S');
							combatMapSlot[x][y+1].updatePlayerStatus(1, true);
							if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
								
								combatMapScreen[x][y+1]="\033[1;44m-2-\033[0m";
								combatMapSlot[x][y+1].setPJ1CarryFlagPJ2(true);
								
							}
							else {
								
							combatMapScreen[x][y+1]="\033[1;47m\033[1;34mIA1\033[0m";
							
							}
							combatMapScreen[x][y]="\033[1;47m   \033[0m";
							combatMapSlot[x][y].removePJ(1);
						
						}
						
						break;
					
					case 'E':

						if(combatMapSlot[x+1][y].isTreeTF()==true || combatMapSlot[x+1][y].isBushTF()==true || combatMapSlot[x][y].isPJ2Here()==true) {
							
							combatMapScreen[x][y]="\033[1;47m\033[1;34m!!!\033[0m";
							setPJ1JustStunned(true);
							setPJ1Stunned(true);
							
						}
						
						else {
						
							combatMapSlot[x+1][y].movePJ(1,'E');
							combatMapSlot[x+1][y].updatePlayerStatus(1, true);
							if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
								
								combatMapScreen[x+1][y]="\033[1;44m-2-\033[0m";
								combatMapSlot[x+1][y].setPJ1CarryFlagPJ2(true);
								
							}
							else {
								
							combatMapScreen[x+1][y]="\033[1;47m\033[1;34mIA1\033[0m";
							
							}
							combatMapScreen[x][y]="\033[1;47m   \033[0m";
							combatMapSlot[x][y].removePJ(1);
						
						}
					
						break;
					
					case 'W':
						
						if(combatMapSlot[x-1][y].isTreeTF()==true || combatMapSlot[x-1][y].isBushTF()==true || combatMapSlot[x][y].isPJ2Here()==true) {
							
							combatMapScreen[x][y]="\033[1;47m\033[1;34m!!!\033[0m";
							setPJ1JustStunned(true);
							setPJ1Stunned(true);
							
						}
						
						else {
						
							combatMapSlot[x-1][y].movePJ(1,'W');
							if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
								
								combatMapScreen[x-1][y]="\033[1;44m-2-\033[0m";
								combatMapSlot[x-1][y].setPJ1CarryFlagPJ2(true);
								
							}
							else {
								
							combatMapScreen[x-1][y]="\033[1;47m\033[1;34mIA1\033[0m";
							
							}
							combatMapScreen[x][y]="\033[1;47m   \033[0m";
							combatMapSlot[x][y].removePJ(1);
						
						}
					
						break;
						
					case 'P':
						
							combatMapSlot[x][y].movePJ(1,'P');
							if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
								
								combatMapScreen[x][y]="\033[1;44m-2-\033[0m";
								combatMapSlot[x][y].setPJ1CarryFlagPJ2(true);
								
							}
							else {
								
							combatMapScreen[x][y-1]="\033[1;47m\033[1;34mIA1\033[0m";
							
							}
					
						break;
					
					default:
						
						combatMapSlot[x][y].movePJ(1,'.');
						combatMapScreen[x][y]="\033[1;47m\033[1;34m???\033[0m";
						
				}
			}
					
			catch(ArrayIndexOutOfBoundsException e) {
						
				combatMapSlot[x][y].movePJ(1,'.');
				combatMapScreen[x][y]="\033[1;47m\033[1;34m???\033[0m";
						
			}
					
			}
			
		if(combatMapSlot[x][y].isPJ1Here()==true && combatMapSlot[x][y].isPJ1Updated()==true) {
			
			combatMapSlot[x][y].updatePlayerStatus(1, false);
		}
		
		if(combatMapSlot[x][y].isPJ1Here()==true && isPJ1Stunned()==true) {
			
			if(isPJ1JustRespawned()==true){
				
				setPJ1JustRespawned(false);
				
				}
				
			
			else {
				
				if(isPJ1JustStunned()==true) {
					
					setPJ1JustStunned(false);
					
				}
				
				else {
				
					setPJ1Stunned(false);
				
				}
				
			}
			
		}
		
		//PJ2***********************************************************************************************************************
		
		if(combatMapSlot[x][y].isPJ2Here()==true && combatMapSlot[x][y].isPJ2Updated()==false && combatMapSlot[x][y].isBulletHerePJ1()==false && isPJ2Stunned()==false) {
			
			try {
			
				switch (combatMapSlot[x][y].getPJ2Dir()) {
				
					case 'N':
							
						if(combatMapSlot[x][y-1].isTreeTF()==true || combatMapSlot[x][y-1].isBushTF()==true || combatMapSlot[x][y].isPJ1Here()==true) {
							
							combatMapScreen[x][y]="\033[1;47m\033[1;35m!!!\033[0m";
							setPJ2JustStunned(true);
							setPJ2Stunned(true);
							
						}
						else {
							combatMapSlot[x][y-1].movePJ(2,'N');
							combatMapScreen[x][y-1]="\033[1;47m\033[1;35mIA2\033[0m";
							combatMapScreen[x][y]="\033[1;47m   \033[0m";
							combatMapSlot[x][y].removePJ(2);
						}
						
						break;
					
					case 'S':
						
						if(combatMapSlot[x][y+1].isTreeTF()==true || combatMapSlot[x][y+1].isBushTF()==true || combatMapSlot[x][y].isPJ1Here()==true) {
							
							combatMapScreen[x][y]="\033[1;47m\033[1;35m!!!\033[0m";
							setPJ2JustStunned(true);
							setPJ2Stunned(true);
							
						}
						
						else {
							combatMapSlot[x][y+1].movePJ(2,'S');
							combatMapSlot[x][y+1].updatePlayerStatus(2, true);
							combatMapScreen[x][y+1]="\033[1;47m\033[1;35mIA2\033[0m";
							combatMapScreen[x][y]="\033[1;47m   \033[0m";
							combatMapSlot[x][y].removePJ(2);
						
						}
						
						break;
					
					case 'E':

						if(combatMapSlot[x+1][y].isTreeTF()==true || combatMapSlot[x+1][y].isBushTF()==true || combatMapSlot[x][y].isPJ1Here()==true) {
							
							combatMapScreen[x][y]="\033[1;47m\033[1;35m!!!\033[0m";
							setPJ2JustStunned(true);
							setPJ2Stunned(true);
							
						}
						
						else {
						
							combatMapSlot[x+1][y].movePJ(2,'E');
							combatMapSlot[x+1][y].updatePlayerStatus(2, true);
							combatMapScreen[x+1][y]="\033[1;47m\033[1;35mIA2\033[0m";
							combatMapScreen[x][y]="\033[1;47m   \033[0m";
							combatMapSlot[x][y].removePJ(2);
						
						}
					
						break;
					
					case 'W':
						
						if(combatMapSlot[x-1][y].isTreeTF()==true || combatMapSlot[x-1][y].isBushTF()==true || combatMapSlot[x][y].isPJ1Here()==true) {
							
							combatMapScreen[x][y]="\033[1;47m\033[1;35m!!!\033[0m";
							setPJ2JustStunned(true);
							setPJ2Stunned(true);
							
						}
						
						else {
						
							combatMapSlot[x-1][y].movePJ(2,'W');
							combatMapScreen[x-1][y]="\033[1;47m\033[1;35mIA2\033[0m";
							combatMapScreen[x][y]="\033[1;47m   \033[0m";
							combatMapSlot[x][y].removePJ(2);
						
						}
					
						break;
						
					case 'P':
						
							combatMapSlot[x][y].movePJ(2,'P');
							combatMapScreen[x][y]="\033[1;47m\033[1;35mIA2\033[0m";
					
						break;
					
					default:
						
						combatMapSlot[x][y].movePJ(2,'P');
						combatMapScreen[x][y]="\033[1;47m\033[1;35m???\033[0m";
						
				}
			}
					
			catch(ArrayIndexOutOfBoundsException e) {
						
				combatMapSlot[x][y].movePJ(2,'P');
				combatMapScreen[x][y]="\033[1;47m\033[1;35m!!!\033[0m";
						
			}
					
			}
			
		if(combatMapSlot[x][y].isPJ2Here()==true && combatMapSlot[x][y].isPJ2Updated()==true) {
			
			combatMapSlot[x][y].updatePlayerStatus(2, false);
		}
		
		if(combatMapSlot[x][y].isPJ2Here()==true && isPJ2Stunned()==true) {
			
			if(isPJ2JustRespawned()==true){
				
				setPJ2JustRespawned(false);
				
				}
				
			
			else {
				
				if(isPJ2JustStunned()==true) {
					
					setPJ2JustStunned(false);
					
				}
				
				else {
				
					setPJ2Stunned(false);
				
				}
				
			}
			
		}
		}
		
	}
	
	public void updateMap() {
		
		System.out.println("\033[H\033[2J");
		
		for(int y=0;y<50;y++) {
			for(int x=0;x<50;x++) {
				
				updateShots(x,y);
				
				//updateMovements(x,y);
				
				generateColorMap(x,y);
				
				System.out.print(combatMapScreen[x][y]);
				
				if(x==49) {
					System.out.print("\n");
				}
			}
		}
		for(int r=0;r<50;r++) {
			for(int j=0;j<50;j++) {
				
			
				if(combatMapSlot[j][r].isFlagP1TF()==true && combatMapSlot[j][r].isRedZone()==true) {
					gameEnded=true;
				}
				if(combatMapSlot[j][r].isFlagP2TF()==true && combatMapSlot[j][r].isRedZone()==true) {
					gameEnded=true;
				}
				if(combatMapSlot[j][r].isPJ1CarryFlagPJ2()==true && combatMapSlot[j][r].isFlagP1TF()==true) {
					winner=BOT1Name;
					gameEnded=true;
				}
				if(combatMapSlot[j][r].isPJ2CarryFlagPJ1()==true && combatMapSlot[j][r].isFlagP2TF()==true) {
					winner=BOT2Name;
					gameEnded=true;
				}
				if(combatMapSlot[j][r].isPJ1Here()==true && combatMapSlot[j][r].isBulletHerePJ2()==true) {
					
					PJ1Shooted=true;
					
				}
				if(combatMapSlot[j][r].isPJ2Here()==true && combatMapSlot[j][r].isBulletHerePJ1()==true) {
					
					PJ2Shooted=true;
					
				}
				if(combatMapSlot[j][r].isPJ1Here()==true && combatMapSlot[j][r].isRedZone()==true) {
			
					PJ1Red=true;
			
				}
				if(combatMapSlot[j][r].isPJ2Here()==true && combatMapSlot[j][r].isRedZone()==true) {
					
					PJ2Red=true;
					
				}
				if(PJ1Shooted==true && PJ2Shooted==true) {
					
					winner="BOTH AI WERE SHOOTED. NO ONE";
					gameEnded=true;
					
				}
				if(PJ1Shooted==true && PJ2Shooted==false) {
					
					winner=BOT2Name;
					gameEnded=true;
					
				}
				if(PJ1Shooted==false && PJ2Shooted==true) {
					
					winner=BOT1Name;
					gameEnded=true;
					
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
		
				if(combatMapSlot[x][y].isPJ1Here()==true && isPJ1Stunned()==false) {
		
					switch(m) {
		
					case '8':
						
						combatMapSlot[x][y-1].moveBullet(1,'N');
						combatMapSlot[x][y-1].updateBulletStatus(1, false);//*********************
			
						break;
			
					case '2':
			
						combatMapSlot[x][y+1].moveBullet(1,'S');
						combatMapSlot[x][y+1].updateBulletStatus(1, true);
			
						break;
			
					case '6':
			
						combatMapSlot[x+1][y].moveBullet(1,'E');
						combatMapSlot[x+1][y].updateBulletStatus(1, true);
			
						break;
			
					case '4':
			
						combatMapSlot[x-1][y].moveBullet(1,'W');
						combatMapSlot[x-1][y].updateBulletStatus(1, false);
			
						break;
			
					default:
			
						combatMapSlot[x][y].movePJ(1, m);
			
						;
					}	
				}
				if(combatMapSlot[x][y].isPJ2Here()==true && isPJ2Stunned()==false) {
					
					switch(n) {
		
					case '8':
			
						combatMapSlot[x][y-1].moveBullet(2,'N');
						combatMapSlot[x][y-1].updateBulletStatus(2, false);
			
						break;
			
					case '2':
			
						combatMapSlot[x][y+1].moveBullet(2,'S');
						combatMapSlot[x][y+1].updateBulletStatus(2, true);
			
						break;
			
					case '6':
			
						combatMapSlot[x+1][y].moveBullet(2,'E');
						combatMapSlot[x+1][y].updateBulletStatus(2, true);
			
						break;
			
					case '4':
			
						combatMapSlot[x-1][y].moveBullet(2,'W');
						combatMapSlot[x-1][y].updateBulletStatus(2, false);
			
						break;
			
					default:
			
						combatMapSlot[x][y].movePJ(2, n);;
			
						;
					}
				}
				
				updateMovements(x,y);
			}
		}
		turn++;
		
	}
	
	
	public void resetSlot(int x,int y) {
		
		combatMapSlot[x][y].setTreeTF(false);
		combatMapSlot[x][y].setBushTF(false);
		combatMapSlot[x][y].setHoleTF(false);
		combatMapSlot[x][y].removeBullet(1);;
		combatMapSlot[x][y].removeBullet(2);;
		
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