package com.sonibe.tdmv1;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExampleMod implements ModInitializer {
	public static final Item WHITE_FABRIC = new CustomItem(new FabricItemSettings().maxCount(16));
	public static final Item RED_FABRIC = new Item(new FabricItemSettings());
	public static final Item RAW_MCFEE = new Item(new FabricItemSettings());
	public final Block MCFEE_ORE = new McFeeOreBlock(AbstractBlock.Settings.create().pistonBehavior(PistonBehavior.DESTROY).strength(50F,1200F).hardness(50F));

	public class McFeeOreBlock extends Block {
		public McFeeOreBlock(Settings settings) {
			super(settings);
		}

		@Override
		public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
			if(!world.isClient){
				player.sendMessage(Text.of("This is McFEE Total Protection"), false);
			}
			return ActionResult.SUCCESS;
		}
	}

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3", "custom_item"), WHITE_FABRIC);	//测试
		Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3", "custom_item2"), RED_FABRIC);	//测试
		Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3", "raw_mcfee"), RAW_MCFEE);
		Registry.register(Registries.BLOCK, new Identifier("tunanodradmodv3", "mcfee_ore"), MCFEE_ORE);
		Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3", "mcfee_ore"), new BlockItem(MCFEE_ORE, new FabricItemSettings()));

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
			content.add(WHITE_FABRIC);
			content.add(RED_FABRIC);
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

		@Override
		public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
			user.playSound(SoundEvents.ENTITY_SHEEP_AMBIENT, 1.0F, 1.0F);
			return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
		}
	}
}
