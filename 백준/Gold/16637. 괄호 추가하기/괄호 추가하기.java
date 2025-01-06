import java.io.*;
import java.util.*;

public class Main {
	static int calc(int a, int b, char op) {
		switch (op) {
			case '*':return a * b;
			case '+':return a + b;
			case '-':return a - b;
			default: throw new IllegalArgumentException();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();

		int opLength = N/ 2;
		int[] nums = new int[opLength+1];
		char[] operators = new char[opLength];

		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				nums[i / 2] = input[i] - '0';
			} else {
				operators[i / 2] = input[i];
			}
		}

		int answer = Integer.MIN_VALUE;
		for(int mask=0;mask<(1<<opLength);mask++){
			if(!isValid(mask)) continue;

			int numIdx = 0;
			int opIdx = 0;
			Deque<Integer> stk = new LinkedList<>();
			Deque<Integer> opStk = new LinkedList<>();
			stk.add(nums[numIdx++]);
			while(opIdx<opLength){
				if((mask&(1<<opIdx))>0){
					int a = stk.removeLast();
					int b = nums[numIdx];
					stk.add(calc(a,b,operators[opIdx]));
					opIdx++;
					numIdx++;
				} else{
					opStk.add(opIdx++);
					stk.add(nums[numIdx++]);
				}
			}

			int a = stk.poll();
			while(!stk.isEmpty() || !opStk.isEmpty()){
				int b = stk.poll();
				opIdx = opStk.poll();
				a = calc(a, b, operators[opIdx]);
			}
			answer =Math.max(answer, a);
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	static boolean isValid(int mask){
		return (mask & (mask >> 1)) == 0;
	}
}

