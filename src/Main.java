import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final char[] alphabet =
                      {'а','б','в','г','д',
                       'е','ё','ж','з','и',
                       'й','к','л','м','н',
                       'о','п','р','с','т',
                       'у','ф','х','ц','ч',
                       'ш','щ','ъ','ы','ь',
                       'э','ю','я',
                       '.',',',':','-','!','?',' '};

    public static void main(String[] args){
        CodeCezar codeCezar = new CodeCezar();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Сhoose modу, enter number");
        System.out.println("1. encryption/decryption");
        System.out.println("2. cryptanalysis");
        System.out.println("3. exit");
        int number = scanner.nextInt();
        while(true){
            if(number==1){
                System.out.println("Сhoose encryption/decription");
                System.out.println("1. encryption");
                System.out.println("2. decryption");
                System.out.println("3. back");
                int encryptionMode = scanner.nextInt();
                if(encryptionMode == 1){
//                    System.out.println("enter to path to a file");
//                    String pathToFile = scanner.next();
                    Path path = Paths.get("C:\\Users\\Sanal\\Documents\\CodeCezar\\src\\text.txt");
                    Path encFile = Paths.get("C:\\Users\\Sanal\\Documents\\CodeCezar\\src\\enc.txt");
                    List<String> list = null;
                    List<String> encriptionList = null;
                    if(Files.exists(path)){
                        try {
                            list = Files.readAllLines(path);
                            encriptionList =  codeCezar.encryption(list,2,alphabet);
                            StringBuilder sb =new StringBuilder();
                            for(String str : encriptionList){
                                sb.append(str).append("\\n");
                            }
                            Files.write(encFile,sb.toString().getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    encryptionMode = 3;
                }else if(encryptionMode == 2){
                    //List<String> dencriptionList =  codeCezar.decryption(list,2,alphabet);
                }else if(encryptionMode == 3){
                    number = 4;
                    continue;
                }else {
                    System.out.println("Wrong number, please choose mode");
                    System.out.println("1. encryption");
                    System.out.println("2. decryption");
                    System.out.println("3. back");
                }
            }else if(number==2){
                System.out.println("Сhoose brute_force/cryptanalysis");
                System.out.println("1. brute_force");
                System.out.println("2. cryptanalysis");
                System.out.println("3. back");
                int encryptionMode = scanner.nextInt();
                if(encryptionMode == 1){

                }else if(encryptionMode == 2){

                }else if(encryptionMode == 3){
                    number = 4;
                    continue;
                }else {
                    System.out.println("Wrong number, please choose mode");
                    System.out.println("1. brute_force");
                    System.out.println("2. cryptanalysis");
                    System.out.println("3. back");
                }
            }else if(number == 3) {
                break;
            }else if(number == 4) {
                System.out.println("Сhoose modу, enter number");
                System.out.println("1. encryption/decryption");
                System.out.println("2. cryptanalysis");
                System.out.println("3. exit");
                number = scanner.nextInt();
            }else{
                System.out.println("Wrong number, please choose mode");
                System.out.println("1. encryption/decription");
                System.out.println("2. cryptanalysis");
                System.out.println("3. exit");
                number = scanner.nextInt();
            }

        }










    }
}
