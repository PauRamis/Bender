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
        this.mapRows = rows.length;
        this.mapCols = rows[0].length();
        this.myMap = new char[this.mapRows][this.mapCols];

        for (int i = 0; i < this.mapRows; i++) {
            char[] mapElements = rows[i].toCharArray();
            for (int j = 0; j < this.mapCols; j++) {
                char currentMapElement = mapElements[j];
                this.myMap[i][j] = currentMapElement;
            }
        }
    }

    private void robotGo() {
        this.myLocation = find('X');
        this.goalLocation = find('$');
        while (!Arrays.equals(this.myLocation, this.goalLocation)) {
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
            } else if (canGoRight()) {
                goRight();
            } else if (canGoDown()) {
                goDown();
            } else if (canGoLeft()) {
                goLeft();
            }
        }
    }

    //Assegurem que no hi hagi una paret a la casella d'adalt
    private boolean canGoUp() {
        int nexty = this.myLocation[0] - 1;
        return myMap[nexty][this.myLocation[1]] != '#';
    }

    //Up = North
    private void goUp() {
        boolean stillUp = true;
        while (stillUp) {
            route += "N";
            //my row goes up
            this.myLocation[0]--;
            stillUp = canGoUp();
            checkMyLocation();
            if (Arrays.equals(this.myLocation, this.goalLocation))
                break;
        }
    }

    //Assegurem que no hi hagi una paret a la casella de la dreta
    private boolean canGoRight() {
        int nextx = this.myLocation[1] + 1;
        return myMap[this.myLocation[0]][nextx] != '#';
    }

    //Right = East
    private void goRight() {
        boolean stillRight = true;
        while (stillRight) {
            route += "E";
            //my col goes right
            this.myLocation[1]++;
            stillRight = canGoRight();
            checkMyLocation();
            if (Arrays.equals(this.myLocation, this.goalLocation))
                break;
        }
    }

    //Assegurar que no hi hagi una paret a la casella de la d'avall
    private boolean canGoDown() {
        int nexty = this.myLocation[0] + 1;
        return myMap[nexty][this.myLocation[1]] != '#';
    }

    //Down = South
    private void goDown() {
        boolean stillDown = true;
        while (stillDown) {
            route += "S";
            //my row goes down
            this.myLocation[0]++;
            stillDown = canGoDown();
            checkMyLocation();
            if (Arrays.equals(this.myLocation, this.goalLocation))
                break;
        }
    }

    //Assegurem que no hi hagi una paret a la casella de la de l'esquerra
    private boolean canGoLeft() {
        int nextx = this.myLocation[1] - 1;
        return myMap[this.myLocation[0]][nextx] != '#';
    }

    //Left = West
    private void goLeft() {
        boolean stillLeft = true;
        while (stillLeft) {
            route += "W";
            stillLeft = canGoLeft();
            //my col goes left
            this.myLocation[1]--;
            checkMyLocation();
            if (Arrays.equals(this.myLocation, this.goalLocation))
                break;
        }
    }

    private void checkMyLocation() {
        if (this.myMap[myLocation[0]][myLocation[1]] == 'I') {
            this.reverse = !this.reverse;
        }
        if (this.myMap[myLocation[0]][myLocation[1]] == 'T') {
            teleport();
        }
    }

    //Teleport ha de fer un bucle cercant el T més proper en una espiral en sentit horari. sempre començar adalt.
    private void teleport() {
    }

    private int[] find(char objective) {
        for (int i = 0; i < this.mapRows; i++) {
            for (int j = 0; j < this.mapCols; j++) {
                if (this.myMap[i][j] == objective) {
                    return new int[]{i, j};
                }
            }
        }
        throw new RuntimeException("Can't find element: " + objective);
    }
}