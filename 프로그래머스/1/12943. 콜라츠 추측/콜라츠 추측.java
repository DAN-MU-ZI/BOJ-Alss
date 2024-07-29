class Solution {
    public int solution(int input) {
        long num = input;
        if(num==1)
            return 0;
        
        for(int step=0;step<500;step++){
            if(num%2==0){
                num/=2;
            }else{
                num=num*3+1;
            }
            if(num==1)
                return step+1;
        }
        return -1;
    }
}