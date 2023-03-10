import java.util.Arrays;

class Bender {
    String mapa;
    String route = "";
    static int mapRows;
    static int mapCols;
    static int[] myLocation; // = find('X')
    int[] goalLocation; // = find('$')
    static char[][] myMap;
    boolean reverse = false;


    // Constructor: ens passen el mapa en forma d'String
    public Bender(String mapa) {
        this.mapa = mapa;
    }
    // Navegar fins a l'objectiu («$»).
    // El valor retornat pel mètode consisteix en una cadena dels valors «S», «N», «W» o «E».

    public String run() {
        makeMap();
        robotGo();
        return this.route;
    }

    private void makeMap() {
        String[] rows = this.mapa.split("\n");
        mapRows = rows.length;
        mapCols = rows[0].length();
        myMap = new char[mapRows][mapCols];

        for (int i = 0; i < mapRows; i++) {
            char[] mapElements = rows[i].toCharArray();
            for (int j = 0; j < mapCols; j++) {
                char currentMapElement = mapElements[j];
                myMap[i][j] = currentMapElement;
            }
        }
    }

    private void robotGo() {
        myLocation = find('X');
        this.goalLocation = find('$');
        while (!Arrays.equals(myLocation, this.goalLocation)) {
            walk();
        }
    }

    private void walk() {
        if (!reverse) {
            if (canGoDown()) {
                goDown();
            } else if (canGoRight()) {
                goRight();
            } else if (canGoUp()) {
                goUp();
            } else if (canGoLeft()) {
                goLeft();
            }
        } else {
            if (canGoUp()) {
                goUp();
            } else if (canGoLeft()) {
                goLeft();
            } else if (canGoDown()) {
                goDown();
            } else if (canGoRight()) {
                goRight();
            }
        }
    }

    //Assegurem que no hi hagi una paret a la casella d'adalt
    private boolean canGoUp() {
        int nexty = myLocation[0] - 1;
        return myMap[nexty][myLocation[1]] != '#';
    }

    //Up = North
    private void goUp() {
        boolean stillUp = true;
        while (stillUp) {
            route += "N";
            //my row goes up
            myLocation[0]--;
            checkMyLocation();
            if (Arrays.equals(myLocation, this.goalLocation))
                break;
            stillUp = canGoUp();
        }
    }

    //Assegurem que no hi hagi una paret a la casella de la dreta
    private boolean canGoRight() {
        int nextx = myLocation[1] + 1;
        return myMap[myLocation[0]][nextx] != '#';
    }

    //Right = East
    private void goRight() {
        boolean stillRight = true;
        while (stillRight) {
            route += "E";
            //my col goes right
            myLocation[1]++;
            checkMyLocation();
            if (Arrays.equals(myLocation, this.goalLocation))
                break;
            stillRight = canGoRight();
        }
    }

    //Assegurar que no hi hagi una paret a la casella de la d'avall
    private boolean canGoDown() {
        int nexty = myLocation[0] + 1;
        return myMap[nexty][myLocation[1]] != '#';
    }

    //Down = South
    private void goDown() {
        boolean stillDown = true;
        while (stillDown) {
            route += "S";
            //my row goes down
            myLocation[0]++;
            checkMyLocation();
            if (Arrays.equals(myLocation, this.goalLocation))
                break;
            stillDown = canGoDown();
        }
    }

    //Assegurem que no hi hagi una paret a la casella de la de l'esquerra
    private boolean canGoLeft() {
        int nextx = myLocation[1] - 1;
        return myMap[myLocation[0]][nextx] != '#';
    }

    //Left = West
    private void goLeft() {
        boolean stillLeft = true;
        while (stillLeft) {
            route += "W";
            //my col goes left
            myLocation[1]--;
            checkMyLocation();
            if (Arrays.equals(myLocation, this.goalLocation))
                break;
            stillLeft = canGoLeft();
        }
    }

    private void checkMyLocation() {
        if (myMap[myLocation[0]][myLocation[1]] == 'I') {
            this.reverse = !this.reverse;
        }
        if (myMap[myLocation[0]][myLocation[1]] == 'T') {
            teleport();
        }
    }

    //Teleport ha de fer un bucle cercant el T més proper en una espiral en sentit horari. sempre començar adalt.
    private void teleport() {
        myLocation = Teleporter.nextTeleporter;
    }

    private int[] find(char objective) {
        for (int i = 0; i < mapRows; i++) {
            for (int j = 0; j < mapCols; j++) {
                if (myMap[i][j] == objective) {
                    return new int[]{i, j};
                }
            }
        }
        throw new RuntimeException("Can't find element: " + objective);
    }
}