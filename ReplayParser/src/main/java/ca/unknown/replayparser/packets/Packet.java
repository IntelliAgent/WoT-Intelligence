package ca.unknown.replayparser.packets;

import com.google.gson.Gson;

import java.nio.ByteBuffer;

public abstract class Packet {

    protected int length;
    protected PacketType type;
    protected float clock;
    protected ByteBuffer payload;
    protected int playerID;


    public Packet(PacketType type, int length, float clock, ByteBuffer payload) {
        this.type = type;
        this.length = length;
        this.clock = clock;
        this.playerID = payload.getInt();
        this.payload = payload;
    }

    public abstract void toReadableFormat();

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
