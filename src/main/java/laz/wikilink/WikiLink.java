package laz.wikilink;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WikiLink extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {

        getCommand("wiki").setExecutor(this);
        getCommand("simwiki").setExecutor(this);
        getLogger().info("WikiLink plugin enabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            return false;
        }

        String message = String.join(" ", args);

        // Remove obscure characters and replace spaces with underscores
        message = message.replaceAll("[^A-Za-z0-9_ ]", "").replaceAll(" ", "_");

        String url;

        if (command.getName().equalsIgnoreCase("wiki")) {
            url = "https://minecraft.wiki/?search=" + message;

        } else if (command.getName().equalsIgnoreCase("simwiki")) {
            url = "https://simplicitypvp.net/w/index.php?search=" + message;

        } else {
            return false;
        }

        player.sendMessage(url);

        return true;
    }
}
