package ca.intelliagent.replayparser.packets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.ByteBuffer;

public abstract class Packet {

    protected int length;
    protected PacketType type;
    protected float clock;
    protected ByteBuffer payload;
    protected int playerID;


    protected Packet(PacketType type, int length, float clock, ByteBuffer payload) {
        this.type = type;
        this.length = length;
        this.clock = clock;
        playerID = payload.getInt();
        this.payload = payload;

        parse(payload);
    }

    public Packet(RawPacket rawPacket){
        type = rawPacket.getType();
        length = rawPacket.getPayloadLength();
        clock = rawPacket.getClock();
        playerID = rawPacket.getPayload().getInt();
        payload = rawPacket.getPayload();

        parse(payload);
    }

    public abstract void toReadableFormat();

    protected void parse(ByteBuffer payload){

    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
