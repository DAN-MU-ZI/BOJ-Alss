class Solution {
    boolean solution(String s) {
        boolean answer = true;

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        int count = 0;
        
        for(char c: s.toCharArray()){
            if(c=='p'||c=='P'){
                count++;
            }
            if(c=='y'||c=='Y'){
                count--;
            }
        }
        
        answer = count==0;
        return answer;
    }
}