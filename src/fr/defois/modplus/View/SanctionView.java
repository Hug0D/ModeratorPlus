package fr.defois.modplus.View;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class SanctionView {

    public TextComponent[][] getHeaderView(String[] usernames, TextComponent[] reasons) {
        TextComponent[][] header = {
                {new TextComponent("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")},
                {new TextComponent("You want to punish "), new TextComponent(String.join(", ", usernames))},
                {new TextComponent("Please select a reason:")},
                reasons,
                {new TextComponent("")},
                {new TextComponent("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")}
        };
        header[0][0].setColor(ChatColor.YELLOW);
        header[0][0].setBold(true);
        header[1][0].setBold(true);
        header[1][1].setColor(ChatColor.RED);
        header[1][1].setBold(true);
        header[2][0].setBold(true);
        header[5][0].setColor(ChatColor.YELLOW);
        header[5][0].setBold(true);
        return header;
    }

    public TextComponent getReasonView(String title, String description, String id, String[] usernames) {
        TextComponent reason = new TextComponent("[" + title + "] ");
        reason.setBold(true);
        reason.setColor(ChatColor.GREEN);
        reason.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(description).create()
        ));
        reason.setClickEvent(new ClickEvent(
                ClickEvent.Action.RUN_COMMAND,
                "/sanctionr " + id + " " + String.join(" ", usernames)
        ));
        return reason;
    }
}
