package com.sonibe.tdmv1;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ExampleMod implements ModInitializer {
	public static final Item CUSTOM_ITEM = new CustomItem(new FabricItemSettings().maxCount(16));
	public static final Item CUSTOM_ITEM2 = Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3","custom_item2"), new Item(new FabricItemSettings()));
	public static final Item RAW_MCFEE = Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3","raw_mcfee") , new Item(new FabricItemSettings()));
	public static final Block MCFEE_ORE = new Block(AbstractBlock.Settings.create().pistonBehavior(PistonBehavior.DESTROY).strength(50F,1200F).hardness(50F));
	public static final Item MCFEE_ORE_ITEM = Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3","mcfee_ore"), new BlockItem(MCFEE_ORE, new FabricItemSettings()));




	@Override
	public void onInitialize() {
		//注册两个物品
		Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3", "custom_item"), CUSTOM_ITEM);
		Registry.register(Registries.BLOCK, new Identifier("tunanodradmodv3","mcfee_ore"), MCFEE_ORE);
		//
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
			content.add(CUSTOM_ITEM);
			content.add(CUSTOM_ITEM2);
			content.add(RAW_MCFEE);
				});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
			content.add(MCFEE_ORE);
			});
	}

	public static class CustomItem extends Item {
		public CustomItem(Settings settings) {
			super(settings);
		}

		//@Override


		public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
			user.playSound(SoundEvents.ENTITY_SHEEP_AMBIENT, 1.0F, 1.0F);
			return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
		}
	}
}
