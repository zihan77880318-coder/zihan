package top.olws.idcarddetect;

import android.database.sqlite.SQLiteDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SmallTest
class DatabaseUnitTest {
    private lateinit var db: SQLiteDatabase
    private lateinit var dbHelper: DatabaseHelper

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>();
        dbHelper = DatabaseHelper(context);
        db = dbHelper.writableDatabase;
    }

    @Test
    fun testInsertData() {
        // 执行数据库插入操作
        // 使用断言来验证插入是否成功
    }

    @Test
    fun testQueryData() {
        // 执行数据库查询操作
        // 使用断言来验证查询结果是否符合预期
    }

    @After
    fun cleanup() {
        db.close();
        dbHelper.close();
    }
}
