import java.util.ArrayList;
import java.util.List;

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
}
