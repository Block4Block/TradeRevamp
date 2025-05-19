package hasjamon.block4block;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.Material;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

public class TradeManager {
    public static List<MerchantRecipe> getTradesFor(Profession profession, int level) {
        List<MerchantRecipe> trades = new ArrayList<>();
        if (profession == Profession.LIBRARIAN) {
            trades.add(bookTrade(Enchantment.MENDING, 1));
            trades.add(bookTrade(Enchantment.UNBREAKING, 3));
            trades.add(bookTrade(Enchantment.VANISHING_CURSE, 1));
        }
        else if (profession == Profession.ARMORER) {
            trades.add(bookTrade(Enchantment.AQUA_AFFINITY, 1));
            trades.add(bookTrade(Enchantment.BLAST_PROTECTION, 4));
            trades.add(bookTrade(Enchantment.BINDING_CURSE, 1));
            trades.add(bookTrade(Enchantment.DEPTH_STRIDER, 3));
            trades.add(bookTrade(Enchantment.FEATHER_FALLING, 4));
            trades.add(bookTrade(Enchantment.FIRE_PROTECTION, 4));
            trades.add(bookTrade(Enchantment.FROST_WALKER, 2));
            trades.add(bookTrade(Enchantment.PROJECTILE_PROTECTION, 4));
            trades.add(bookTrade(Enchantment.PROTECTION, 4));
            trades.add(bookTrade(Enchantment.RESPIRATION, 3));
            trades.add(bookTrade(Enchantment.SOUL_SPEED, 3));
            trades.add(bookTrade(Enchantment.THORNS, 3));
            trades.add(bookTrade(Enchantment.SWIFT_SNEAK, 3));
        }
        else if (profession == Profession.FLETCHER) {
            trades.add(bookTrade(Enchantment.CHANNELING, 1));
            trades.add(bookTrade(Enchantment.FLAME, 1));
            trades.add(bookTrade(Enchantment.INFINITY, 1));
            trades.add(bookTrade(Enchantment.MULTISHOT, 1));
            trades.add(bookTrade(Enchantment.PIERCING, 4));
            trades.add(bookTrade(Enchantment.POWER, 5));
            trades.add(bookTrade(Enchantment.PUNCH, 2));
            trades.add(bookTrade(Enchantment.QUICK_CHARGE, 3));
            // Buys:
            trades.add(buyingTrade(Material.FEATHER, 32));
            trades.add(buyingTrade(Material.FLINT, 32));
            trades.add(buyingTrade(Material.STICK, 32));
            trades.add(buyingTrade(Material.STRING, 16));
            trades.add(buyingTrade(Material.ARROW, 16));
            // Sell all tipped arrows (arrows of effect)
            PotionType[] usablePotionTypes = new PotionType[] {
                    PotionType.SLOWNESS,
                    PotionType.STRENGTH,
                    PotionType.REGENERATION,
                    PotionType.FIRE_RESISTANCE,
                    PotionType.WATER_BREATHING,
                    PotionType.INVISIBILITY,
                    PotionType.NIGHT_VISION,
                    PotionType.WEAKNESS,
                    PotionType.POISON,
                    PotionType.LUCK,
                    PotionType.SLOW_FALLING,
            };

            for (PotionType type : usablePotionTypes) {
                ItemStack tipped = new ItemStack(Material.TIPPED_ARROW, 1);
                PotionMeta pm = (PotionMeta) tipped.getItemMeta();
                pm.setBasePotionData(new PotionData(type));
                tipped.setItemMeta(pm);

                MerchantRecipe tipRecipe = new MerchantRecipe(tipped, 0, 12, true);
                tipRecipe.addIngredient(new ItemStack(Material.EMERALD, 1));
                tipRecipe.addIngredient(new ItemStack(Material.ARROW, 1));
                trades.add(tipRecipe);
            }
        }
        else if (profession == Profession.TOOLSMITH) {
            trades.add(bookTrade(Enchantment.EFFICIENCY, 5));
            trades.add(bookTrade(Enchantment.FORTUNE, 3));
            trades.add(bookTrade(Enchantment.SILK_TOUCH, 1));
        }
        else if (profession == Profession.WEAPONSMITH) {
            trades.add(bookTrade(Enchantment.BANE_OF_ARTHROPODS, 5));
            trades.add(bookTrade(Enchantment.EFFICIENCY, 5));
            trades.add(bookTrade(Enchantment.FIRE_ASPECT, 2));
            trades.add(bookTrade(Enchantment.LOOTING, 3));
            trades.add(bookTrade(Enchantment.IMPALING, 5));
            trades.add(bookTrade(Enchantment.KNOCKBACK, 2));
            trades.add(bookTrade(Enchantment.SHARPNESS, 5));
            trades.add(bookTrade(Enchantment.SMITE, 5));
            trades.add(bookTrade(Enchantment.SWEEPING_EDGE, 3));
        }
        else if (profession == Profession.FISHERMAN) {
            // Enchanted books for fishing gear
            trades.add(bookTrade(Enchantment.LUCK_OF_THE_SEA, 3));
            trades.add(bookTrade(Enchantment.LURE, 3));
            trades.add(bookTrade(Enchantment.LOYALTY, 3));
            trades.add(bookTrade(Enchantment.RIPTIDE, 3));
            trades.add(bookTrade(Enchantment.IMPALING, 5));

            // Buys fishing-related items
            trades.add(buyingTrade(Material.STRING, 16));
            trades.add(buyingTrade(Material.STICK, 16));
            trades.add(buyingTrade(Material.COD, 16));
            trades.add(buyingTrade(Material.SALMON, 16));
            trades.add(buyingTrade(Material.TROPICAL_FISH, 8));
            trades.add(buyingTrade(Material.PUFFERFISH, 8));

            // Sell each type of boat
            Material[] boats = new Material[] {
                    Material.OAK_BOAT, Material.SPRUCE_BOAT, Material.BIRCH_BOAT,
                    Material.JUNGLE_BOAT, Material.ACACIA_BOAT, Material.DARK_OAK_BOAT,
                    Material.MANGROVE_BOAT, Material.BAMBOO_RAFT
            };
            for (Material boat : boats) {
                trades.add(sellingTrade(boat, 5)); // price: 5 emeralds
            }
        }
        else if (profession == Profession.BUTCHER) {
            trades.add(buyingTrade(Material.CHICKEN, 16));
            trades.add(buyingTrade(Material.PORKCHOP, 16));
            trades.add(buyingTrade(Material.RABBIT, 16));
            trades.add(buyingTrade(Material.MUTTON, 16));
            trades.add(buyingTrade(Material.BEEF, 16));
            trades.add(buyingTrade(Material.ROTTEN_FLESH, 16));
        }
        else if (profession == Profession.FARMER) {
            trades.add(buyingTrade(Material.WHEAT, 20));
            trades.add(buyingTrade(Material.HAY_BLOCK, 6));
            trades.add(buyingTrade(Material.BEETROOT, 16));
            trades.add(buyingTrade(Material.CARROT, 18));
            trades.add(buyingTrade(Material.POTATO, 18));
            trades.add(buyingTrade(Material.PUMPKIN, 12));
            trades.add(buyingTrade(Material.MELON, 12));
            trades.add(buyingTrade(Material.APPLE, 10));
        }
        else if (profession == Profession.SHEPHERD) {
            // Raw mutton
            trades.add(buyingTrade(Material.MUTTON, 16));

            // Wool: buy and sell every color
            Material[] woolColors = new Material[]{
                    Material.WHITE_WOOL, Material.ORANGE_WOOL, Material.MAGENTA_WOOL, Material.LIGHT_BLUE_WOOL,
                    Material.YELLOW_WOOL, Material.LIME_WOOL, Material.PINK_WOOL, Material.GRAY_WOOL,
                    Material.LIGHT_GRAY_WOOL, Material.CYAN_WOOL, Material.PURPLE_WOOL, Material.BLUE_WOOL,
                    Material.BROWN_WOOL, Material.GREEN_WOOL, Material.RED_WOOL, Material.BLACK_WOOL
            };
            for (Material wool : woolColors) {
                trades.add(buyingTrade(wool, 16));
                trades.add(sellingTrade(wool, 1));
            }

            // Dyes: buy each color only
            Material[] dyes = new Material[]{
                    Material.WHITE_DYE, Material.ORANGE_DYE, Material.MAGENTA_DYE, Material.LIGHT_BLUE_DYE,
                    Material.YELLOW_DYE, Material.LIME_DYE, Material.PINK_DYE, Material.GRAY_DYE,
                    Material.LIGHT_GRAY_DYE, Material.CYAN_DYE, Material.PURPLE_DYE, Material.BLUE_DYE,
                    Material.BROWN_DYE, Material.GREEN_DYE, Material.RED_DYE, Material.BLACK_DYE
            };
            for (Material dye : dyes) {
                trades.add(buyingTrade(dye, 8));
            }
        }
        else if (profession == Profession.MASON) {
            trades.add(sellingTrade(Material.BRICKS, 6));
        }
        else if (profession == Profession.CARTOGRAPHER) {
            trades.add(sellingTrade(Material.MAP, 1));
            // Ocean Explorer Map
            ItemStack oceanMap = new ItemStack(Material.FILLED_MAP, 1);
            MapMeta oceanMeta = (MapMeta) oceanMap.getItemMeta();
            oceanMeta.setDisplayName("Ocean Explorer Map");
            oceanMap.setItemMeta(oceanMeta);
            MerchantRecipe oceanRecipe = new MerchantRecipe(oceanMap, 0, 12, true);
            oceanRecipe.addIngredient(new ItemStack(Material.EMERALD, 5));
            trades.add(oceanRecipe);

            // Woodland Explorer Map
            ItemStack woodMap = new ItemStack(Material.FILLED_MAP, 1);
            MapMeta woodMeta = (MapMeta) woodMap.getItemMeta();
            woodMeta.setDisplayName("Woodland Explorer Map");
            woodMap.setItemMeta(woodMeta);
            MerchantRecipe woodRecipe = new MerchantRecipe(woodMap, 0, 12, true);
            woodRecipe.addIngredient(new ItemStack(Material.EMERALD, 5));
            trades.add(woodRecipe);
        }
        else if (profession == Profession.CLERIC) {
            // Sell all types of potions
            PotionType[] clericPotionTypes = new PotionType[] {
                    PotionType.REGENERATION,
                    PotionType.FIRE_RESISTANCE,
                    PotionType.WATER_BREATHING,
                    PotionType.NIGHT_VISION,
                    PotionType.INVISIBILITY,
                    PotionType.STRENGTH,
                    PotionType.SLOW_FALLING,
                    PotionType.LUCK,
            };

            for (PotionType type : clericPotionTypes) {
                ItemStack potion = new ItemStack(Material.POTION, 1);
                PotionMeta meta = (PotionMeta) potion.getItemMeta();
                meta.setBasePotionData(safePotionData(type));
                potion.setItemMeta(meta);

                MerchantRecipe potionRecipe = new MerchantRecipe(potion, 0, 12, true);
                potionRecipe.addIngredient(new ItemStack(Material.EMERALD, 1));
                trades.add(potionRecipe);
            }
            // Buying trades unchanged
            trades.add(buyingTrade(Material.ROTTEN_FLESH, 16));
            trades.add(buyingTrade(Material.SPIDER_EYE, 16));
        }
        else if (profession == Profession.LEATHERWORKER) {
            trades.add(buyingTrade(Material.LEATHER, 8));
            trades.add(buyingTrade(Material.RABBIT_HIDE, 8));
            trades.add(sellingTrade(Material.SADDLE, 1));
        }
        return trades;
    }

    private static PotionData safePotionData(PotionType type) {
        String name = type.name();

        if (name.startsWith("LONG_")) {
            PotionType base = PotionType.valueOf(name.substring("LONG_".length()));
            return new PotionData(base, true, false);
        } else if (name.startsWith("STRONG_")) {
            PotionType base = PotionType.valueOf(name.substring("STRONG_".length()));
            return new PotionData(base, false, true);
        } else {
            return new PotionData(type, false, false);
        }
    }

    private static MerchantRecipe bookTrade(Enchantment enchant, int maxLevel) {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
        meta.addStoredEnchant(enchant, maxLevel, true);
        book.setItemMeta(meta);

        MerchantRecipe recipe = new MerchantRecipe(book, 0, 12, true);
        recipe.addIngredient(new ItemStack(Material.EMERALD, 8));
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
}