import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        //инициализация омновных переменных
        CodeCezar codeCezar = new CodeCezar();
        Cryptanalysis cryptanalysis = new Cryptanalysis();
        Scanner scanner = new Scanner(System.in);
        //меню выбора
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

                    //вводим ключ и путь до файла
                    System.out.println("Key must be between 1 and "+ (alphabet.length-1));
                    System.out.println("Enter Key: ");
                    int key = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter path to a file: ");
                    String pathToFile = scanner.nextLine();
                    Path path = Paths.get(pathToFile);

                    //создаю файл для зашифрованного текста
                    Path encFile = Paths.get("C:\\CodeCezar\\enc.txt");
                    List<String> list = null;
                    List<String> encriptionList = null;
                    // проверяю фуществования переданного файла
                    if(Files.exists(path)){
                        try {
                            if(!Files.exists(encFile)){
                                if(!Files.exists(Paths.get("C:\\CodeCezar"))){
                                    Files.createDirectory(Paths.get("C:\\CodeCezar"));
                                }
                                Files.createFile(encFile);
                            }
                            list = Files.readAllLines(path);

                            //защифровываю текст
                            encriptionList =  codeCezar.encryption(list,key,alphabet);

                            //записываю защифрованный текст в строку, потом записываю строку в файл
                            StringBuilder sb =new StringBuilder();
                            for(String str : encriptionList){
                                sb.append(str).append('\n');
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

                    //вводим ключ и путь до файла
                    System.out.println("Key must be between 1 and "+ (alphabet.length-1));
                    System.out.println("Enter Key: ");
                    int key = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter path to a file: ");
                    String pathToFile = scanner.nextLine();
                    Path path = Paths.get(pathToFile);

                    //создаю файл для рашифрованного текста
                    Path decFile = Paths.get("C:\\CodeCezar\\dec.txt");
                    List<String> list = null;
                    List<String> decriptionList = null;
                    if(Files.exists(path)){
                        try {
                            if(!Files.exists(decFile)){
                                if(!Files.exists(Paths.get("C:\\CodeCezar"))){
                                    Files.createDirectory(Paths.get("C:\\CodeCezar"));
                                }
                                Files.createFile(decFile);
                            }
                            list = Files.readAllLines(path);

                            //расщифровываю текст
                            decriptionList =  codeCezar.decryption(list,key,alphabet);

                            //записываю защифрованный текст в строку, потом записываю строку в файл
                            StringBuilder sb =new StringBuilder();
                            for(String str : decriptionList){
                                sb.append(str).append('\n');
                            }
                            Files.write(decFile,sb.toString().getBytes());
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

                    //ввожу путь до файла
                    System.out.println("Enter path to a file: ");
                    String pathToFile = scanner.nextLine();
                    Path path = Paths.get(pathToFile);

                    //создаю файл для рашифрованного текста
                    Path decFile = Paths.get("C:\\CodeCezar\\bruteforce.txt");
                    List<String> list = null;
                    if(Files.exists(path)){
                        try {
                            if(!Files.exists(decFile)){
                                if(!Files.exists(Paths.get("C:\\CodeCezar"))){
                                    Files.createDirectory(Paths.get("C:\\CodeCezar"));
                                }
                                Files.createFile(decFile);
                            }
                            list = Files.readAllLines(path);

                            //алгорит brute force перебор алфавита
                            //выводит первую не пустую строку в консоль со сдвигом в один символ использую алфавит
                            System.out.println("brute forse will be work between 1 and "+ (alphabet.length-1));
                            cryptanalysis.bruteForce(list,alphabet);

                            //после вывода в консоль всех значений ключа, выбираем подходящий ключ и расшифровываем текст
                            System.out.println("Enter Key: ");
                            int key = Integer.parseInt(scanner.nextLine());
                            List<String> decriptionList = codeCezar.decryption(list,key,alphabet);
                            StringBuilder sb =new StringBuilder();
                            for(String str : decriptionList){
                                sb.append(str).append('\n');
                            }
                            Files.write(decFile,sb.toString().getBytes());
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
                    System.out.println("Enter path to a first file for cryptanalysis: ");
                    String firstFile = scanner.nextLine();
                    Path firstPath = Paths.get(firstFile);

                    System.out.println("Enter path to a second file for decryption: ");
                    String secondToFile = scanner.nextLine();
                    Path secondPath = Paths.get(secondToFile);

                    Path cryptoFile = Paths.get("C:\\CodeCezar\\crypto.txt");
                    Map<Character, Double> firstMap = null;
                    Map<Character, Double> secondMap = null;
                    List<String> firstList = null;
                    List<String> secondList = null;

                    if(Files.exists(firstPath)){
                        try {
                            if(!Files.exists(cryptoFile)){
                                if(!Files.exists(Paths.get("C:\\CodeCezar"))){
                                    Files.createDirectory(Paths.get("C:\\CodeCezar"));
                                }
                                Files.createFile(cryptoFile);
                            }
                            if(Files.exists(secondPath)){
                                firstList = Files.readAllLines(firstPath);
                                secondList = Files.readAllLines(secondPath);
                            }else{
                                System.out.println("Can't find the second file");
                                continue;
                            }
                            System.out.println("cryptanalysis first file");
                            firstMap = cryptanalysis.cryptanalusisFile(firstList,alphabet);
                            System.out.println("cryptanalysis first file");
                            secondMap = cryptanalysis.cryptanalusisFile(secondList,alphabet);
                            //расщифровываю текст
                            String decryptionString = cryptanalysis.cryptanalusisMap(firstMap,secondMap,secondList);
                            //System.out.print(decryptionString);
                            Files.write(cryptoFile,decryptionString.getBytes());
                            //записываю защифрованный текст в строку, потом записываю строку в файл


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("Can't find the first file");
                        continue;
                    }
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
