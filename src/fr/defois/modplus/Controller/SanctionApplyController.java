package fr.defois.modplus.Controller;

import fr.defois.modplus.ConfigManager;
import fr.defois.modplus.Helper.ReasonHelper;
import fr.defois.modplus.Helper.SanctionHelper;
import fr.defois.modplus.Model.ReasonModel;
import fr.defois.modplus.View.SanctionApplyView;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.BanList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.Date;

import static org.bukkit.Bukkit.getServer;

public class SanctionApplyController implements CommandExecutor {

    private ConfigManager _config;
    private SanctionApplyView _view;
    private SanctionHelper _sanctionHelper;
    private ReasonHelper _reasonHelper;

    public SanctionApplyController(ConfigManager config) {
        _view = new SanctionApplyView();
        _config = config;
        _reasonHelper = new ReasonHelper(_config);
        _sanctionHelper = new SanctionHelper(_config);
    }

    private void warn(String username, String msg) {
        TextComponent[] warn = _view.getWarnView(msg);
        for (int index = 0 ; index < warn.length ; index++)
            getServer().getPlayer(username).spigot().sendMessage(warn[index]);
    }

    private void kick(String username, String msg) {
        String kickMessage = _view.getKickView(msg);
        getServer().getPlayer(username).kickPlayer(kickMessage);
    }

    private void ban(String username, String msg, int minutes_time) {
        String banKickMessage = _view.getBanKickView(msg);
        getServer().getPlayer(username).kickPlayer(banKickMessage);
        Date date = minutes_time == -1
                ? null
                : new Date(System.currentTimeMillis() + 60 * minutes_time * 1000);
        String banMessage = _view.getBanView(msg);
        getServer().getBanList(BanList.Type.NAME).addBan(username, banMessage, date, "");
    }

    private String[] chooseSanction(String username, ReasonModel reason) {
        String[][] recidivism = reason.getRecidivism();
        int numberOfSanctions = _sanctionHelper.getNumberOfSanctions(reason.getId(), username);
        System.out.println(numberOfSanctions);
        return numberOfSanctions >= recidivism.length
                ? recidivism[recidivism.length - 1]
                : recidivism[numberOfSanctions];
    }

    private void sanctionApply(CommandSender sender, String[] args) {
        ReasonModel reason = _reasonHelper.getReason(args[0]);
        if (reason == null) {
            sender.sendMessage("Reason not found");
        }
        String display_message = reason.getWarning();
        String reason_id = reason.getId();
        for (int i = 1 ; i < args.length ; i++) {
            if (getServer().getPlayer(args[i]) != null) {
                String sanction[] = chooseSanction(args[i], reason);
                if (sanction[0].equalsIgnoreCase("warn"))
                    warn(args[i], display_message);
                else if (sanction[0].equalsIgnoreCase("kick"))
                    kick(args[i], display_message);
                else if (sanction[0].equalsIgnoreCase("ban"))
                    ban(args[i], display_message, Integer.parseInt(sanction[1]));
            }
            _sanctionHelper.addSanction(reason_id, args[i]);
        }
        sender.spigot().sendMessage(_view.getSuccessMessage());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("sanctionr")) {
            if (args.length < 2) {
                sender.sendMessage("USAGE: ./sanctionr [reason identifier] [username 1] [username 2] [...]");
            } else {
                sanctionApply(sender, args);
            }
            return true;
        }
        return false;
    }
}
