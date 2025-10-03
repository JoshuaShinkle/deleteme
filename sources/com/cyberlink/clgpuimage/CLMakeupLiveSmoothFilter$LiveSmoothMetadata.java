package com.cyberlink.clgpuimage;

/* loaded from: classes.dex */
public class CLMakeupLiveSmoothFilter$LiveSmoothMetadata {
    public float m_environment_brightest_reference_normalized_luma;
    public float m_environment_darkest_reference_normalized_luma;
    public float m_intensity;

    public void Copy(CLMakeupLiveSmoothFilter$LiveSmoothMetadata cLMakeupLiveSmoothFilter$LiveSmoothMetadata) {
        this.m_environment_darkest_reference_normalized_luma = cLMakeupLiveSmoothFilter$LiveSmoothMetadata.m_environment_darkest_reference_normalized_luma;
        this.m_environment_brightest_reference_normalized_luma = cLMakeupLiveSmoothFilter$LiveSmoothMetadata.m_environment_brightest_reference_normalized_luma;
        this.m_intensity = cLMakeupLiveSmoothFilter$LiveSmoothMetadata.m_intensity;
    }
}
