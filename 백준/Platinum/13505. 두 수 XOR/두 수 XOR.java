import java.io.*;
import java.util.*;

public class Main {

	static class TrieNode {
		TrieNode[] child = new TrieNode[2];
	}

	static class Trie {
		TrieNode root = new TrieNode();

		public void add(int x) {
			TrieNode node = root;
			for (int i = 31; i >= 0; i--) {
				int b = (x >> i) & 1;
				if (node.child[b] == null) {
					node.child[b] = new TrieNode();
				}
				node = node.child[b];
			}
		}

		public int findPair(int x) {
			TrieNode node = root;
			int result = 0;

			for (int i = 31; i >= 0; i--) {
				int b = (x >> i) & 1;
				int want = 1 - b;
				if (node.child[want]!=null) {
					result |= (1 << i);
					node = node.child[want];
				}else {
					node = node.child[b];
				}
			}

			return result;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Trie trie = new Trie();

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			trie.add(nums[i]);
		}

		int maxXor = 0;
		for (int num : nums) {
			maxXor = Math.max(maxXor, trie.findPair(num));
		}

		System.out.println(maxXor);
	}
}
