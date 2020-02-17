package com.study.pachong;

import net.sf.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiderKugou {

    public static String filePath = "E:/music/";//存放的路径
    public static String mp3 = "https://www.kugou.com/yy/index.php?r=play/getdata&hash=HASH";//访问mp3的地址数据
    public static String LINK = "https://www.kugou.com/yy/rank/home/PAGE-8888.html?from=rank";//酷狗分页目录
    public static List<Map<String,String>> musicUrlList = new ArrayList<>();//测试存放类

    public static void main(String[] args) throws IOException {
        for(int i = 1 ; i < 23; i++){
            String url = LINK.replace("PAGE", i + "");
            getTitle(url);
        }
        FileDownload down = new FileDownload();

        for (int i = 0; i <musicUrlList.size() ; i++) {
            Map<String, String> map = musicUrlList.get(i);
            System.out.println(map.get("musicName"));
            down.download(map.get("playUrl"), map.get("musicName"));
        }

    }

    public static String getTitle(String url) throws IOException{
        HttpGetConnect connect = new HttpGetConnect();
        String content = connect.connect(url, "utf-8");
        HtmlManage html = new HtmlManage();
        Document doc = html.manage(content);//转换成Doc对象
        Element ele = doc.getElementsByClass("pc_temp_songlist").get(0);
        Elements eles = ele.getElementsByTag("li");
        for(int i = 0 ; i < eles.size() ; i++){
            Element item = eles.get(i);
            String title = item.attr("title").trim();
            String link = item.getElementsByTag("a").first().attr("href");

            download(link,title);
        }
        return null;
    }

    public static void download(String url,String name) throws IOException{
        String hash = "";
        HttpGetConnect connect = new HttpGetConnect();
        String content = connect.connect(url, "utf-8");
        String regEx = "\"hash\":\"[0-9A-Z]+\"";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            hash = matcher.group();
            hash = hash.replace("\"hash\":\"", "");
            hash = hash.replace("\"", "");
        }
        String item = mp3.replace("HASH", hash);
        String mp = connect.getReturn(item,"utf-8");
        JSONObject json = JSONObject.fromObject(mp);
        String err_code = String.valueOf(json.get("err_code"));
        if (!"30020".equals(err_code)){
            String playUrl = json.getJSONObject("data").getString("play_url");
            final Map<String,String> map = new HashMap<>();
            map.put("playUrl",playUrl);
            map.put("musicName",filePath + name + ".mp3");
            musicUrlList.add(map);
            System.out.println("获取成功："+name+ ".mp3");
        }else{
            System.out.println("获取失败");
        }
    }

}
