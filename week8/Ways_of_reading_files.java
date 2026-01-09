public class Ways_of_reading_files {
    public static void main(String[] args) {
        System.out.println(
                "Here i have put sub classes to implement the ways of reading the files mu favourite is Scanner method");
    }
}

public class UsingBufferReader {
    public static void main(String[] args) throws Exception {

        // Creating BufferedReader for Input
        BufferedReader bfri = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Enter the Path : ");

        // Reading File name
        String path = bfri.readLine();

        BufferedReader bfro = new BufferedReader(new FileReader(path));
        String st;

        while ((st = bfro.readLine()) != null)
            System.out.println(st);
    }
}

// this is one also can do i like it is easy to code b ut reads character by
// character
public class UsingFileReader {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the Path : ");

        // Reading File name
        String path = br.readLine();

        FileReader fr = new FileReader(path);

        int i;

        // Holds true till there is nothing to read
        while ((i = fr.read()) != -1)

            // Print all the content of a file
            System.out.print((char) i);
    }
}

// this is my number 1🫰
public class UsingScannerClass {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the Path : ");

        // Reading File name
        String path = br.readLine();

        // pass the path to the file as a parameter
        File file = new File(path);

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
            System.out.println(sc.nextLine());
        System.out.println(sc.next());// reading the whole file without loops
    }
}

public class ReadFileIntoList {
    public static List<String> readFileInList(String fileName) {
        // Created List of String
        List<String> lines = Collections.emptyList();

        try {
            lines = Files.readAllLines(
                    Paths.get(fileName),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void main(String[] args)
            throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the Path : ");

        // Reading File name
        String path = br.readLine();

        List l = readFileInList(path);

        // Iterator iterating over List
        Iterator<String> itr = l.iterator();

        while (itr.hasNext())
            System.out.println(itr.next());
    }
}

public class ReadTextAsString {

    public static String readFileAsString(String fileName)
            throws Exception {
        String data = "";
        data = new String(
                Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the Path : ");

        // Reading File name
        String path = br.readLine();

        String data = readFileAsString(path);

        System.out.println(data);
    }
}