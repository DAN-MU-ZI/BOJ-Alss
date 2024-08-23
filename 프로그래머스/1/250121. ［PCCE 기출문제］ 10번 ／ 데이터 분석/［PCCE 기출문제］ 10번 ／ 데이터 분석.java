import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        // data에서 
        // ext 값이 
        // val_ext보다 작은 데이터만 
        // sort_by에 해당하는 값을 기준으로
        // 오름차순으로 정렬
        List<Datum> table = new ArrayList<Datum>();
        for(int i=0;i<data.length; i++){
            Datum d = new Datum();
            d.code = data[i][0];
            d.date = data[i][1];
            d.maximum = data[i][2];
            d.remain = data[i][3];
            table.add(d);
        }
        
        List<Datum> filteredList = new ArrayList<>();
        if (ext.equals("code")) {
            table.forEach((x)->{
                if(x.code < val_ext)
                    filteredList.add(x);
            });
        }
        if (ext.equals("date")) {
            table.forEach((x)->{
                if(x.date < val_ext)
                    filteredList.add(x);
            });
        }
        if (ext.equals("maximum")) {
            table.forEach((x)->{
                if(x.maximum < val_ext)
                    filteredList.add(x);
            });
        }
        if (ext.equals("remain")) {
            table.forEach((x)->{
                if(x.remain < val_ext)
                    filteredList.add(x);
            });
        }
        
        if (sort_by.equals("code")) {
            Collections.sort(filteredList, (o1, o2)->{
                return o1.code - o2.code;
            });
        }
        if (sort_by.equals("date")) {
            Collections.sort(filteredList, (o1, o2)->{
                return o1.date - o2.date;
            });
        }
        if (sort_by.equals("maximum")) {
            Collections.sort(filteredList, (o1, o2)->{
                return o1.maximum - o2.maximum;
            });
        }
        if (sort_by.equals("remain")) {
            Collections.sort(filteredList, (o1, o2)->{
                return o1.remain - o2.remain;
            });
        }
        
        int[][] answer = new int[filteredList.size()][4];
        for(int i=0;i<filteredList.size();i++) { 
            Datum datum = filteredList.get(i);
            int[] line = new int[4];
            line[0] = datum.code;
            line[1] = datum.date;
            line[2] = datum.maximum;
            line[3] = datum.remain;
            answer[i] = line;
        }
        return answer;
    }
}

class Datum {
    Integer code;
    Integer date;
    Integer maximum;
    Integer remain;
    
    Integer getCode(){
        return code;
    }
    Integer getDate(){
        return date;
    }
    Integer getMaximum(){
        return maximum;
    }
    Integer getRemain(){
        return remain;
    }
}