import java.util.*;

public class Cryptanalysis {
    public int bruteForce(List<String> list, char[] alphabet, CodeCezar codeCezar){
        int countDecription = 0;
        int oldDecr = 0;
        int bruteForceKey = 0;
        boolean isSuccess =false;
        for(int k=0;k<alphabet.length;k++){
            List<String> listDescyption = codeCezar.decryption(list,k,alphabet);
            for(String str : listDescyption){
                int space = str.split(",\\s").length;
                countDecription+=space;
            }
            if(countDecription>oldDecr){
                bruteForceKey = k;
                oldDecr = countDecription;
            }

            countDecription =0;
        }

        System.out.println("key bruteforce: " + bruteForceKey);
        return bruteForceKey;
    }
    public Map<Character,Double> cryptanalusisFile(List<String> firstFile, char[] ar){
        Map<Character,Double> map = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        for(String str : firstFile){
            sb.append(str);
        }
        for(int i=0;i<ar.length;i++){
//            boolean inAlphabet = false;
//            for(int a = 0; a<ar.length;a++){
//                if(ar[a] == sb.charAt(i)){
//                    inAlphabet = true;
//                    break;
//                }
//            }
            double count = 0;
            for(int j=0;j<sb.length();j++){
                if(ar[i] == sb.charAt(j)) count++;
            }
            map.put(ar[i],count/sb.length());
        }
        for(Map.Entry<Character, Double> i :map.entrySet()){
            System.out.println( i.getKey() + " = " + i.getValue());
        }
        System.out.println("__________________________________________________________________");
        return  map;
    }

    public String cryptanalusisMap(Map<Character,Double> mapCryptanalysis,Map<Character,Double> mapDecryption, List<String> list){
        StringBuilder sb = new StringBuilder();
        for(String str :list){
            sb.append(str);
        }
        //String result = sb.toString();

        List<Map.Entry<Character,Double>> listMap = new ArrayList(mapCryptanalysis.entrySet());
        Collections.sort(listMap, new Comparator<Map.Entry<Character, Double>>() {
            @Override
            public int compare(Map.Entry<Character, Double> a, Map.Entry<Character, Double> b) {
                if(a.getValue() - b.getValue()>0) return 1;
                else if(a.getValue() == b.getValue()) return 0;
                return -1;
            }
        });
        Map<Character,Double> mapSortedCryptanalysy = new LinkedHashMap<>();
        for(Map.Entry<Character,Double> entry:listMap){
            mapSortedCryptanalysy.put(entry.getKey(),entry.getValue());
        }

        List<Map.Entry<Character,Double>> listMap2 = new ArrayList(mapDecryption.entrySet());
        Collections.sort(listMap2, new Comparator<Map.Entry<Character, Double>>() {
            @Override
            public int compare(Map.Entry<Character, Double> a, Map.Entry<Character, Double> b) {
                if(a.getValue() - b.getValue()>0) return 1;
                else if(a.getValue() == b.getValue()) return 0;
                return -1;
            }
        });

        Map<Character,Double> mapSortedDecryption = new LinkedHashMap<>();
        for(Map.Entry<Character,Double> entry:listMap2){
            mapSortedDecryption.put(entry.getKey(),entry.getValue());
        }
//        for(Map.Entry<Character,Double> f:mapSortedCryptanalysy.entrySet()){
//            System.out.println(f.getKey() + "    " + f.getValue());
//        }
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
//        for(Map.Entry<Character,Double> f:mapSortedDecryption.entrySet()){
//            System.out.println(f.getKey() + "    " + f.getValue());
//        }
        Iterator<Map.Entry<Character,Double>> iteratorCrypta = mapSortedCryptanalysy.entrySet().iterator();
        Iterator<Map.Entry<Character,Double>> iteratorDecryp = mapSortedDecryption.entrySet().iterator();
        Map<Character,Character> mapCharacter = new HashMap<>();
        while(iteratorCrypta.hasNext() && iteratorDecryp.hasNext()){
            Map.Entry<Character,Double> f = iteratorCrypta.next();
            Map.Entry<Character,Double> s = iteratorDecryp.next();
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
