package hasjamon.block4block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

public class TradeManager {

    public static List<MerchantRecipe> getTradesFor(Profession profession, int level, Location location) {
        List<MerchantRecipe> trades = new ArrayList<>();

        if (profession == Profession.LIBRARIAN) {
            trades.add(customBookTrade(Enchantment.MENDING, 1, 12));
            trades.add(customBookTrade(Enchantment.UNBREAKING, 3, 10));
            trades.add(customBookTrade(Enchantment.VANISHING_CURSE, 1, 15));
        }
        else if (profession == Profession.ARMORER) {
            trades.add(customBookTrade(Enchantment.AQUA_AFFINITY, 1, 10));
            trades.add(customBookTrade(Enchantment.BLAST_PROTECTION, 4, 12));
            trades.add(customBookTrade(Enchantment.BINDING_CURSE, 1, 15));
            trades.add(customBookTrade(Enchantment.DEPTH_STRIDER, 3, 10));
            trades.add(customBookTrade(Enchantment.FEATHER_FALLING, 4, 12));
            trades.add(customBookTrade(Enchantment.FIRE_PROTECTION, 4, 12));
            trades.add(customBookTrade(Enchantment.FROST_WALKER, 2, 10));
            trades.add(customBookTrade(Enchantment.PROJECTILE_PROTECTION, 4, 12));
            trades.add(customBookTrade(Enchantment.PROTECTION, 4, 12));
            trades.add(customBookTrade(Enchantment.RESPIRATION, 3, 10));
            trades.add(customBookTrade(Enchantment.SOUL_SPEED, 3, 12));
            trades.add(customBookTrade(Enchantment.THORNS, 3, 12));
            trades.add(customBookTrade(Enchantment.SWIFT_SNEAK, 3, 12));
        }
        else if (profession == Profession.FLETCHER) {
            trades.add(customBookTrade(Enchantment.CHANNELING, 1, 12));
            trades.add(customBookTrade(Enchantment.FLAME, 1, 12));
            trades.add(customBookTrade(Enchantment.INFINITY, 1, 15));
            trades.add(customBookTrade(Enchantment.MULTISHOT, 1, 15));
            trades.add(customBookTrade(Enchantment.PIERCING, 4, 12));
            trades.add(customBookTrade(Enchantment.POWER, 5, 15));
            trades.add(customBookTrade(Enchantment.PUNCH, 2, 12));
            trades.add(customBookTrade(Enchantment.QUICK_CHARGE, 3, 12));
            trades.add(buyingTrade(Material.FEATHER, 48));
            trades.add(buyingTrade(Material.FLINT, 48));
            trades.add(buyingTrade(Material.STICK, 48));
            trades.add(buyingTrade(Material.STRING, 24));
            trades.add(buyingTrade(Material.ARROW, 24));
            PotionType[] usablePotionTypes = new PotionType[]{
                    PotionType.SLOWNESS, PotionType.STRENGTH, PotionType.REGENERATION,
                    PotionType.FIRE_RESISTANCE, PotionType.WATER_BREATHING, PotionType.INVISIBILITY,
                    PotionType.NIGHT_VISION, PotionType.WEAKNESS, PotionType.POISON,
                    PotionType.LUCK, PotionType.SLOW_FALLING
            };
            for (PotionType type : usablePotionTypes) {
                ItemStack tipped = new ItemStack(Material.TIPPED_ARROW, 1);
                PotionMeta pm = (PotionMeta) tipped.getItemMeta();
                pm.setBasePotionData(new PotionData(type));
                tipped.setItemMeta(pm);
                MerchantRecipe tipRecipe = new MerchantRecipe(tipped, 0, 12, true);
                tipRecipe.addIngredient(new ItemStack(Material.EMERALD, 3));
                tipRecipe.addIngredient(new ItemStack(Material.ARROW, 1));
                trades.add(tipRecipe);
            }
        }
        else if (profession == Profession.TOOLSMITH) {
            trades.add(customBookTrade(Enchantment.EFFICIENCY, 5, 12));
            trades.add(customBookTrade(Enchantment.FORTUNE, 3, 15));
            trades.add(customBookTrade(Enchantment.SILK_TOUCH, 1, 15));
        }
        else if (profession == Profession.WEAPONSMITH) {
            trades.add(customBookTrade(Enchantment.BANE_OF_ARTHROPODS, 5, 15));
            trades.add(customBookTrade(Enchantment.FIRE_ASPECT, 2, 12));
            trades.add(customBookTrade(Enchantment.LOOTING, 3, 12));
            trades.add(customBookTrade(Enchantment.IMPALING, 5, 15));
            trades.add(customBookTrade(Enchantment.KNOCKBACK, 2, 12));
            trades.add(customBookTrade(Enchantment.SHARPNESS, 5, 15));
            trades.add(customBookTrade(Enchantment.SMITE, 5, 15));
            trades.add(customBookTrade(Enchantment.SWEEPING_EDGE, 3, 12));
        }
        else if (profession == Profession.FISHERMAN) {
            trades.add(customBookTrade(Enchantment.LUCK_OF_THE_SEA, 3, 12));
            trades.add(customBookTrade(Enchantment.LURE, 3, 12));
            trades.add(customBookTrade(Enchantment.LOYALTY, 3, 15));
            trades.add(customBookTrade(Enchantment.RIPTIDE, 3, 15));
            trades.add(customBookTrade(Enchantment.IMPALING, 5, 15));
            trades.add(buyingTrade(Material.STRING, 32));
            trades.add(buyingTrade(Material.STICK, 48));
            trades.add(buyingTrade(Material.COD, 24));
            trades.add(buyingTrade(Material.SALMON, 24));
            trades.add(buyingTrade(Material.TROPICAL_FISH, 16));
            trades.add(buyingTrade(Material.PUFFERFISH, 16));
            Material[] boats = new Material[]{
                    Material.OAK_BOAT, Material.SPRUCE_BOAT, Material.BIRCH_BOAT,
                    Material.JUNGLE_BOAT, Material.ACACIA_BOAT, Material.DARK_OAK_BOAT,
                    Material.MANGROVE_BOAT, Material.BAMBOO_RAFT
            };
            for (Material boat : boats) {
                trades.add(sellingTrade(boat, 2));
            }
        }
        else if (profession == Profession.BUTCHER) {
            trades.add(buyingTrade(Material.CHICKEN, 64));
            trades.add(buyingTrade(Material.PORKCHOP, 24));
            trades.add(buyingTrade(Material.RABBIT, 24));
            trades.add(buyingTrade(Material.MUTTON, 24));
            trades.add(buyingTrade(Material.BEEF, 24));
            trades.add(buyingTrade(Material.ROTTEN_FLESH, 48));
        }
        else if (profession == Profession.FARMER) {
            trades.add(buyingTrade(Material.WHEAT, 30));
            trades.add(buyingTrade(Material.HAY_BLOCK, 3));
            trades.add(buyingTrade(Material.BEETROOT, 24));
            trades.add(buyingTrade(Material.CARROT, 24));
            trades.add(buyingTrade(Material.POTATO, 24));
            trades.add(buyingTrade(Material.PUMPKIN, 16));
            trades.add(buyingTrade(Material.MELON, 16));
            trades.add(buyingTrade(Material.APPLE, 10));
        }
        else if (profession == Profession.SHEPHERD) {
            trades.add(buyingTrade(Material.MUTTON, 24));
            Material[] woolColors = new Material[]{
                    Material.WHITE_WOOL, Material.ORANGE_WOOL, Material.MAGENTA_WOOL, Material.LIGHT_BLUE_WOOL,
                    Material.YELLOW_WOOL, Material.LIME_WOOL, Material.PINK_WOOL, Material.GRAY_WOOL,
                    Material.LIGHT_GRAY_WOOL, Material.CYAN_WOOL, Material.PURPLE_WOOL, Material.BLUE_WOOL,
                    Material.BROWN_WOOL, Material.GREEN_WOOL, Material.RED_WOOL, Material.BLACK_WOOL
            };
            for (Material wool : woolColors) {
                trades.add(buyingTrade(wool, 24));
                trades.add(sellingTrade(wool, 2));
            }
            Material[] dyes = new Material[]{
                    Material.WHITE_DYE, Material.ORANGE_DYE, Material.MAGENTA_DYE, Material.LIGHT_BLUE_DYE,
                    Material.YELLOW_DYE, Material.LIME_DYE, Material.PINK_DYE, Material.GRAY_DYE,
                    Material.LIGHT_GRAY_DYE, Material.CYAN_DYE, Material.PURPLE_DYE, Material.BLUE_DYE,
                    Material.BROWN_DYE, Material.GREEN_DYE, Material.RED_DYE, Material.BLACK_DYE
            };
            for (Material dye : dyes) {
                trades.add(buyingTrade(dye, 12));
            }
        }
        else if (profession == Profession.MASON) {
            trades.add(sellingTrade(Material.BRICKS, 8));
        }
        else if (profession == Profession.CLERIC) {
            PotionType[] clericPotionTypes = new PotionType[]{
                    PotionType.REGENERATION, PotionType.FIRE_RESISTANCE,
                    PotionType.WATER_BREATHING, PotionType.NIGHT_VISION,
                    PotionType.INVISIBILITY, PotionType.STRENGTH,
                    PotionType.SLOW_FALLING, PotionType.LUCK
            };
            for (PotionType type : clericPotionTypes) {
                ItemStack potion = new ItemStack(Material.POTION, 1);
                PotionMeta meta = (PotionMeta) potion.getItemMeta();
                meta.setBasePotionData(safePotionData(type));
                potion.setItemMeta(meta);
                MerchantRecipe potionRecipe = new MerchantRecipe(potion, 0, 12, true);
                potionRecipe.addIngredient(new ItemStack(Material.EMERALD, 2));
                trades.add(potionRecipe);
            }
            trades.add(buyingTrade(Material.ROTTEN_FLESH, 36));
            trades.add(buyingTrade(Material.SPIDER_EYE, 24));
        }
        else if (profession == Profession.LEATHERWORKER) {
            trades.add(buyingTrade(Material.LEATHER, 12));
            trades.add(buyingTrade(Material.RABBIT_HIDE, 12));
            trades.add(sellingTrade(Material.SADDLE, 6));
        }

        return trades;
    }

    private static MerchantRecipe customBookTrade(Enchantment enchant, int maxLevel, int cost) {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
        meta.addStoredEnchant(enchant, maxLevel, true);
        book.setItemMeta(meta);
        MerchantRecipe recipe = new MerchantRecipe(book, 0, 12, true);
        recipe.addIngredient(new ItemStack(Material.EMERALD, cost));
        return recipe;
    }

    private static MerchantRecipe buyingTrade(Material mat, int price) {
        MerchantRecipe recipe = new MerchantRecipe(new ItemStack(Material.EMERALD, 1), 0, 12, true);
        recipe.addIngredient(new ItemStack(mat, price));
        return recipe;
    }

    private static MerchantRecipe sellingTrade(Material mat, int price) {
        MerchantRecipe recipe = new MerchantRecipe(new ItemStack(mat, 1), 0, 12, true);
        recipe.addIngredient(new ItemStack(Material.EMERALD, price));
        return recipe;
    }

    private static PotionData safePotionData(PotionType type) {
        String name = type.name();
        if (name.startsWith("LONG_")) {
            PotionType base = PotionType.valueOf(name.substring(5));
            return new PotionData(base, true, false);
        } else if (name.startsWith("STRONG_")) {
            PotionType base = PotionType.valueOf(name.substring(7));
            return new PotionData(base, false, true);
        } else {
            return new PotionData(type, false, false);
        }
    }
}