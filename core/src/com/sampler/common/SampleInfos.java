package com.sampler.common;

import com.sampler.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleInfos {

    public static final List<SampleInfo> ALL  =Arrays.asList(
            ApplicationListenerSample.SAMPLE_INFO,
            GdxGeneratedSample.SAMPLE_INFO,
            GdxReflectionSample.SAMPLE_INFO,
            InputListeningSample.SAMPLE_INFO,
            InputPollingSample.SAMPLE_INFO,
            ModuleInfoSample.SAMPLE_INFO,
            OrthographicCameraSample.SAMPLE_INFO,
            ViewportSample.SAMPLE_INFO,
            SpritebatchSample.SAMPLE_INFO,
            ShapeRenderSample.SAMPLE_INFO

    );


    public static final List<String> getSampleNames(){
        List<String> ret = new ArrayList<>();

        for (SampleInfo info: ALL) {
            ret.add(info.getName());
            
        }
        Collections.sort(ret);

        return ret;
    }

    public static SampleInfo find(String name){
        SampleInfo ret = null;
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name argument is required");
        }

        for(SampleInfo info : ALL){
            if(info.getName().equals(name)){
                ret = info;
                break;
            }
        }
        if (ret == null){
            throw new IllegalArgumentException("couldn't find sample with name = " +name);
        }

        return ret;
    }

    private SampleInfos() {
    }
}
