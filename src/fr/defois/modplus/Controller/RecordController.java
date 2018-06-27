package fr.defois.modplus.Controller;

import fr.defois.modplus.ConfigManager;
import fr.defois.modplus.Helper.SanctionHelper;
import fr.defois.modplus.Model.SanctionModel;
import fr.defois.modplus.View.RecordView;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class RecordController implements CommandExecutor {

    private ConfigManager _config;
    private RecordView _view;
    private SanctionHelper _sanctionHelper;

    public RecordController(ConfigManager config) {
        _config = config;
        _view = new RecordView();
        _sanctionHelper = new SanctionHelper(_config);
    }

    private void getInfractions(String username, CommandSender sender) {
        List<SanctionModel> sanctions = _sanctionHelper.getSanctions(username);
        if (sanctions == null) {
            sender.spigot().sendMessage(_view.getNoSanctionView());
        } else {
            for (SanctionModel sanction : sanctions) {
                TextComponent[] sanctionInfo = _view.getSanctionView(sanction.getTitle(), sanction.getNb());
                sender.spigot().sendMessage(sanctionInfo[0], new TextComponent(": "), sanctionInfo[1]);
            }
        }
    }

    private void record(CommandSender sender, String username) {
        TextComponent[][] header = _view.getHeaderView(username);
        for (TextComponent[] msg : header) {
            sender.spigot().sendMessage(msg);
        }
        getInfractions(username, sender);
        sender.spigot().sendMessage(header[0]);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("record")) {
            if (args.length != 1) {
                sender.sendMessage("USAGE: ./record [username]");
            } else {
                record(sender, args[0]);
            }
            return true;
        }
        return false;
    }

}
