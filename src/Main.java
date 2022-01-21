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
        Cryptanalysis cryptanalysis = new Cryptanalysis();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Сhoose modу, enter number");
        System.out.println("1. encryption/decryption");
        System.out.println("2. cryptanalysis");
        System.out.println("3. exit");
        int number = Integer.parseInt(scanner.nextLine());
        while(true){
            if(number==1){
                System.out.println("Сhoose encryption/decription");
                System.out.println("1. encryption");
                System.out.println("2. decryption");
                System.out.println("3. back");
                int encryptionMode = Integer.parseInt(scanner.nextLine());
                if(encryptionMode == 1){
                    System.out.println("Enter Key: ");
                    int key = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter path to a file: ");
                    String pathToFile = scanner.nextLine();
                    Path path = Paths.get(pathToFile);
                    Path encFile = Paths.get("C:\\курсы\\курсы stepick\\CodeCezar2\\src\\enc.txt");
                    List<String> list = null;
                    List<String> encriptionList = null;
                    if(Files.exists(path)){
                        try {
                            list = Files.readAllLines(path);
                            encriptionList =  codeCezar.encryption(list,key,alphabet);
                            StringBuilder sb =new StringBuilder();
                            for(String str : encriptionList){
                                sb.append(str).append("\\n");
                            }
                            Files.write(encFile,sb.toString().getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("Can't find the file");
                        continue;
                    }
                    encryptionMode = 3;
                    System.out.println("Success encryption");
                    System.out.println();
                }else if(encryptionMode == 2){
                    System.out.println("Enter Key: ");
                    int key = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter path to a file: ");
                    String pathToFile = scanner.nextLine();
                    Path path = Paths.get(pathToFile);
                    Path encFile = Paths.get("C:\\курсы\\курсы stepick\\CodeCezar2\\src\\dec.txt");
                    List<String> list = null;
                    List<String> decriptionList = null;
                    if(Files.exists(path)){
                        try {
                            list = Files.readAllLines(path);
                            decriptionList =  codeCezar.decryption(list,key,alphabet);
                            StringBuilder sb =new StringBuilder();
                            for(String str : decriptionList){
                                sb.append(str).append("\\r\\n");
                            }
                            Files.write(encFile,sb.toString().getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("Can't find the file");
                        continue;
                    }
                    encryptionMode = 3;
                    System.out.println("Success decryption");
                    System.out.println();
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
                int encryptionMode = Integer.parseInt(scanner.nextLine());
                if(encryptionMode == 1){
                    System.out.println("Enter path to a file: ");
                    String pathToFile = scanner.nextLine();
                    Path path = Paths.get(pathToFile);
                    Path encFile = Paths.get("C:\\Users\\Sanal\\Documents\\CodeCezar\\src\\bruteforce.txt");
                    List<String> list = null;
                    if(Files.exists(path)){
                        try {
                            list = Files.readAllLines(path);
                            cryptanalysis.bruteForce(list,alphabet);
                            System.out.println("Enter Key: ");
                            int key = Integer.parseInt(scanner.nextLine());
                            List<String> decriptionList = codeCezar.decryption(list,key,alphabet);
                            StringBuilder sb =new StringBuilder();
                            for(String str : decriptionList){
                                sb.append(str).append("\\r\\n");
                            }
                            Files.write(encFile,sb.toString().getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("Can't find the file");
                        continue;
                    }
                    encryptionMode = 3;
                    System.out.println("Success decryption");
                    System.out.println();
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
                number = Integer.parseInt(scanner.nextLine());
            }else{
                System.out.println("Wrong number, please choose mode");
                System.out.println("1. encryption/decription");
                System.out.println("2. cryptanalysis");
                System.out.println("3. exit");
                number = Integer.parseInt(scanner.nextLine());
            }

        }










    }
}
