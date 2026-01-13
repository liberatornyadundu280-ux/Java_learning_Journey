
/*
so here there are no note required i will just RANK ACCORDING TO HOW THEY PERFORM
1--> reader class
2--> fast BufferReader
3--> BufferReader "BufferReaderClass"
4--> Scanner class
 */
public class Competitive_I_and_O {
    public static void main(String[] args) {
        System.out.println("this program contains classes with inplementation of taking the inputs");
    }
}

public class UsingScanner {

    public static void main(String[] args) {

        // Create Scanner object
        Scanner s = new Scanner(System.in);

        // Read number of inputs
        int n = s.nextInt();

        // Read divisor
        int k = s.nextInt();

        // Initialize count
        int c = 0;

        while (n-- > 0) {

            // Read the number
            int x = s.nextInt();

            // Check divisibility
            if (x % k == 0)
                c++;
        }

        System.out.println(c);
    }
}

public class UsingBuffer {
    public static void main(String[] args) throws IOException {

        // Create BufferedReader to read input efficiently
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read the first line and split it into tokens
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Read the total number of integers (n) and the divisor (k)
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // Initialize counter for divisible numbers
        int count = 0;

        // Process each integer
        while (n-- > 0) {

            // Read the next integer
            int x = Integer.parseInt(br.readLine());

            // Check if the number is divisible by k
            if (x % k == 0)
                count++;
        }

        System.out.println(count);
    }
}

public class FastReaderBuffer {

    // FastReader class for efficient input
    static class FastReader {

        // BufferedReader to read input
        BufferedReader b;

        // StringTokenizer to tokenize input
        StringTokenizer s;

        // Constructor to initialize BufferedReader
        public FastReader() {
            b = new BufferedReader(new InputStreamReader(System.in));
        }

        // Method to read the next token as a string
        String next() {
            while (s == null || !s.hasMoreElements()) {
                try {
                    s = new StringTokenizer(b.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return s.nextToken();
        }

        // Method to read the next token as an integer
        int nextInt() {
            return Integer.parseInt(next());
        }

        // Method to read the next token as a long
        long nextLong() {
            return Long.parseLong(next());
        }

        // Method to read the next token as a double
        double nextDouble() {
            return Double.parseDouble(next());
        }

        // Method to read the next line as a string
        String nextLine() {
            String str = "";
            try {
                if (s.hasMoreTokens()) {
                    str = s.nextToken("\n");
                } else {
                    str = b.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {

        // Create a FastReader instance for input
        FastReader s = new FastReader();

        // Read the number of integers (n) and the divisor (k)
        int n = s.nextInt();
        int k = s.nextInt();

        // Initialize count for divisible numbers
        int c = 0;

        // Loop through the integers
        while (n-- > 0) {

            // Read the next integer
            int x = s.nextInt();

            // Check if divisible by k
            if (x % k == 0)
                c++;
        }

        System.out.println(c);
    }
}

public class CustomReader {

    // Custom Reader class for fast input
    static class Reader {
        private final int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        // Reads the next integer from input
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return neg ? -ret : ret;
        }

        // Reads the next byte from the buffer
        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        // Fills the buffer with new data
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader s = new Reader();

        // Read the number of integers
        // (n) and the divisor (k)
        int n = s.nextInt();
        int k = s.nextInt();

        // Count divisible numbers
        int count = 0;

        // Process each number
        while (n-- > 0) {
            int x = s.nextInt();
            if (x % k == 0)
                count++;
        }

        System.out.println(count);
    }
}