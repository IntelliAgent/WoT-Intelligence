package ca.unknown.replaydecoder.packets;

public abstract class Packet {

    protected String type;
    protected int timestamp;

    public abstract void toReadableFormat();
}
