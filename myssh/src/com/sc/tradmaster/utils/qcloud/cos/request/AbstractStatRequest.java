package com.sc.tradmaster.utils.qcloud.cos.request;

/**
 * @author chengwu
 * 获取文件属性信息
 */
public class AbstractStatRequest extends AbstractBaseRequest {

    public AbstractStatRequest(String bucketName, String cosPath) {
        super(bucketName, cosPath);
    }
    
}
