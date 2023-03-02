import java.util.Arrays;

class Bender {
    String mapa;
    String route = "";
    int mapRows;
    int mapCols;
    int[] myLocation; // = find('X')
    int[] goalLocation; // = find('$')
    char[][] myMap;

    // Constructor: ens passen el mapa en forma d'String
    public Bender(String mapa) {
        this.mapa = mapa;
    }
    // Navegar fins a l'objectiu («$»).
    // El valor retornat pel mètode consisteix en una cadena dels valors «S», «N», «W» o «E».

    public String run() {
        makeMap();
        return robotGo();
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

    private String robotGo() {
        boolean reverse = false;
        this.myLocation = find('X');
        this.goalLocation = find('$');
        while (!Arrays.equals(this.myLocation, this.goalLocation)) {
            /*int myX = this.myLocation[0];
            int myY = this.myLocation[1];

            //Teleport
            if (this.myMap[myX][myY] == 'T') {
                //teleport();
            }

            //Reverse
            if (this.myMap[myX][myY] == 'I') {
                reverse = !reverse;
            }*/

            walk(reverse);
        }
        return this.route;
    }

    private void walk(boolean reverse) {
        if (!reverse) {
            if (canGoDown()) {
                goDown();
            } else if (canGoLeft()) {
                goLeft();
            } else if (canGoUp()) {
                goUp();
            } else if (canGoRight()) {
                goRight();
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

    //Asegurar que no estem al maxim, o que hi hagi una paret
    private boolean canGoUp() {
        int nexty = this.myLocation[0] - 1;
        return this.myLocation[0] != mapRows && myMap[nexty][this.myLocation[1]] != '#';
    }

    private void goUp() {
        boolean stillUp = true;
        while (stillUp) {
            route += "W";
            //my row goes up
            this.myLocation[0]--;
            stillUp = canGoUp();
            if (this.myLocation == this.goalLocation)
                break;
        }
    }

    private boolean canGoRight() {
        int nextx = this.myLocation[1] - 1;
        return this.myLocation[1] != mapCols && myMap[this.myLocation[0]][nextx] != '#';
    }

    private void goRight() {
        boolean stillRight = true;
        while (stillRight) {
            route += "E";
            //my col goes right
            this.myLocation[1]--;
            stillRight = canGoRight();
            if (this.myLocation == this.goalLocation)
                break;
        }
    }

    private boolean canGoDown() {
        int nexty = this.myLocation[0] + 1;
        return this.myLocation[0] > 0 && myMap[nexty][this.myLocation[1]] != '#';
    }

    private void goDown() {
        boolean stillDown = true;
        while (stillDown) {
            route += "S";
            //my row goes down
            this.myLocation[0]++;
            stillDown = canGoDown();
            if (Arrays.equals(this.myLocation, this.goalLocation))
                break;
        }
    }

    private boolean canGoLeft() {
        int nextx = this.myLocation[1] + 1;
        return this.myLocation[1] > 0 && myMap[this.myLocation[0]][nextx] != '#';
    }

    private void goLeft() {
        boolean stillLeft = true;
        while (stillLeft) {
            route += "E";
            stillLeft = canGoLeft();
            //my col goes left
            this.myLocation[1]++;
            if (this.myLocation == this.goalLocation)
                break;
        }
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