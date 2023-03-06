public class Teleporter {
    //Localització del teleporter
    static int[] teleporterLocation = Bender.myLocation;

    //El teleportador més proxim
    static int[] nextTeleporter = findTeleporter();

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
        int newYDistance = newTeleporter[0] - teleporterLocation[0];
        int newXDistance = newTeleporter[1] - teleporterLocation[1];
        int newDistance = newYDistance + newXDistance;
        int nearestYDistance = nearestTeleporter[0] - teleporterLocation[0];
        int nearestXDistance = nearestTeleporter[1] - teleporterLocation[1];
        int nearestDistance = nearestYDistance + nearestXDistance;
        if (newDistance < nearestDistance)
            return true;
        return false;
    }

}
