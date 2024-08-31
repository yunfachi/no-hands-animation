package com.example.mixin.client;

import com.google.common.collect.Maps;
import net.minecraft.client.item.ClampedModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static net.minecraft.item.Items.SHIELD;

@Mixin(ModelPredicateProviderRegistry.class)
public class LivingEntityClientMixin {

    @Shadow @Final private static Map<Item, Map<Identifier, ModelPredicateProvider>> ITEM_SPECIFIC;

    @Inject(at = @At(value = "HEAD"), method = "register(Lnet/minecraft/item/Item;Lnet/minecraft/util/Identifier;Lnet/minecraft/client/item/ClampedModelPredicateProvider;)V", cancellable = true)
    private static void asd(Item item, Identifier id, ClampedModelPredicateProvider provider, CallbackInfo ci) {
        if (item != SHIELD)
            ((Map)ITEM_SPECIFIC.computeIfAbsent(item, key -> Maps.newHashMap())).put(id, provider);
        else {
            provider = (stack, world, entity, seed) -> 1.0F;
            ((Map) ITEM_SPECIFIC.computeIfAbsent(item, key -> Maps.newHashMap())).put(id, provider);
        }
        ci.cancel();
    }
}
