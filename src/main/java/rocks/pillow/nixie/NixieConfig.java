package rocks.pillow.nixie;

import nilloader.api.NilLogger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NixieConfig {
    public static final NilLogger log = NilLogger.get("NixieConfig");

    private Path mcDir = Paths.get("").toAbsolutePath().normalize(); // get cwd, which is the instance's minecraft dir
    private Path configFolder = mcDir.resolve("config");
    private Path configPath = configFolder.resolve("nixie.conf");

    private String URL = "wss://connect.pixie.rip/v1";

    public NixieConfig() {
        this.assureExists();
        this.extractConfig();
    }

    public String getUrl() {
        return this.URL;
    }

    private void extractConfig() {
        try {
            byte[] bytes = Files.readAllBytes(configPath);
            String text = new String(bytes, StandardCharsets.UTF_8);
            String url = text.split("=")[1].trim();
            this.URL = url;
            log.info("Got Pixie instance URL from config: " + url);
        } catch (IOException e) {
            log.warn("Encountered IOException reading config: " + e.getMessage());
        }

    }

    private void assureExists() {
        try {
            Files.createDirectories(configFolder);
        } catch (IOException e) {
            log.warn("Encountered IOException confirming config folder exists: " + e.getMessage());
        }

        try {
            if (!Files.exists(configPath)) {
                String text = String.format("pixie-url=%s", URL);
                Files.write(configPath, text.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            log.warn("Encountered IOException confirming writing default config: " + e.getMessage());
        }
    }
}
