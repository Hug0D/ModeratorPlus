package fr.defois.modplus.View;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class SanctionApplyView {

    public TextComponent[] getWarnView(String warningMessage) {
        TextComponent[] warn = {
                new TextComponent("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"),
                new TextComponent("You have been warned by a moderator:"),
                new TextComponent(warningMessage),
                new TextComponent("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        };
        warn[0].setBold(true);
        warn[0].setColor(ChatColor.YELLOW);
        warn[3].setBold(true);
        warn[3].setColor(ChatColor.YELLOW);
        warn[1].setBold(true);
        warn[2].setBold(true);
        warn[2].setColor(ChatColor.RED);
        return warn;
    }

    public String getKickView(String kickMessage) {
        return ChatColor.translateAlternateColorCodes('&',
                "&4You have just been kicked by a moderator.\n\n&c" + kickMessage
        );
    }

    public String getBanKickView(String banMessage) {
        return ChatColor.translateAlternateColorCodes('&',
                "&4Your account has been blocked by a moderator.\n\n&c" + banMessage
        );
    }


    public String getBanView(String banMessage) {
        return ChatColor.translateAlternateColorCodes('&',
                "\n\n&4Your account has been blocked by a moderator.\n\n&c" + banMessage
        );
    }

    public TextComponent getSuccessMessage() {
        return new TextComponent("Done!");
    }
}
