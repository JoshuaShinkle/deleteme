package com.cyberlink.clgpuimage;

/* loaded from: classes.dex */
public class CLMakeupLiveEyebrowFilter$EyebrowData {
    public int m_background_image_height;
    public int m_background_image_width;
    public byte[] m_eyebrow_mask_data;
    public int m_mask_height;
    public int m_mask_width;
    public int m_roi_height;
    public int m_roi_width;
    public int m_roi_x;
    public int m_roi_y;
    public int m_rotation;

    public void AllocByteArray(int i9, int i10) {
        if (this.m_mask_width == i9 && this.m_mask_height == i10) {
            return;
        }
        this.m_mask_width = i9;
        this.m_mask_height = i10;
        this.m_eyebrow_mask_data = new byte[i9 * i10];
    }

    public void Copy(CLMakeupLiveEyebrowFilter$EyebrowData cLMakeupLiveEyebrowFilter$EyebrowData) {
        if (cLMakeupLiveEyebrowFilter$EyebrowData.m_mask_width != 0 && cLMakeupLiveEyebrowFilter$EyebrowData.m_mask_height != 0) {
            this.m_eyebrow_mask_data = (byte[]) cLMakeupLiveEyebrowFilter$EyebrowData.m_eyebrow_mask_data.clone();
        }
        this.m_background_image_width = cLMakeupLiveEyebrowFilter$EyebrowData.m_background_image_width;
        this.m_background_image_height = cLMakeupLiveEyebrowFilter$EyebrowData.m_background_image_height;
        this.m_mask_width = cLMakeupLiveEyebrowFilter$EyebrowData.m_mask_width;
        this.m_mask_height = cLMakeupLiveEyebrowFilter$EyebrowData.m_mask_height;
        this.m_roi_x = cLMakeupLiveEyebrowFilter$EyebrowData.m_roi_x;
        this.m_roi_y = cLMakeupLiveEyebrowFilter$EyebrowData.m_roi_y;
        this.m_roi_width = cLMakeupLiveEyebrowFilter$EyebrowData.m_roi_width;
        this.m_roi_height = cLMakeupLiveEyebrowFilter$EyebrowData.m_roi_height;
        this.m_rotation = cLMakeupLiveEyebrowFilter$EyebrowData.m_rotation;
    }
}
