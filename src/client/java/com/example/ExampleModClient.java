package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.block.entity.BannerPatterns;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentMapImpl;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.PillagerOutpostGenerator;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

import static net.minecraft.block.entity.BannerPatterns.GRADIENT_UP;
import static net.minecraft.client.item.ModelPredicateProviderRegistry.register;
import static net.minecraft.component.DataComponentTypes.BANNER_PATTERNS;
import static net.minecraft.component.DataComponentTypes.BASE_COLOR;

public class ExampleModClient implements ClientModInitializer {
	public static ItemStack createShield(DyeColor BaseColor, BannerPatternsComponent patterns) {
		return new ItemStack(Items.SHIELD.getRegistryEntry(), 1, ComponentChanges.builder()
				.add(BASE_COLOR, BaseColor)
				.add(BANNER_PATTERNS, patterns)
				.build());
	}

	public static BannerPatternsComponent lr(DyeColor color, RegistryKey<BannerPattern> pattern) {
		return new BannerPatternsComponent.Builder()
				.add(RegistryEntry.of(new BannerPattern(pattern.getValue(), pattern.toString())), color)
				.build();
	}
	public static BannerPatternsComponent lr(DyeColor color1, RegistryKey<BannerPattern> pattern1, DyeColor color2, RegistryKey<BannerPattern> pattern2) {
		return new BannerPatternsComponent.Builder()
				.add(RegistryEntry.of(new BannerPattern(pattern1.getValue(), pattern1.toString())), color1)
				.add(RegistryEntry.of(new BannerPattern(pattern2.getValue(), pattern2.toString())), color2)
				.build();
	}


	public static final Map<String, ItemStack> defaultShields = Map.ofEntries(
			Map.entry("0000", createShield(DyeColor.BLACK, BannerPatternsComponent.DEFAULT)),
			Map.entry("0001", createShield(DyeColor.BLACK, lr(DyeColor.WHITE, BannerPatterns.HALF_HORIZONTAL_BOTTOM, DyeColor.BLACK, BannerPatterns.HALF_VERTICAL))),
			Map.entry("0010", createShield(DyeColor.BLACK, lr(DyeColor.WHITE, BannerPatterns.HALF_HORIZONTAL_BOTTOM, DyeColor.BLACK, BannerPatterns.HALF_VERTICAL_RIGHT))),
			Map.entry("0011", createShield(DyeColor.BLACK, lr(DyeColor.WHITE, BannerPatterns.HALF_HORIZONTAL_BOTTOM))),
			Map.entry("0100", createShield(DyeColor.BLACK, lr(DyeColor.WHITE, BannerPatterns.HALF_HORIZONTAL, DyeColor.BLACK, BannerPatterns.HALF_VERTICAL))),
			Map.entry("0101", createShield(DyeColor.BLACK, lr(DyeColor.WHITE, BannerPatterns.HALF_VERTICAL_RIGHT))),
			Map.entry("0110", createShield(DyeColor.WHITE, lr(DyeColor.BLACK, BannerPatterns.SQUARE_TOP_LEFT, DyeColor.BLACK, BannerPatterns.SQUARE_BOTTOM_RIGHT))),
			Map.entry("0111", createShield(DyeColor.WHITE, lr(DyeColor.BLACK, BannerPatterns.HALF_HORIZONTAL, DyeColor.WHITE, BannerPatterns.HALF_VERTICAL_RIGHT))),
			Map.entry("1000", createShield(DyeColor.BLACK, lr(DyeColor.WHITE, BannerPatterns.HALF_HORIZONTAL, DyeColor.BLACK, BannerPatterns.HALF_VERTICAL_RIGHT))),
			Map.entry("1001", createShield(DyeColor.WHITE, lr(DyeColor.BLACK, BannerPatterns.SQUARE_TOP_RIGHT, DyeColor.BLACK, BannerPatterns.SQUARE_BOTTOM_LEFT))),
			Map.entry("1010", createShield(DyeColor.BLACK, lr(DyeColor.WHITE, BannerPatterns.HALF_VERTICAL))),
			Map.entry("1011",  createShield(DyeColor.WHITE, lr(DyeColor.BLACK, BannerPatterns.HALF_HORIZONTAL, DyeColor.WHITE, BannerPatterns.HALF_VERTICAL))),
			Map.entry("1100", createShield(DyeColor.BLACK, lr(DyeColor.WHITE, BannerPatterns.HALF_HORIZONTAL))),
			Map.entry("1101", createShield(DyeColor.WHITE, lr(DyeColor.BLACK, BannerPatterns.HALF_HORIZONTAL_BOTTOM, DyeColor.WHITE, BannerPatterns.HALF_VERTICAL_RIGHT))),
			Map.entry("1110", createShield(DyeColor.WHITE, lr(DyeColor.BLACK, BannerPatterns.HALF_HORIZONTAL_BOTTOM, DyeColor.WHITE, BannerPatterns.HALF_VERTICAL))),
			Map.entry("1111", createShield(DyeColor.WHITE, BannerPatternsComponent.DEFAULT))
	);

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}