package com.modularwarfare.common.type;

import javax.annotation.Nullable;

import com.modularwarfare.api.IMWModel;
import com.modularwarfare.client.fpp.enhanced.models.EnhancedModel;
import com.modularwarfare.common.guns.SkinType;
import com.modularwarfare.loader.MWModelBase;
import com.modularwarfare.loader.MWModelBipedBase;
import com.modularwarfare.loader.ObjModel;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseType {

    /**
     * Basic model, only loaded for guns if WeaponAnimationType.BASIC
     */
    @SideOnly(value = Side.CLIENT)
    public transient MWModelBase model;

    @SideOnly(value = Side.CLIENT)
    public transient MWModelBipedBase bipedModel;

    /**
     * Enhanced model, only loaded for guns if WeaponAnimationType.ENHANCED
     * MWModelBase model will be ignored
     */
    @SideOnly(value = Side.CLIENT)
    public transient EnhancedModel enhancedModel;

    /**
     * Max stack size
     */
    public Integer maxStackSize;
    /**
     * Weapon staticModel skins/textures
     */
    public SkinType[] modelSkins;
    public String internalName;
    /**
     * Used to generate .lang files automatically
     */
    public String displayName;
    public String iconName;

    public transient int id;
    public transient String contentPack;
    public transient boolean isInDirectory;

    @SideOnly(value = Side.CLIENT)
    public static BaseType fromModel(ObjModel model) {
        return null;
    }

    /**
     * Method for sub types to use for loading extra values
     */
    public void loadExtraValues() {

    }

    public void loadBaseValues() {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
            reloadModel();

        if (modelSkins == null)
            modelSkins = new SkinType[]{SkinType.getDefaultSkin(this)};

        if (iconName == null)
            iconName = internalName;
    }

    public void postLoad(){

    }

    /**
     * Method for sub types to use for handling staticModel reloading
     */
    public void reloadModel() {

    }

    /**
     * Method for sub types to use for having models
     */
    public boolean hasModel() {
        return (model != null || bipedModel != null || enhancedModel != null);
    }
    
    @Nullable
    public IMWModel getModel() {
        if(enhancedModel!=null) {
            return enhancedModel;
        }
        if(model!=null) {
            return model;
        }
        return null;
    }

    /**
     * Returns internal name if not overridden by sub-type
     */
    @Override
    public String toString() {
        return internalName;
    }

    public String getAssetDir() {
        //System.out.println("called");
        return "";
    }
}
