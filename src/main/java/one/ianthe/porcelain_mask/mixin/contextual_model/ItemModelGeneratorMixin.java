package one.ianthe.porcelain_mask.mixin.contextual_model;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import one.ianthe.porcelain_mask.model.ContextualModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemModelGenerator.class)
public class ItemModelGeneratorMixin{
	@ModifyReturnValue(
		method = "generateBlockModel",
		at = @At(value = "RETURN", ordinal = 0)
	)
	public BlockModel porcelain_mask$generateContextualBlockModel(BlockModel original, @Local(ordinal = 0) BlockModel model){
		ContextualModel contextual = (ContextualModel) model;
		
		if(contextual.isContextual()){
			ContextualModel originalContextual = (ContextualModel) original;
			originalContextual.setContextualFromOther(contextual);
			
			return (BlockModel) originalContextual;
		}
		return original;
	}
}
