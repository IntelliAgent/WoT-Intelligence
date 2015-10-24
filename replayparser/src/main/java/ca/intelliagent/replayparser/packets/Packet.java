package ca.intelliagent.replayparser.packets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.ByteBuffer;

public abstract class Packet {

    protected final int length;
    protected final PacketType type;
    protected final float clock;
    protected final ByteBuffer payload;
    protected final int playerID;


    protected Packet(PacketType type, int length, float clock, ByteBuffer payload) {
        this.type = type;
        this.length = length;
        this.clock = clock;
        playerID = payload.getInt();
        this.payload = payload;

        parse(payload);
    }

    public Packet(RawPacket rawPacket) {
        type = rawPacket.getType();
        length = rawPacket.getPayloadLength();
        clock = rawPacket.getClock();
        playerID = rawPacket.getPayload().getInt();
        payload = rawPacket.getPayload();

        parse(payload);
    }

    public abstract void toReadableFormat();

    public float getPosX() {
        return 0.0F;
    }

    public float getPosY() {
        return 0.0F;
    }

    public float getPosZ() {
        return 0.0F;
    }

    public int getPlayerID() {
        return playerID;
    }

    public float getClock() {
        return clock;
    }

    protected void parse(ByteBuffer payload) {

    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
