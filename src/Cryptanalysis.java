import java.util.*;

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
    public Map<Character,Double> cryptanalusisFile(List<String> firstFile, char[] ar){
        Map<Character,Double> map = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        for(String str : firstFile){
            sb.append(str);
        }
        for(int i=0;i<sb.length();i++){
            boolean inAlphabet = false;
            for(int a = 0; a<ar.length;a++){
                if(ar[a] == sb.charAt(i)){
                    inAlphabet = true;
                    break;
                }

            }
            if(!map.containsKey(sb.charAt(i))){
                double count = 1;
                for(int j=i+1;j<sb.length();j++){
                    if(sb.charAt(i) == sb.charAt(j)) count++;
                }
                if(inAlphabet){
                    map.put(sb.charAt(i),count/sb.length());
                }

            }
        }
        for(Map.Entry<Character, Double> i :map.entrySet()){
            System.out.println( i.getKey() + " = " + i.getValue());
        }
        System.out.println("__________________________________________________________________");
        return  map;
    }

    public String cryptanalusisMap(Map<Character,Double> first,Map<Character,Double> second, List<String> list){
        StringBuilder sb = new StringBuilder();
        for(String str :list){
            sb.append(str);
        }
        //String result = sb.toString();

        List<Map.Entry<Character,Double>> listMap = new ArrayList(first.entrySet());
        Collections.sort(listMap, new Comparator<Map.Entry<Character, Double>>() {
            @Override
            public int compare(Map.Entry<Character, Double> a, Map.Entry<Character, Double> b) {
                if(a.getValue() - b.getValue()>0) return 1;
                else if(a.getValue() == b.getValue()) return 0;
                return -1;
            }
        });
        Map<Character,Double> resultFirst = new LinkedHashMap<>();
        for(Map.Entry<Character,Double> entry:listMap){
            resultFirst.put(entry.getKey(),entry.getValue());
        }

        List<Map.Entry<Character,Double>> listMap2 = new ArrayList(second.entrySet());
        Collections.sort(listMap2, new Comparator<Map.Entry<Character, Double>>() {
            @Override
            public int compare(Map.Entry<Character, Double> a, Map.Entry<Character, Double> b) {
                if(a.getValue() - b.getValue()>0) return 1;
                else if(a.getValue() == b.getValue()) return 0;
                return -1;
            }
        });

        Map<Character,Double> resultSecond = new LinkedHashMap<>();
        for(Map.Entry<Character,Double> entry:listMap2){
            resultSecond.put(entry.getKey(),entry.getValue());
        }
//        for(Map.Entry<Character,Double> f:resultFirst.entrySet()){
//            System.out.println(f.getKey() + "    " + f.getValue());
//        }
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
//        for(Map.Entry<Character,Double> f:resultSecond.entrySet()){
//            System.out.println(f.getKey() + "    " + f.getValue());
//        }
        Iterator<Map.Entry<Character,Double>> iteratorF = resultFirst.entrySet().iterator();
        Iterator<Map.Entry<Character,Double>> iteratorS = resultSecond.entrySet().iterator();
        Map<Character,Character> mapCharacter = new HashMap<>();
        while(iteratorF.hasNext() && iteratorS.hasNext()){
            Map.Entry<Character,Double> f = iteratorF.next();
            Map.Entry<Character,Double> s = iteratorS.next();
            System.out.println(s.getKey()+":"+s.getValue()+"-->"+f.getKey()+":"+f.getValue());
            mapCharacter.put(s.getKey(),f.getKey());
//            result=result.replace(f.getKey(),'*');
//            result=result.replace(s.getKey(), f.getKey());
//            result=result.replace('*',f.getKey());
        }
        StringBuilder sbFinish = new StringBuilder();
        for(int i=0;i<sb.length();i++){
            if(mapCharacter.containsKey(sb.charAt(i))){
                //System.out.println(sb.charAt(i)+"-->"+mapCharacter.get(sb.charAt(i)));
                sbFinish.append(mapCharacter.get(sb.charAt(i)));
            }else{
                sbFinish.append(sb.charAt(i));
            }

        }
//
//        for(Map.Entry<Character,Double> f:first.entrySet()){
//            for(Map.Entry<Character,Double> s: second.entrySet()){
//
//                if(Math.abs(f.getValue()-s.getValue())<0.000001){
//                    System.out.println(s.getKey()+":"+s.getValue()+"-->"+f.getKey()+":"+f.getValue());
//                    result=result.replace(s.getKey(), f.getKey());
//                    break;
//                }
//            }
//        }
        return sbFinish.toString();
    }
}
