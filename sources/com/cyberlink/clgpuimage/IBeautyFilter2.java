package com.cyberlink.clgpuimage;

/* loaded from: classes.dex */
public interface IBeautyFilter2 {

    public enum EffectMode {
        ARTISTIC_COFFEE,
        ARTISTIC_CRESCENT,
        ARTISTIC_GLOOM,
        ARTISTIC_GRACE,
        ARTISTIC_HIPPIE,
        ARTISTIC_INK,
        ARTISTIC_LAVENDER,
        ARTISTIC_MATRIX,
        ARTISTIC_MEMORY,
        ARTISTIC_MODERN,
        ARTISTIC_NEWAGE,
        ARTISTIC_NOIR,
        ARTISTIC_NOSTALGIA,
        ARTISTIC_OCHRE,
        ARTISTIC_ORCHID,
        ARTISTIC_TRENDY,
        FOOD_BISTRO,
        FOOD_CAKE,
        FOOD_CUISINE,
        FOOD_DIVINE,
        FOOD_FEAST,
        FOOD_FLAVORFUL,
        FOOD_HAZEL,
        FOOD_PAST,
        FOOD_REDSCALE,
        FOOD_SAVORY,
        FOOD_SMOKED,
        FOOD_SOFT,
        FOOD_SUPPER,
        FOOD_TEATIME,
        FOOD_TEMPTING,
        FOOD_THE70S,
        PORTRAIT_AESTHETIC,
        PORTRAIT_BLACK_WHITE,
        PORTRAIT_CANDY,
        PORTRAIT_COOL,
        PORTRAIT_ELEGANT,
        PORTRAIT_FOREST,
        PORTRAIT_FRESH,
        PORTRAIT_GENTLE,
        PORTRAIT_LIGHT,
        PORTRAIT_NATURAL,
        PORTRAIT_RED,
        PORTRAIT_RETRO,
        PORTRAIT_SILVER,
        PORTRAIT_SOFTLIGHT,
        PORTRAIT_VINTAGE,
        PORTRAIT_WARM,
        SCENERY_ANCIENT,
        SCENERY_AUTUMN,
        SCENERY_CRYSTAL,
        SCENERY_DAWN,
        SCENERY_DUSK,
        SCENERY_FILM,
        SCENERY_FOGGY,
        SCENERY_JADE,
        SCENERY_LONDON,
        SCENERY_LUNA,
        SCENERY_PROCESS,
        SCENERY_SPRING,
        SCENERY_TINTED,
        SCENERY_TURQUOISE,
        SCENERY_VERDANT,
        SCENERY_ZEPHYR;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static EffectMode[] valuesCustom() {
            EffectMode[] effectModeArrValuesCustom = values();
            int length = effectModeArrValuesCustom.length;
            EffectMode[] effectModeArr = new EffectMode[length];
            System.arraycopy(effectModeArrValuesCustom, 0, effectModeArr, 0, length);
            return effectModeArr;
        }
    }

    public enum FilterType {
        LIVE_SMOOTH,
        ENABLE_SMOOTH,
        DISABLE_SMOOTH;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static FilterType[] valuesCustom() {
            FilterType[] filterTypeArrValuesCustom = values();
            int length = filterTypeArrValuesCustom.length;
            FilterType[] filterTypeArr = new FilterType[length];
            System.arraycopy(filterTypeArrValuesCustom, 0, filterTypeArr, 0, length);
            return filterTypeArr;
        }
    }

    /* renamed from: a */
    void mo4178a(float f9);

    /* renamed from: b */
    void mo4179b(float f9);
}
