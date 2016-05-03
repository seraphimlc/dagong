package com.dagong.mq.apply;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.dagong.mq.MessageProcessor;
import com.dagong.pojo.Company;
import com.dagong.pojo.Job;
import com.dagong.service.CompanyService;
import com.dagong.service.JobService;
import sun.plugin2.message.Message;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by liuchang on 16/5/2.
 */
@Resource
public class ApplyJobMessageProcessor extends MessageProcessor{
    @Resource
    private JobService jobService;
    @Resource
    private CompanyService companyService;

    public ApplyJobMessageProcessor() {
        this.setTopic("apply");
        this.setTag("userApplyJob");
    }

    @Override
    protected void process(List<MessageExt> list) {
        list.forEach(messageExt -> {
            try {
                String body =new String(messageExt.getBody(),"UTF-8");
                Map<String,String> map= JSON.parse(body,Map.class);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

    }
}