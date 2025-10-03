package com.cyberlink.clgpuimage;

import android.graphics.PointF;

/* loaded from: classes.dex */
public class CLMakeupLiveEyeFilter$LiveEyeMakeupMetadata {
    int m_analyzing_frame_height;
    int m_analyzing_frame_width;
    public float m_environment_brightest_reference_normalized_luma;
    public float m_environment_darkest_reference_normalized_luma;
    PointF[] m_eye_points = new PointF[4];
    public boolean m_is_flipped;
    public EyeMode m_mode;
    PointF[] m_oriented_eye_centers;
    PointF[] m_oriented_eye_points;
    float[] m_parabolic_polar_transform_bottom_left_dst_aligned_coeff;
    PointF m_parabolic_polar_transform_bottom_left_dst_center;
    float[] m_parabolic_polar_transform_bottom_left_src_aligned_coeff;
    PointF m_parabolic_polar_transform_bottom_left_src_center;
    float[] m_parabolic_polar_transform_bottom_right_dst_aligned_coeff;
    PointF m_parabolic_polar_transform_bottom_right_dst_center;
    float[] m_parabolic_polar_transform_bottom_right_src_aligned_coeff;
    PointF m_parabolic_polar_transform_bottom_right_src_center;
    float[] m_parabolic_polar_transform_top_left_dst_aligned_coeff;
    PointF m_parabolic_polar_transform_top_left_dst_center;
    float[] m_parabolic_polar_transform_top_left_src_aligned_coeff;
    PointF m_parabolic_polar_transform_top_left_src_center;
    float[] m_parabolic_polar_transform_top_right_dst_aligned_coeff;
    PointF m_parabolic_polar_transform_top_right_dst_center;
    float[] m_parabolic_polar_transform_top_right_src_aligned_coeff;
    PointF m_parabolic_polar_transform_top_right_src_center;
    public float m_ratio_of_actual_lower_lid_height_to_limited_height;
    public float m_ratio_of_actual_upper_lid_height_to_limited_height;
    public int m_rotation;
    float m_target_eye_lower_lid_luma;
    float m_target_level_orientation_cos;
    float m_target_level_orientation_sin;

    public enum EyeMode {
        NORMAL,
        BLINK;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static EyeMode[] valuesCustom() {
            EyeMode[] eyeModeArrValuesCustom = values();
            int length = eyeModeArrValuesCustom.length;
            EyeMode[] eyeModeArr = new EyeMode[length];
            System.arraycopy(eyeModeArrValuesCustom, 0, eyeModeArr, 0, length);
            return eyeModeArr;
        }
    }

    public CLMakeupLiveEyeFilter$LiveEyeMakeupMetadata() {
        for (int i9 = 0; i9 < 4; i9++) {
            this.m_eye_points[i9] = new PointF();
        }
        this.m_oriented_eye_points = new PointF[4];
        for (int i10 = 0; i10 < 4; i10++) {
            this.m_oriented_eye_points[i10] = new PointF();
        }
        this.m_oriented_eye_centers = new PointF[2];
        for (int i11 = 0; i11 < 2; i11++) {
            this.m_oriented_eye_centers[i11] = new PointF();
        }
        this.m_parabolic_polar_transform_top_left_src_center = new PointF();
        this.m_parabolic_polar_transform_top_left_dst_center = new PointF();
        this.m_parabolic_polar_transform_top_left_src_aligned_coeff = new float[2];
        this.m_parabolic_polar_transform_top_left_dst_aligned_coeff = new float[2];
        this.m_parabolic_polar_transform_top_right_src_center = new PointF();
        this.m_parabolic_polar_transform_top_right_dst_center = new PointF();
        this.m_parabolic_polar_transform_top_right_src_aligned_coeff = new float[2];
        this.m_parabolic_polar_transform_top_right_dst_aligned_coeff = new float[2];
        this.m_parabolic_polar_transform_bottom_left_src_center = new PointF();
        this.m_parabolic_polar_transform_bottom_left_dst_center = new PointF();
        this.m_parabolic_polar_transform_bottom_left_src_aligned_coeff = new float[2];
        this.m_parabolic_polar_transform_bottom_left_dst_aligned_coeff = new float[2];
        this.m_parabolic_polar_transform_bottom_right_src_center = new PointF();
        this.m_parabolic_polar_transform_bottom_right_dst_center = new PointF();
        this.m_parabolic_polar_transform_bottom_right_src_aligned_coeff = new float[2];
        this.m_parabolic_polar_transform_bottom_right_dst_aligned_coeff = new float[2];
    }

    public void Copy(CLMakeupLiveEyeFilter$LiveEyeMakeupMetadata cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata) {
        this.m_target_eye_lower_lid_luma = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_target_eye_lower_lid_luma;
        this.m_analyzing_frame_width = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_analyzing_frame_width;
        this.m_analyzing_frame_height = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_analyzing_frame_height;
        this.m_target_level_orientation_cos = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_target_level_orientation_cos;
        this.m_target_level_orientation_sin = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_target_level_orientation_sin;
        for (int i9 = 0; i9 < 4; i9++) {
            PointF pointF = this.m_eye_points[i9];
            PointF pointF2 = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_eye_points[i9];
            pointF.x = pointF2.x;
            pointF.y = pointF2.y;
            PointF pointF3 = this.m_oriented_eye_points[i9];
            PointF pointF4 = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_oriented_eye_points[i9];
            pointF3.x = pointF4.x;
            pointF3.y = pointF4.y;
        }
        for (int i10 = 0; i10 < 2; i10++) {
            PointF pointF5 = this.m_oriented_eye_centers[i10];
            PointF pointF6 = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_oriented_eye_centers[i10];
            pointF5.x = pointF6.x;
            pointF5.y = pointF6.y;
        }
        PointF pointF7 = this.m_parabolic_polar_transform_top_left_src_center;
        PointF pointF8 = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_top_left_src_center;
        pointF7.x = pointF8.x;
        pointF7.y = pointF8.y;
        PointF pointF9 = this.m_parabolic_polar_transform_top_left_dst_center;
        PointF pointF10 = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_top_left_dst_center;
        pointF9.x = pointF10.x;
        pointF9.y = pointF10.y;
        for (int i11 = 0; i11 < 2; i11++) {
            this.m_parabolic_polar_transform_top_left_src_aligned_coeff[i11] = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_top_left_src_aligned_coeff[i11];
            this.m_parabolic_polar_transform_top_left_dst_aligned_coeff[i11] = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_top_left_dst_aligned_coeff[i11];
        }
        PointF pointF11 = this.m_parabolic_polar_transform_top_right_src_center;
        PointF pointF12 = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_top_right_src_center;
        pointF11.x = pointF12.x;
        pointF11.y = pointF12.y;
        PointF pointF13 = this.m_parabolic_polar_transform_top_right_dst_center;
        PointF pointF14 = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_top_right_dst_center;
        pointF13.x = pointF14.x;
        pointF13.y = pointF14.y;
        for (int i12 = 0; i12 < 2; i12++) {
            this.m_parabolic_polar_transform_top_right_src_aligned_coeff[i12] = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_top_right_src_aligned_coeff[i12];
            this.m_parabolic_polar_transform_top_right_dst_aligned_coeff[i12] = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_top_right_dst_aligned_coeff[i12];
        }
        PointF pointF15 = this.m_parabolic_polar_transform_bottom_left_src_center;
        PointF pointF16 = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_bottom_left_src_center;
        pointF15.x = pointF16.x;
        pointF15.y = pointF16.y;
        PointF pointF17 = this.m_parabolic_polar_transform_bottom_left_dst_center;
        PointF pointF18 = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_bottom_left_dst_center;
        pointF17.x = pointF18.x;
        pointF17.y = pointF18.y;
        for (int i13 = 0; i13 < 2; i13++) {
            this.m_parabolic_polar_transform_bottom_left_src_aligned_coeff[i13] = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_bottom_left_src_aligned_coeff[i13];
            this.m_parabolic_polar_transform_bottom_left_dst_aligned_coeff[i13] = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_bottom_left_dst_aligned_coeff[i13];
        }
        PointF pointF19 = this.m_parabolic_polar_transform_bottom_right_src_center;
        PointF pointF20 = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_bottom_right_src_center;
        pointF19.x = pointF20.x;
        pointF19.y = pointF20.y;
        PointF pointF21 = this.m_parabolic_polar_transform_bottom_right_dst_center;
        PointF pointF22 = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_bottom_right_dst_center;
        pointF21.x = pointF22.x;
        pointF21.y = pointF22.y;
        for (int i14 = 0; i14 < 2; i14++) {
            this.m_parabolic_polar_transform_bottom_right_src_aligned_coeff[i14] = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_bottom_right_src_aligned_coeff[i14];
            this.m_parabolic_polar_transform_bottom_right_dst_aligned_coeff[i14] = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_parabolic_polar_transform_bottom_right_dst_aligned_coeff[i14];
        }
        this.m_environment_darkest_reference_normalized_luma = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_environment_darkest_reference_normalized_luma;
        this.m_environment_brightest_reference_normalized_luma = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_environment_brightest_reference_normalized_luma;
        this.m_rotation = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_rotation;
        this.m_is_flipped = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_is_flipped;
        this.m_mode = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_mode;
        this.m_ratio_of_actual_upper_lid_height_to_limited_height = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_ratio_of_actual_upper_lid_height_to_limited_height;
        this.m_ratio_of_actual_lower_lid_height_to_limited_height = cLMakeupLiveEyeFilter$LiveEyeMakeupMetadata.m_ratio_of_actual_lower_lid_height_to_limited_height;
    }
}
