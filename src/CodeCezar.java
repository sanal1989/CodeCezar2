import java.util.ArrayList;
import java.util.List;

public class CodeCezar {

    public List<String> encryption(List<String> list, int key, char[] alphabet){
        List<String> result = new ArrayList<>();
        char[] ar = null;
        for (String str : list){
            ar = str.toCharArray();
            for(int i = 0; i< ar.length; i++) {
                for (int j = 0; j < alphabet.length; j++) {
                    if (ar[i] == alphabet[j]) {
                        if (j + key >= alphabet.length) {
                            ar[i] = alphabet[j + key - alphabet.length];
                            break;
                        } else{
                            ar[i] = alphabet[j + key];
                            break;
                        }
                    }
                }
            }
            result.add(String.valueOf(ar));
        }
        return  result;
    }

    public List<String> decryption(List<String> list, int key, char[] alphabet){
        List<String> result = new ArrayList<>();
        char[] ar = null;
        for (String str : list){
            ar = str.toCharArray();
            for(int i = 0; i< ar.length; i++) {
                for (int j = 0; j < alphabet.length; j++) {
                    if (ar[i] == alphabet[j]) {
                        if (j - key < 0 ) {
                            ar[i] = alphabet[alphabet.length-(key-j)];
                            break;
                        } else{
                            ar[i] = alphabet[j - key];
                            break;
                        }
                    }
                }
            }
            result.add(String.valueOf(ar));
        }
        return result;
    }

}
