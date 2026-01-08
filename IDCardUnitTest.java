package top.olws.idcarddetect;

import android.database.sqlite.SQLiteDatabase;

import com.alibaba.fastjson.JSON;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.TreeMap;

import top.olws.idcarddetect.Client.Client;
import top.olws.idcarddetect.Controller.DataController;
import top.olws.idcarddetect.POJO.IdCard;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class IDCardUnitTest {
    /**
     * 测试身份张号识别函数
     */
    @Test
    public void testRecognizeIDCard() {
        // 图片路径
        String imgPath = "E:\\AndroidStudioProjects\\IDCardDetect\\app\\src\\test\\java\\top\\olws\\idcarddetect\\sfz.jpg";
        // 调用身份证识别函数
        IdCard idCardInfo = Client.recognizeResult(imgPath);
        // 预期结果
        String info = "IdCard{name='代用名', sex='男', ethnicity='汉', birthDate='2013年5月6日', address='湖南省长沙市开福区巡道街幸福小区居民组', idNumber='430512198908131367'}";
        // 比较结果
        Assert.assertEquals(idCardInfo.toString(), info);
    }


    /**
     * 测试数据提取并且转换
     */
    @Test
    public void testDataConvert(){
        String jsonString = "{\"algo_version\":\"48f3e265513a79d5f9bc26f0c010476bbd856b2d\",\"data\":{\"face\":{\"algo_version\":\"48f3e265513a79d5f9bc26f0c010476bbd856b2d\",\"angle\":0,\"data\":{\"address\":\"河南省封丘县应举镇阳武里村289号\",\"birthDate\":\"2001年5月8日\",\"ethnicity\":\"汉\",\"idNumber\":\"410727200105082673\",\"name\":\"李洪博\",\"sex\":\"男\"},\"ftype\":0,\"height\":1280,\"orgHeight\":1280,\"orgWidth\":2016,\"prism_keyValueInfo\":[{\"key\":\"name\",\"keyProb\":100,\"value\":\"李洪博\",\"valuePos\":[{\"x\":378,\"y\":175},{\"x\":653,\"y\":173},{\"x\":653,\"y\":268},{\"x\":378,\"y\":270}],\"valueProb\":100},{\"key\":\"sex\",\"keyProb\":100,\"value\":\"男\",\"valuePos\":[{\"x\":377,\"y\":352},{\"x\":446,\"y\":352},{\"x\":446,\"y\":428},{\"x\":377,\"y\":428}],\"valueProb\":100},{\"key\":\"ethnicity\",\"keyProb\":100,\"value\":\"汉\",\"valuePos\":[{\"x\":787,\"y\":354},{\"x\":862,\"y\":352},{\"x\":863,\"y\":424},{\"x\":787,\"y\":425}],\"valueProb\":100},{\"key\":\"birthDate\",\"keyProb\":100,\"value\":\"2001年5月8日\",\"valuePos\":[{\"x\":377,\"y\":510},{\"x\":940,\"y\":510},{\"x\":940,\"y\":572},{\"x\":377,\"y\":572}],\"valueProb\":100},{\"key\":\"address\",\"keyProb\":100,\"value\":\"河南省封丘县应举镇阳武里村289号\",\"valuePos\":[{\"x\":376,\"y\":671},{\"x\":1185,\"y\":666},{\"x\":1187,\"y\":839},{\"x\":377,\"y\":844}],\"valueProb\":100},{\"key\":\"idNumber\",\"keyProb\":100,\"value\":\"410727200105082673\",\"valuePos\":[{\"x\":703,\"y\":1065},{\"x\":1760,\"y\":1049},{\"x\":1761,\"y\":1120},{\"x\":705,\"y\":1137}],\"valueProb\":100}],\"sliceRect\":{\"x0\":0,\"x1\":2016,\"x2\":2014,\"x3\":20,\"y0\":0,\"y1\":6,\"y2\":1260,\"y3\":1280},\"width\":2016}},\"height\":1280,\"orgHeight\":1280,\"orgWidth\":2016,\"width\":2016}";
        int begin = jsonString.lastIndexOf("data");
        int last = jsonString.substring(begin).indexOf("}") + 1;
        String newjson = jsonString.substring(begin).substring(6,last);
        String info = "IdCard{name='李洪博', sex='男', ethnicity='汉', birthDate='2001年5月8日', address='河南省封丘县应举镇阳武里村289号', idNumber='410727200105082673'}";

        IdCard idCard = JSON.parseObject(newjson, IdCard.class);
        Assert.assertEquals(idCard.toString(), info);

        System.out.println(idCard.toString());
        System.out.println(idCard.getName());
        System.out.println(idCard.getSex());
        System.out.println(idCard.getEthnicity());
        System.out.println(idCard.getAddress());
        System.out.println(idCard.getBirthDate());
        System.out.println(idCard.getIdNumber());
    }


/*    @Test
    public void testSelectById(){
        String id = "430512198908131367";
        DataController dc = new DataController();
        boolean byId = dc.findById(id);
        Assert.assertTrue(byId);
    }*/
