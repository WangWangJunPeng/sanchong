package com.sc.tradmaster.utils.qcloud.cos.request;

import com.sc.tradmaster.utils.qcloud.cos.common_utils.CommonParamCheckUtils;
import com.sc.tradmaster.utils.qcloud.cos.exception.ParamException;

public class ListPartsRequest extends AbstractBaseRequest {

    public ListPartsRequest(String bucketName, String cosPath) {
        super(bucketName, cosPath);
    }

    @Override
    public void check_param() throws ParamException {
        super.check_param();
        CommonParamCheckUtils.AssertLegalCosFilePath(this.getCosPath());
    }

}
