class Bender {
    String mapa;
    String route;
    int mapRows;
    int mapCols;
    int[] myLocation = find('X');
    int[] goalLocation = find('$');
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
        String result = "";
        boolean reverse = false;
        while (this.myLocation != this.goalLocation) {
            int myX = this.myLocation[0];
            int myY = this.myLocation[1];

            //Teleport
            if (this.myMap[myX][myY] == 'T') {
                //teleport();
            }

            //Reverse
            if (this.myMap[myX][myY] == 'I') {
                reverse = !reverse;
            }

            walk(reverse);
        }
        return result;
    }

    private void walk(boolean reverse) {
        if (!reverse){
            if (canGoDown()){
                goDown();
            }
            else if (canGoLeft()){
                goLeft();
            }
            else if (canGoUp()){
                goUp();
            }
            else if (canGoRight()){
                goRight();
            }
        }
        else {
            if (canGoUp()){
                goUp();
            }
            else if (canGoRight()){
                goRight();
            }
            else if (canGoDown()){
                goDown();
            }
            else if (canGoLeft()){
                goLeft();
            }
        }
    }

    private boolean canGoUp() {
        return false;
    }

    private void goUp() {
        boolean stillUp = true;
        while (stillUp){
            route += "W";
            stillUp = canGoUp();
            if (this.myLocation == this.goalLocation)
                break;
        }
    }

    private boolean canGoRight() {
        return false;
    }

    private void goRight() {
        boolean stillRight = true;
        while (stillRight){
            route += "W";
            stillRight = canGoUp();
            if (this.myLocation == this.goalLocation)
                break;
        }
    }

    private boolean canGoDown() {
        return false;
    }

    private void goDown() {
        boolean stillDown = true;
        while (stillDown){
            route += "W";
            stillDown = canGoUp();
            if (this.myLocation == this.goalLocation)
                break;
        }
    }

    private boolean canGoLeft() {
        return false;
    }

    private void goLeft() {
        boolean stillLeft = true;
        while (stillLeft){
            route += "W";
            stillLeft = canGoUp();
            if (this.myLocation == this.goalLocation)
                break;
        }
    }



    private int[] find(char objective) {
        for (int i = 0; i < this.mapRows; i++) {
            for (int j = 0; j < this.mapCols; j++) {
                if (myMap[i][j] == objective) {
                    return new int[]{i, j};
                }
            }
        }
        throw new RuntimeException("Can't find element: " + objective);
    }
}