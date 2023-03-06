public class Teleporter {
    //Localització del teleporter
    static int[] teleporterLocation = Bender.myLocation;

    //El teleportador més proxim
    static int[] nextTeleporter = findTeleporter();

    /*private int[] findTeleporter() {
        int[][] teleporters = new int[][];

        int[] nearestTeleporter;
        return nearestTeleporter;
    }*/

    static int[] findTeleporter() {
        int[] nearestTeleporter = {0, 0};
        for (int i = 0; i < Bender.mapRows; i++) {
            for (int j = 0; j < Bender.mapCols; j++) {
                if (Bender.myMap[i][j] == 'T') {
                    int[] newTeleporter = new int[]{i, j};
                    if (isShorter(newTeleporter, nearestTeleporter)){
                        nearestTeleporter = newTeleporter;
                    }
                }
            }
        }
        return nearestTeleporter;
    }

    private static boolean isShorter(int[] newTeleporter, int[] nearestTeleporter) {

    }

}
