package com.itheima.com.itheima.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.itheima.dao.DemoDAO;
import com.itheima.listener.DemoDataListener;
import com.itheima.poji.DemoData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class EasyExcelTest {


    /**
     *读操作
     */
    @Test
    public void testReadExcel() {

        String fileName = "C:\\Users\\yxkf\\Desktop\\test.xls";
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName,DemoData.class,new PageReadListener<DemoData>(dataList->{
            for (DemoData demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).sheet().doRead();
    }

    @Test
    public void testReadExcel2() {

        String fileName = "C:\\Users\\yxkf\\Desktop\\test.xls";

        EasyExcel.read(fileName,DemoData.class,new ReadListener<DemoData>(){
            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 100;
            /**
             *临时存储
             */
            private List<DemoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
            //备注，自己利用匿名内部类写，不然就是自己写监听器
            @Override
            public void invoke(DemoData demoData, AnalysisContext analysisContext) {
                cachedDataList.add(demoData);
                if (cachedDataList.size() >= BATCH_COUNT) {
                   saveData();
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    saveData();
            }

            /**
             * 加上存储数据库
             */
            private void saveData() {
                log.info("{}条数据，开始存储数据库！", cachedDataList.size());
                //这里可以插入插入的数据库DAO代码
                for (DemoData demoData : cachedDataList) {
                    log.info("读取到一条数据{}", JSON.toJSONString(demoData));
                }
                log.info("存储数据库成功！");
            }
        }).sheet().doRead();
    }


    /**多表读取*/
    @Test
    public void testReadExcel3() {
        String fileName = "C:\\Users\\yxkf\\Desktop\\test.xls";
        // 读取全部sheet
        // 这里需要注意 DemoDataListener的doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
        EasyExcel.read(fileName,DemoData.class,new DemoDataListener()).doReadAll();

    }


}
