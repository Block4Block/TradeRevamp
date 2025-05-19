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
            villager.setRecipes(TradeManager.getTradesFor(villager.getProfession(), villager.getVillagerLevel()));
        }
    }

    @EventHandler
    public void onAcquire(VillagerAcquireTradeEvent event) {
        if (event.getEntity() instanceof Villager villager) {

            // 1) Get the vanilla list of recipes (in order)
            List<MerchantRecipe> vanilla = villager.getRecipes();

            // 2) Find which slot/index the player clicked
            int slot = vanilla.indexOf(event.getRecipe());
            if (slot == -1) return;  // safety check

            // 3) Load your custom list
            List<MerchantRecipe> custom = TradeManager.getTradesFor(
                    villager.getProfession(),
                    villager.getVillagerLevel()
            );

            // 4) If you have a replacement for that slot, swap it in
            if (slot < custom.size()) {
                event.setRecipe(custom.get(slot));
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onVillagerSpawn(CreatureSpawnEvent e) {
        if (e.getEntityType() != EntityType.VILLAGER) return;
        Villager v = (Villager)e.getEntity();

        // schedule for next tick
        Bukkit.getScheduler().runTask(this, () -> {
            v.setRecipes(TradeManager.getTradesFor(v.getProfession(), v.getVillagerLevel()));
        });
    }

    @EventHandler
    public void onCareerChange(VillagerCareerChangeEvent e) {
        if (e.getReason() == VillagerCareerChangeEvent.ChangeReason.EMPLOYED) {
            Villager v = e.getEntity();
            Bukkit.getScheduler().runTask(this, () -> {
                v.setRecipes(TradeManager.getTradesFor(e.getProfession(), v.getVillagerLevel()));
            });
        }
    }
}