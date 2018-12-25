package PELEAS;


//Creamos objeto que generará el mapa
/**
 * Clase que cumple la función de generador de mapa y motor del juego. Se ha hecho utilizando única y exclusivamente herramientas vistas en clase.
 * @author kryon
 *
 */
public class GameEngine {
	
	//Generamos una serie de variables que usaremos en métodos y demás herramientas
	
	private int redZoneCalls=0,P1Kill=0,P2Kill=0,turn=0;
	private String winner="NO ONE",BOT1Name, BOT2Name;
	private boolean PJ1Red,PJ2Red, PJ1Stunned=false, PJ2Stunned=false, PJ1JustRespawned=false, PJ2JustRespawned=false, PJ1JustStunned=false,
			PJ2JustStunned=false, PJ1Shooted=false, PJ2Shooted=false, PJ1Confused=false, PJ2Confused=false,
			mapSlotGenerated=true,mapFlagGenerated=true,gameEnded=false,player1StartingPos=true,player2StartingPos=true;
	
	
	/**
	 * Constructor que será llamado desde "UserInterface" donde se le pasarán los dos jugadores que se enfrentarán en una partida.
	 * @param bot1 Player 1
	 * @param bot2 Player 2
	 */
	public GameEngine(String bot1, String bot2) {
		BOT1Name=bot1;
		BOT2Name=bot2;
		
	}
		
		/*creamos el objeto <<slot>>, que será cada una de las casillas de nuestro mapa. Incluye dentro los "subobjetos" <<bullet>> y <<player>>.
		 Estas casillas servirán para indicar si hay un árbol, un arbusto, un agujero, un disparo, zona roja, una bandera o un jugador en esa posición*/
	/**
	 * Objeto bala que será instanciado DOS veces en cada SLOT del mapa
	 * @author kryon
	 *
	 */
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
	/**
	 * Objeto jugador que será instanciado DOS veces en cada SLOT del mapa
	 * @author kryon
	 *
	 */
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
	/**
	 * Objeto casilla que será instanciada una vez en cada posición del mapa. Contiene toda la información para saber qué hay o deja de haber en cada casilla
	 * @author kryon
	 *
	 */
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
			/**
			 * 
			 * @return ¿Está la bala de jugador 1 aquí? T/F
			 */
			public boolean isBulletHerePJ1() {
					
				return shotPJ1.isBulletHere();
				
			}
			/**
			 * 
			 * @return ¿Está la bala del jugador 2 aquí? T/F
			 */
			public boolean isBulletHerePJ2() {
				
				return shotPJ2.isBulletHere();
			
			}
			/**
			 * 
			 * @return Dirección de la bala de PJ1
			 */
			public char getBulletDirPJ1() {
				
				return shotPJ1.getBulletDir();
				
			}
			/**
			 * 
			 * @return Dirección de la bala de PJ2
			 */
			public char getBulletDirPJ2() {
				
				return shotPJ2.getBulletDir();
				
			}
			/**
			 * 
			 * @return ¿Está la bala de PJ1 actualizada? T/F
			 */
			public boolean isBulletUpdatedPJ1() {
				
				return shotPJ1.isJustUpdated();
				
			}
			/**
			 * 
			 * @return ¿Está la bala de PJ2 actualizada? T/F
			 */
			public boolean isBulletUpdatedPJ2() {
				
				return shotPJ2.isJustUpdated();
				
			}
			/**
			 * Método para colocar una bala en una nueva casilla
			 * @param who (De qué PJ es (1 ó 2))
			 * @param D (Dirección que tiene la bala (N,S,E,W))
			 */
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
			/**
			 * Método para borrar una bala de una casilla (porque ya ha avanzado o se ha chocado)
			 * @param who (De quién es la bala (1 ó 2))
			 */
			public void removeBullet(int who) {
				
				switch (who) {
				case 1:
					
					shotPJ1.setBulletHere(false);
					
					break;
				
				case 2:
				
					shotPJ2.setBulletHere(false);
				
			}
				
			}
			/**
			 * Método para actualizar el estado de una bala, para cuando mueve en el mismo sentido que la generación del mapa.
			 *  Así se evita que se vuelva a desplazar cuando se genere el trozo de mapa a donde acaba de mover
			 * @param who (De quién es la bala (1 ó 2))
			 * @param tf (Si la actualizamos o no (true/false))
			 */
			public void updateBulletStatus(int who, boolean tf) {
				
				switch (who) {
				case 1:
					
					shotPJ1.setJustUpdated(tf);
					
					break;
				
				case 2:
				
					shotPJ2.setJustUpdated(tf);
				
			}
				
			}
			/**
			 * 
			 * @return ¿Está el PJ1 aquí?
			 */
		public boolean isPJ1Here() {
			
			return player1.isPlayerHere();
			
		}
		/**
		 * 
		 * @return ¿Está el PJ2 aquí?
		 */
		public boolean isPJ2Here() {
			
			return player2.isPlayerHere();
		
		}
		/**
		 * 
		 * @return Dirección del PJ1
		 */
		public char getPJ1Dir() {
			
			return player1.getPlayerDir();
			
		}
		/**
		 * 
		 * @return Dirección del PJ2
		 */
		public char getPJ2Dir() {
			
			return player2.getPlayerDir();
			
		}
		/**
		 * Saber si está actualizado para cuando mueve en la misma dirección del mapa. Así evitamos que cuando se
		 *  genere ese nuevo trozo de mapa a donde ha movido vuelva a moverse
		 * @return ¿Está el PJ1 actualizado? 
		 */
		public boolean isPJ1Updated() {
			
			return player1.isJustUpdated();
			
		}
		/**
		 * Saber si está actualizado para cuando mueve en la misma dirección del mapa. Así evitamos que cuando se
		 *  genere ese nuevo trozo de mapa a donde ha movido vuelva a moverse
		 * @return ¿Está el PJ2 actualizado? 
		 */
		public boolean isPJ2Updated() {
			
			return player2.isJustUpdated();
			
		}
		/**
		 * Método para recolocar al PJ en una nueva casilla
		 * @param who (Qué jugador es (1 ó 2))
		 * @param D (Dirección que lleva (N,S,E,W))
		 */
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
		/**
		 * Método para borrar al PJ indicado
		 * @param who (1 ó 2)
		 */
		public void removePJ(int who) {
			
			switch (who) {
			case 1:
				
				player1.setPlayerHere(false);
				
				break;
			
			case 2:
			
				player2.setPlayerHere(false);
			
		}
			
		}
		/**
		 * Actualizar estado del PJ para cuando mueve en la misma dirección del mapa. Así evitamos que cuando se
		 *  genere ese nuevo trozo de mapa a donde ha movido vuelva a moverse
		 * @param who (1 ó 2)
		 * @param tf (true/false)
		 */
		public void updatePlayerStatus(int who, boolean tf) {
			
			switch (who) {
			case 1:
				
				player1.setJustUpdated(tf);
				
				break;
			
			case 2:
			
				player2.setJustUpdated(tf);
			
			}
			
		}
		/**
		 * 
		 * @return ¿Está el PJ1 llevando la bandera de PJ2?
		 */
		public boolean isPJ1CarryFlagPJ2() {
			
			return player1.isCarryFlagPJ2();
			
		}
		/**
		 * 
		 * @return ¿Está el PJ2 llevando la bandera de PJ1?
		 */
		public boolean isPJ2CarryFlagPJ1() {
			
			return player2.isCarryFlagPJ1();
			
		}
		/**
		 * Poner si el PJ1 lleva o no la bandera de PJ2
		 * @param carry (t/f)
		 */
		public void setPJ1CarryFlagPJ2(boolean carry) {
			
			player1.setCarryFlagPJ2(carry);
			
		}
		/**
		 * Poner si el PJ2 lleva o no la bandera de PJ1
		 * @param carry (t/f)
		 */
		public void setPJ2CarryFlagPJ1(boolean carry) {
			
			player2.setCarryFlagPJ1(carry);
			
		}
			/**
			 * 
			 * @return ¿Hay árbol en esta posición?
			 */
			public boolean isTreeTF() {
				return treeTF;
			}
			/**
			 * Colocar o quitar un árbol
			 * @param treeTF
			 */
			public void setTreeTF(boolean treeTF) {
				this.treeTF = treeTF;
			}
			/**
			 * 
			 * @return ¿Hay arbusto en esta posición?
			 */
			public boolean isBushTF() {
				return bushTF;
			}
			/**
			 * Colocar o quitar un arbusto
			 * @param bushTF
			 */
			public void setBushTF(boolean bushTF) {
				this.bushTF = bushTF;
			}
			/**
			 * 
			 * @return ¿Hay un agujero en esta posición?
			 */
			public boolean isHoleTF() {
				return holeTF;
			}
			/**
			 * Colocar o quitar un agujero en esta posición
			 * @param holeTF
			 */
			public void setHoleTF(boolean holeTF) {
				this.holeTF = holeTF;
			}
			/**
			 * 
			 * @return ¿Hay una bandera en esta posición?
			 */
			public boolean isFlagP1TF() {
				return flagP1TF;
			}
			/**
			 * colocar o quitar una bandera en esta posición
			 * @param flagP1TF
			 */
			public void setFlagP1TF(boolean flagP1TF) {
				this.flagP1TF = flagP1TF;
			}
			/**
			 * 
			 * @return ¿Está la bandera de PJ2 en esta posición?
			 */
			public boolean isFlagP2TF() {
				return flagP2TF;
			}
			/**
			 * Colocar o quitar la bandera de PJ2 en esta posición
			 * @param flagP2TF
			 */
			public void setFlagP2TF(boolean flagP2TF) {
				this.flagP2TF = flagP2TF;
			}
			/**
			 * 
			 * @return ¿Hay zona roja en esta posición?
			 */
			public boolean isRedZone() {
				return redZone;
			}
			/**
			 * Colocar o quitar una zona roja en esta posición
			 * @param redZone
			 */
			public void setRedZone(boolean redZone) {
				this.redZone = redZone;
			}
			
		}
	
	//Métodos para leer y escribir las variables privadas de la clase
	/**
	 * Método para pasar turno
	 * @return
	 */
	public int nextTurn(){
		turn++;
		return turn;
	}
	/**
	 * Método para saber en qué turno estamos. Información extra para la IA que desee saberlo para tomar sus decisiones
	 * @return
	 */
	public int getTurn() {
		return turn;
	}
	/**
	 * 
	 * @return ¿Ha terminado la partida?
	 */
	public boolean isGameEnded() {
		return gameEnded;
	}
	/**
	 * Método para finalizar el juego
	 * @param gameEnded
	 */
	public void setGameEnded(boolean gameEnded) {
		this.gameEnded = gameEnded;
	}
	/**
	 * 
	 * @return ¿Está el PJ1 aturdido?
	 */
	public boolean isPJ1Stunned() {
		return PJ1Stunned;
	}
	/**
	 * Poner o quitar aturdimiento al PJ1
	 * @param stunned
	 */
	public void setPJ1Stunned(boolean stunned) {
		this.PJ1Stunned = stunned;
	}
	/**
	 * 
	 * @return ¿Está el PJ2 aturdido?
	 */
	public boolean isPJ2Stunned() {
		return PJ2Stunned;
	}
	/**
	 * Poner o quitar aturdimiento al PJ2
	 * @param stunned
	 */
	public void setPJ2Stunned(boolean stunned) {
		this.PJ2Stunned = stunned;
	}
	/**
	 * 
	 * @return ¿Acaba de respawnear el PJ1?
	 */
	public boolean isPJ1JustRespawned() {
		return PJ1JustRespawned;
	}
	/**
	 * Pone o quita si el PJ1 acaba de respawnear
	 * @param pJ1JustRespawned
	 */
	public void setPJ1JustRespawned(boolean pJ1JustRespawned) {
		PJ1JustRespawned = pJ1JustRespawned;
	}
	/**
	 * 
	 * @return ¿Acaba de respawnear PJ2?
	 */
	public boolean isPJ2JustRespawned() {
		return PJ2JustRespawned;
	}
	/**
	 * Pone o quita si PJ2 acaba de respawnear
	 * @param pJ2JustRespawned
	 */
	public void setPJ2JustRespawned(boolean pJ2JustRespawned) {
		PJ2JustRespawned = pJ2JustRespawned;
	}
	/**
	 * 
	 * @return ¿Está PJ1 recién aturdido?
	 */
	public boolean isPJ1JustStunned() {
		return PJ1JustStunned;
	}
	/**
	 * Pone o quita el RECIÉN aturdimiento de PJ1. Se hace de esta manera por cómo está estructurado el código en la parte de updateMovements()
	 * @param pJ1JustStunned
	 */
	public void setPJ1JustStunned(boolean pJ1JustStunned) {
		PJ1JustStunned = pJ1JustStunned;
	}
	/**
	 * 
	 * @return ¿Está PJ2 recién aturdido?
	 */
	public boolean isPJ2JustStunned() {
		return PJ2JustStunned;
	}
	/**
	 *  Pone o quita el RECIÉN aturdimiento de PJ2. Se hace de esta manera por cómo está estructurado el código en la parte de updateMovements()
	 * @param pJ2JustStunned
	 */
	public void setPJ2JustStunned(boolean pJ2JustStunned) {
		PJ2JustStunned = pJ2JustStunned;
	}
	/**
	 * 
	 * @return ¿Está PJ1 confundido?
	 */
	public boolean isPJ1Confused() {
		return PJ1Confused;
	}
	/**
	 * Pone o quita el estado de confusión de PJ1
	 * @param pJ1Confused
	 */
	public void setPJ1Confused(boolean pJ1Confused) {
		PJ1Confused = pJ1Confused;
	}
	/**
	 * 
	 * @return ¿Está PJ2 confundido?
	 */
	public boolean isPJ2Confused() {
		return PJ2Confused;
	}
	/**
	 * Pone o quita el estado de confusión de PJ2
	 * @param pJ2Confused
	 */
	public void setPJ2Confused(boolean pJ2Confused) {
		PJ2Confused = pJ2Confused;
	}

		//Array bidimensional que será nuestro mapa hecho de casillas
	
	slot[][] combatMapSlot=new slot[50][50];
		
		//Array bidimensional que será nuestra reproducción a colores de lo que hay en cada slot del mapa
		
	String[][] combatMapScreen=new String[50][50];
		
		//Métodos para inicializar las arrays
	/**
	 * Método para inicializar la array de combatMapSlot[][]
	 */
	public void initializeCombatMapSlot() {
		
		//Inicializamos la array
		
		for(int x=0;x<50;x++) {
			for(int y=0;y<50;y++) {
				combatMapSlot[x][y]=new slot();
			}
		}
	}
	/**
	 * Método para inicializar la array de combatMapScreen[][]
	 */
	public void initializeCombatMapScreen() {
		
		//Inicializamos la array
		
		for(int x=0;x<50;x++) {
			for(int y=0;y<50;y++) {
				combatMapScreen[x][y]="\033[1;47m   \033[0m";
			}
		}
	}
		//Método que genera el bosque y coloca los true de cada elemento en combatMapSlot
		/**
		 * Método para generar el "bosque" de obstáculos
		 */
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
					
			}
		}
			
			//Le indicamos que cuando acabe de poner los obstáculos ponga una bandera de cada y un jugador de cada
			
			mapFlagGenerated=true;
			while(mapFlagGenerated) {
				//Genera una posición aleatoria, si está libre, pone la bandera, si no, repite la operación
				x=(int)(Math.random() * 50);
				y=(int)(Math.random() * 50);
				
				if (combatMapSlot[x][y].isBushTF()==false && combatMapSlot[x][y].isTreeTF()==false && combatMapSlot[x][y].isHoleTF()==false) {
				
					combatMapSlot[x][y].setFlagP1TF(true);
					mapFlagGenerated=false;
					
				}
				
			}
		
		
			mapFlagGenerated=true;
			
			while(mapFlagGenerated) {
				
				x=(int)(Math.random() * 50);
				y=(int)(Math.random() * 50);
				
				if (combatMapSlot[x][y].isBushTF()==false && combatMapSlot[x][y].isTreeTF()==false && combatMapSlot[x][y].isHoleTF()==false && combatMapSlot[x][y].isFlagP1TF()==false) {
				
					combatMapSlot[x][y].setFlagP2TF(true);
					mapFlagGenerated=false;
					
				}
				
			}
		
		
		player1StartingPos=true;
		player2StartingPos=true;
		
		//Mismo procedimiento que con las banderas, pero para colocar a los jugadores
		
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
	/**
	 * Método para generar el "dibujo" de qué hay en cada casilla de combatMapScreen[][]. Los ejes determinan la posición de la casilla
	 * @param x Eje X
	 * @param y Eje Y
	 */
	public void generateColorMap(int x, int y) {
		
		/*
		 * La única manera que tenemos de pintar las balas que van en dirección contraria a la generación del mapa es la que viene a continuación
		 * Comprobamos que si hay alguna bala que va a entrar en nuestra casilla, nos adelantamos y la pintamos. Ésto lo hacemos
		 * al principio del todo para que si luego resulta que hay un árbol o lo que sea, se borre. En updateShots ya será borrada la bala en sí, ahora
		 * sólo nos preocupamos por el dibujo.
		 * 
		 * */
		
		try {
			
			if(combatMapSlot[x][y+1].isBulletHerePJ1()==true&&combatMapSlot[x][y+1].getBulletDirPJ1()=='N'&&combatMapSlot[x][y].isRedZone()==false&&combatMapSlot[x][y].isTreeTF()==false&&combatMapSlot[x][y+1].isTreeTF()==false) {
				combatMapScreen[x][y]="\033[1;47m\033[1;34m + \033[0m";
				combatMapSlot[x][y].moveBullet(1,'N');
			}
			if(combatMapSlot[x][y+1].isBulletHerePJ2()==true&&combatMapSlot[x][y+1].getBulletDirPJ2()=='N'&&combatMapSlot[x][y].isRedZone()==false&&combatMapSlot[x][y].isTreeTF()==false&&combatMapSlot[x][y+1].isTreeTF()==false) {
				combatMapScreen[x][y]="\033[1;47m\033[1;35m + \033[0m";
				combatMapSlot[x][y].moveBullet(2,'N');
			}
			if(combatMapSlot[x+1][y].isBulletHerePJ1()==true&&combatMapSlot[x+1][y].getBulletDirPJ1()=='W'&&combatMapSlot[x][y].isRedZone()==false&&combatMapSlot[x][y].isTreeTF()==false&&combatMapSlot[x+1][y].isTreeTF()==false) {
				combatMapScreen[x][y]="\033[1;47m\033[1;34m + \033[0m";
				combatMapSlot[x][y].moveBullet(1,'W');
			}
			if(combatMapSlot[x+1][y].isBulletHerePJ2()==true&&combatMapSlot[x+1][y].getBulletDirPJ2()=='W'&&combatMapSlot[x][y].isRedZone()==false&&combatMapSlot[x][y].isTreeTF()==false&&combatMapSlot[x+1][y].isTreeTF()==false) {
				combatMapScreen[x][y]="\033[1;47m\033[1;35m + \033[0m";
				combatMapSlot[x][y].moveBullet(2,'W');
			}
			
			}
			catch(ArrayIndexOutOfBoundsException e) {
				//Si entra aquí es que ha tratado de comprobar si había bala en una posición fuera de la matriz.Simplemente lo ignoramos
			}
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
						
						if(combatMapSlot[x][y].isBushTF()==true) {
							combatMapScreen[x][y]="\033[42m\033[1;34m + \033[0m";
						}
						else if(combatMapSlot[x][y].isHoleTF()==true) {
							combatMapScreen[x][y]="\033[40m\033[1;34m + \033[0m";
						}
						else {
							combatMapScreen[x][y]="\033[1;47m\033[1;34m + \033[0m";
						}
					}
					if(combatMapSlot[x][y].isBulletHerePJ2()==true) {
						
						if(combatMapSlot[x][y].isBushTF()==true) {
							combatMapScreen[x][y]="\033[42m\033[1;35m + \033[0m";
						}
						else if(combatMapSlot[x][y].isHoleTF()==true) {
							combatMapScreen[x][y]="\033[40m\033[1;35m + \033[0m";
						}
						else {
							combatMapScreen[x][y]="\033[1;47m\033[1;35m + \033[0m";
						}
					}
					if(combatMapSlot[x][y].isPJ1Here()==true&&isPJ1Confused()==false) {
						
						if(isPJ1Stunned()==true) {
							
								combatMapScreen[x][y]="\033[1;44m!!!\033[0m";
								
						}
							
						else {
							
						combatMapScreen[x][y]="\033[1;44mIA1\033[0m";
						
						}
					}
					if(isPJ1Confused()==true&&combatMapSlot[x][y].isPJ1Here()==true) {
						
						combatMapScreen[x][y]="\033[1;44m???\033[0m";
					
					}
					if(combatMapSlot[x][y].isPJ2Here()==true&&isPJ2Confused()==false) {
						
						if(isPJ2Stunned()==true) {
							
							combatMapScreen[x][y]="\033[1;45m!!!\033[0m";
							
						}
						
						else {
						
							combatMapScreen[x][y]="\033[1;45mIA2\033[0m";
					
						}
					}
					if(isPJ2Confused()==true&&combatMapSlot[x][y].isPJ2Here()==true) {
						
						combatMapScreen[x][y]="\033[1;45m???\033[0m";
					
					}
					if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
						combatMapScreen[x][y]="\033[1;44m-2-\033[0m";
					}
					if(combatMapSlot[x][y].isPJ2CarryFlagPJ1()==true) {
						combatMapScreen[x][y]="\033[1;45m-1-\033[0m";
					}
					//Nos aseguramos de que si no hay nada en esa casilla, se pinte (o repinte) en blanco
					
					if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==false&&combatMapSlot[x][y].isPJ2CarryFlagPJ1()==false&&combatMapSlot[x][y].isPJ2Here()==false&&combatMapSlot[x][y].isPJ1Here()==false&&
						combatMapSlot[x][y].isBulletHerePJ2()==false&&combatMapSlot[x][y].isBulletHerePJ1()==false&&combatMapSlot[x][y].isRedZone()==false&&combatMapSlot[x][y].isFlagP2TF()==false&&
						combatMapSlot[x][y].isFlagP1TF()==false&&combatMapSlot[x][y].isHoleTF()==false&&combatMapSlot[x][y].isTreeTF()==false&&combatMapSlot[x][y].isBushTF()==false) {
						
						combatMapScreen[x][y]="\033[1;47m   \033[0m";
						
					}
		
	}

		//Método para crear todo el conjunto del mapa y printearlo una primera vez
	/**
	 * Método para imprimir el mapa AL PRINCIPIO, la PRIMERA VEZ
	 */
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

		//Método para actualizar las posiciones de las balas
	/**
	 * Método apra actualizar las posiciones de las balas en cada posición de las cuadrículas.
	 * @param x Eje X
	 * @param y Eje Y
	 * @throws IllegalArgumentException (Sólo cuando haya un fallo en GameEngine que permita pasar una dirección imposible a una bala. No debería darse el caso)
	 */
	public void updateShots(int x,int y) throws IllegalArgumentException {
		
		//Si la bala se encuentra donde el jugador contrario a quien la disparó, lo mata y lo respawnea
		
		if(combatMapSlot[x][y].isBulletHerePJ1()==true && combatMapSlot[x][y].isPJ2Here()==true)  {
			
			respawnPlayer(2,x,y);
			P2Kill++;
			
			//Si el jugador que muere lleva una bandera consigo, ésta cae al suelo en esa posición
			
			if(combatMapSlot[x][y].isBulletHerePJ1()==true && combatMapSlot[x][y].isPJ2CarryFlagPJ1()==true) {
				
				combatMapSlot[x][y].setFlagP1TF(true);
				combatMapSlot[x][y].setPJ2CarryFlagPJ1(false);
				
			}
			
		}
		
		if(combatMapSlot[x][y].isBulletHerePJ2()==true && combatMapSlot[x][y].isPJ1Here()==true)  {
			
			respawnPlayer(1,x,y);
			P1Kill++;
			
			if(combatMapSlot[x][y].isBulletHerePJ2()==true && combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
				
				combatMapSlot[x][y].setFlagP2TF(true);
				combatMapSlot[x][y].setPJ1CarryFlagPJ2(false);
				
			}
			
		}
		
		//Comprobamos que no haya un árbol o una zona roja para pasar a seguir moviendo la bala
		
		if(combatMapSlot[x][y].isTreeTF()==false) {
		
		if(combatMapSlot[x][y].isRedZone()==false) {
		
		if(combatMapSlot[x][y].isBulletHerePJ1()==true && combatMapSlot[x][y].isBulletUpdatedPJ1()==false) {
			
			try {
				
			//Basándonos en la dirección que tiene, pasamos a moverla y a volver a indicarle la dirección a la siguiente posición
				
				switch (combatMapSlot[x][y].getBulletDirPJ1()) {
				
					case 'N':
						//Tenemos que repintar lo que había en una casilla ya pintada (y-1) ya que ahora hay una bala, y antes no. En el caso 'W' igual

						combatMapSlot[x][y-1].moveBullet(1,'N');
						combatMapScreen[x][y-1]="\033[1;47m\033[1;34m + \033[0m";
						combatMapSlot[x][y].removeBullet(1);
						
						break;
					
					case 'S':
						
						combatMapSlot[x][y+1].moveBullet(1,'S');
						combatMapSlot[x][y+1].updateBulletStatus(1, true);
						combatMapSlot[x][y].removeBullet(1);
						
						break;
					
					case 'E':

						combatMapSlot[x+1][y].moveBullet(1,'E');
						combatMapSlot[x+1][y].updateBulletStatus(1, true);
						combatMapSlot[x][y].removeBullet(1);
					
						break;
					
					case 'W':
						
						combatMapScreen[x-1][y]="\033[1;47m\033[1;34m + \033[0m";
						combatMapSlot[x-1][y].moveBullet(1,'W');
						combatMapSlot[x][y].removeBullet(1);
					
						break;
					
					default:
						
					throw new IllegalArgumentException(String.format("HUGE ERROR happened on method updateShots(int x, int y)."+
					"\nPossibly a non acceptable CHAR was inputed on the bullet direction of shotPJ1: "+combatMapSlot[x][y].getBulletDirPJ1()));
				}
			}
					//Si salta un error de Out of Bounds es porque ha llegado al límite del mapa. Simplemente borramos la bala en su posición actual
			catch(ArrayIndexOutOfBoundsException e) {
						
				combatMapSlot[x][y].removeBullet(1);
						
			}
					
			}
			
			//Si la bala acaba de ser colocada ahí en este mismo turno y en la isguiente iteración se vuelve a encontrar con ella, pasamos para que no la vuelva a mover
		
		if(combatMapSlot[x][y].isBulletHerePJ1()==true && combatMapSlot[x][y].isBulletUpdatedPJ1()==true) {
			
			combatMapSlot[x][y].updateBulletStatus(1, false);
		}
		
		
		
		
		
		//PJ2*********************************************************************************************************************************
		
		
		
		
		
		if(combatMapSlot[x][y].isBulletHerePJ2()==true && combatMapSlot[x][y].isBulletUpdatedPJ2()==false) {
			
			try {
			
				switch (combatMapSlot[x][y].getBulletDirPJ2()) {
				
					case 'N':
						
						combatMapScreen[x][y-1]="\033[1;47m\033[1;35m + \033[0m";
						combatMapSlot[x][y-1].moveBullet(2,'N');
						combatMapSlot[x][y].removeBullet(2);
						
						break;
					
					case 'S':
						
						combatMapSlot[x][y+1].moveBullet(2,'S');
						combatMapSlot[x][y+1].updateBulletStatus(2, true);
						combatMapSlot[x][y].removeBullet(2);
						
						break;
					
					case 'E':

						combatMapSlot[x+1][y].moveBullet(2,'E');
						combatMapSlot[x+1][y].updateBulletStatus(2, true);
						combatMapSlot[x][y].removeBullet(2);
					
						break;
					
					case 'W':
						
						combatMapScreen[x-1][y]="\033[1;47m\033[1;35m + \033[0m";
						combatMapSlot[x-1][y].moveBullet(2,'W');
						combatMapSlot[x][y].removeBullet(2);
					
						break;
					
					default:
						
					throw new IllegalArgumentException(String.format("HUGE ERROR happened on method updateShots(int x, int y)."+
					"\nPossibly a non acceptable CHAR was inputed on the bullet direction of shotPJ2: "+combatMapSlot[x][y].getBulletDirPJ2()));
				}
			}
					
			catch(ArrayIndexOutOfBoundsException e) {
						
				combatMapSlot[x][y].removeBullet(2);
						
			}
					
			}
		
		if(combatMapSlot[x][y].isBulletHerePJ2()==true && combatMapSlot[x][y].isBulletUpdatedPJ2()==true) {
			
			combatMapSlot[x][y].updateBulletStatus(2, false);
		}
		
		}
		}
		
		
		//-----------------------------------------------------------------------------------------------------------------------
		
		//Si la bala se encuentra con un árbol la borramos
		
		if(combatMapSlot[x][y].isTreeTF()==true && (combatMapSlot[x][y].isBulletHerePJ2()==true || combatMapSlot[x][y].isBulletHerePJ1()==true)) {
			
			combatMapSlot[x][y].removeBullet(1);
			combatMapSlot[x][y].updateBulletStatus(1, false);
			combatMapSlot[x][y].removeBullet(2);
			combatMapSlot[x][y].updateBulletStatus(2, false);
			
		}
		
		//Si la bala se encuentra en zona roja la borramos
		
		if(combatMapSlot[x][y].isRedZone()==true && (combatMapSlot[x][y].isBulletHerePJ2()==true || combatMapSlot[x][y].isBulletHerePJ1()==true)) {
			
			combatMapSlot[x][y].removeBullet(1);
			combatMapSlot[x][y].updateBulletStatus(1, false);
			combatMapSlot[x][y].removeBullet(2);
			combatMapSlot[x][y].updateBulletStatus(2, false);
			
		}
			
	}

	//Método para respawnear a los jugadores cuando mueren
	/**
	 * Método para respawnear a un PJ que muere porque cae en un agujero o porque le da una bala enemiga
	 * @param who Qué jugador (1 ó 2)
	 * @param x Posición del eje X donde estaba
	 * @param y Posición del eje Y donde estaba
	 */
	public void respawnPlayer(int who, int x, int y) {
		
		switch(who) {
		
		case 1:
			
			//Repetimos como cuando lo colocamos por primera vez pero teniendo más cosas en cuenta
			
			player1StartingPos=true;
			
			while(player1StartingPos) {
				
				int respawnx=(int)(Math.random() * 50);
				int respawny=(int)(Math.random() * 50);
				
				combatMapSlot[x][y].removePJ(1);
				
				if(!(respawnx==x) || !(respawny==y)) {
				
				if (combatMapSlot[respawnx][respawny].isRedZone()==false && combatMapSlot[respawnx][respawny].isBushTF()==false && combatMapSlot[respawnx][respawny].isTreeTF()==false && combatMapSlot[respawnx][respawny].isHoleTF()==false && combatMapSlot[respawnx][respawny].isFlagP2TF()==false && combatMapSlot[respawnx][respawny].isPJ2Here()==false) {
				
					//Lo colocamos en su nueva posición y lo dejamos aturdido
					
					combatMapSlot[respawnx][respawny].movePJ(1, 'P');
					setPJ1Stunned(true);
					setPJ1JustRespawned(true);
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
			
					if (combatMapSlot[respawnx][respawny].isRedZone()==false && combatMapSlot[respawnx][respawny].isBushTF()==false && combatMapSlot[respawnx][respawny].isTreeTF()==false && combatMapSlot[respawnx][respawny].isHoleTF()==false && combatMapSlot[respawnx][respawny].isFlagP1TF()==false && combatMapSlot[respawnx][respawny].isPJ1Here()==false) {
			
						combatMapSlot[respawnx][respawny].movePJ(2, 'P');
						setPJ2Stunned(true);
						setPJ2JustRespawned(true);
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

	//Método para respawnear la bandera cuando caiga en un aguujero
	/**
	 * Método para respawnear la posición de la bandera cuando cae en un agujero
	 * @param who Bandera de PJ1 o PJ2 (1 ó 2)
	 * @param x Posición X donde ha caído en un agujero
	 * @param y Posición Y donde ha caído en un agujero
	 */
	public void respawnFlag(int who, int x, int y) {
		
		switch(who) {
		
		case 1:
			
			player1StartingPos=true;
			
			while(player1StartingPos) {
				
				int respawnx=(int)(Math.random() * 50);
				int respawny=(int)(Math.random() * 50);
				
				if(!(respawnx==x) || !(respawny==y)) {
				
				if (combatMapSlot[respawnx][respawny].isBushTF()==false && combatMapSlot[respawnx][respawny].isTreeTF()==false && combatMapSlot[respawnx][respawny].isHoleTF()==false && combatMapSlot[respawnx][respawny].isFlagP2TF()==false && combatMapSlot[respawnx][respawny].isPJ1Here()==false && combatMapSlot[respawnx][respawny].isPJ2Here()==false) {
				
					combatMapSlot[respawnx][respawny].setFlagP2TF(true);
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
				
					combatMapSlot[respawnx][respawny].setFlagP1TF(true);
					player1StartingPos=false;
					
				}
				}
				
			}
		
			break;
	
		}
		
	}

	//Método para actualizar las posiciones de los jugadores cuando se mueven
	/**
	 * Actualizar movimientos de los PJs en cada casilla (Busca en la casilla indicada si hay PJs, si los hay, los mueve o lo que pertoque, si no los hay, pasa de largo
	 * @param x Posición del eje X
	 * @param y Posición del eje Y
	 */
	public void updateMovements(int x,int y) {
		
		if(combatMapSlot[x][y].isRedZone()==false) {
			
			//Si al moverse se da cuenta de que tocaría estar muerto, respawnea en otro sitio. Si lleva bandera la suelta.
			
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
			
			
			
			//PJ1*************************************************************************************************************************************
			
			
			
			//Si el jugador 1 está sobre la bandera 2, la coge
			
			if(combatMapSlot[x][y].isPJ1Here() && combatMapSlot[x][y].isFlagP2TF()==true) {
				
				combatMapSlot[x][y].setPJ1CarryFlagPJ2(true);
				combatMapSlot[x][y].setFlagP2TF(false);
				
			}
		
			//Si el jugador está aquí y no está atontado ni recién colocado en este mismo turno, entra y procede a moverlo
			
		if(combatMapSlot[x][y].isPJ1Here()==true && combatMapSlot[x][y].isPJ1Updated()==false && combatMapSlot[x][y].isBulletHerePJ2()==false && isPJ1Stunned()==false) {
			
			try {
			
				//Cogemos la dirección del jugador y lo movemos como nos indique
				
				switch (combatMapSlot[x][y].getPJ1Dir()) {
				
					case 'N':
							
						//Si en la posición a la que va a mover hay algo con lo que va a chocar, no va y se queda stunned
						
						if(combatMapSlot[x][y-1].isTreeTF()==true || combatMapSlot[x][y-1].isBushTF()==true || combatMapSlot[x][y].isPJ2Here()==true) {
							
							setPJ1JustStunned(true);
							setPJ1Stunned(true);
							
						}
						
						//Si no va a chocar, mueve
						
						else {
							combatMapSlot[x][y-1].movePJ(1,'N');
							
							//Si lleva una abndera, la sigue llevando a su nueva posición
							
							if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
								
								combatMapScreen[x][y-1]="\033[1;44m-2-\033[0m";
								combatMapSlot[x][y-1].setPJ1CarryFlagPJ2(true);
								combatMapSlot[x][y].setPJ1CarryFlagPJ2(false);
								
							}
							
							setPJ1Confused(false);
							//Lo borramos de su actual posición porque ya ha avanzado
							combatMapSlot[x][y].removePJ(1);
						}
						
						break;
					
					case 'S':
						
						if(combatMapSlot[x][y+1].isTreeTF()==true || combatMapSlot[x][y+1].isBushTF()==true || combatMapSlot[x][y+1].isPJ2Here()==true) {
							
							setPJ1JustStunned(true);
							setPJ1Stunned(true);
							
						}
						
						else {
							
							combatMapSlot[x][y+1].movePJ(1,'S');
							combatMapSlot[x][y+1].updatePlayerStatus(1, true);
							
							if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
								
								
								combatMapSlot[x][y+1].setPJ1CarryFlagPJ2(true);
								combatMapSlot[x][y].setPJ1CarryFlagPJ2(false);
								
							}

							setPJ1Confused(false);
							combatMapSlot[x][y].removePJ(1);
						
						}
						
						break;
					
					case 'E':

						if(combatMapSlot[x+1][y].isTreeTF()==true || combatMapSlot[x+1][y].isBushTF()==true || combatMapSlot[x+1][y].isPJ2Here()==true) {
							
							setPJ1JustStunned(true);
							setPJ1Stunned(true);
							
						}
						
						else {
						
							combatMapSlot[x+1][y].movePJ(1,'E');
							combatMapSlot[x+1][y].updatePlayerStatus(1, true);
							if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
								
								combatMapSlot[x+1][y].setPJ1CarryFlagPJ2(true);
								combatMapSlot[x][y].setPJ1CarryFlagPJ2(false);
								
							}
							
							setPJ1Confused(false);
							combatMapSlot[x][y].removePJ(1);
						
						}
					
						break;
					
					case 'W':
						
						if(combatMapSlot[x-1][y].isTreeTF()==true || combatMapSlot[x-1][y].isBushTF()==true || combatMapSlot[x-1][y].isPJ2Here()==true) {
							
							setPJ1JustStunned(true);
							setPJ1Stunned(true);
							
						}
						
						else {
						
							combatMapSlot[x-1][y].movePJ(1,'W');
							
							if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
								
								combatMapScreen[x-1][y]="\033[1;44m-2-\033[0m";
								combatMapSlot[x-1][y].setPJ1CarryFlagPJ2(true);
								combatMapSlot[x][y].setPJ1CarryFlagPJ2(false);
								
							}
							
							setPJ1Confused(false);
							combatMapSlot[x][y].removePJ(1);
						
						}
					
						break;
						
					case 'P':
						
							combatMapSlot[x][y].movePJ(1,'P');
							
							if(combatMapSlot[x][y].isPJ1CarryFlagPJ2()==true) {
								
								combatMapSlot[x][y].setPJ1CarryFlagPJ2(true);
								
							}
							
							setPJ1Confused(false);
					
						break;
					
					default:
						
						setPJ1Confused(true);
						
				}
			}
				
			catch(ArrayIndexOutOfBoundsException e) {
						
				setPJ1Confused(true);
				
			}
					
			}
			
		//Si está recién actualizado, ya no ha movido. Lo cambiamos para que la siguiente vez que nos topemos con él ya sí lo movamos
		
		if(combatMapSlot[x][y].isPJ1Here()==true && combatMapSlot[x][y].isPJ1Updated()==true) {
			
			combatMapSlot[x][y].updatePlayerStatus(1, false);
		}
		
		//Comprobamos si está stunned, si lo está, entramos
		
		if(combatMapSlot[x][y].isPJ1Here()==true && isPJ1Stunned()==true) {
			
			//Si está recién spawneado, se lo quitamos 
			
			if(isPJ1JustRespawned()==true){
				
				setPJ1JustRespawned(false);
				
				}
				
			//Si no está recién spawneado, miramos si está recién stunneado o no, si lo está, lo quitamos. Si no está recién stunneado, le quitamos el stunned
			
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
		
		
		
		
		if(combatMapSlot[x][y].isPJ2Here() && combatMapSlot[x][y].isFlagP1TF()==true) {
			
			combatMapSlot[x][y].setPJ2CarryFlagPJ1(true);
			combatMapSlot[x][y].setFlagP1TF(false);
			
		}
	
	if(combatMapSlot[x][y].isPJ2Here()==true && combatMapSlot[x][y].isPJ2Updated()==false && combatMapSlot[x][y].isBulletHerePJ1()==false && isPJ2Stunned()==false) {
		
		try {
		
			switch (combatMapSlot[x][y].getPJ2Dir()) {
			
				case 'N':
						
					if(combatMapSlot[x][y-1].isTreeTF()==true || combatMapSlot[x][y-1].isBushTF()==true || combatMapSlot[x][y-1].isPJ1Here()==true) {
						
						setPJ2JustStunned(true);
						setPJ2Stunned(true);
						
					}
					else {
						
						combatMapSlot[x][y-1].movePJ(2,'N');
						
						if(combatMapSlot[x][y].isPJ2CarryFlagPJ1()==true) {
							
							combatMapSlot[x][y-1].setPJ2CarryFlagPJ1(true);
							combatMapSlot[x][y].setPJ2CarryFlagPJ1(false);
							
						}
						
						setPJ2Confused(false);
						combatMapSlot[x][y].removePJ(2);
						
					}
					
					break;
				
				case 'S':
					
					if(combatMapSlot[x][y+1].isTreeTF()==true || combatMapSlot[x][y+1].isBushTF()==true || combatMapSlot[x][y+1].isPJ1Here()==true) {
						
						setPJ2JustStunned(true);
						setPJ2Stunned(true);
						
					}
					
					else {
						combatMapSlot[x][y+1].movePJ(2,'S');
						combatMapSlot[x][y+1].updatePlayerStatus(2, true);
						if(combatMapSlot[x][y].isPJ2CarryFlagPJ1()==true) {
							
							combatMapSlot[x][y+1].setPJ2CarryFlagPJ1(true);
							combatMapSlot[x][y].setPJ2CarryFlagPJ1(false);
							
						}
						
						setPJ2Confused(false);
						combatMapSlot[x][y].removePJ(2);
					
					}
					
					break;
				
				case 'E':

					if(combatMapSlot[x+1][y].isTreeTF()==true || combatMapSlot[x+1][y].isBushTF()==true || combatMapSlot[x+1][y].isPJ1Here()==true) {
						
						setPJ2JustStunned(true);
						setPJ2Stunned(true);
						
					}
					
					else {
					
						combatMapSlot[x+1][y].movePJ(2,'E');
						combatMapSlot[x+1][y].updatePlayerStatus(2, true);
						if(combatMapSlot[x][y].isPJ2CarryFlagPJ1()==true) {
							
							combatMapSlot[x+1][y].setPJ2CarryFlagPJ1(true);
							combatMapSlot[x][y].setPJ2CarryFlagPJ1(false);
							
						}
						
						setPJ2Confused(false);
						combatMapSlot[x][y].removePJ(2);
					
					}
				
					break;
				
				case 'W':
					
					if(combatMapSlot[x-1][y].isTreeTF()==true || combatMapSlot[x-1][y].isBushTF()==true || combatMapSlot[x-1][y].isPJ1Here()==true) {
						
						setPJ1JustStunned(true);
						setPJ1Stunned(true);
						
					}
					
					else {
					
						combatMapSlot[x-1][y].movePJ(2,'W');
						
						if(combatMapSlot[x][y].isPJ2CarryFlagPJ1()==true) {
							
							combatMapSlot[x-1][y].setPJ2CarryFlagPJ1(true);
							combatMapSlot[x][y].setPJ2CarryFlagPJ1(false);
							
						}
						
						setPJ2Confused(false);
						combatMapSlot[x][y].removePJ(2);
					
					}
				
					break;
					
				case 'P':
					
						combatMapSlot[x][y].movePJ(2,'P');
						if(combatMapSlot[x][y].isPJ2CarryFlagPJ1()==true) {
							
							combatMapSlot[x][y].setPJ2CarryFlagPJ1(true);
							
						}
				
						setPJ2Confused(false);
						
					break;
				
				default:
					
					setPJ2Confused(true);
					
			}
		}
			
		catch(ArrayIndexOutOfBoundsException e) {
					
			setPJ2Confused(true);
			
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
	
	//Método para actualizar el mapa en cada subturno
	/**
	 * Método para actualizar el mapa cada subturno
	 */
	public void updateMap() {
		
		//Secuencia de escape que limpia la pantalla (da sensación de fotogramas)
		
		System.out.println("\033[H\033[2J");
		
		//Bucleamos por todo el mapa
		
		for(int y=0;y<50;y++) {
			for(int x=0;x<50;x++) {
				
				//Actualizamos cada posición respecto a las balas si las hubiere
				
				updateShots(x,y);
				
				//Aprovechamos el mismo bucle para pintar el mapa
				
				generateColorMap(x,y);
				
				//Una vez ya está pintado, lo imprimimos
				
				System.out.print(combatMapScreen[x][y]);
				
				//Final de línea
				
				if(x==49) {
					System.out.print("\n");
				}
			}
		}
		
		/*Una vez ya está todo impreso comprobamos si la partida toca acabarse (no se hace en el mismo bucle para que al acabarse se pueda ver el mapa entero
		y no sólo hasta donde haya pasado algo que lo finalice */
		
		for(int r=0;r<50;r++) {
			for(int j=0;j<50;j++) {
				
				//Comprobamos si alguna bandera ha tocado zona roja
				if(combatMapSlot[j][r].isFlagP1TF()==true && combatMapSlot[j][r].isRedZone()==true) {
					gameEnded=true;
				}
				
				if(combatMapSlot[j][r].isFlagP2TF()==true && combatMapSlot[j][r].isRedZone()==true) {
					gameEnded=true;
				}
				
				//Comprobamos si algún jugador ha llevado la bandera contraria hasta su propia bandera
				
				if(combatMapSlot[j][r].isPJ1CarryFlagPJ2()==true && combatMapSlot[j][r].isFlagP1TF()==true) {
					winner=BOT1Name;
					gameEnded=true;
				}
				if(combatMapSlot[j][r].isPJ2CarryFlagPJ1()==true && combatMapSlot[j][r].isFlagP2TF()==true) {
					winner=BOT2Name;
					gameEnded=true;
				}
				
				/*
				 * Esto queda comentado para activarse si se quiere que la partida acabe cuando un jugador es disparado, pero implica cambiar también los respawn de cuando
				 * te da un disparo enemigo. Posible v2 del juego sin banderas.PD: aunque no estuviera comentado no tendría efecto porque cuando la bala
				 * te pilla automáticamente repawneas, nunca llegarías a compartir espacio con ella cuando se llegara a este loop de comprobaciones, pero bueno.
				 * 
				if(combatMapSlot[j][r].isPJ1Here()==true && combatMapSlot[j][r].isBulletHerePJ2()==true) {
					
					PJ1Shooted=true;
					
				}
				if(combatMapSlot[j][r].isPJ2Here()==true && combatMapSlot[j][r].isBulletHerePJ1()==true) {
					
					PJ2Shooted=true;
					
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
				*
				*/
				
				//Comprobamos qiuén está en zona roja y decidimos ganador en consecuencia
				
				if(combatMapSlot[j][r].isPJ1Here()==true && combatMapSlot[j][r].isRedZone()==true) {
			
					PJ1Red=true;
			
				}
				if(combatMapSlot[j][r].isPJ2Here()==true && combatMapSlot[j][r].isRedZone()==true) {
					
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
		
		//contador debajo de la pantalla que muestra cuántas veces ha muerto cada jugador, el turno y, una vez se ha acabado la partida, quién ha ganado
		
		System.out.println("Player 1 killed "+P1Kill+" times. \t Player 2 killed "+P2Kill+" times");
		System.out.println("Turn:"+turn);
		if(gameEnded==true) {
			System.out.println(winner+" WON!");
		}
		
	}

	//Método para transmitir la decisión de cada jugador
	/**
	 * Método que recoge las decisiones de las dos IAs enfrentadas en esta partida (en forma de CHAR) para 
	 * transmitirlas a los métodos pertinentes y que realicen dichas acciones
	 * @param m Decisión de PJ1
	 * @param n Decisión de PJ2
	 */
	public void actionPlayers(char m, char n) {
		
		//Bucleamos por todo el mapa para encontrar a cada jugador
		
		for(int y=0;y<50;y++) {
			for(int x=0;x<50;x++) {
		
				//Ponemos el try/catch para evitar que trate de colocar una bala fuera del mapa
				
				try {
				//Si lo encontramos y no está stunned, entramos
				
				if(combatMapSlot[x][y].isPJ1Here()==true && isPJ1Stunned()==false) {
		
					//m es la decisión (char) que le pasa J1
					
					switch(m) {
		
					//Los cases de aquí sólo miran por los disparos, cualquier cosa que no sea disparo, lo escupe a updateMovements()
					
					case '8':
						
						combatMapSlot[x][y-1].moveBullet(1,'N');
						combatMapSlot[x][y].movePJ(1, 'P');
			
						break;
			
					case '2':
			
						combatMapSlot[x][y+1].moveBullet(1,'S');
						combatMapSlot[x][y+1].updateBulletStatus(1, true);
						combatMapSlot[x][y].movePJ(1, 'P');
			
						break;
			
					case '6':
			
						combatMapSlot[x+1][y].moveBullet(1,'E');
						combatMapSlot[x+1][y].updateBulletStatus(1, true);
						combatMapSlot[x][y].movePJ(1, 'P');
			
						break;
			
					case '4':
			
						combatMapSlot[x-1][y].moveBullet(1,'W');
						combatMapSlot[x][y].movePJ(1, 'P');
			
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
						combatMapSlot[x][y].movePJ(2, 'P');
			
						break;
			
					case '2':
			
						combatMapSlot[x][y+1].moveBullet(2,'S');
						combatMapSlot[x][y+1].updateBulletStatus(2, true);
						combatMapSlot[x][y].movePJ(2, 'P');
			
						break;
			
					case '6':
			
						combatMapSlot[x+1][y].moveBullet(2,'E');
						combatMapSlot[x+1][y].updateBulletStatus(2, true);
						combatMapSlot[x][y].movePJ(2, 'P');
			
						break;
			
					case '4':
			
						combatMapSlot[x-1][y].moveBullet(2,'W');
						combatMapSlot[x-1][y].updateBulletStatus(2, false);
						combatMapSlot[x][y].movePJ(2, 'P');
			
						break;
			
					default:
			
						combatMapSlot[x][y].movePJ(2, n);;
			
						;
					}
				}
				}
				catch(ArrayIndexOutOfBoundsException e) {
					
				}
				
				//Una vez ya se han hecho todos los movimientos pertinentes, actualizamos movimientos
				
				updateMovements(x,y);
			}
		}
		
		//Subimos turno
		
		turn++;
		
	}

	//Método para resetear cada slot cuando se vuelve zona roja
	/**
	 * Método para resetear y poner en blanco un slot que ha pasado a ser zona roja
	 * @param x Posición eje X
	 * @param y Posición eje Y
	 */
	public void resetSlot(int x,int y) {
		
		combatMapSlot[x][y].setTreeTF(false);
		combatMapSlot[x][y].setBushTF(false);
		combatMapSlot[x][y].setHoleTF(false);
		combatMapSlot[x][y].removeBullet(1);;
		combatMapSlot[x][y].removeBullet(2);;
		
	}
	
	//Método que amplía la zona roja
	/**
	 * Método que acciona un nuevo avance de la zona roja
	 */
	public void redZone() {
		
		/*La zona roja empieza en 0 pero aquí la subimos a 1. Lo hacemos así para que los jugadores puedan diferenciar entre una zona
		 * roja que aún no ha empezado y una zona roja que sólo lleva una iteración, por si lo quieren saber para tomar
		 * mejores decisiones*/
		
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
