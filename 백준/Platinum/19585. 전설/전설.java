import java.io.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;

public class Main {

    static class Node {
        Node[] children;
        boolean isWord;

        public Node() {
            children = new Node[26];
        }
    }

    static Node colorDict;
    static HashSet<String> nameDict;
    static int C, N, Q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        colorDict = new Node();
        // nameDict = new Node();
        nameDict = new HashSet();
        for (int i = 0; i < C; i++) {
            insert(colorDict, br.readLine().trim());
        }
        for (int i = 0; i < N; i++) {
            nameDict.add(br.readLine().trim());
            // insert(nameDict, br.readLine().trim());
        }

        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            boolean isValid = find(br.readLine().trim());
            if (isValid) {
                sb.append("Yes\n");
            } else {
                sb.append("No\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void insert(Node cur, String str) {
        for (char c : str.toCharArray()) {
            int nc = c - 'a';
            if (cur.children[nc] == null) {
                cur.children[nc] = new Node();
            }
            cur = cur.children[nc];
        }
        cur.isWord = true;
    }

    static boolean find(String str) {
        int i = 0;
        Node cur = colorDict;
        while (i < str.length()) {
            int idx = str.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                return false;
            }
            cur = cur.children[idx];
            i++;
            if (cur.isWord) {
                if (nameDict.contains(str.substring(i, str.length()))) {
                    return true;
                }
            }
        }
        return false;
    }
}
