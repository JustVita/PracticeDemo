package com.meijian.qp.qprxjava.datas;


import java.util.List;

/**
 * Created by QP on 2018/5/23.
 */

public class YDTranslation {

    private String type;
    private int errorCode;
    private int elapsedTime;
    private List<List<YDTranslateResultBean>> translateResult;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public List<List<YDTranslateResultBean>> getTranslateResult() {
        return translateResult;
    }

    public void setTranslateResult(List<List<YDTranslateResultBean>> translateResult) {
        this.translateResult = translateResult;
    }
    
    public static class YDTranslateResultBean{

        public String src;
        public String tgt;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getTgt() {
            return tgt;
        }

        public void setTgt(String tgt) {
            this.tgt = tgt;
        }
    }
}
