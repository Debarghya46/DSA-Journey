import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0;
        int startC = 0;
        int litterCount = 0;

        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        // Find S and assign IDs to L
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount;
                    litterCount++;
                }
            }
        }

        int allCollected = (1 << litterCount) - 1;

        int[][][] bestEnergy =
                new int[m][n][1 << litterCount];

        for (int[][] row : bestEnergy) {
            for (int[] cell : row) {
                Arrays.fill(cell, -1);
            }
        }

        Queue<State> queue = new LinkedList<>();

        queue.offer(new State(
                startR,
                startC,
                energy,
                0,
                0
        ));

        bestEnergy[startR][startC][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            State current = queue.poll();

            int r = current.r;
            int c = current.c;
            int currentEnergy = current.energy;
            int mask = current.mask;
            int moves = current.moves;

            // All litter collected
            if (mask == allCollected) {
                return moves;
            }

            // Try 4 directions
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                // No energy
                if (currentEnergy == 0) {
                    continue;
                }

                int newEnergy = currentEnergy - 1;
                int newMask = mask;

                char nextCell = classroom[nr].charAt(nc);

                // Collect litter
                if (nextCell == 'L') {
                    int id = litterId[nr][nc];
                    newMask = newMask | (1 << id);
                }

                // Reset energy
                if (nextCell == 'R') {
                    newEnergy = energy;
                }

                // Already reached with better energy
                if (bestEnergy[nr][nc][newMask] >= newEnergy) {
                    continue;
                }

                bestEnergy[nr][nc][newMask] = newEnergy;

                queue.offer(new State(
                        nr,
                        nc,
                        newEnergy,
                        newMask,
                        moves + 1
                ));
            }
        }

        return -1;
    }

    static class State {

        int r;
        int c;
        int energy;
        int mask;
        int moves;

        State(int r, int c, int energy, int mask, int moves) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
            this.moves = moves;
        }
    }
}