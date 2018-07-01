package fr.defois.modplus;

import fr.defois.modplus.Controller.RecordController;
import fr.defois.modplus.Controller.SanctionApplyController;
import fr.defois.modplus.Controller.SanctionController;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {

    private ConfigManager _config = new ConfigManager();

    @Override
    public void onEnable() {
        _config.setup();
        getCommand("sanction").setExecutor(new SanctionController(_config));
        getCommand("sanctionr").setExecutor(new SanctionApplyController(_config));
        getCommand("record").setExecutor(new RecordController(_config));
        loadConfig();
    }

    @Override
    public void onDisable() {
        try {
            _config.saveSanctionsConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}