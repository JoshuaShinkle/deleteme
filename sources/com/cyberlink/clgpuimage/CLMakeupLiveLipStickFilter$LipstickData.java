package com.cyberlink.clgpuimage;

/* loaded from: classes.dex */
public class CLMakeupLiveLipStickFilter$LipstickData {
    public int m_background_image_height;
    public int m_background_image_width;
    public float m_environment_brightest_reference_normalized_luma;
    public float m_environment_darkest_reference_normalized_luma;
    public int m_force_bright_threshold;
    public float m_gloss_contrast_scale;
    public int m_gloss_contrast_shift;
    public boolean m_is_flipped;
    public int m_mask_height;
    public int m_mask_width;
    public byte[] m_mouth_mask_data;
    public byte[] m_reflection_data;
    public int m_roi_height;
    public int m_roi_width;
    public int m_roi_x;
    public int m_roi_y;
    public int m_rotation;
    public byte[] m_blend_weight = new byte[256];
    public byte[] m_level_map = new byte[256];

    public void AllocByteArray(int i9, int i10) {
        if (this.m_mask_width == i9 && this.m_mask_height == i10) {
            return;
        }
        this.m_mask_width = i9;
        this.m_mask_height = i10;
        int i11 = i9 * i10;
        this.m_mouth_mask_data = new byte[i11];
        this.m_reflection_data = new byte[i11];
    }

    public void Copy(CLMakeupLiveLipStickFilter$LipstickData cLMakeupLiveLipStickFilter$LipstickData) {
        if (cLMakeupLiveLipStickFilter$LipstickData.m_mask_width != 0 && cLMakeupLiveLipStickFilter$LipstickData.m_mask_height != 0) {
            this.m_mouth_mask_data = (byte[]) cLMakeupLiveLipStickFilter$LipstickData.m_mouth_mask_data.clone();
            this.m_reflection_data = (byte[]) cLMakeupLiveLipStickFilter$LipstickData.m_reflection_data.clone();
        }
        this.m_mask_width = cLMakeupLiveLipStickFilter$LipstickData.m_mask_width;
        this.m_mask_height = cLMakeupLiveLipStickFilter$LipstickData.m_mask_height;
        this.m_roi_x = cLMakeupLiveLipStickFilter$LipstickData.m_roi_x;
        this.m_roi_y = cLMakeupLiveLipStickFilter$LipstickData.m_roi_y;
        this.m_roi_width = cLMakeupLiveLipStickFilter$LipstickData.m_roi_width;
        this.m_roi_height = cLMakeupLiveLipStickFilter$LipstickData.m_roi_height;
        this.m_background_image_width = cLMakeupLiveLipStickFilter$LipstickData.m_background_image_width;
        this.m_background_image_height = cLMakeupLiveLipStickFilter$LipstickData.m_background_image_height;
        this.m_gloss_contrast_scale = cLMakeupLiveLipStickFilter$LipstickData.m_gloss_contrast_scale;
        this.m_gloss_contrast_shift = cLMakeupLiveLipStickFilter$LipstickData.m_gloss_contrast_shift;
        this.m_force_bright_threshold = cLMakeupLiveLipStickFilter$LipstickData.m_force_bright_threshold;
        this.m_blend_weight = (byte[]) cLMakeupLiveLipStickFilter$LipstickData.m_blend_weight.clone();
        this.m_level_map = (byte[]) cLMakeupLiveLipStickFilter$LipstickData.m_level_map.clone();
        this.m_environment_darkest_reference_normalized_luma = cLMakeupLiveLipStickFilter$LipstickData.m_environment_darkest_reference_normalized_luma;
        this.m_environment_brightest_reference_normalized_luma = cLMakeupLiveLipStickFilter$LipstickData.m_environment_brightest_reference_normalized_luma;
        this.m_rotation = cLMakeupLiveLipStickFilter$LipstickData.m_rotation;
        this.m_is_flipped = cLMakeupLiveLipStickFilter$LipstickData.m_is_flipped;
    }
}
