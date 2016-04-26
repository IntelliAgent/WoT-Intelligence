package ca.intelliagent.replayparser.packets;

import java.nio.ByteBuffer;

/**
 * @see <a href="http://wiki.vbaddict.net/pages/Packet_0x0a">Packet 0a</a>
 */
public class Packet0a extends Packet {

    private float hullYaw;
    private float hully;
    private float hullz;
    private float posx, posy, posz;

    public Packet0a(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public Packet0a(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public float getHullZ() {
        return hullz;
    }

    @Override
    public float getHullY() {
        return hully;
    }

    @Override
    protected void parse(ByteBuffer payload) {
        payload.position(12);

        posx = payload.getFloat();
        posz = payload.getFloat();
        posy = payload.getFloat();

        payload.position(36);

        hullYaw = payload.getFloat();
        hully = payload.getFloat();
        hullz = payload.getFloat();
    }

    @Override
    public void toReadableFormat() {

    }

    @Override
    public float getPosX() {
        return posx;
    }

    @Override
    public float getPosY() {
        return posy;
    }

    @Override
    public float getPosZ() {
        return posz;
    }

    @Override
    public float getHullYaw() {
        return hullYaw;
    }
}