import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cryptanalysis {
    public void bruteForce(List<String> list, char[] alphabet){
        for(int k=0;k<alphabet.length;k++){
            List<String> result = new ArrayList<>();
            char[] ar = null;
            for(String str : list){
                if(!str.trim().equals("")){
                    ar = str.toCharArray();
                    break;
                }
            }
            for(int i = 0; i< ar.length; i++) {
                for (int j = 0; j < alphabet.length; j++) {
                    if (ar[i] == alphabet[j]) {
                        if (j - k < 0) {
                            ar[i] = alphabet[alphabet.length - (k - j)];
                            break;
                        } else {
                            ar[i] = alphabet[j - k];
                            break;
                        }
                    }
                }
            }
            System.out.println("Key value:" + k + " Example of decryption :" + String.valueOf(ar));
        }
    }
    public Map<Character,Double> cryptanalusisFile(List<String> firstFile){
        Map<Character,Double> map = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        for(String str : firstFile){
            sb.append(str);
        }
        for(int i=0;i<sb.length();i++){
            if(!map.containsKey(sb.charAt(i))){
                double count = 1;
                for(int j=i+1;j<sb.length();j++){
                    if(sb.charAt(i) == sb.charAt(j)) count++;
                }
                map.put(sb.charAt(i),count/sb.length());
            }
        }
//        for(Map.Entry<Character, Double> i :map.entrySet()){
//            System.out.println( i.getKey() + " = " + i.getValue());
//        }
        return  map;
    }

    public void cryptanalusisMap(Map<Character,Double> first,Map<Character,Double> second){

    }
}
