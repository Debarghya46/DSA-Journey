import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Store reserved seats row-wise
        Map<Integer, Set<Integer>> reserved = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            reserved.putIfAbsent(row, new HashSet<>());
            reserved.get(row).add(col);
        }

        // Every completely empty row can accommodate 2 families
        int answer = (n - reserved.size()) * 2;

        // Process only rows having reservations
        for (Map.Entry<Integer, Set<Integer>> entry : reserved.entrySet()) {

            Set<Integer> seats = entry.getValue();

            boolean left = true;   // 2,3,4,5
            boolean middle = true; // 4,5,6,7
            boolean right = true;  // 6,7,8,9

            // Check left block
            for (int seat = 2; seat <= 5; seat++) {
                if (seats.contains(seat)) {
                    left = false;
                    break;
                }
            }

            // Check middle block
            for (int seat = 4; seat <= 7; seat++) {
                if (seats.contains(seat)) {
                    middle = false;
                    break;
                }
            }

            // Check right block
            for (int seat = 6; seat <= 9; seat++) {
                if (seats.contains(seat)) {
                    right = false;
                    break;
                }
            }

            // Both independent sides are available
            if (left && right) {
                answer += 2;
            }
            // Either left or right is available
            else if (left || right || middle) {
                answer += 1;
            }
        }

        return answer;
    }
}