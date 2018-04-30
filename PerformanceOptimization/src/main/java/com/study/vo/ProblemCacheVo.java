package com.study.vo;

import java.io.Serializable;

/**
 * 
 * 创建日期：2018/4/12
 * 创建时间: 20:57
 * 题目保存在缓存中的实体类
 */
public class ProblemCacheVo implements Serializable{

    private String processedContent;
    private String problemSha;

    public ProblemCacheVo() {
    }

    public ProblemCacheVo(String processedContent, String problemSha) {
        this.processedContent = processedContent;
        this.problemSha = problemSha;
    }

    public String getProcessedContent() {
        return processedContent;
    }

    public void setProcessedContent(String processedContent) {
        this.processedContent = processedContent;
    }

    public String getProblemSha() {
        return problemSha;
    }

    public void setProblemSha(String problemSha) {
        this.problemSha = problemSha;
    }
}
