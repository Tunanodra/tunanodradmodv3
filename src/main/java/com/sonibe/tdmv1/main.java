package com.sonibe.tdmv1;import net.fabricmc.api.ModInitializer;import net.fabricmc.fabric.api.item.v1.FabricItemSettings;import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;import net.minecraft.block.AbstractBlock;import net.minecraft.block.Block;import net.minecraft.block.BlockState;import net.minecraft.block.piston.PistonBehavior;import net.minecraft.entity.player.PlayerEntity;import net.minecraft.item.*;import net.minecraft.registry.Registries;import net.minecraft.registry.Registry;import net.minecraft.registry.RegistryKey;import net.minecraft.registry.RegistryKeys;import net.minecraft.text.Text;import net.minecraft.util.ActionResult;import net.minecraft.util.Hand;import net.minecraft.util.Identifier;import net.minecraft.util.hit.BlockHitResult;import net.minecraft.util.math.BlockPos;import net.minecraft.world.World;public class main implements ModInitializer {	public static final Item MCFEE_ANTIVIRUS = new Item(new FabricItemSettings().maxCount(1));	public static final Item MCFEE_FIREWALL = new Item(new FabricItemSettings().maxCount(1));	public static final Item RAW_MCFEE = new Item(new FabricItemSettings());	public final Block MCFEE_Total_Protection = new McFeeOreBlock(AbstractBlock.Settings.create().pistonBehavior(PistonBehavior.DESTROY).strength(50F,1200F).hardness(50F));	//public static final RegistryKey ITEM_GROUP = RegistryKey.of( , new Identifier("tunanodradmodv3", "general"));	public static final RegistryKey<ItemGroup> MCFEE = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("tunanodradmodv3", "mcfee"));	public class McFeeOreBlock extends Block {		public McFeeOreBlock(Settings settings) {			super(settings);		}		@Override		public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {			if(!world.isClient){				BlockPos belowPos = pos.down();				BlockState belowState = world.getBlockState(belowPos);				world.setBlockState(pos, belowState);			}			return ActionResult.SUCCESS;		}	}	@Override	public void onInitialize() {		//Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3", "custom_item"), WHITE_FABRIC);	//测试		//Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3", "custom_item2"), RED_FABRIC);	//测试		Registry.register(Registries.ITEM_GROUP, MCFEE, FabricItemGroup.builder()				.icon(() -> new ItemStack(Items.DIAMOND_PICKAXE))				.displayName(Text.translatable("tunaodradmodv3.group1")).entries((displayContext, entries) -> {					entries.add(MCFEE_ANTIVIRUS);					entries.add(MCFEE_FIREWALL);					entries.add(MCFEE_Total_Protection);					entries.add(RAW_MCFEE);				})				.build());		Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3","mcfee_antivirus"), MCFEE_ANTIVIRUS);		Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3","mcfee_firewall"), MCFEE_FIREWALL);		Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3", "raw_mcfee"), RAW_MCFEE);		Registry.register(Registries.BLOCK, new Identifier("tunanodradmodv3", "mcfee_total_protection"), MCFEE_Total_Protection);		Registry.register(Registries.ITEM, new Identifier("tunanodradmodv3", "mcfee_total_protection"), new BlockItem(MCFEE_Total_Protection, new FabricItemSettings()));		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {			//content.add(WHITE_FABRIC);			//content.add(RED_FABRIC);			content.add(RAW_MCFEE);		});		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {			content.add(MCFEE_Total_Protection);		});	}}