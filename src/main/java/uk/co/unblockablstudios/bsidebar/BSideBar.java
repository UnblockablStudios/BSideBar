package uk.co.unblockablstudios.bsidebar;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public final class BSideBar extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

//        An example is below!
        createDynamicSideBar(e.getPlayer(), "%player_name%", new String[] {"hi", "%player_x%", "%player_y%", String.valueOf(e.getPlayer().getHealth() / 2)}, 5);

    }



    /**
     * @param p The player to give the sidebar
     * @param title The title of the sidebar (at the top)
     * @param lines A string array with each string being a new line in the sidebar
     * @return The scoreboard
     */
    public void createStaticSideBar(Player p, String title, String[] lines) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("BSidebarBoard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', title)));

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() >= 30) { i=lines.length+1; p.sendMessage("The scoreboard is invalid. Line " + i + " was more than 30 characters."); }
            Score scoreLine = obj.getScore(PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', lines[i])));
            scoreLine.setScore(lines.length - i);
        }

        p.setScoreboard(board);

    }

    /**
     * @param p The player to give the sidebar
     * @param board The scoreboard to set
     */
    public void createStaticSideBar(Player p, Scoreboard board) {

        p.setScoreboard(board);

    }


    /**
     * @param p The player to give the sidebar
     * @param title The title of the sidebar (at the top)
     * @param lines A string array with each string being a new line in the sidebar
     * @param updateInterval How often the sidebar updates (in ticks)
     */
    public void createDynamicSideBar(Player p, String title, String[] lines, int updateInterval) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("BSidebarBoard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', title)));

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() >= 30) { i=lines.length+1; p.sendMessage("The scoreboard is invalid. Line " + i + " was more than 30 characters."); }
            Score scoreLine = obj.getScore(PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', lines[i])));
            scoreLine.setScore(lines.length - i);
        }

        p.setScoreboard(board);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

                    Objective obj = board.registerNewObjective("BSidebarBoard", "dummy");
                    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                    obj.setDisplayName(PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', title)));

                    for (int i = 0; i < lines.length; i++) {
                        if (lines[i].length() >= 30) { i=lines.length+1; p.sendMessage("The scoreboard is invalid. Line " + i + " was more than 30 characters."); }
                        Score scoreLine = obj.getScore(PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', lines[i])));
                        scoreLine.setScore(lines.length - i);
                    }

                    p.setScoreboard(board);
                }
            }
        }, 0L, updateInterval);

    }


}
