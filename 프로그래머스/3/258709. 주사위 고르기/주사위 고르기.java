import java.util.*;

class Solution {
    public int[] solution(int[][] dice) {
        int n = dice.length;
        int r = n / 2;
        int[] best = new int[r];
        int maxWin = -1;

        List<int[]> allAComb = generateCombinations(n, r);

        for (int[] aIndices : allAComb) {
            int[] bIndices = getRestIndices(n, aIndices);

            Map<Integer, Integer> aSumMap = enumerateSumCounts(dice, aIndices);
            Map<Integer, Integer> bSumMap = enumerateSumCounts(dice, bIndices);

            int winCount = countAWinsByMap(aSumMap, bSumMap);

            if (winCount > maxWin) {
                maxWin = winCount;
                for (int i = 0; i < r; i++) best[i] = aIndices[i] + 1;
            }
        }

        Arrays.sort(best);
        return best;
    }

    
    List<int[]> generateCombinations(int n, int r) {
        List<int[]> result = new ArrayList<>();
        generateCombinationsHelper(0, n, r, new int[r], 0, result);
        return result;
    }
    
    private void generateCombinationsHelper(int start, int n, int r, int[] temp, int depth, List<int[]> result) {
        if (depth == r) {
            result.add(temp.clone());
            return;
        }
        
        for (int i = start; i < n; i++) {
            temp[depth] = i;
            generateCombinationsHelper(i + 1, n, r, temp, depth + 1, result);
        }
    }
    
    int[] getRestIndices(int n, int[] selected) {
        boolean[] isSelected = new boolean[n];
        for (int idx: selected) isSelected[idx] = true;
        int[] rest = new int[n / 2];
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (!isSelected[i]) rest[p++] = i;
        }
        return rest;
    }
    
    // List<Integer> -> Map<Integer, Integer>로 변경
    private Map<Integer, Integer> enumerateSumCounts(int[][] dice, int[] indices) {
        Map<Integer, Integer> result = new HashMap<>();
        enumerateSumsCountDFS(dice, indices, 0, 0, result);
        return result;
    }

    private void enumerateSumsCountDFS(int[][] dice, int[] indices, int depth, int sum, Map<Integer, Integer> result) {
        if (depth == indices.length) {
            result.put(sum, result.getOrDefault(sum, 0) + 1);
            return;
        }
        for (int face : dice[indices[depth]]) {
            enumerateSumsCountDFS(dice, indices, depth + 1, sum + face, result);
        }
    }

    
    int countAWinsByMap(Map<Integer, Integer> aSumMap, Map<Integer, Integer> bSumMap) {
        // B의 합 오름차순 및 누적합
        List<Integer> bKeys = new ArrayList<>(bSumMap.keySet());
        Collections.sort(bKeys);

        List<Integer> bAcc = new ArrayList<>();
        int acc = 0;
        for (int key : bKeys) {
            acc += bSumMap.get(key);
            bAcc.add(acc);
        }
        // key → 누적합 맵핑
        Map<Integer, Integer> bSum2Acc = new HashMap<>();
        for (int i = 0; i < bKeys.size(); i++) {
            bSum2Acc.put(bKeys.get(i), bAcc.get(i));
        }

        int win = 0;
        for (int aSum : aSumMap.keySet()) {
            int aCount = aSumMap.get(aSum);

            // B에서 bSum < aSum인 모든 경우의 수
            int idx = Collections.binarySearch(bKeys, aSum);
            if (idx < 0) idx = -idx - 1;
            int bWin = (idx > 0) ? bAcc.get(idx - 1) : 0;

            win += aCount * bWin;
        }
        return win;
    }

}