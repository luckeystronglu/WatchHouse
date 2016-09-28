package com.qf.entity;

/**
 * Created by Administrator on 2016/8/3.
 */
public class HouseNewsEntity {

    /**
     * id : HOS2016080302404102
     * type : 0
     * title : 发改委喊话：发购房补贴去库存 扩土地供给抑房价
     * summary : （原标题：发改委喊话：发购房补贴去库存扩土地供给抑房价）和讯房产消息关于下半年房地产市场，近期，政策层面持续释放信号。先是中央政治局会议定调抑制资产泡沫，后有证监会、央行就再融资市场及房企银行贷款设限。8月3日，国家发展和改革委员会在《更好发挥投资对经济增长的关键作用》一文中指出，进一步促进房地产投
     * thumbnail : http://inews.gtimg.com/newsapp_ls/0/459340527_640330/0
     * groupthumbnail : http://inews.gtimg.com/newsapp_ls/0/459340527_150120/0
     * commentcount : 0
     * imagecount : 1
     * commentid : 1488627725
     */

    private String id;
    private int type;
    private String title;
    private String summary;
    private String thumbnail;
    private String groupthumbnail;
    private int commentcount;
    private int imagecount;
    private String commentid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getGroupthumbnail() {
        return groupthumbnail;
    }

    public void setGroupthumbnail(String groupthumbnail) {
        this.groupthumbnail = groupthumbnail;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public int getImagecount() {
        return imagecount;
    }

    public void setImagecount(int imagecount) {
        this.imagecount = imagecount;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }
}
