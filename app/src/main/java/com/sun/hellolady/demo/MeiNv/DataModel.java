package com.sun.hellolady.demo.MeiNv;

import java.util.List;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：3/23/2016 3:07 PM
 * 修改人：Jiamin.Sun
 * 修改时间：3/23/2016 3:07 PM
 * 修改备注：
 */
public class DataModel {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-03-06 14:11","title":"可爱少女图书馆清纯可爱写真","description":"美女图片","picUrl":"http://t1.27270.com/uploads/allimg/150626/7_0626164F02L9.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/49289.html"},{"ctime":"2016-03-06 14:11","title":"长腿美女性感比基尼海边靓丽风景线","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150619/7-150619163402132.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/49258.html"},{"ctime":"2016-03-06 14:11","title":"顶级美女内衣秀性感身姿私房照","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150710/8-150G01F314534.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/52009.html"},{"ctime":"2016-03-06 14:11","title":"宅男女神如梦诱惑私房美照","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150709/8-150F9153001461.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/52033.html"},{"ctime":"2016-03-06 14:11","title":"刘雨欣美女桌面","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/06/m.xxxiao.com_2411c2dfab27e4411a27c16f4f87dd22-760x500.jpg","url":"http://m.xxxiao.com/1811"},{"ctime":"2016-03-06 14:11","title":"韩国性感车模制服展场靓丽图片","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_8000d44d92b4ea5aba46b13bd938e0e48-760x500.jpg","url":"http://m.xxxiao.com/892"},{"ctime":"2016-03-06 14:11","title":"美艳少妇蕾丝睡裙魅惑照","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150714/8-150G4114134593.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/49164.html"},{"ctime":"2016-03-06 14:11","title":"性感姐妹花露白皙肌肤亲密无间","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150715/8-150G514410CN.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/51953.html"},{"ctime":"2016-03-06 14:11","title":"推女神 制服百变秀 于姬Una","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/06/m.xxxiao.com_c553709f472ec054ad91d637e2ba7857-760x500.jpg","url":"http://m.xxxiao.com/1701"},{"ctime":"2016-03-06 14:11","title":"柔弱美丽女神性感诱惑纱衣高清图","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150717/9-150GG62410923.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/51941.html"}]
     */

    private int code;
    private String msg;
    /**
     * ctime : 2016-03-06 14:11
     * title : 可爱少女图书馆清纯可爱写真
     * description : 美女图片
     * picUrl : http://t1.27270.com/uploads/allimg/150626/7_0626164F02L9.jpg
     * url : http://www.27270.com/ent/meinvtupian/2015/49289.html
     */

    private List<NewslistEntity> newslist;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setNewslist(List<NewslistEntity> newslist) {
        this.newslist = newslist;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<NewslistEntity> getNewslist() {
        return newslist;
    }

    public static class NewslistEntity {
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCtime() {
            return ctime;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public String getUrl() {
            return url;
        }
    }
}
