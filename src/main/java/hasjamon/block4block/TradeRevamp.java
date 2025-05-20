package hasjamon.block4block;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.entity.VillagerReplenishTradeEvent;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class TradeRevamp extends JavaPlugin implements Listener, CommandExecutor {
    @Override
    public void onEnable() {
        getLogger().info("TradeRevamp enabled");
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("trevamp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            reloadConfig();
            sender.sendMessage("[TradeRevamp] Configuration reloaded.");
            return true;
        }
        return false;
    }

    @EventHandler
    public void onRepopulate(VillagerReplenishTradeEvent event) {
        if (event.getEntity() instanceof Villager villager) {
            // 1) Stop Paper from mutating its own list under you
            event.setCancelled(true);

            // 2) Grab your fresh trades
            List<MerchantRecipe> custom = TradeManager.getTradesFor(
                    villager.getProfession(),
                    villager.getVillagerLevel(),
                    villager.getLocation()
            );

            // 3) Swap them in *next tick* on the main thread
            Bukkit.getScheduler().runTask(this, () -> {
                villager.setRecipes(custom);
            });
        }
    }

    @EventHandler
    public void onCareerChange(VillagerCareerChangeEvent e) {
        if (e.getReason() == VillagerCareerChangeEvent.ChangeReason.EMPLOYED) {
            Villager v = e.getEntity();
            Bukkit.getScheduler().runTask(this, () ->
                    v.setRecipes(TradeManager.getTradesFor(v.getProfession(), v.getVillagerLevel(),v.getLocation()))
            );
        }
    }

    @EventHandler
    public void onVillagerSpawn(CreatureSpawnEvent e) {
        if (e.getEntityType() == EntityType.VILLAGER) {
            Villager v = (Villager)e.getEntity();
            Bukkit.getScheduler().runTask(this, () ->
                    v.setRecipes(TradeManager.getTradesFor(v.getProfession(), v.getVillagerLevel(),v.getLocation()))
            );
        }
    }
}