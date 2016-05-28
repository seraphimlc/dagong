import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.dagong.mapper.JobMapper;
import com.dagong.mapper.JobTypeMapper;
import com.dagong.mapper.UserMapper;
import com.dagong.pojo.Company;
import com.dagong.pojo.DegreeType;
import com.dagong.pojo.Job;
import com.dagong.pojo.JobType;
import com.dagong.service.CompanyService;
import com.dagong.service.JobService;
import com.dagong.util.IdGenerator;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.io.Files;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuchang on 16/1/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/resources/base/all.xml")
public class TestDagong {
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private JobTypeMapper jobTypeMapper;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IdGenerator idGenerator;

    private Map<String, JobType> jobTypeMap = new HashMap<>();

    private Map<String, Company> companyMap = new HashMap<>();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    public void test() throws Exception {
//        Job job = jobService.getJob(130 + "");
//        System.out.println("job = " + job);
//        Company company = companyService.getCompanyByName("����");
//        System.out.println("company = " + company);
////      companyService.createCompany(new String("����"), "");
//        System.out.println("\"����\" = " + "����");
//        readFromExcel();
//        testSendValidateCode();
//        testRegister();
//        generateJobType();
//        for (int i = 0; i < 1000; i++) {
//            System.out.println("idGenerator = " + idGenerator.generate(User.class.getSimpleName()));
//        }
//        Job job = jobService.getJob("140");
//        System.out.println("job = " + job);
    }

    private void readFromExcel() throws IOException {
        jobTypeMapper.selectAll().forEach(jobType -> {
            jobTypeMap.put(jobType.getName(), jobType);
        });
        HSSFWorkbook book = getExcelBook();
        HSSFSheet sheet = book.getSheetAt(0);
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            HSSFRow row = sheet.getRow(i);
            analyzeRow(row);
        }
    }

    private void analyzeRow(HSSFRow row) throws UnsupportedEncodingException {

        Job job = new Job();

        String value = null;
        job.setJobType(52);

        job.setNeedNumber(Integer.parseInt(getValue(row, 1)));

        value = getValue(row, 2);
        String property[] = value.split("-");
        if (property != null && property.length == 2) {
            job.setStartSalary(Integer.parseInt(property[0]));
            job.setEndSalary(Integer.parseInt(property[1]));
        }

        job.setDegree(DegreeType.valueOf(getValue(row, 3)).getValue());
        job.setWorkedYear(getValue(row, 4));

        value = getValue(row, 5);
        job.setWelfare(value);

        job.setAddress(getValue(row, 6));

        value = getValue(row, 7);
        Company company = companyMap.get(value);
//        if (company == null) {
//            company = companyService.getCompanyByName(value);
//            if (company == null) {
//                company = companyService.createCompany(value, "");
//            }
//            companyMap.put(company.getName(), company);
//        }
        job.setCompanyId(company.getId());
        job.setJobName(getValue(row, 8));
        job.setContractor(getValue(row, 9));
        job.setPhoneNumber(getValue(row, 10));
        job.setDetail(getValue(row, 11));
        job.setId(idGenerator.generate(Job.class.getSimpleName()));
        job.setCreateTime(new Date());
        job.setModifyTime(new Date());
        job.setStatus(1);
//        jobService.createJob(job,"lc");

    }

    private String getValue(HSSFRow row, int column) throws UnsupportedEncodingException {
        if (row.getPhysicalNumberOfCells() <= column) {
            return null;
        }
        HSSFCell cell = row.getCell(column);

        String value = getValueFromCell(cell);

        return filter(value);
    }

    private String filter(String value) throws UnsupportedEncodingException {
        return Joiner.on(",").skipNulls().join(Splitter
                .onPattern("[\n|,]")
                .omitEmptyStrings().trimResults().split(value));
    }

    private String getValueFromCell(HSSFCell cell) {
        String value = null;
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_FORMULA:
                value = "" + cell.getCellFormula();
                break;

            case HSSFCell.CELL_TYPE_NUMERIC:
                value = "" + cell.getNumericCellValue();
                break;

            case HSSFCell.CELL_TYPE_STRING:
                value = "" + cell.getStringCellValue();
                break;

            default:
        }
        return value.trim();
    }

    private void showRow(HSSFRow row) {
        for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
            HSSFCell cell = row.getCell(j);
            String value = null;

            switch (cell.getCellType()) {

                case HSSFCell.CELL_TYPE_FORMULA:
                    value = "FORMULA value=" + cell.getCellFormula();
                    break;

                case HSSFCell.CELL_TYPE_NUMERIC:
                    value = "NUMERIC value=" + cell.getNumericCellValue();
                    break;

                case HSSFCell.CELL_TYPE_STRING:
                    value = "STRING value=" + cell.getStringCellValue();
                    break;

                default:
            }
            System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="
                    + value);
        }
    }

    private HSSFWorkbook getExcelBook() throws IOException {
        String filename = "/Users/liuchang/Desktop/list1.xls";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            return new HSSFWorkbook(fis);
        } finally {

            fis.close();
        }
    }

    public void generateJobType() {
        Map<Integer, JobType> map = new HashMap<>();
        List<JobType> list = jobTypeMapper.selectByParentId(new Integer(0));
        for (JobType jobType : list) {
            generateChildType(jobType);
        }

        String json = JSON.toJSONString(list);
        File tempFile = new File("/Users/liuchang/Documents/workspace/projects/dagong_page/jobType.js");
        try {


            Files.write(json.getBytes("UTF-8"), tempFile);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void generateChildType(JobType jobType) {
        List<JobType> list = jobTypeMapper.selectByParentId(jobType.getId());
        for (JobType cobType : list) {
            jobType.addChild(cobType);
            generateChildType(cobType);
        }
    }

//    @Test
//    public void testSearchJob() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/job/forUser.do").cookie(new Cookie("user", "123")))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print()).andReturn();
//
//    }


    private void testSendValidateCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/sendValidateCode.do").param("phoneNumber", "18601151003"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    private void testRegister() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/register.do")
                .param("name", "lc")
                .param("gender", "1")
                .param("birthday", "1983-10-19")
                .param("phoneNumber", "18601151003")
                .param("validateCode", "2910")
                .param("cardId", "210922198310190931")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    //    @Test
    public void testJob() {
        Job job = jobMapper.selectByPrimaryKey("1");
        System.out.println("job = " + job);
        for (int i = 100; i < 500; i++) {
            job.setId(i + 2 + "");
            job.setJobType(487 - i % 5);
            jobMapper.insert(job);
        }

    }
@Test
    public void testSearch() throws UnknownHostException {
        List<Job> jobs = jobMapper.listJob(new Job());
        System.out.println("list = " + jobs.size());
//        List<Job> jobs = jobService.listJob("241", 1);
        TransportClient transportClient = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.16.54.144"), 9300));
        BulkRequestBuilder bulkRequestBuilder = transportClient.prepareBulk();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        jobs.forEach(job -> {
            job.setJobType(51);
            bulkRequestBuilder.add(transportClient.prepareIndex("test", "job", job.getId()+atomicInteger.incrementAndGet()).setSource(JSON.toJSONString(job)));

            job.setJobType(53);
            bulkRequestBuilder.add(transportClient.prepareIndex("test", "job", job.getId()+atomicInteger.incrementAndGet()).setSource(JSON.toJSONString(job)));

            job.setJobType(54);
            bulkRequestBuilder.add(transportClient.prepareIndex("test", "job", job.getId()+atomicInteger.incrementAndGet()).setSource(JSON.toJSONString(job)));

            job.setJobType(55);
            bulkRequestBuilder.add(transportClient.prepareIndex("test", "job", job.getId()+atomicInteger.incrementAndGet()).setSource(JSON.toJSONString(job)));

            job.setJobType(56);
            bulkRequestBuilder.add(transportClient.prepareIndex("test", "job", job.getId()+atomicInteger.incrementAndGet()).setSource(JSON.toJSONString(job)));

            job.setJobType(57);
            bulkRequestBuilder.add(transportClient.prepareIndex("test", "job", job.getId()+atomicInteger.incrementAndGet()).setSource(JSON.toJSONString(job)));
        });
        BulkResponse bulkItemResponses = bulkRequestBuilder.execute().actionGet();
        bulkItemResponses.forEach(bulkItemResponse -> {
            System.out.println("fail = " + bulkItemResponse.getFailure());
            System.out.println("bulkItemResponse.getFailureMessage() = " + bulkItemResponse.getFailureMessage());
        });
//        bulkRequestBuilder.add(transportClient.prepareIndex("test", "job", job.getId()).setSource(JSON.toJSONString(job)))
//        Job job = jobMapper.selectByPrimaryKey("111");
//        transportClient.prepareIndex("test", "job", job.getId()).setSource(JSON.toJSONString(job)).execute().actionGet();
//
//        GetResponse response = transportClient.prepareGet("test", "job", "140").execute().actionGet();
//        String string = response.getSourceAsString();
//        Job newJob = JSON.parseObject(string,Job.class);
//        System.out.println("string = " + string);
//        System.out.println("newJob = " + newJob);
        transportClient.close();
    }

    //@Test
    public void search() throws UnknownHostException {
        TransportClient transportClient = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.16.54.144"), 9300));

        SearchResponse searchResponse = transportClient.prepareSearch("test").setTypes("job")
                .setQuery(QueryBuilders.termQuery("jobType", "52")).execute().actionGet();
        System.out.println("searchResponse = " + searchResponse);
    }

    @Test
    public void testMQ() throws InterruptedException {

//    testReceiveMessage();
        testSendMessage();
        Thread.sleep(1000 * 1000);
    }

    public void testSendMessage() {
        DefaultMQProducer producer = new DefaultMQProducer("TestJobProducer");
        producer.setNamesrvAddr("172.16.54.144:9876");
        producer.setVipChannelEnabled(false);
        try {
            producer.start();
            for (int i = 0; i < 100; i++) {

                Message message = new Message("job", i % 2 == 0 ? "createJob" : "followJob", i + "", "justforTEST".getBytes("UTF-8"));
                SendResult sendResult = producer.send(message);
                System.out.println("sendResult.getMsgId() = " + sendResult.getMsgId());
                System.out.println("sendResult = " + sendResult.getSendStatus());
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }

    public void testReceiveMessage() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PushConsumer");
        consumer.setNamesrvAddr("172.16.54.144:9876");

        try {
            consumer.subscribe("Job", "publishJob");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                    list.forEach(messageExt -> {
//                        System.out.println("messageExt = " + messageExt);
//                    });
                    for (MessageExt messageExt : list) {
                        System.out.println("messageExt = " + messageExt);
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
//            Thread.sleep(1000*60);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

}
