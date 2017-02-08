package hellolady.imsdk.Listener;

/**
 * 项目名称：HeartPro
 * 类描述： 接收服务器发过来的消息   消息监听
 * 创建人：Jiamin.Sun
 * 创建时间：5/7/2016 9:04 PM
 * 修改人：Jiamin.Sun
 * 修改时间：5/7/2016 9:04 PM
 * 修改备注：
 */
public interface IMessageListener {
    void onMessage(final String message);
}
