package fr.defois.modplus.Helper;

import fr.defois.modplus.ConfigManager;
import fr.defois.modplus.Model.SanctionModel;

import java.util.ArrayList;
import java.util.List;

public class SanctionHelper {

    private ConfigManager _config;
    private ReasonHelper _reasonHelper;

    public SanctionHelper(ConfigManager config) {
        _config = config;
        _reasonHelper = new ReasonHelper(_config);
    }

    public List<SanctionModel> getSanctions(String username) {
        if (_config.getSanctionsConfig().getConfigurationSection(username.toLowerCase()) == null) {
            return null;
        }
        List<SanctionModel> sanctions = new ArrayList<>();
        for(String key : _config.getSanctionsConfig().getConfigurationSection(username.toLowerCase()).getKeys(false)) {
            sanctions.add(new SanctionModel(
                    key,
                    _reasonHelper.getReason(key).getTitle(),
                    _config.getSanctionsConfig().getInt(username.toLowerCase() + "." + key)
            ));
        }
        return sanctions;
    }

    public int getNumberOfSanctions(String sanction, String username) {
        String path = username.toLowerCase() + "." + sanction;
        return _config.getSanctionsConfig().getInt(path);
    }

    public void addSanction(String sanction, String username) {
        String path = username.toLowerCase() + "." + sanction;
        System.out.println(path);
        _config.getSanctionsConfig().set(path, _config.getSanctionsConfig().getInt(path) + 1);
    }
}
