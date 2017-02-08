package hellolady.imsdk.Listener;

import org.java_websocket.handshake.ServerHandshake;

/**
 * 项目名称：HeartPro
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：5/7/2016 8:58 PM
 * 修改人：Jiamin.Sun
 * 修改时间：5/7/2016 8:58 PM
 * 修改备注：
 */
public interface IConnectListener {
    /**
     * 已经连接到服务器 ->onOpen
     * @param serverHandshakeData
     */
    void onSuccess(final ServerHandshake serverHandshakeData);


    /**
     * 断开服务器连接
     * @param code
     * @param reason
     * @param remote
     */
    void onClose(final int code, final String reason, final boolean remote);


    /**
     * 连接发生了异常
     * @param e
     */
    void onError(final Exception e);
}
