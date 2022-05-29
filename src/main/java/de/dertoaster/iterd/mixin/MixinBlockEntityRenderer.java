package de.dertoaster.iterd.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import de.dertoaster.iterd.ITERDMod;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

@Mixin(BlockEntityRenderer.class)
public interface MixinBlockEntityRenderer {
	
	@Shadow
	private int getRenderDistance() {
		throw new IllegalStateException("Mixin failed to shadow getRenderDistance()");
	}
	
	@Inject(
			at = @At("HEAD"), 
			method = "isInRenderDistance(Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/util/math/Vec3d;)Z",
			cancellable = true
	)
	private <T extends BlockEntity> void init(T blockEntity, Vec3d pos, CallbackInfoReturnable<Boolean> cir) {
		if(ITERDMod.CONFIG_MAP != null) {
			Identifier beId = BlockEntityType.getId(blockEntity.getType());
			String id = beId.toString();
			
			int result = ITERDMod.CONFIG_MAP.getOrDefault(id, this.getRenderDistance());
			
			cir.setReturnValue(Vec3d.ofCenter(blockEntity.getPos()).isInRange(pos, (double) result));
		}
	}
}
