class Solution {
    public int solution(int[] numbers) {
        int answer = -1;
        
        boolean[] table = new boolean[10];
        for(int i: numbers){
            table[i]=true;
        }
        
        int sum = 0;
        for(int i=0;i<table.length;i++){
            if(!table[i])
                sum+=i;
        }
        
        return sum;
    }
}