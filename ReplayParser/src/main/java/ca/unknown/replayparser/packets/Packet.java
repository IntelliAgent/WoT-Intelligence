package ca.unknown.replayparser.packets;

public abstract class Packet {

    protected String type;
    protected int timestamp;

    public abstract void toReadableFormat();
}
