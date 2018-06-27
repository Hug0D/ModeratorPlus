package fr.defois.modplus.Helper;

import fr.defois.modplus.ConfigManager;
import fr.defois.modplus.Model.ReasonModel;

import java.util.ArrayList;
import java.util.List;

public class ReasonHelper {

    private ConfigManager _config;

    public ReasonHelper(ConfigManager config) {
        _config = config;
    }

    public ReasonModel getReason(String reason) {
        for(String key : _config.getReasonsConfig().getKeys(false)) {
            if (reason.equalsIgnoreCase(key)) {
                return new ReasonModel(
                        key,
                        _config.getReasonsConfig().getString(key + ".title"),
                        _config.getReasonsConfig().getString(key + ".description"),
                        _config.getReasonsConfig().getString(key + ".warning"),
                        _config.getReasonsConfig().getString(key + ".recidivism")
                );
            }
        }
        return null;
    }

    public List<ReasonModel> getReasons() {
        List<ReasonModel> reasons = new ArrayList<>();
        for(String key : _config.getReasonsConfig().getKeys(false)) {
            reasons.add(new ReasonModel(
                    key,
                    _config.getReasonsConfig().getString(key + ".title"),
                    _config.getReasonsConfig().getString(key + ".description"),
                    _config.getReasonsConfig().getString(key + ".warning"),
                    _config.getReasonsConfig().getString(key + ".recidivism")
            ));
        }
        return reasons;
    }

}
