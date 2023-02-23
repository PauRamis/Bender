class Bender {
    String mapa;
    char[][] myMap;

    // Constructor: ens passen el mapa en forma d'String
    public Bender(String mapa) {
        this.mapa = mapa;
    }
    // Navegar fins a l'objectiu («$»).
    // El valor retornat pel mètode consisteix en una cadena dels valors «S», «N», «W» o «E».

    public String run() {
        makeMap();
        findX();
        return findPath();
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
        int mapRows = rows.length;
        int mapCols = rows[0].length();
        this.myMap = new char[mapRows][mapCols];
    }

    private String findPath() {
        String result = "";
        Boolean onX = false;
        while (!onX){
            //robot();
        }

        return result;
    }
}