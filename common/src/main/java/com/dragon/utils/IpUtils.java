package com.dragon.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.net.*;
import java.util.Enumeration;
import java.util.Objects;

/**
 * 获取IP工具类
 */
public class IpUtils {

    public static final String IP_UNKNOWN = "unknown ip";

    /**
     * 获取本机IP
     * @return 本机IP
     */
    public static String getLocalIp() {
        String os = System.getProperty("os.name");
        return os.toLowerCase().startsWith("win") ? getLocalIpFromWindows() : getLocalIpFromLinux();
    }

    public static String getMacAddr() {
        String macAddress = "";
        StringBuilder str = new StringBuilder();

        try {
            NetworkInterface nic = NetworkInterface.getByName("eth0");
            byte[] buf = nic.getHardwareAddress();
            int length = buf.length;

            for (byte aBuf : buf) {
                str.append(byteHEX(aBuf));
            }

            macAddress = str.toString().toUpperCase();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return macAddress;
    }

    /**
     * 获取Linux服务器的本地IP
     * @return IP
     */
    private static String getLocalIpFromLinux() {
        String ip = IP_UNKNOWN;

        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();

            while (enumeration.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) enumeration.nextElement();
                if ("eth0".equals(ni.getName())) {
                    Enumeration<InetAddress> addresses = ni.getInetAddresses();
                    while (addresses.hasMoreElements()) {

                        InetAddress ia = (InetAddress) addresses.nextElement();
                        if (!(ia instanceof Inet6Address)) {
                            ip = ia.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        if (IP_UNKNOWN.equals(ip)) {
            ip = getLocalIpFromWindows();
        }

        return ip;
    }

    private static String byteHEX(byte ib) {
        char[] Digit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] ob = new char[]{Digit[ib >>> 4 & 15], Digit[ib & 15]};
        return new String(ob);
    }

    /**
     * 获取Windows服务器的本地IP
     * @return IP
     */
    private static String getLocalIpFromWindows() {
        String localIp;
        try {
            localIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            localIp = IP_UNKNOWN;
        }

        return localIp;
    }

    /**
     * 获取主机名
     * @return 主机名
     */
    public static String getHostName() {
        String hostName;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            hostName = "hostname";
        }
        return hostName;
    }

    /**
     * 获取客户端IP
     * @param request ServerHttpRequest
     * @return 客户端IP
     */
    public static String getClientIp(@NonNull ServerHttpRequest request) {
        String result = null;
        HttpHeaders headers = request.getHeaders();
        if (headers.getFirst("X-Forwarded-For") != null) {
            result = headers.getFirst("X-Forwarded-For");
        } else if (headers.getFirst("Proxy-Client-IP") != null) {
            result = headers.getFirst("Proxy-Client-IP");
        } else if (headers.getFirst("WL-Proxy-Client-IP") != null) {
            result = headers.getFirst("WL-Proxy-Client-IP");
        } else if (headers.getFirst("HTTP_CLIENT_IP") != null) {
            result = headers.getFirst("HTTP_CLIENT_IP");
        } else if (headers.getFirst("X-Real-IP") != null) {
            result = headers.getFirst("X-Real-IP");
        }
        if (StringUtils.isEmpty(result) || "unknown".equalsIgnoreCase(result)) {
            result = Objects.requireNonNull(request.getRemoteAddress()).getAddress().getHostAddress();
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(getLocalIpFromWindows());
        System.out.println(getLocalIpFromLinux());
    }
}
