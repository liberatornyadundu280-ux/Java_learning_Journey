package downloader;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class DownloadUtils {

    /**
     * Validates the given URL to check if it's a valid and accessible URL.
     *
     * @param urlString The URL to validate.
     * @return true if the URL is valid, false otherwise.
     */
    public static boolean isValidUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD"); // Only check the headers (no body)
            connection.setConnectTimeout(5000); // Set timeout to 5 seconds
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK);
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + urlString);
            return false;
        } catch (IOException e) {
            System.err.println("Error checking URL: " + e.getMessage());
            return false;
        }
    }

    /**
     * Downloads a file from a URL and saves it to the specified destination.
     *
     * @param fileUrl     The URL of the file to download.
     * @param destination The file path to save the downloaded file.
     * @throws IOException if an I/O error occurs during downloading.
     */
    public static void downloadFile(String fileUrl, String destination) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000); // Set timeout to 5 seconds
        connection.setReadTimeout(5000);

        try (InputStream in = connection.getInputStream();
                FileOutputStream out = new FileOutputStream(destination)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        System.out.println("File downloaded to: " + destination);
    }

    /**
     * Verifies if the destination directory exists. If it doesn't, it creates the
     * directory.
     *
     * @param directoryPath The path to the directory.
     * @return true if the directory exists or was successfully created, false
     *         otherwise.
     */
    public static boolean verifyDirectory(String directoryPath) {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Directory created: " + directoryPath);
                return true;
            } catch (IOException e) {
                System.err.println("Failed to create directory: " + e.getMessage());
                return false;
            }
        }
        return true;
    }
}