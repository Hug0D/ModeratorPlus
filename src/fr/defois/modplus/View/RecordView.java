package fr.defois.modplus.View;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class RecordView {

    public TextComponent[][] getHeaderView(String username) {
        TextComponent[][] header = {
                {new TextComponent("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")},
                {new TextComponent("Criminal record of "), new TextComponent(username)},
                {new TextComponent("Number of sanctions:")}
        };
        header[0][0].setBold(true);
        header[0][0].setColor(ChatColor.YELLOW);
        header[1][0].setBold(true);
        header[1][1].setBold(true);
        header[1][1].setColor(ChatColor.RED);
        header[2][0].setBold(true);
        return header;
    }

    public TextComponent[] getSanctionView(String title, int nb) {
        TextComponent[] view = {
                new TextComponent(title),
                new TextComponent(String.valueOf(nb))
        };
        view[0].setBold(true);
        view[0].setColor(ChatColor.DARK_RED);
        view[1].setBold(true);
        view[1].setColor(ChatColor.YELLOW);
        return view;
    }

    public TextComponent getNoSanctionView() {
        TextComponent view = new TextComponent("No sanction found");
        view.setBold(true);
        view.setColor(ChatColor.GREEN);
        return view;
    }
}
