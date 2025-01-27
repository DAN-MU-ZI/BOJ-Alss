class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;
        
        int[] idx = new int[n + 1];
        for (int i = 0; i < n; i++) {
            idx[n + 1 - cards[i]] = i;
        }
        
        boolean[] used = new boolean[n];
        
        int hand = n / 3;
        int draw = 0;
        int turn = 1;
        while (turn * 2 <= n - hand) {
            // System.out.printf("turn : %d, coin : %d, draw : %d \n", turn, coin, draw);
            draw += 2;
            
            boolean useHand = false;
            submit:
            for (int i = 0; i < hand; i++) {
                if(used[i]) continue;
                if (idx[cards[i]] < hand) {
                    used[i] = true;
                    used[idx[cards[i]]] = true;
                    useHand = true;
                    break submit;
                }
            }
            if (useHand) {
                turn++;
                continue;
            }
            
            boolean useHandWithDraw = false;
            if (coin > 0) {
                submit:
                for(int i = hand; i < hand + draw; i++) {
                    if (used[i]) continue;
                    if (idx[cards[i]] < hand) {
                        used[i] = true;
                        used[idx[cards[i]]] = true;
                        useHandWithDraw = true;
                        break submit;
                    }
                }
            } else {
                break;
            }
            if (useHandWithDraw) {
                coin--;
                turn++;
                continue;
            }
            
            boolean useDraw = false;
            if (coin > 1) {
                submit:
                for(int i = hand; i < hand + draw; i++) {
                    if (used[i]) continue;
                    if (idx[cards[i]] < hand + draw) {
                        used[i] = true;
                        used[idx[cards[i]]] = true;
                        useDraw = true;
                        break submit;
                    }
                }
            } else {
                break;
            }
            if (useDraw) {
                coin-=2;
                turn++;
                continue;
            }
            
            if ((useHand || useHandWithDraw || useDraw) == false) {
                break;
            }
        }
        
        return turn;
    }
}