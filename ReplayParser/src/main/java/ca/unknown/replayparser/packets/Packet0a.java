package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet0a extends Packet {

    private float hullx, hully, hullz;

    private float posx, posy, posz;


    public Packet0a(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);

        payload.position(8);

        posx = payload.getFloat();
        posy = payload.getFloat();
        posz = payload.getFloat();

        payload.position(12);

        hullx = payload.getFloat();
        hully = payload.getFloat();
        hullz = payload.getFloat();
    }

    @Override
    public void toReadableFormat() {

    }
}