class Solution {

    class Node {
        int leftChar;
        int rightChar;
        int length;
        int prefix;
        int suffix;
        int max;

        Node(char ch) {
            leftChar = ch;
            rightChar = ch;
            length = 1;
            prefix = 1;
            suffix = 1;
            max = 1;
        }

        Node() {
        }
    }

    Node[] tree;
    char[] str;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();

        str = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] answer = new int[queryCharacters.length()];

        for (int i = 0; i < queryCharacters.length(); i++) {

            int index = queryIndices[i];
            char newChar = queryCharacters.charAt(i);

            update(1, 0, n - 1, index, newChar);

            answer[i] = tree[1].max;
        }

        return answer;
    }

    void build(int node, int left, int right) {

        if (left == right) {
            tree[node] = new Node(str[left]);
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int left, int right, int index, char ch) {

        if (left == right) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, right, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node a, Node b) {

        Node result = new Node();

        result.length = a.length + b.length;

        result.leftChar = a.leftChar;
        result.rightChar = b.rightChar;

        result.prefix = a.prefix;
        result.suffix = b.suffix;

        result.max = Math.max(a.max, b.max);

        // The two parts can join
        if (a.rightChar == b.leftChar) {

            result.max = Math.max(
                result.max,
                a.suffix + b.prefix
            );

            // Entire left part has same character
            if (a.prefix == a.length) {
                result.prefix = a.length + b.prefix;
            }

            // Entire right part has same character
            if (b.suffix == b.length) {
                result.suffix = b.length + a.suffix;
            }
        }

        return result;
    }
}