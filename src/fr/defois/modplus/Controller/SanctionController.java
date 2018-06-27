package fr.defois.modplus.Controller;

import fr.defois.modplus.ConfigManager;
import fr.defois.modplus.Helper.ReasonHelper;
import fr.defois.modplus.Model.ReasonModel;
import fr.defois.modplus.View.SanctionView;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.ArrayList;
import java.util.List;

public class SanctionController implements CommandExecutor {

    private ConfigManager _config;
    private SanctionView _view;
    private ReasonHelper _reasonHelper;

    public SanctionController(ConfigManager config) {
        _config = config;
        _view = new SanctionView();
        _reasonHelper = new ReasonHelper(_config);
    }

    private TextComponent[] getReasonsComponents(String[] usernames) {
        List<TextComponent> reasons = new ArrayList<>();
        for (ReasonModel reasonInDb : _reasonHelper.getReasons()) {
            reasons.add(_view.getReasonView(
                    reasonInDb.getTitle(),
                    reasonInDb.getDescription(),
                    reasonInDb.getId(),
                    usernames
            ));
        }
        return reasons.toArray(new TextComponent[reasons.size()]);
    }

    private void sanctionChoose(CommandSender sender, String[] usernames) {
        TextComponent[][] header = _view.getHeaderView(usernames, getReasonsComponents(usernames));
        for (TextComponent[] msg : header) {
            sender.spigot().sendMessage(msg);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("sanction")) {
            if (args.length < 1) {
                sender.sendMessage("USAGE: ./sanction [username 1] [username 2] [...]");
            } else {
                sanctionChoose(sender, args);
            }
            return true;
        }
        return false;
    }

}
