class Solution {

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> result = new ArrayList<>();

        for (int row = 1; row <= numRows; row++) {

            List<Integer> currentRow = new ArrayList<>();

            long value = 1;
            currentRow.add(1);

            for (int col = 1; col < row; col++) {

                value = value * (row - col);
                value = value / col;

                currentRow.add((int) value);
            }

            result.add(currentRow);
        }

        return result;
    }
}