package com.sun.hellolady.demo.MeiNv;

import java.util.List;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：3/24/2016 3:55 PM
 * 修改人：Jiamin.Sun
 * 修改时间：3/24/2016 3:55 PM
 * 修改备注：
 */
public class NewImageModel {


    /**
     * col : 美女
     * tag : 全部
     * tag3 :
     * sort : 0
     * totalNum : 16851
     * startIndex : 10
     * returnNumber : 10
     *
     *  */

    private String col;
    private String tag;
    private String tag3;
    private String sort;
    private int totalNum;
    private int startIndex;
    private int returnNumber;
    /**
     * id : 15085109989
     * desc : 气质高雅美女合集1209
     * tags : ["高雅大气很有范"]
     * owner : {"userName":"greenideas","userId":"62593895","userSign":"452984832 62859743","isSelf":"0","portrait":"671b677265656e6964656173bb03","isVip":"0","isLanv":"0","isJiaju":"","isHunjia":"","orgName":"","resUrl":"","cert":"","budgetNum":"","lanvName":"","contactName":""}
     * fromPageTitle : 气质高雅美女合集1209
     * column : 美女
     * parentTag :
     * date : 2016-01-26
     * downloadUrl : http://f.hiphotos.baidu.com/image/pic/item/b21bb051f8198618d3f8fe3749ed2e738ad4e6c2.jpg
     * imageUrl : http://f.hiphotos.baidu.com/image/pic/item/b21bb051f8198618d3f8fe3749ed2e738ad4e6c2.jpg
     * imageWidth : 608
     * imageHeight : 915
     * thumbnailUrl : http://f.hiphotos.baidu.com/image/w%3D230/sign=eca68f9b942bd40742c7d4fe4b889e9c/b21bb051f8198618d3f8fe3749ed2e738ad4e6c2.jpg
     * thumbnailWidth : 230
     * thumbnailHeight : 346
     * thumbLargeWidth : 310
     * thumbLargeHeight : 466
     * thumbLargeUrl : http://f.hiphotos.baidu.com/image/w%3D310/sign=4e9ab3a8d70735fa91f048b8ae500f9f/b21bb051f8198618d3f8fe3749ed2e738ad4e6c2.jpg
     * thumbLargeTnWidth : 400
     * thumbLargeTnHeight : 601
     * thumbLargeTnUrl : http://f.hiphotos.baidu.com/image/w%3D400/sign=72a9f08436fae6cd0cb4aa613fb20f9e/b21bb051f8198618d3f8fe3749ed2e738ad4e6c2.jpg
     * siteName :
     * siteLogo :
     * siteUrl :
     * fromUrl : http://www.iyi8.com/2014/sexy_1210/2303_4.html
     * isBook : 0
     * bookId : 0
     * objUrl : http://www.iyi8.com/uploadfile/2014/1210/20141210121459767.jpg
     * shareUrl : http://f.hiphotos.baidu.com/image/s%3D550%3Bc%3Dwantu%2C8%2C95/sign=e28a0cbb1c30e924cba49c347c330d3b/b21bb051f8198618d3f8fe3749ed2e738ad4e6c2.jpg?referer=ed7875be259759ee134754fb42c0
     * setId : -1
     * albumId : 401443963
     * isAlbum : 0
     * albumName :
     * albumNum : 1
     * userId : 62593895
     * isVip : 0
     * isDapei : 0
     * dressId :
     * dressBuyLink :
     * dressPrice : 0
     * dressDiscount : 0
     * dressExtInfo :
     * dressTag :
     * dressNum : 0
     * objTag : 高雅大气很有范
     * dressImgNum : 0
     * hostName : www.iyi8.com
     * pictureId : 15085109989
     * pictureSign : 181494045f13e02d645dcbd740cc3134eef2560e
     * dataSrc :
     * contentSign : 4113154075,3063273088
     * albumDi :
     * canAlbumId :
     * albumObjNum :
     * appId :
     * photoId :
     * fromName : 0
     * fashion : null
     * title : 气质高雅美女合集1209
     */

    private List<ImgsEntity> imgs;


    public void setCol(String col) {
        this.col = col;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setReturnNumber(int returnNumber) {
        this.returnNumber = returnNumber;
    }

    public void setImgs(List<ImgsEntity> imgs) {
        this.imgs = imgs;
    }

    public String getCol() {
        return col;
    }

    public String getTag() {
        return tag;
    }

    public String getTag3() {
        return tag3;
    }

    public String getSort() {
        return sort;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getReturnNumber() {
        return returnNumber;
    }

    public List<ImgsEntity> getImgs() {
        return imgs;
    }

    public static class ImgsEntity {
        private String id;
        private String desc;
        /**
         * userName : greenideas
         * userId : 62593895
         * userSign : 452984832 62859743
         * isSelf : 0
         * portrait : 671b677265656e6964656173bb03
         * isVip : 0
         * isLanv : 0
         * isJiaju :
         * isHunjia :
         * orgName :
         * resUrl :
         * cert :
         * budgetNum :
         * lanvName :
         * contactName :
         */

        private OwnerEntity owner;
        private String fromPageTitle;
        private String column;
        private String parentTag;
        private String date;
        private String downloadUrl;
        private String imageUrl;
        private int imageWidth;
        private int imageHeight;
        private String thumbnailUrl;
        private int thumbnailWidth;
        private int thumbnailHeight;
        private int thumbLargeWidth;
        private int thumbLargeHeight;
        private String thumbLargeUrl;
        private int thumbLargeTnWidth;
        private int thumbLargeTnHeight;
        private String thumbLargeTnUrl;
        private String siteName;
        private String siteLogo;
        private String siteUrl;
        private String fromUrl;
        private String isBook;
        private String bookId;
        private String objUrl;
        private String shareUrl;
        private String setId;
        private String albumId;
        private int isAlbum;
        private String albumName;
        private int albumNum;
        private String userId;
        private int isVip;
        private int isDapei;
        private String dressId;
        private String dressBuyLink;
        private int dressPrice;
        private int dressDiscount;
        private String dressExtInfo;
        private String dressTag;
        private int dressNum;
        private String objTag;
        private int dressImgNum;
        private String hostName;
        private String pictureId;
        private String pictureSign;
        private String dataSrc;
        private String contentSign;
        private String albumDi;
        private String canAlbumId;
        private String albumObjNum;
        private String appId;
        private String photoId;
        private int fromName;
        private String fashion;
        private String title;
        private List<String> tags;

        public void setId(String id) {
            this.id = id;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setOwner(OwnerEntity owner) {
            this.owner = owner;
        }

        public void setFromPageTitle(String fromPageTitle) {
            this.fromPageTitle = fromPageTitle;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public void setParentTag(String parentTag) {
            this.parentTag = parentTag;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        public void setThumbnailWidth(int thumbnailWidth) {
            this.thumbnailWidth = thumbnailWidth;
        }

        public void setThumbnailHeight(int thumbnailHeight) {
            this.thumbnailHeight = thumbnailHeight;
        }

        public void setThumbLargeWidth(int thumbLargeWidth) {
            this.thumbLargeWidth = thumbLargeWidth;
        }

        public void setThumbLargeHeight(int thumbLargeHeight) {
            this.thumbLargeHeight = thumbLargeHeight;
        }

        public void setThumbLargeUrl(String thumbLargeUrl) {
            this.thumbLargeUrl = thumbLargeUrl;
        }

        public void setThumbLargeTnWidth(int thumbLargeTnWidth) {
            this.thumbLargeTnWidth = thumbLargeTnWidth;
        }

        public void setThumbLargeTnHeight(int thumbLargeTnHeight) {
            this.thumbLargeTnHeight = thumbLargeTnHeight;
        }

        public void setThumbLargeTnUrl(String thumbLargeTnUrl) {
            this.thumbLargeTnUrl = thumbLargeTnUrl;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public void setSiteLogo(String siteLogo) {
            this.siteLogo = siteLogo;
        }

        public void setSiteUrl(String siteUrl) {
            this.siteUrl = siteUrl;
        }

        public void setFromUrl(String fromUrl) {
            this.fromUrl = fromUrl;
        }

        public void setIsBook(String isBook) {
            this.isBook = isBook;
        }

        public void setBookId(String bookId) {
            this.bookId = bookId;
        }

        public void setObjUrl(String objUrl) {
            this.objUrl = objUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public void setSetId(String setId) {
            this.setId = setId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

        public void setIsAlbum(int isAlbum) {
            this.isAlbum = isAlbum;
        }

        public void setAlbumName(String albumName) {
            this.albumName = albumName;
        }

        public void setAlbumNum(int albumNum) {
            this.albumNum = albumNum;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setIsVip(int isVip) {
            this.isVip = isVip;
        }

        public void setIsDapei(int isDapei) {
            this.isDapei = isDapei;
        }

        public void setDressId(String dressId) {
            this.dressId = dressId;
        }

        public void setDressBuyLink(String dressBuyLink) {
            this.dressBuyLink = dressBuyLink;
        }

        public void setDressPrice(int dressPrice) {
            this.dressPrice = dressPrice;
        }

        public void setDressDiscount(int dressDiscount) {
            this.dressDiscount = dressDiscount;
        }

        public void setDressExtInfo(String dressExtInfo) {
            this.dressExtInfo = dressExtInfo;
        }

        public void setDressTag(String dressTag) {
            this.dressTag = dressTag;
        }

        public void setDressNum(int dressNum) {
            this.dressNum = dressNum;
        }

        public void setObjTag(String objTag) {
            this.objTag = objTag;
        }

        public void setDressImgNum(int dressImgNum) {
            this.dressImgNum = dressImgNum;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public void setPictureId(String pictureId) {
            this.pictureId = pictureId;
        }

        public void setPictureSign(String pictureSign) {
            this.pictureSign = pictureSign;
        }

        public void setDataSrc(String dataSrc) {
            this.dataSrc = dataSrc;
        }

        public void setContentSign(String contentSign) {
            this.contentSign = contentSign;
        }

        public void setAlbumDi(String albumDi) {
            this.albumDi = albumDi;
        }

        public void setCanAlbumId(String canAlbumId) {
            this.canAlbumId = canAlbumId;
        }

        public void setAlbumObjNum(String albumObjNum) {
            this.albumObjNum = albumObjNum;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public void setPhotoId(String photoId) {
            this.photoId = photoId;
        }

        public void setFromName(int fromName) {
            this.fromName = fromName;
        }

        public void setFashion(String fashion) {
            this.fashion = fashion;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getId() {
            return id;
        }

        public String getDesc() {
            return desc;
        }

        public OwnerEntity getOwner() {
            return owner;
        }

        public String getFromPageTitle() {
            return fromPageTitle;
        }

        public String getColumn() {
            return column;
        }

        public String getParentTag() {
            return parentTag;
        }

        public String getDate() {
            return date;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public int getThumbnailWidth() {
            return thumbnailWidth;
        }

        public int getThumbnailHeight() {
            return thumbnailHeight;
        }

        public int getThumbLargeWidth() {
            return thumbLargeWidth;
        }

        public int getThumbLargeHeight() {
            return thumbLargeHeight;
        }

        public String getThumbLargeUrl() {
            return thumbLargeUrl;
        }

        public int getThumbLargeTnWidth() {
            return thumbLargeTnWidth;
        }

        public int getThumbLargeTnHeight() {
            return thumbLargeTnHeight;
        }

        public String getThumbLargeTnUrl() {
            return thumbLargeTnUrl;
        }

        public String getSiteName() {
            return siteName;
        }

        public String getSiteLogo() {
            return siteLogo;
        }

        public String getSiteUrl() {
            return siteUrl;
        }

        public String getFromUrl() {
            return fromUrl;
        }

        public String getIsBook() {
            return isBook;
        }

        public String getBookId() {
            return bookId;
        }

        public String getObjUrl() {
            return objUrl;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public String getSetId() {
            return setId;
        }

        public String getAlbumId() {
            return albumId;
        }

        public int getIsAlbum() {
            return isAlbum;
        }

        public String getAlbumName() {
            return albumName;
        }

        public int getAlbumNum() {
            return albumNum;
        }

        public String getUserId() {
            return userId;
        }

        public int getIsVip() {
            return isVip;
        }

        public int getIsDapei() {
            return isDapei;
        }

        public String getDressId() {
            return dressId;
        }

        public String getDressBuyLink() {
            return dressBuyLink;
        }

        public int getDressPrice() {
            return dressPrice;
        }

        public int getDressDiscount() {
            return dressDiscount;
        }

        public String getDressExtInfo() {
            return dressExtInfo;
        }

        public String getDressTag() {
            return dressTag;
        }

        public int getDressNum() {
            return dressNum;
        }

        public String getObjTag() {
            return objTag;
        }

        public int getDressImgNum() {
            return dressImgNum;
        }

        public String getHostName() {
            return hostName;
        }

        public String getPictureId() {
            return pictureId;
        }

        public String getPictureSign() {
            return pictureSign;
        }

        public String getDataSrc() {
            return dataSrc;
        }

        public String getContentSign() {
            return contentSign;
        }

        public String getAlbumDi() {
            return albumDi;
        }

        public String getCanAlbumId() {
            return canAlbumId;
        }

        public String getAlbumObjNum() {
            return albumObjNum;
        }

        public String getAppId() {
            return appId;
        }

        public String getPhotoId() {
            return photoId;
        }

        public int getFromName() {
            return fromName;
        }

        public String getFashion() {
            return fashion;
        }

        public String getTitle() {
            return title;
        }

        public List<String> getTags() {
            return tags;
        }

        public static class OwnerEntity {
            private String userName;
            private String userId;
            private String userSign;
            private String isSelf;
            private String portrait;
            private String isVip;
            private String isLanv;
            private String isJiaju;
            private String isHunjia;
            private String orgName;
            private String resUrl;
            private String cert;
            private String budgetNum;
            private String lanvName;
            private String contactName;

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public void setUserSign(String userSign) {
                this.userSign = userSign;
            }

            public void setIsSelf(String isSelf) {
                this.isSelf = isSelf;
            }

            public void setPortrait(String portrait) {
                this.portrait = portrait;
            }

            public void setIsVip(String isVip) {
                this.isVip = isVip;
            }

            public void setIsLanv(String isLanv) {
                this.isLanv = isLanv;
            }

            public void setIsJiaju(String isJiaju) {
                this.isJiaju = isJiaju;
            }

            public void setIsHunjia(String isHunjia) {
                this.isHunjia = isHunjia;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public void setResUrl(String resUrl) {
                this.resUrl = resUrl;
            }

            public void setCert(String cert) {
                this.cert = cert;
            }

            public void setBudgetNum(String budgetNum) {
                this.budgetNum = budgetNum;
            }

            public void setLanvName(String lanvName) {
                this.lanvName = lanvName;
            }

            public void setContactName(String contactName) {
                this.contactName = contactName;
            }

            public String getUserName() {
                return userName;
            }

            public String getUserId() {
                return userId;
            }

            public String getUserSign() {
                return userSign;
            }

            public String getIsSelf() {
                return isSelf;
            }

            public String getPortrait() {
                return portrait;
            }

            public String getIsVip() {
                return isVip;
            }

            public String getIsLanv() {
                return isLanv;
            }

            public String getIsJiaju() {
                return isJiaju;
            }

            public String getIsHunjia() {
                return isHunjia;
            }

            public String getOrgName() {
                return orgName;
            }

            public String getResUrl() {
                return resUrl;
            }

            public String getCert() {
                return cert;
            }

            public String getBudgetNum() {
                return budgetNum;
            }

            public String getLanvName() {
                return lanvName;
            }

            public String getContactName() {
                return contactName;
            }
        }
    }
}
