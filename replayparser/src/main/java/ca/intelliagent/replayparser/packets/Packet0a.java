package ca.intelliagent.replayparser.packets;

import com.google.gson.annotations.Expose;

import java.nio.ByteBuffer;

public class Packet0a extends Packet {

    @Expose
    private float hullYaw, hully, hullz;

    @Expose
    private float posx, posy, posz;


    public Packet0a(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public Packet0a(RawPacket rawPacket) {
        super(rawPacket);
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
}