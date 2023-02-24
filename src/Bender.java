class Bender {
    String mapa;
    int mapRows;
    int mapCols;
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
/*
    private void findX() {
        for (int i = 0; i < ; i++) {
            for (int j = 0; j < ; j++) {
                if (myMap[i][j] == '$') return ;
            }
        }
    }
*/
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
        int[] goalLocation = find('$');
        int[] myLocation = find('X');
        boolean onX = false;
        while (!onX){

            if (myLocation == goalLocation)
                onX = true;
        }

        return result;
    }

    private int[] find(char objective) {
        int[] location = new int[]{0, 0};
        for (int i = 0; i < this.mapRows; i++) {
            for (int j = 0; j < this.mapCols; j++) {
                if (myMap[i][j] == objective) {
                    location = new int[]{i, j};
                    return location;
                }
            }
        }
        return location;
    }
}