import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.lang.String;

public class GetFiles {
    public String downloadAndExtractRepository(String repoUrl) throws IOException {
        // Create a temporary directory to store the downloaded ZIP file
        Path tempDirectory = Files.createTempDirectory("GitHubRepo");

        // Download the repository as a ZIP file
        URL url = new URL(repoUrl + "/archive/refs/heads/main.zip");
        try (InputStream in = url.openStream()) {
            Path zipFilePath = tempDirectory.resolve("repository.zip");
            Files.copy(in, zipFilePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Extract the contents of the ZIP file
        Path extractDirectory = tempDirectory.resolve("extracted");
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(tempDirectory.resolve("repository.zip")))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                Path entryPath = extractDirectory.resolve(entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectories(entryPath);
                } else {
                    Files.copy(zipInputStream, entryPath, StandardCopyOption.REPLACE_EXISTING);
                }
                zipInputStream.closeEntry();
            }
        }

        // Clean up: delete the ZIP file
        Files.delete(tempDirectory.resolve("repository.zip"));
        return extractDirectory.toAbsolutePath().toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GetFiles g = new GetFiles();
        // Download and extract the GitHub repository
        try (scanner) {
            System.out.print("Enter GitHub repository URL: ");
            String repoUrl = scanner.nextLine().trim();
            String p = g.downloadAndExtractRepository(repoUrl);
            System.out.println("The files are located at :" + p);
        } catch (IOException e) {
            System.err.println("Error downloading repository: " + e.getMessage());
        }
    }
}
