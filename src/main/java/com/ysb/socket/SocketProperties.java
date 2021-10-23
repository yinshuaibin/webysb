package com.ysb.socket;

/**
 * @author yinshuaibin
 * @date 2021/9/8 10:55
 * @description
 */
class SocketProperties {

    /**
     * 发送的数据包头
     */
    static byte MSG_START = (byte)0xEA;

    /**
     * 发送的字节数组长度阈开始的下标(根据协议得到)
     */
    static int lengthFieldOffset = 1;

    /**
     * 发送的字节数组长度阈的长度(根据协议得到)
     */
    static int lengthFieldLength = 2;

    /**
     * 标签数据上传
     */
    static byte CMD_REPORT_EPC_FRAME = (byte)0xA3;

    /**
     * 重启门禁读写器控制板
     */
    static byte cmd_ctrb_restart = (byte)0xF0;

    /**
     * 设置实时时间
     */
    static byte cmd_ctrb_set_rtctime = (byte)0xF1;

    /**
     * 读取实时时间
     */
    static byte cmd_ctrb_get_rtctime = (byte)0xF2;

    /**
     * 连接确认
     */
    static byte cmd_ctrb_linkconfirm = (byte)0xF3;

    /**
     * 设置MAC地址
     */
    static byte cmd_ctrb_set_mac = (byte)0xF4;

    /**
     * 查询MAC地址
     */
    static byte cmd_ctrb_get_mac = (byte)0xF5;

    /**
     * 设置IP地址
     */
    static byte cmd_ctrb_set_ip = (byte)0xF6;

    /**
     * 查询IP地址
     */
    static byte cmd_ctrb_get_ip = (byte)0xF7;

    /**
     * 恢复默认设置
     */
    static byte cmd_ctrb_set_defaultparam = (byte)0xF8;

    /**
     * 查询固件版本
     */
    static byte cmd_ctrb_get_ver = (byte)0xFE;

    /**
     * 设置EAS报警参数
     */
    static byte cmd_set_eas_mode = (byte)0x50;

    /**
     * 查询EAS报警参数
     */
    static byte cmd_get_eas_mask = (byte)0x51;

    /**
     * 设置读写器参数
     */
    static byte cmd_set_work_reader = (byte)0x52;

    /**
     * 查询读写器参数
     */
    static byte cmd_get_work_reader = (byte)0x53;

    /**
     * 设置红外触发延时
     */
    static byte cmd_set_ir_delay = (byte)0x55;

    /**
     * 读取红外触发延时
     */
    static byte cmd_get_ir_delay = (byte)0x56;

    /**
     * 启动手动盘存参数
     */
    static byte cmd_manual_inventory = (byte)0x57;

    /**
     * 设置手动盘存参数
     */
    static byte cmd_set_inventory_mode = (byte)0x58;

    /**
     * 查询手动盘寸参数
     */
    static byte cmd_get_inventory_mode = (byte)0x59;

    /**
     * 红外触发状态上传报文
     */
    static byte cmd_report_ir_trigger_state = (byte)0x5A;

    /**
     * 进出人数状态上传报文
     */
    static byte cmd_report_inout_result = (byte)0x5B;

    /**
     * 设置人数统计状态参数
     */
    static byte cmd_set_count = (byte)0x5C;

    /**
     * 查询人数统计状态参数
     */
    static byte cmd_get_count = (byte)0x5D;

    /**
     * 设置缓存模式
     */
    static byte cmd_set_bufmode = (byte)0x5E;

    /**
     * 读取缓存模式参数
     */
    static byte cmd_get_bufmode = (byte)0x5F;

    /**
     * 缓存标签数据上传报文
     */
    static byte cmd_report_bufdata = (byte)0x60;


    /**
     * 设置客户端模式参数
     */
    static byte cmd_set_clientparam = (byte)0x61;


    /**
     * 查询客户端模式参数
     */
    static byte cmd_get_clientparam = (byte)0x62;


    /**
     * 设置GP0状态
     */
    static byte cmd_set_GPO = (byte)0x63;


    /**
     * 查询GPI状态
     */
    static byte cmd_get_GPI = (byte)0x64;


    /**
     *  GPI 触发条件被触发时，读写器返回数据
     */
    static byte cmd_report_GPI_trigger_state = (byte)0x67;

    /**
     * 通道触发状态上传帧
     */
    static byte cmd_report_RFID_trigger_state = (byte)0x6A;


    /**
     * 设置红外传感方向参数
     */
    static byte  cmd_set_trigger_IN_OUT_dir = (byte)0x6C;


    /**
     * 查询红外传感方向参数
     */
    static byte  cmd_get_trigger_IN_OUT_dir = (byte)0x6D;


    /**
     * 设置EAS掩码匹配组
     */
    static byte cmd_set_eas_Mask_Match = (byte)0x6E;


    /**
     * 查询EAS掩码匹配组
     */
    static byte cmd_get_eas_Mask_Matc = (byte)0x6F;
}
