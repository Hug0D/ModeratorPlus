package fr.defois.modplus;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private Main plugin = Main.getPlugin(Main.class);

    private FileConfiguration _reasonsConfig;
    private FileConfiguration _sanctionsConfig;
    private File _reasonsFile;
    private File _sanctionsFile;

    public void setup() {
        setupReasons();
        setupSanctions();
    }

    public FileConfiguration getReasonsConfig() {
        return _reasonsConfig;
    }

    public FileConfiguration getSanctionsConfig() {
        return _sanctionsConfig;
    }

    private void setupReasons() {
        boolean newFile = false;
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        _reasonsFile = new File(plugin.getDataFolder(), "reasons.yml");
        if (!_reasonsFile.exists()) {
            newFile = true;
            try {
                _reasonsFile.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        _reasonsConfig = YamlConfiguration.loadConfiguration(_reasonsFile);
        if (newFile) {
            loadReasonsDefaults();
            try {
                saveReasonsConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupSanctions() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        _sanctionsFile = new File(plugin.getDataFolder(), "sanctions.yml");
        if (!_sanctionsFile.exists()) {
            try {
                _sanctionsFile.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        _sanctionsConfig = YamlConfiguration.loadConfiguration(_sanctionsFile);
    }

    void saveReasonsConfig() throws IOException {
        _reasonsConfig.save(_reasonsFile);
    }

    void saveSanctionsConfig() throws IOException {
        _sanctionsConfig.save(_sanctionsFile);
    }


    private void loadReasonsDefaults() {
       _reasonsConfig.set("a_good_reason.title", "A good reason");
       _reasonsConfig.set("a_good_reason.description", "A good description");
       _reasonsConfig.set("a_good_reason.warning", "A good display for players");
       _reasonsConfig.set("a_good_reason.recidivism", "warn kick ban:5 ban:30");

        _reasonsConfig.set("another_good_reason.title", "Another good reason");
        _reasonsConfig.set("another_good_reason.description", "Another good description");
        _reasonsConfig.set("another_good_reason.warning", "Another good display for players");
        _reasonsConfig.set("another_good_reason.recidivism", "warn warn warn kick");
    }
}
