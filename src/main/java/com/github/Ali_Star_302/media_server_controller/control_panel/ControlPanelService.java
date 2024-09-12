package com.github.Ali_Star_302.media_server_controller.control_panel;

import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Service
public class ControlPanelService {

    public final int PORT = 9;

    private long wakeTime = System.currentTimeMillis() - 11000;

    //Based on code from Paul Mutton: http://www.jibble.org/wake-on-lan/
    public void wakePc(String ipAddr, String macAddr) {
        System.out.println("Waking PC");
        wakeTime = System.currentTimeMillis();
        System.out.println("IP: " + ipAddr + ", MAC: " + macAddr);

        try {
            byte[] macBytes = getMacBytes(macAddr);
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }

            InetAddress address = InetAddress.getByName(ipAddr);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();

            System.out.println("WOL packet sent");
        }
        catch (Exception e) {
            System.out.println("Failed to send WOL packet:" + e);
        }
    }

    private static byte[] getMacBytes(String macAddr) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macAddr.split("(\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("Invalid MAC address");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hex digit in MAC address");
        }
        return bytes;
    }

    public boolean isWaking() {
        return (System.currentTimeMillis() - wakeTime) < 20000;
    }
}
