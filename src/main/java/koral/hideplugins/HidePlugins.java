package koral.hideplugins;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class HidePlugins extends JavaPlugin implements Listener {

public void textOnlyHover(String text, Player player, String hover)
{
    TextComponent tekstwiadomosci = new TextComponent(ChatColor.translateAlternateColorCodes('&', text));
    player.spigot().sendMessage(tekstwiadomosci);
    hover = hover.replace("\\n", "\n");
    tekstwiadomosci.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', hover)).create()));
}
//
// show_text
    // suggest_command
    // run_command
    // open_url
public void textClickableHover(String text, Player player, String hover, String URL)
{
    TextComponent tekstwiadomosci = new TextComponent(ChatColor.translateAlternateColorCodes('&', text));
    player.spigot().sendMessage(tekstwiadomosci);
    tekstwiadomosci.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.translateAlternateColorCodes('&', hover))));
    tekstwiadomosci.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, URL));
}


    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (event.getMessage().toLowerCase().startsWith("/plugins") || event.getMessage().toLowerCase().startsWith("/pl") || event.getMessage().toLowerCase().startsWith("/bukkit:pl")
        || event.getMessage().toLowerCase().startsWith("/bukkit:plugins"));
        {
            if(!player.hasPermission("HidePlugins.admin")) {
                event.setCancelled(true);
                textOnlyHover(getConfig().getString("pluginsline"), player, getConfig().getString("hovertext"));
            }

        }
    }


            @Override
            public void onEnable () {
                File file = new File(getDataFolder() + File.separator + "config.yml");
                if (!file.exists()) {
                    saveDefaultConfig();;
                } else {
                    saveDefaultConfig();
                    reloadConfig();
                }

                getServer().getPluginManager().registerEvents(this, this);
                // Plugin startup logic

            }

            @Override
            public void onDisable () {
                // Plugin shutdown logic
            }
        }

