package ca.intelliagent.replayparser.packets;

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

    public Packet(RawPacket rawPacket){
        type = rawPacket.getType();
        length = rawPacket.getPayloadLength();
        clock = rawPacket.getClock();
        playerID = rawPacket.getPayload().getInt();
        payload = rawPacket.getPayload();

        parse(payload);
    }

    public int getPlayerID() {
        return playerID;
    }

    public abstract void toReadableFormat();

    protected void parse(ByteBuffer payload){

    }

    @Override
    public String toString() {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        return gson.toJson(this);
        return new String();
    }
}
