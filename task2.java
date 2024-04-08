import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = "";

        System.out.print("Enter '1' for providing input \nor\nEnter '2' for word count from a file:");
        int c = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();

        if (c == 1) {
            System.out.println("Enter the text:");
            text = scanner.nextLine();
        } else if (c == 2) {
            System.out.println("Enter the path of the file:");
            String fpath = scanner.nextLine();

            try {
                text = readFile(fpath);
            } catch (FileNotFoundException e) {
                System.err.println(" File not found.");
                System.exit(1);
            }
        } else {
            System.err.println("Invalid input enter '1' or '2'.");
            System.exit(1);
        }

        whileloop: while (true) {
            System.out.print(
                    "Please Enter:\n1.Total Number of Words.\n2.the frequency of all input ws.\n3.the frequency of all input ws except common ws.\n4.exit : ");
            int a = scanner.nextInt();
            switch (a) {
                case 1: {
                    int twd= countWords(text);
                    System.out.println("Total no. of Word Count: " + twd);
                    break;
                }
                case 2: {
                    Map<String, Integer> wf = getWordFrequency(text, 2);
                    System.out.println("Total Number of Words count: " + wf.size());
                    System.out.println("Total Word Frequency:");
                    for (Map.Entry<String, Integer> entry : wf.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;
                }
                case 3: {
                    Map<String, Integer> wf = getWordFrequency(text, 3);
                    System.out.println("--  Total Number of Words without repeatation: " + wf.size());
                    System.out.println("The Common Words are : { \"a\", \"and\", \"as\", \"in\", \"is\", \"it\", \"of\", \"that\", \"the\", \"to\", \"with\"}");
                    System.out.println("Word Frequency:");
                    for (Map.Entry<String, Integer> entry : wf.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;
                }
                case 4: {
                    System.out.println("......End of program.......");
                    break whileloop;
                }
                default: {
                    System.out.println("Wrong Input\nEnter correct input:");
                    break;
                }
            }
        }

        scanner.close();
    }

    public static int countWords(String text) {
        String[] ws = text.split("[\\s.,!?;:]+");
        return ws.length;
    }

    public static Map<String, Integer> getWordFrequency(String text, int n) {
        String[] ws = text.split("[\\s.,!?;:]+");
        Map<String, Integer> wf = new HashMap<>();

        for (String word : ws) {
            word = word.toLowerCase(); // Convert to lowercase to ensure case-insensitive counts
            if (isCommonWord(word) && n == 3) {
                continue;
                // wf.put(word, wf.getOrDefault(word, 0) + 1);
            } else {
                wf.put(word, wf.getOrDefault(word, 0) + 1);
            }
        }

        return wf;
    }

    public static boolean isCommonWord(String word) {
        // Define a list of common ws to ignore (stop ws)
        String[] commonWords = { "a", "and", "as", "in", "is", "it", "of", "that", "the", "to", "with"};
        return Arrays.asList(commonWords).contains(word.toLowerCase());
    }

    public static String readFile(String fpath) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        File file = new File(fpath);

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                content.append(fileScanner.nextLine());
                if (fileScanner.hasNextLine()) {
                    content.append("\n");
                }
            }
        }

        return content.toString();
    }
}